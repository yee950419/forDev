package com.dev.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    * 스프링 시큐리티는 사용자 인증 시 비밀번호에 대해 단방향 해시 암호화를 진행하여 저장되어 있는 비밀번호와 대조한다.
    * */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 접근 경로 제어 로직
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "loginProc", "/join", "/joinProc").permitAll()             // 해당 경로에 대한 접근은 모두 허용
                        .requestMatchers("/admin").hasRole("ADMIN")                // 특정 role이 있는 사용자는 접근 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")           // /my/로 지삭되는 경로에 대해서, 여러 개의 role 중 해닫되는 것이 있는 사용자는 접근 허용
                        .anyRequest().authenticated());                             // 나머지 접근은 인증된 사용자만 접근 허용

        http
                .formLogin((auth) -> auth.loginPage("/login").permitAll()   // 권한이 없는 page로 접근하였을 때, 자동으로 /login 경로로 redirect 시켜주는 설정
                        .loginProcessingUrl("/loginProc")
                        .permitAll());

        http
                .csrf((auth) -> auth.disable());    // 사이트 위변조방지설정 동작 시, post 요청은 csrf 토큰도 같이 보내줘야 로그인이 동작하므로, 임시적으로 비활성화

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)                                             // 최대 허용하는 동시접속 로그인 개수 설정
                        .maxSessionsPreventsLogin(true));                               // 최대 허용 로그인 개수 초과시 처리 설정 (true : 새로운 로그인을 차단, false : 기존 세션 하나를 삭제)

        // 세션 고정 공격에 대한 보호 설정
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());                          // 로그인 시 동일한 세션에 대한 id 변경

        return http.build();
    }
}
