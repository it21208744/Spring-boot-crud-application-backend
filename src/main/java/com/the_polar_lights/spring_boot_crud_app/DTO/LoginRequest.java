package com.the_polar_lights.spring_boot_crud_app.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String email;
    private String password;
}
