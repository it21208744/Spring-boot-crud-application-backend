package com.the_polar_lights.spring_boot_crud_app.repository;

import com.the_polar_lights.spring_boot_crud_app.Entity.refreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TokenRepository extends JpaRepository<refreshTokenEntity, Long> {
}
