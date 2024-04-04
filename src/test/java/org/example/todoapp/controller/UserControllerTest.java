package org.example.todoapp.controller;

import org.junit.jupiter.api.Test;

import org.springframework.test.context.ActiveProfiles;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
 

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.todoapp.model.User;
import org.example.todoapp.service.UserService;
import org.example.todoapp.service.AuthenticationService;
import org.example.todoapp.controller.UserController;
import org.example.todoapp.Role;
import org.example.todoapp.MockData;




// @Profile("test")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private MockHttpSession session;

    @Mock
    private UserService userServiceMock;

    @Mock
    private AuthenticationService authServiceMock;

    @InjectMocks
    private UserController userControllerMock;

    public UserControllerTest(){
        this.session = new MockHttpSession();
    }


    @Test
    public void testUsersLis() throws Exception{
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("UserPage")) 
                .andExpect(model().attributeExists("userlist")); 
    }

    @Test
    public void testLogin() throws Exception{
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("LoginPage"));
    }

    @Test
    public void testShowUserSignin() throws Exception {
        mockMvc.perform(get("/signin"))
                .andExpect(status().isOk()) 
                .andExpect(view().name("SignInPage"));
    }


    @Transactional
    @Rollback
    @Test
    public void testAddUser() throws Exception {
        User user = new User("newuser", "New User", "New pass", Role.USER);

        when(userServiceMock.saveUser(user)).thenReturn(user);

        mockMvc.perform(post("/users/signin")
                .param("username", user.getUsername())
                .param("name", user.getName())
                .param("password", user.getPassword())
                .param("confirmPassword", user.getPassword()) 
                .param("role", user.getRole().toString()))
                .andExpect(status().is3xxRedirection()) 
                .andExpect(redirectedUrl("/login"));

    }


    @Test
    public void testLoginUser() throws Exception {
        User user = new MockData().getUsersList().get(0);
        String username = user.getUsername();
        String password = user.getPassword();

        when(authServiceMock.authenticate(username,password)).thenReturn(true);
        when(userServiceMock.findUser(username)).thenReturn(user);

        mockMvc.perform(post("/login").session(session)
                .param("username", username)
                .param("password",password))
                .andExpect(status().isOk());

        // verify(authServiceMock, times(1)).authenticate(username,password);

    }
}