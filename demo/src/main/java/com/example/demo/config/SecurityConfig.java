package com.example.demo.config;

import com.example.demo.enums.Authority;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").hasAuthority(Authority.ADMIN_AUTHORITY)
                .antMatchers(HttpMethod.POST, "/student/**").permitAll()
                .antMatchers("/student/**", "/txn/**").hasAuthority(Authority.STUDENT_AUTHORITY)
                .antMatchers("/book/filter/**","/book/**").hasAnyAuthority(Authority.STUDENT_AUTHORITY,Authority.ADMIN_AUTHORITY)
                .and()
                .formLogin();
    }
}
