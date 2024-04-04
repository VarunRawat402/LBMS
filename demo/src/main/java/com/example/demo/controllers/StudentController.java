package com.example.demo.controllers;

import com.example.demo.models.Book;
import com.example.demo.models.Student;
import com.example.demo.models.Txn;
import com.example.demo.models.User;
import com.example.demo.requests.StudentRequest;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class StudentController {

    @Autowired
    StudentService ss;

    @Autowired
    UserService userService;

    //To create a Student in the DB
    //Need to pass the StudentRequest in the Postman
    @PostMapping("/student")
    public String createStudent(@RequestBody @Valid StudentRequest studentRequest){
        ss.create(studentRequest);
        return "Your account has been created successfully.";
    }

    //To check the details of own ( no need to pass anything )
    @GetMapping("/student")
    public Student getStudent() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user.getStudent() == null){
            throw new Exception("User requesting the details is not a student");
        }
        int studentId = user.getStudent().getId();
        return ss.findStudentById(studentId);
    }

    //To check the details of any student in the DB ( need to pass studentId )
    @GetMapping("/admin/student")
    public Student getStudentForAdmin(@RequestParam("studentId") int studentId) throws Exception {
        // check the person accessing this API is an admin or not
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user.getAdmin() == null){
            throw new Exception("User requesting the details is not an admin");
        }

        return ss.findStudentById(studentId);
    }

    //To check the books issued by the student ( No need to pass anything )
    @GetMapping("/student/books")
    public List<Book> getBooks() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user.getStudent() == null){
            throw new Exception("User requesting the details is not a student");
        }
        int studentId = user.getStudent().getId();
        Student student = ss.findStudentById(studentId);
        return student.getBookList();
    }

    //To check the txn done by the student ( No need to pass anything )
    @GetMapping("/student/txn")
    public List<Txn> getTxn() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user.getStudent() == null){
            throw new Exception("User requesting the details is not a student");
        }
        int studentId = user.getStudent().getId();
        Student student = ss.findStudentById(studentId);
        return student.getTxnList();
    }

    @DeleteMapping("/student/self")
    public String deleteSelf() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user.getStudent() == null){
            throw new Exception("You are not a student of the library.");
        }
        int studentId = user.getStudent().getId();
        ss.delete(studentId);
        userService.delete(user.getId());
        return "Student has been deleted Successfully";
    }

    @DeleteMapping("/admin/student/{studentId}")
    public String deleteStudent(@PathVariable int studentId) throws Exception {
        Student student = ss.findStudentById(studentId);
        int userId = student.getUser().getId();
        ss.delete(studentId);
        userService.delete(userId);
        return "Student has been deleted Successfully";
    }

}
