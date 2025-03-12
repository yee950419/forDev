package com.dev.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 접근 경로 제어 로직
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login").permitAll()             // 해당 경로에 대한 접근은 모두 허용
                        .requestMatchers("/admin").hasRole("ADMIN")                // 특정 role이 있는 사용자는 접근 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")           // /my/로 지삭되는 경로에 대해서, 여러 개의 role 중 해닫되는 것이 있는 사용자는 접근 허용
                        .anyRequest().authenticated());                             // 나머지 접근은 인증된 사용자만 접근 허용

        http
                .formLogin((auth) -> auth.loginPage("/login").permitAll()   // 권한이 없는 page로 접근하였을 때, 자동으로 /login 경로로 redirect 시켜주는 설정
                        .loginProcessingUrl("/loginProc")
                        .permitAll());

        http
                .csrf((auth) -> auth.disable());    // 사이트 위변조방지설정 동작 시, post 요청은 csrf 토큰도 같이 보내줘야 로그인이 동작하므로, 임시적으로 비활성화

        return http.build();
    }
}
