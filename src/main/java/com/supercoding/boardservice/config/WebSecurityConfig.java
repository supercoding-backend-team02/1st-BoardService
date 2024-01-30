package com.supercoding.boardservice.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests() //시큐리티 처리에 HttpServletRequest를 이용한다는 것을 의미
                .antMatchers("/", "/user/**",
                        "/image/**", "/subscribe/**", "/comment/**", "/api/**") //특정한 경로를 지정
                .permitAll() //권한 허락
                .anyRequest() //모든 리소스를 의미
        ;
    }
}
