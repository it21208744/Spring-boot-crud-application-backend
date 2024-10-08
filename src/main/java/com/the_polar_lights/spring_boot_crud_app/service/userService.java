package com.the_polar_lights.spring_boot_crud_app.service;

import com.the_polar_lights.spring_boot_crud_app.DTO.LoginResponse;
import com.the_polar_lights.spring_boot_crud_app.Entity.userEntity;
import com.the_polar_lights.spring_boot_crud_app.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired userService(userRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public userEntity createUser(userEntity user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }



    //get all users
    //get user by email



    public LoginResponse loginUser(String email, String rawPassword) {
        userEntity user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return new LoginResponse(200 ,user.getEmail(), user.getToken(), user.getRoles());
        }
        else if (user != null && !passwordEncoder.matches(rawPassword, user.getPassword())) {
            LoginResponse response = new LoginResponse();
            response.setStatus(401);
            return response;

        }
        LoginResponse response = new LoginResponse();
        response.setStatus(404);
        return response;
    }


}
