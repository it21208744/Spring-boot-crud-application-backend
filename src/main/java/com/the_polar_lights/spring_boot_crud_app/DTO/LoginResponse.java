package com.the_polar_lights.spring_boot_crud_app.DTO;

import com.the_polar_lights.spring_boot_crud_app.Entity.refreshTokenEntity;
import com.the_polar_lights.spring_boot_crud_app.Entity.roleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Integer status;
    private String email;
    private refreshTokenEntity token;
    private Set<roleEntity> role;

}
