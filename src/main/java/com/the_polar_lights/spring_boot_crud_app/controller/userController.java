package com.the_polar_lights.spring_boot_crud_app.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class userController {

    @PostMapping("register")
    public String registerUser(){
        return "register a user";
    }

    @PostMapping("login")
    public String loginUser(){
        return "login user";
    }

    @GetMapping
    public String getSingleUser(){
        return "get single user";
    }


}
