package com.artantech.unipdsspring.security;

import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.Collections;
import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

public class TokenUtil {
    public static Authentication decode(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        System.out.println("Request authHeader: " + authHeader);
        if (Objects.nonNull(authHeader)) {
            String[] tokenList = authHeader.split(" ");

            System.out.println("Type: " + tokenList[0] + ", Token: " + tokenList[1]);
            if (tokenList[0].equalsIgnoreCase("basic")) {

            } else if (tokenList[0].equalsIgnoreCase("bearer")) {
                if (tokenList[1].equals("security123")) {
                    System.out.println("Valid token!");
                    return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
                } else {
                    System.out.println("Invalid token!");
                    return null;
                }

            }
        }

        return null;
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
