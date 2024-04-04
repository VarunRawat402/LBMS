package com.example.demo.service;

import com.example.demo.Repository.StudentRepository;
import com.example.demo.models.Student;
import com.example.demo.models.User;
import com.example.demo.requests.StudentRequest;
import com.example.demo.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository sr;

    @Autowired
    UserService userService;

    public void create(StudentRequest studentRequest) {

        UserRequest userRequest = studentRequest.toUser();
        User user = userService.create(userRequest);
        Student student = studentRequest.to();
        student.setUser(user);
        sr.save(student);
    }

    public Student findStudentById(int id){
        return sr.findById(id).orElse(null);
    }

    public void delete(int studentId) {
        sr.deleteById(studentId);
    }
}
