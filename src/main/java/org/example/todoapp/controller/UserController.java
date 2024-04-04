package org.example.todoapp.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;

import org.example.todoapp.Role;
import org.example.todoapp.model.User;
import org.example.todoapp.service.UserService;
import org.example.todoapp.service.TodoService;
import org.example.todoapp.service.AuthenticationService;
import org.example.todoapp.exception.InvalidCredentialsException;

@Controller
public class UserController{

    @Autowired
    private UserService userService;
    @Autowired
    private TodoService todoService;

    @Autowired
    private AuthenticationService authService;
    

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String login(){
        return "LoginPage";
    }

    @RequestMapping(value="signin", method=RequestMethod.GET)
    public String showUserSignin( ){
        return "SignInPage";
    }

    @RequestMapping(value="users/signin", method=RequestMethod.POST)
    public String addUser(@RequestParam String username,
                          @RequestParam String name,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          @RequestParam Role role
        ){
            userService.saveUser(new User(username,name,password,role));
        return "redirect:/login";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public String loginUser(@RequestParam String username,
                          @RequestParam String password,
                          ModelMap model, HttpSession session
        ){
        try{
            if(authService.authenticate(username,password)){
                session.setAttribute("user", userService.findUser(username));
                return "redirect:/todos";
            }
            throw new InvalidCredentialsException("Invalid credentials provided");
        }
        catch (InvalidCredentialsException ex) {
            model.addAttribute("error", "Invalid credentials. Please try again.");
             return "LoginPage"; // Return the login page with error message
        }
    }

    @RequestMapping(value="users", method=RequestMethod.GET)
    public String usersList(ModelMap model){
        model.put("userlist",userService.getAllusers());
        return "UserPage";
    }


    @RequestMapping("/users/delete/{username}")
    public String deleteUsers(@PathVariable String username){
        todoService.deleteUserTodos(username);
        userService.deleteUser(userService.findUser(username).getId());
        return "redirect:/users";
    }

    @RequestMapping(value="logout", method=RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }

}
