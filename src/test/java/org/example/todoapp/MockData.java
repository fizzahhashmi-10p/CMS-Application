package org.example.todoapp;

import org.example.todoapp.Role;
import org.example.todoapp.model.User;
import org.example.todoapp.model.Todo;


import java.util.List;
import java.time.LocalDate;


public class MockData{
    public List<User> getUsersList() {
        return List.of(
            new User("username1", "Test User 1", "password 1", Role.USER),
            new User("username2", "Test User 2", "password 2", Role.USER),
            new User("adminuser", "Test User Admin", "admin password", Role.ADMIN)
        );
    };

    public List<Todo> getTodosList() {
        return List.of(
            new Todo("username1", "Task 1", LocalDate.now().plusYears(1), false),
            new Todo("username2", "Task 2", LocalDate.now().plusYears(2), false),
            new Todo("username1", "Task 3", LocalDate.now().minusYears(3), true)
        );
    };

}