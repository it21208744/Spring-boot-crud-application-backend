package com.the_polar_lights.spring_boot_crud_app.service;

import com.the_polar_lights.spring_boot_crud_app.DTO.DecryptToken;
import com.the_polar_lights.spring_boot_crud_app.DTO.JwtResponse;
import com.the_polar_lights.spring_boot_crud_app.DTO.LoginResponse;
import com.the_polar_lights.spring_boot_crud_app.DTO.UserDto;
import com.the_polar_lights.spring_boot_crud_app.Entity.refreshTokenEntity;
import com.the_polar_lights.spring_boot_crud_app.Entity.roleEntity;
import com.the_polar_lights.spring_boot_crud_app.Entity.userEntity;
import com.the_polar_lights.spring_boot_crud_app.repository.TokenRepository;
import com.the_polar_lights.spring_boot_crud_app.repository.userRepository;
import com.the_polar_lights.spring_boot_crud_app.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class userService {

    private final userRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;
    @Autowired userService(userRepository userRepository, TokenRepository tokenRepository ,PasswordEncoder passwordEncoder, JwtTokenUtils jwtTokenUtils){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    public ResponseEntity<String> createUser(userEntity user){
        userEntity Existuser = userRepository.findByEmail(user.getEmail());
        if (Existuser != null){
            return new ResponseEntity("User with same email already exists", HttpStatus.CONFLICT);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return new ResponseEntity("User registered", HttpStatus.CREATED);
    }



    //get all users
    public ResponseEntity<?> viewAllUsers(String token) {

        DecryptToken decryptedToken = jwtTokenUtils.getEmailRoleFromToken(token);
        System.out.println(decryptedToken.getRoles());
        List<userEntity> allUsers = userRepository.findAll();
        List<UserDto> userList = new ArrayList<UserDto>();
        for (userEntity user : allUsers) {
            UserDto newUser = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
            userList.add(newUser);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    //get user by email



    public ResponseEntity<?> loginUser(String email, String rawPassword) {
        userEntity user = userRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            refreshTokenEntity existToken = tokenRepository.findByUser(user);
            Set<roleEntity> roles = user.getRoles();

            List roleList = roles.stream()
                    .map(role ->  role.getName())
                    .collect(Collectors.toList());
            if (existToken == null){

                String accessToken = jwtTokenUtils.generateAccessToken(email, roleList);
                String refreshToken = jwtTokenUtils.generateRefreshToken(email, roleList);
                refreshTokenEntity saveToken = new refreshTokenEntity();
                saveToken.setToken(refreshToken);
                saveToken.setUser(user);
                tokenRepository.save(saveToken);
                return ResponseEntity.ok()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Refresh-Token", existToken.getToken())
                        .build();

            }
            else if(!jwtTokenUtils.validateToken(existToken.getToken())){
                String accessToken = jwtTokenUtils.generateAccessToken(email, roleList);
                String refreshToken = jwtTokenUtils.generateRefreshToken(email, roleList);
                existToken.setToken(refreshToken);
                tokenRepository.save(existToken);


                return ResponseEntity.ok()
                        .header("Authorization", "Bearer " + accessToken)
                        .header("Refresh-Token", existToken.getToken())
                        .build();
            }
            String accessToken = jwtTokenUtils.generateAccessToken(email, roleList);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Refresh-Token", existToken.getToken())
                    .build();

        } else if (user != null && !passwordEncoder.matches(rawPassword, user.getPassword())) {
            return new ResponseEntity("Incorrect password", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }


}
