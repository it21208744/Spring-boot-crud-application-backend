package com.the_polar_lights.spring_boot_crud_app.test;

import com.the_polar_lights.spring_boot_crud_app.Entity.ruleEntity;
import com.the_polar_lights.spring_boot_crud_app.Entity.roleEntity;
import com.the_polar_lights.spring_boot_crud_app.Entity.userEntity;
import com.the_polar_lights.spring_boot_crud_app.repository.ruleRepository;
import com.the_polar_lights.spring_boot_crud_app.repository.roleRepository;
import com.the_polar_lights.spring_boot_crud_app.repository.userRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ruleRepoTest {

    @Autowired
    private ruleRepository ruleRepo;

    @Autowired
    private roleRepository roleRepo;

    @Autowired
    private userRepository userRepo; // Include userRepository

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateRuleWithRolesAndUsers() {
        // Create and save a new user
        userEntity user1 = new userEntity();
        user1.setEmail("user1@example.com");
        user1.setPassword("password1");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setRole("USER"); // Set a default role
        userRepo.save(user1); // Persist the user to the database

        // Create and save roles and associate them with the user
        roleEntity role1 = new roleEntity();
        role1.setName("Admin Role");
        role1.getUsers().add(user1); // Add user to this role
        roleRepo.save(role1); // Persist the role

        roleEntity role2 = new roleEntity();
        role2.setName("User Role");
        role2.getUsers().add(user1); // Add user to this role
        roleRepo.save(role2); // Persist the second role

        // Create a new rule and associate it with the roles
        ruleEntity rule = new ruleEntity();
        rule.setName("Test Rule");

        // Associate roles with the rule
        rule.getRoles().add(role1);
        rule.getRoles().add(role2);

        // Save the rule
        ruleEntity savedRule = ruleRepo.save(rule);

        // Fetch the rule from the database
        ruleEntity existRule = entityManager.find(ruleEntity.class, savedRule.getId());

        // Assertions
        assertThat(existRule).isNotNull(); // Ensure the rule is saved
        assertThat(existRule.getName()).isEqualTo(rule.getName());
        assertThat(existRule.getRoles()).hasSize(2); // Check number of roles
        assertThat(existRule.getRoles()).contains(role1, role2); // Ensure correct roles are associated

        // Check if the user is correctly associated with the roles
        assertThat(role1.getUsers()).contains(user1);
        assertThat(role2.getUsers()).contains(user1);
    }
}
