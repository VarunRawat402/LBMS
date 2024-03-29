package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//This class purpose is to not have Circular Dependency
//If we have initialized passwordEncoder in the Security Config class then
//Security Config is dependent on the UserService class for authentication
//User Service class also is dependent on the Security Config class for password Encoding
//Resulting in Circular Dependency
@Configuration
public class CommonConfig {

    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }
}
