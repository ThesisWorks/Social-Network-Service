package com.meong.meongtwork.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean // 스프링 시큐리티 기능 비활성화 -> 정적 리소스 접근 시 보안 인증 필요 x
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring()
                .requestMatchers("/static/**");
    }

    @Bean // 특정 HTTPS 요청에 대한 웹 기반 보안 설정
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers("/login", "/signup", "/user").permitAll() // 로그인, 회원가입 페이지는 인증 없이 접근 가능 (이후 "/user" 추가 가능성 있음)
                .anyRequest().authenticated() // 그 외의 페이지는 인증 필요
                .and()
                .formLogin() // 로그인 페이지는 form 태그로 구성
                .loginPage("/login") // 로그인 페이지는 "/login"
                .defaultSuccessUrl("/home") // 로그인 성공 시 이동할 페이지
                .and()
                .logout() // 로그아웃 관련 설정
                .logoutSuccessUrl("/login") // 로그아웃 성공 시 이동할 페이지
                .invalidateHttpSession(true) // 로그아웃 시 세션 초기화
                .and()
                .csrf().disable() // csrf 보안 설정 비활성화
                .build();
    }

    @Bean // 인증 관리자 관련 설정
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // 인증 관리자에게 사용자 정보 제공
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
