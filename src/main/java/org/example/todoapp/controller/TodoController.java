package org.example.todoapp.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;

import org.example.todoapp.Role;
import org.example.todoapp.model.Todo;
import org.example.todoapp.model.User;
import org.example.todoapp.service.TodoService;
import org.example.todoapp.service.UserService;


@Controller
public class TodoController{

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    // Read
    @RequestMapping(value="/todos", method=RequestMethod.GET)
    public String listTodos( ModelMap model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user.getRole() == Role.ADMIN){
            model.put("todos", todoService.getAllItems());
            model.put("usersList", userService.getAllusers());
        }
        else{
            model.put("todos", todoService.getUserItems(user.getUsername()));
        }
        return "TodoPage";
    }

    // Create
    @RequestMapping(value="/todos", method=RequestMethod.POST)
    public String addTodo(  @RequestParam String username,
                            @RequestParam String description,
                            @RequestParam LocalDate targetDate,
                            @RequestParam(defaultValue="false") boolean done,
                            ModelMap model){
        
        todoService.saveItem(new Todo( username, description, targetDate, done));

        return "redirect:/todos";
    }

    @RequestMapping("/todos/delete/{id}")
    public String deleteTodos(@PathVariable int id){
        todoService.deleteItem(id);
        return "redirect:/todos";
    }

    @RequestMapping("/todos/update/{id}")
    public String openUpdateTodoPage(@PathVariable int id, ModelMap model){
        model.addAttribute("todo",todoService.findItem(id));
        return "UpdatePage";
    }

    @RequestMapping(value="/todos/update/{id}", method=RequestMethod.POST)
    public String updateTodo( @RequestParam int id,
                            @RequestParam String username,
                            @RequestParam String description,
                            @RequestParam LocalDate targetDate,
                            @RequestParam(defaultValue="false") boolean done,
                            ModelMap model){
        todoService.saveItem(new Todo(id, username, description, targetDate, done));
        return "redirect:/todos";
    }

}
