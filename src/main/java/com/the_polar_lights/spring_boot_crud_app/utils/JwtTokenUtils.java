package com.the_polar_lights.spring_boot_crud_app.utils;

import com.the_polar_lights.spring_boot_crud_app.DTO.DecryptToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class JwtTokenUtils {
    private final Key secretKey;
    private DecryptToken decryptToken;
    public JwtTokenUtils() {
        // Use a secure key with HMAC-SHA algorithm
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
    public String generateAccessToken(String email, List<String> role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("roles", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000  *60 * 60 * 24 ))
                .signWith(secretKey)
                .compact();
    }



    public String generateRefreshToken(String email, List role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("roles", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60)) //System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7
                .signWith(secretKey)
                .compact();
//        return "Hello from refresh token";
    }

    public Boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public DecryptToken getEmailRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();
        List<String> roles = (List<String>) claims.get("roles");

        DecryptToken decryptToken = new DecryptToken(email, roles);

        return decryptToken;
    }
}
