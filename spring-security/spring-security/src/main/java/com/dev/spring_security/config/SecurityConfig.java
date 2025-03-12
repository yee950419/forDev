package com.dev.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login").permitAll()             // 해당 경로에 대한 접근은 모두 허용
                        .requestMatchers("/admin").hasRole("ADMIN")                // 특정 role이 있는 사용자는 접근 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")           // /my/로 지삭되는 경로에 대해서, 여러 개의 role 중 해닫되는 것이 있는 사용자는 접근 허용
                        .anyRequest().authenticated());                             // 나머지 접근은 인증된 사용자만 접근 허용

        return http.build();
    }
}
