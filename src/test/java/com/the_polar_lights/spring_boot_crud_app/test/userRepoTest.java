package com.the_polar_lights.spring_boot_crud_app.test;

import com.the_polar_lights.spring_boot_crud_app.Entity.userEntity;
import com.the_polar_lights.spring_boot_crud_app.repository.userRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class userRepoTest {

    @Autowired
    private userRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        userEntity user = new userEntity();
        user.setEmail("agent@gmail.com");
        user.setPassword("password123");
        user.setFirstName("agent");
        user.setLastName("47");
        user.setRole("Admin");

        userEntity savedUser = repo.save(user);

        userEntity existUser = entityManager.find(userEntity.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }


}
