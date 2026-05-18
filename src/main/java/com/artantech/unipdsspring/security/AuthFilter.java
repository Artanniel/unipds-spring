package com.artantech.unipdsspring.security;

import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException,
            IOException {

        System.out.println("AuthFilter#doFilterInternal - request.getRequestURI(): " + request.getRequestURI());
        System.out.println("AuthFilter#doFilterInternal - request.getRequestURL(): " + request.getRequestURL());

        if (Objects.nonNull(request.getHeader("Authorization"))) {
            Authentication authentication = TokenUtil.decode(request);
            System.out.println("Authentication: " + authentication);
            if (Objects.nonNull(authentication)) {
                System.out.println("Authentication sucess!");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
