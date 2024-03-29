package com.example.demo.requests;

import com.example.demo.enums.AccountStatus;
import com.example.demo.models.Student;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StudentRequest {

    @NotBlank
    private String name;
    private int age;
    private String contact;
    private String email;
    private AccountStatus accountStatus;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Student to(){
        return Student.builder().name(name).age(age).contact(contact).email(email).accountStatus(AccountStatus.ACTIVE).build();
    }

    public UserRequest toUser(){
        return UserRequest.builder()
                .student(to())
                .username(username)
                .password(password)
                .build();
    }
}
