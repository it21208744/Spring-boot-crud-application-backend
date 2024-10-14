package com.the_polar_lights.spring_boot_crud_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {
    String email;
    String password;
    String firstName;
    String lastName;
}
