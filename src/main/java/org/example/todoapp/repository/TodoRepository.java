package org.example.todoapp.repository;

import org.example.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUsername(String username);
    void deleteByUsername(String username);
}
