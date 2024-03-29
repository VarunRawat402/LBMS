package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.requests.AdminRequest;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public void create(@RequestBody AdminRequest adminRequest){
        adminService.create(adminRequest);
    }

    @DeleteMapping("/admin")
    public void delete(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        int adminId = user.getAdmin().getId();
        adminService.delete(adminId);
    }


}
