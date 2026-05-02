package com.hospitalmanagement.utility;

import com.hospitalmanagement.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JWTUtility {
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate Token
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        return Jwts.builder()
                .setSubject(user.getUserId())
                .claims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Username
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract Expiry Date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract Single Claim
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    // Extract All Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Check Expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate Token
    public boolean validateToken(String token, String userId) {
        String extractedUserId = extractUserId(token);
        return extractedUserId.equals(userId) && !isTokenExpired(token);
    }
}


