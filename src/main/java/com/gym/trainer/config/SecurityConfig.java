package com.gym.trainer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad con OAuth2/Keycloak.
 *
 * Roles definidos en Keycloak:
 * - ROLE_ADMIN: Acceso completo a todos los endpoints
 * - ROLE_TRAINER: Puede ver y gestionar su información
 * - ROLE_MEMBER: Puede consultar entrenadores
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/api-docs/**",
                    "/v3/api-docs/**",
                    "/h2-console/**",
                    "/actuator/**"
                ).permitAll()

                .requestMatchers(HttpMethod.GET, "/api/entrenadores/**").hasAnyRole("ADMIN", "TRAINER", "MEMBER")
                .requestMatchers(HttpMethod.POST, "/api/entrenadores/**").hasAnyRole("ADMIN", "TRAINER")
                .requestMatchers(HttpMethod.PUT, "/api/entrenadores/**").hasAnyRole("ADMIN", "TRAINER")
                .requestMatchers(HttpMethod.DELETE, "/api/entrenadores/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}
