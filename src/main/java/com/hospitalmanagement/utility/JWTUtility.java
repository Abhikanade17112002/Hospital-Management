package com.hospitalmanagement.utility;

import com.hospitalmanagement.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtility {

    // 1. Externalized secret - set in application.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms:3600000}") // default: 1 hour
    private long expirationMs;

    // 2. Declared as SecretKey - no cast needed later
    private SecretKey key;

    // 3. Initialized after @Value injection
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Generate Token
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setSubject(user.getUserId())
                .claims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract UserId (subject)
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

    // Extract All Claims - throws JwtException if token is invalid/tampered
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)          // No cast needed now
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Check Expiry
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 4. Validate token only (used by filter - no userId needed,
    //    signature verification proves authenticity)
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token); // verifies signature + structure
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;             // malformed, tampered, or expired
        }
    }

    // 5. Validate token + match against a known userId
    //    (use when you want extra binding check)
    public boolean validateToken(String token, String userId) {
        try {
            String extractedId = extractUserId(token);
            return extractedId.equals(userId) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}