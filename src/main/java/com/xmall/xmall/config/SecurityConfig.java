package com.xmall.xmall.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/login", "/sign-up", "/check-email-token").permitAll()
                .mvcMatchers("/items/create-item").hasRole("ADMIN")
                .mvcMatchers("/mypage/**", "/order-payment/**").authenticated()
                .anyRequest().permitAll();

        // FIXME: csrf token 체크 기능 비활성화, 데이터베이스 공격에 취약해질 수 있으므로 반드시 다시 활성화해야한다.
        // -- form tag 에 csrf token 값이 제대로 생성되지 않고 있는 것 같으니 체크해보자.
//        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//         login
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