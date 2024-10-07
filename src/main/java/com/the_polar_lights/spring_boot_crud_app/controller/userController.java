package com.the_polar_lights.spring_boot_crud_app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class userController {

    @PostMapping("register")
    public String registerUser(){
        return "register a user";
    }

    //might go to AuthController
    @PostMapping("login")
    public String loginUser(){
        return "login user";
    }

    @GetMapping("/{id}")
    public String getSingleUser(){
        return "get single user";
    }
    @GetMapping
    public String getAllUsers(){
        return "get all user";
    }
    @PutMapping("/{id}")
    public String updateUser(){
        return "update a user";
    }

    //patch method update

    @DeleteMapping("/{id}")
    public String deleteUser(){
        return "delete a user";
    }


}
