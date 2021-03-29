package tribe.security;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security configuration.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    private JWTAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    private JWTAuthorizationFilter jwtAuthorizationFilter;
    
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",
            "/swagger-ui/**",
            "/h2-console/**"
    };

    public WebSecurityConfig(JWTAuthenticationSuccessHandler jwtAuthenticationSuccessHandler, JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthenticationSuccessHandler = jwtAuthenticationSuccessHandler;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }



    // Hashing pass algorithm
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Identity configuration.
     *
     * @param ds
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(DataSource ds) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(ds);
        manager.setUsersByUsernameQuery("select email, pass, 'true' from member where email=?");
        manager.setAuthoritiesByUsernameQuery("select m.email, rm.role from member m, role_member rm where m.id=rm.member_id and m.email=?");
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // Cross-Domain request support for Spring Security
                // this configuration allows you to use the CORS rules of Spring MVC
                .cors().and()
                 // JSESSIONID cookie deletion
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                 // in case of error, a 403 code is sent
                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN))
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                // login form generation
                // you must produce a request with the following characteristics:
                //      POST /login
                //      'Content-Type': 'application/x-www-form-urlencoded'
                //      Two parameters : username and password
                .formLogin()
                // in case of successful validation of the form
                // jwtAuthenticationSuccessHandler customizes the response to send
                //     => JWT Token generated and set in HTTP header
                //     => and in the response
                .successHandler(jwtAuthenticationSuccessHandler)
                // in case of failure, code 403 sent
                .failureHandler((request, response, exception) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN))
                // POST / login request is not subject to authentication
                .permitAll()
                .and()
                // Filter used to pass from the JWT token to a user connected in the Spring Security sense
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                // Logout management
                // /POST /logout
                .logout()
                // if successful an OK response is sent (instead of a redirection to / login)
                .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpServletResponse.SC_OK));
    }
}
