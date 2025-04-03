package com.project.shop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
    // 세션 방식 로그인 기능
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()); // csrf 끄기
//        http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository())
//                .ignoringRequestMatchers("/login") // 보안을 끌 페이지 설정 안하는게 좋음
//        );
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll() // permitAll 모든 유저의 접속을 허락
        );

        http.formLogin((formLogin) ->
                        formLogin.loginPage("/login")
                .defaultSuccessUrl("/my-page")
                //.failureUrl("/login?error=true")
        );
        http.logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/items/list")
        );

        return http.build();
    }
}

