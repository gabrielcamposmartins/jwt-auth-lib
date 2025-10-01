package gabs.jwt.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JWT Utility class for token generation and validation
 * This class provides methods to generate, validate, and extract information from JWT tokens
 */
@Component
public class JwtUtil {

    @Value("${jwt.security.secret}")
    private String secret;

    @Value("${jwt.security.expiration}")
    private Long expiration;

    /**
     * Get the signing key for JWT operations
     * @return SecretKey for signing/validating tokens
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generate a JWT token for the given username
     * @param username the username to include in the token
     * @return generated JWT token
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extract username from JWT token
     * @param token the JWT token
     * @return username extracted from token
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Check if the token is valid (not malformed or tampered)
     * @param token the JWT token to validate
     * @return true if valid, false otherwise
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Check if the token is expired
     * @param token the JWT token to check
     * @return true if expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return true;
        }
    }

    /**
     * Extract username from token (alias for getUsernameFromToken)
     * @param token the JWT token
     * @return username extracted from token
     */
    public String extractUsername(String token) {
        return getUsernameFromToken(token);
    }

    /**
     * Validate token (checks both validity and expiration)
     * @param token the JWT token to validate
     * @return true if token is valid and not expired
     */
    public boolean validateToken(String token) {
        return isTokenValid(token) && !isTokenExpired(token);
    }

    /**
     * Get the expiration time in milliseconds
     * @return expiration time in milliseconds
     */
    public Long getExpirationTime() {
        return expiration;
    }
}
