package com.artantech.unipdsspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests((authz) -> authz
                                                // ── Página de login (recurso estático público) ──
                                                .requestMatchers(HttpMethod.GET, "/login", "/login/",
                                                                "/login/index.html")
                                                .permitAll()
                                                // ── Endpoints públicos de autenticação ──
                                                .requestMatchers(HttpMethod.POST, "/user").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/user/oauth/google").permitAll()
                                                // ── Autorização fiscal ──
                                                .requestMatchers(HttpMethod.POST, "/api/v1/autorizacao/solicitar").permitAll()
                                                // ── API interna (chamada via WebClient entre serviços) ──
                                                .requestMatchers(HttpMethod.GET, "/lazy-api/**").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/lazy-api/**").permitAll()
                                                // ── Recursos estáticos (JS, CSS, fontes, imagens) ──
                                                .requestMatchers(HttpMethod.GET, "/open").permitAll()
                                                .requestMatchers(HttpMethod.GET,
                                                                "/css/**", "/js/**", "/images/**", "/fonts/**",
                                                                "/*.ico", "/*.png", "/*.svg")
                                                .permitAll()
                                                // ── Swagger / OpenAPI ──
                                                .requestMatchers(HttpMethod.GET, "/v3/api-docs/**", "/swagger-ui/**",
                                                                "/swagger-ui.html")
                                                .permitAll()
                                                // ── Tudo mais exige autenticação ──
                                                .anyRequest().authenticated())
                                .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);
                return http.build();
        }
}
