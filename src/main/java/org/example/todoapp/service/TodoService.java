package org.example.todoapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.example.todoapp.repository.TodoRepository;
import org.example.todoapp.model.Todo;

import java.util.List;


@Service
@Transactional
public class TodoService{

    @Autowired
    private TodoRepository todoRepository;

    public Todo saveItem(Todo todoItem) {
        return todoRepository.save(todoItem);
    }

    public List<Todo> getAllItems(){
        return todoRepository.findAll();
    }

    public List<Todo> getUserItems(String username){
        return todoRepository.findByUsername(username);
    }

    public Todo findItem(int id){
        return todoRepository.findById(id).orElse(null);
    }

    public void deleteItem(int id){
        todoRepository.deleteById(id);
    }

    public void deleteUserTodos(String username){
        todoRepository.deleteByUsername(username);
    }
    
}
