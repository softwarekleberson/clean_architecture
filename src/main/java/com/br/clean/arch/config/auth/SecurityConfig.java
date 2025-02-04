package com.br.clean.arch.config.auth;

import com.br.clean.arch.infra.security.filter.JwtAuthenticationFilter;
import com.br.clean.arch.infra.security.token.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html"
                ).permitAll()
                
                .requestMatchers("/auth/login/customer", "/auth/register/customer").permitAll()
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())  
            .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class); 

        return http.build();
    }
}
