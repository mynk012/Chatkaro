package com.mynkjain.chatkaro.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        //Note ://STATELESS, meaning that no session should be created or used. This is often used in stateless
        // authentication mechanisms like JWT (JSON Web Token) where each request is authenticated independently
        // CSRF protection is a security feature that prevents unauthorized commands from being executed on behalf of an authenticated user.
        // In some cases, such as when using stateless authentication, CSRF protection might not be necessary.
        http.sessionManagement(                             //This line configures the session management for HTTP security.
                        management -> management.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize
                        .requestMatchers("/api/**").authenticated()  //requests matching the pattern "/api/**" should be authenticated,
                        .anyRequest().permitAll()) //  It specifies that any other request (not matching "/api/**") should be permitted to all users
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());  //disables CSRF (Cross-Site Request Forgery) protection

        return http.build(); // This line builds and returns the configured HttpSecurity object, which represents the security configuration for HTTP requests.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
