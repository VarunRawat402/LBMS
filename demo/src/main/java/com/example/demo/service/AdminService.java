package com.example.demo.service;

import com.example.demo.Repository.AdminRepository;
import com.example.demo.models.Admin;
import com.example.demo.models.User;
import com.example.demo.requests.AdminRequest;
import com.example.demo.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserService userService;

    public void create(AdminRequest adminRequest){

        UserRequest userRequest = adminRequest.toUser();
        User user = userService.create(userRequest);
        Admin admin = adminRequest.to();
        admin.setUser(user);
        adminRepository.save(admin);
    }

    public void delete(int id){
        adminRepository.deleteById(id);
    }
}
