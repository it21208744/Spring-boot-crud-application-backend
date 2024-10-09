package com.the_polar_lights.spring_boot_crud_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
}
