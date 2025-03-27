package com.project.shop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()); // 세션방식 csrf 공격을 허용(개발 중에는 번거롭기때문)
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll() // permitAll 모든 유저의 접속을 허락
        );
        return http.build();
    }
}

