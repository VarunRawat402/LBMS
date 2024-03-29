package com.example.demo.requests;

import com.example.demo.models.Admin;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AdminRequest {


    private String name;
    private int age;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Admin to(){
        return Admin.builder()
                .name(name)
                .age(age)
                .build();
    }

    public UserRequest toUser(){
        return UserRequest.builder()
                .username(username)
                .password(password)
                .admin(to())
                .build();
    }


}
