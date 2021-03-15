package com.xmall.xmall.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/login", "/sign-up", "/check-email-token").permitAll()
                .mvcMatchers("/items/**").permitAll()
                .mvcMatchers("/mypage/**").permitAll()
                .mvcMatchers("/favicon.ico/**").permitAll()
                // NOTE: 로그인 기능 전엔 모든 접속 허용.
//                .mvcMatchers("/**").permitAll()
                .anyRequest().authenticated();

        // login
        http.formLogin()
                .loginPage("/login").permitAll();

        // logout
        http.logout()
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}