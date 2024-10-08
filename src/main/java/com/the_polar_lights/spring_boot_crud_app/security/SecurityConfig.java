package com.the_polar_lights.spring_boot_crud_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/users/register", "/users/login").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(customizer -> customizer.disable());

        return http.build();
    }
}
