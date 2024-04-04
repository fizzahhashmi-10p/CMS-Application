package org.example.todoapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.example.todoapp.model.User;
import org.example.todoapp.Role;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest{

    @Test
    void testAuthenticate(){
        String username = "testname";
        String password = "pass";
        String wrong_password = "no pass";
        User testUser = new User(username, "Test User", password, Role.ADMIN);

        UserService userServiceMock = mock(UserService.class);
        when(userServiceMock.findUser(username)).thenReturn(testUser);

        AuthenticationService authService = new AuthenticationService(userServiceMock);
        assertTrue(authService.authenticate(username, password));
        assertFalse(authService.authenticate(username, wrong_password));
    }
}