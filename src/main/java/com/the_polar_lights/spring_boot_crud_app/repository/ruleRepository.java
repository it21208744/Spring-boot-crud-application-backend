package com.the_polar_lights.spring_boot_crud_app.repository;

import com.the_polar_lights.spring_boot_crud_app.Entity.roleEntity;
import com.the_polar_lights.spring_boot_crud_app.Entity.ruleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ruleRepository extends JpaRepository<ruleEntity, Long> {
}
