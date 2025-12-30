package com.mysite.sbb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/admin/**").hasRole("ADMIN")  // 관리자만
                .requestMatchers("/user/signup", "/user/login", "/css/**", "/js/**", "/files/**", "/uploads/**").permitAll()  // 비로그인 허용
                .anyRequest().permitAll()  // ★ 나머지 모두 비로그인 허용 (리스트 보기 등)
            )
            .formLogin((form) -> form
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .successHandler((request, response, authentication) -> {
                    if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                        response.sendRedirect("/admin/dashboard");
                    } else {
                        response.sendRedirect("/music/list");
                    }
                })
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/music/list")
                .permitAll()
            );
        return http.build();
    }
}