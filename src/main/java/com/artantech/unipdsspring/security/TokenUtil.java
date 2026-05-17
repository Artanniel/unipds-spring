package com.artantech.unipdsspring.security;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;
import java.util.Collections;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.artantech.unipdsspring.model.User;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {

    private static final String EMISSOR = "ArtannielFortes";
    private static final String SECRET_KEY = "secret-key-super-secreto-nao-colocar-em-producao";
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hora

    public static String encode(User user) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            return Jwts.builder()
                    .subject(user.getUsername())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .issuer(EMISSOR)
                    .signWith(key)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Token generation failed");
        }
    }

    public static Authentication decode(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        String token = authHeader.substring(7);
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            String username = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();

            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }

    /**
     * Base64 encodes the given plain text string.
     *
     * @param plainText String to be encoded.
     * @return Base64 encoded string.
     */
    public static String encodeToBase64(@NonNull String plainText) {
        byte[] bytesToEncode = plainText.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytesToEncode);
    }

    /**
     * Base64 decodes the given encoded string.
     *
     * @param encodedText String to be decoded.
     * @return Decoded (plain) string.
     */
    public static String decodeFromBase64(@NonNull String encodedText) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
