package com.the_polar_lights.spring_boot_crud_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long id;
    String firstName;
    String lastName;
    String email;
}
