package com.project.ticketeria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.project.ticketeria.filter.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private static final String[] SWAGGER_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };

    private static final String[] USER_LIST = {
            "/auth/create",
            "/auth/login"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        // permite que faça a autenticação do email e senha no processo de auth no login
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(SWAGGER_LIST).permitAll()
                            .requestMatchers(USER_LIST).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

}
