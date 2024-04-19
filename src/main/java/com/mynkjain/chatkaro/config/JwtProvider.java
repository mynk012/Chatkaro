package com.mynkjain.chatkaro.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;

import java.sql.SQLOutput;
import java.util.Date;


public class JwtProvider {

    private static SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());


    public static String generateToken(Authentication auth) {

        String jwt=Jwts.builder()
                .setIssuer("CodeWithMayank")
                .setSubject(auth.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();

        return jwt;

    }
    public static String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email=String.valueOf(claims.get("email"));
        return email;
    }

}
