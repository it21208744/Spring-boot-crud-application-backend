package com.the_polar_lights.spring_boot_crud_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DecryptToken {
    private String email;
    private List<String> roles;
}
