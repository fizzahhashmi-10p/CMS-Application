package org.example.todoapp.model;

import org.springframework.test.context.ActiveProfiles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;

import org.example.todoapp.model.User;
import org.example.todoapp.Role;

// @Profile("test")
@SpringBootTest
public class UserTest {

    @Test
    public void testUserConstructor() {
        // Create a Todo object using the constructor
        User user = new User("test_username", "Test User", "password", Role.USER);
        assertTrue(user instanceof User, "Object returned should be an instance of User");
    }

    @Test
    public void testUserSetterGetter() {
        // Create a User object
        User user = new User();

        // Username
        user.setUsername("usernametest");
        assertEquals("usernametest", user.getUsername());


        // Role
        user.setRole(Role.ADMIN);
        assertEquals(Role.ADMIN, user.getRole());        
    }

}
