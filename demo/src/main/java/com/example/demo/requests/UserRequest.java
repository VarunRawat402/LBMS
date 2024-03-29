package com.example.demo.requests;


import com.example.demo.models.Admin;
import com.example.demo.models.Student;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRequest {


    private String username;

    private String password;

    private String authority;
    private Student student;
    private Admin admin;



}
