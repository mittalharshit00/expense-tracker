package com.example.expensetracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception{
        
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(
                    new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                )
            )
            .oauth2ResourceServer( oauth2 -> oauth2
                .jwt( jwt -> jwt
                    .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
            .authorizeHttpRequests( auth -> auth
                .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/users/{userId}").hasRole("ADMIN")
                .anyRequest().authenticated()
                );
        

            return http.build();
    }

    @Bean 
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        return converter;
    }
}
