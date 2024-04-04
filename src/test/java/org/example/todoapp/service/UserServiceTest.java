package org.example.todoapp.service;

import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.*;

import org.example.todoapp.repository.UserRepository;
import org.example.todoapp.model.User;
import org.example.todoapp.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; 


import org.example.todoapp.MockData;
import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest{

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userServiceMock;

    private List<User> userData;

    public UserServiceTest(){
        this.userData = new MockData().getUsersList();
    }

    @Test
    void testGetAllusers(){
        when(userRepositoryMock.findAll()).thenReturn(this.userData);

        List<User> result = userServiceMock.getAllusers();

        assertNotNull(result);
        assertIterableEquals(result, this.userData);
    }

    @Test
    void testFindUser(){
        String username = "username1";
        when(userRepositoryMock.findByUsername(username)).thenReturn(this.userData.get(0));
        User result = userServiceMock.findUser(username);

        assertNotNull(result);
        assertEquals(result.getUsername(), username);

        verify(userRepositoryMock, times(1)).findByUsername(username);
    }

    @Test
    public void testSaveUser() {
        User user = new User("newuser", "New User", "New pass", Role.USER);

        when(userRepositoryMock.save(user)).thenReturn(user);
        User savedUser = userServiceMock.saveUser(user);
        
        assertNotNull(savedUser);
        verify(userRepositoryMock, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepositoryMock).deleteById(1);
        userServiceMock.deleteUser(1);

        verify(userRepositoryMock, times(1)).deleteById(1);
    }
}