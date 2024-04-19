package com.mynkjain.chatkaro.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtValidator extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        System.out.println("Inside JwtValidator...");
        System.out.println("JWT header: " + jwt);
        System.out.println("Expected JWT header: " + JwtConstant.JWT_HEADER);


        if(jwt != null){
            System.out.println("Inside try block. JWT token: " + jwt);
            try{
                String email = JwtProvider.getEmailFromJwtToken(jwt);
                System.out.println("Extracted email from JWT token: " + email);
                List<GrantedAuthority> authorities = new ArrayList<>();

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch(Exception e){
                System.out.println("Inside catch block. Error: " + e);
                throw new BadCredentialsException("invalid token....");

            }
        }


        filterChain.doFilter(request, response);
    }
}
