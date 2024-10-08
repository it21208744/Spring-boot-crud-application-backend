package com.the_polar_lights.spring_boot_crud_app.controller;

import com.the_polar_lights.spring_boot_crud_app.Entity.userEntity;
import com.the_polar_lights.spring_boot_crud_app.service.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class userController {

    private final userService userService;

    public userController(userService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<userEntity> registerUser(@RequestBody userEntity user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
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
