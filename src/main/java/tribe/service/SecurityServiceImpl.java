package tribe.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tribe.service.interfaces.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	@Value("${jwt.expires_in}")
	protected Integer EXPIRES_IN;

	@Value("${jwt.secret}")
	protected String SECRET;

	@Override
	public String getUserEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	@Override
	public String getNewToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String rolesList = authentication.getAuthorities().stream().map(a -> a.getAuthority())
				.collect(Collectors.joining(","));

		Map<String, Object> tokenInfos = new HashMap<>();
		tokenInfos.put("roles", rolesList);

		return Jwts.builder().setId("softtekJWT").setSubject(authentication.getName()).addClaims(tokenInfos)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
				.setIssuedAt(new Date(System.currentTimeMillis())).signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
	}

}