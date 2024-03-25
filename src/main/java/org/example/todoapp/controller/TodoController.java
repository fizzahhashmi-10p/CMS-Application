package org.example.todoapp.controller;

import org.springframework.ui.ModelMap;
import org.example.todoapp.TodoServiceRepo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

import org.example.todoapp.model.Todo;


@Controller
public class TodoController{


    private TodoServiceRepo todoRepo;

    public TodoController(TodoServiceRepo repo){
        this.todoRepo = repo;
    }

    // loginPage => org.example.todoapp.controller.LoginController => loginPage.jsp
    public List<Todo> getTodos(){
        todoRepo.save(new Todo(1, "user1", "Java Course",LocalDate.now().plusYears(1), false));
        return todoRepo.findAll();
    }

    @RequestMapping(value="/todos", method=RequestMethod.GET)
    public String listTodos(ModelMap model){
        model.put("todos", getTodos());
        return "TodoPage";
    }

    @RequestMapping(value="/todos", method=RequestMethod.POST)
    public String welcome(  @RequestParam int id,
                            @RequestParam String username,
                            @RequestParam String description,
                            @RequestParam LocalDate targetDate,
                            @RequestParam(defaultValue = "false") boolean done,
                            ModelMap model){

        todoRepo.save(new Todo(id, username, description, targetDate, done));

        model.put("todos", getTodos());
        return "TodoPage";
    }


}
