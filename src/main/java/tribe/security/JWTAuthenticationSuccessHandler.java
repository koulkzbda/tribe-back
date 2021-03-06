package tribe.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tribe.controller.dto.ErrorCode;
import tribe.controller.dto.ErrorMessageDto;
import tribe.controller.dto.MemberDto;
import tribe.repository.MemberRepo;

/**
 * Handle HTTP Response if Authentication is successful
 */
@Configuration
public class JWTAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationSuccessHandler.class);

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Value("${jwt.secret}")
	private String SECRET;

	@Autowired
	private MemberRepo memberRepo;

	@Autowired
	private ObjectMapper mapper;

	@Override
	@Transactional
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		LOG.info("JWT Token generating");
		
		User user = (User) authentication.getPrincipal();
		
		tribe.domain.socialNetwork.Member tribeUser = memberRepo.findByEmail(user.getUsername())
				.orElseThrow(() -> new IllegalArgumentException("No matching user for this email"));
		
		if (!tribeUser.getIsConfirmed()) {
			System.out.println(tribeUser.getEmail());
			ErrorMessageDto error = new ErrorMessageDto(ErrorCode.NOT_CONFIRMED, tribeUser.getEmail(), tribeUser.getFirstName() + " " + tribeUser.getLastName());
			response.setContentType("application/json");
			response.getWriter().write(mapper.writeValueAsString(error));
			response.setStatus(403);
			
			return;
		}

		String jws = generateToken(user);

		response.setContentType("application/json");
		response.getWriter().write(mapper.writeValueAsString(new MemberDto(tribeUser, jws)));

	}
	
	private String generateToken(User user) {
		String rolesList = user.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));

		
		Map<String, Object> tokenInfos = new HashMap<>();
		tokenInfos.put("roles", rolesList);
		LOG.info("roles {} ", tokenInfos);

		return Jwts.builder().setId("softtekJWT").setSubject(user.getUsername()).addClaims(tokenInfos)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
	}

}
