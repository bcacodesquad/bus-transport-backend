package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Allow static resources
                .requestMatchers("/", "/index.html", "/static/**", "/manifest.json", "/favicon.ico").permitAll()
                // Allow API endpoints
                .requestMatchers("/api/**").permitAll()
                // Allow H2 console
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()
            );
        
        // Disable X-Frame-Options for H2 console
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        
        return http.build();
    }
}
