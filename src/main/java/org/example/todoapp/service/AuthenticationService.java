package org.example.todoapp.service;

import org.example.todoapp.service.UserService;
import org.example.todoapp.model.User;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthenticationService{
    
    private UserService userService;

    public AuthenticationService(UserService userService){
        this.userService = userService;
    }

    public boolean authenticate(String username, String password){
        User user = userService.findUser(username);
        if (user != null) {
            return password.equals(user.getPassword());
        }
        return false;
    }
}
