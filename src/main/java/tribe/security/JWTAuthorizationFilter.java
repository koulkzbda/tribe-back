package tribe.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * Filter used to pass from the JWT token to a user connected in the Spring Security sense.
 */
@Configuration
public class JWTAuthorizationFilter  extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        // Search for the token by Cookie
        if(req.getCookies() != null) {
            Stream.of(req.getCookies()).filter(cookie -> cookie.getName().equals(TOKEN_COOKIE))
                    .map(Cookie::getValue)
                    .forEach(token -> {

                        try {
                            Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

                            String username = body.getSubject();

                            List<SimpleGrantedAuthority> roles = Arrays.stream(body.get("roles", String.class).split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

                            Authentication authentication =  new UsernamePasswordAuthenticationToken(username, null, roles);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } catch (ExpiredJwtException e) {
                            System.out.println(" Token expired ");
                        } catch (SignatureException e) {
                        	LOGGER.error("Signature error ", e);
                        } catch (JwtException e) {
                            // In the event of an error reading the token, the request is not authenticated and will not have access to secure resources
                            LOGGER.error("Error reading JWT token", e);
                        }
                    });
        }

        chain.doFilter(req, res);

    }

}
