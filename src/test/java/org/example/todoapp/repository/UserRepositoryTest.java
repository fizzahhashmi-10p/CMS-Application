package org.example.todoapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import org.example.todoapp.model.User;
import org.example.todoapp.Role;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();   
    }

    @Test
    public void testSaveUser() {
        User user = new User("usernameTest", "User Name", "password", Role.USER);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
    }

    @Test
    public void testFindUserByUsername() {
        User user = new User("usernameTest", "User Name", "password", Role.USER);
        User savedUser = userRepository.save(user);

        User foundUser= userRepository.findByUsername(savedUser.getUsername());

        assertNotNull(foundUser);
    }

}
