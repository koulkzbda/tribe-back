package tribe.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
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

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

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

		String rolesList = user.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));

		tribe.domain.Member tribeUser = memberRepo.findByEmail(user.getUsername())
				.orElseThrow(() -> new IllegalArgumentException("No matching user for this email"));

		response.setContentType("application/json");
		response.getWriter().write(mapper.writeValueAsString(new MemberDto(tribeUser)));

		Map<String, Object> tokenInfos = new HashMap<>();
		tokenInfos.put("roles", rolesList);
		LOG.info("roles {} ", tokenInfos);

		String jws = Jwts.builder().setSubject(user.getUsername()).addClaims(tokenInfos)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET).compact();

		Cookie authCookie = new Cookie(TOKEN_COOKIE, (jws));
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(EXPIRES_IN * 1000);
		authCookie.setPath("/");
		response.addCookie(authCookie);
		LOG.info("JWT Token generated and set in HTTP header and in a cookie");
		clearAuthenticationAttributes(request);
		addSameSiteCookieAttribute(response);
	}

	private void addSameSiteCookieAttribute(HttpServletResponse response) {
		Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
		boolean firstHeader = true;
		// there can be multiple Set-Cookie attributes
		for (String header : headers) {
			if (firstHeader) {
				response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Lax"));
				firstHeader = false;
				continue;
			}
			response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; %s", header, "SameSite=Lax"));
		}
	}
}
