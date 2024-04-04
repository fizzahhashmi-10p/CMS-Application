package org.example.todoapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.example.todoapp.model.Todo;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;


@SpringBootTest
@Transactional
@Rollback
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testFindAll() {
        List<Todo> todos = todoRepository.findAll();   
    }

    @Test
    public void testSaveTodo() {
        Todo todo = new Todo("usernameTest", "Test task 1", LocalDate.now(), true);

        Todo savedTodo = todoRepository.save(todo);

        assertNotNull(savedTodo.getId());
        assertEquals(savedTodo.getDescription(), "Test task 1");
    }

    @Test
    public void testFindById() {
        Todo todo = new Todo("usernameTest", "Test task 1", LocalDate.now(), true);
        Todo savedTodo = todoRepository.save(todo);

        Optional<Todo> foundTodoOptional = todoRepository.findById(savedTodo.getId());
        Todo foundTodo = foundTodoOptional.orElse(null);

        assertNotNull(foundTodo);
        assertEquals(foundTodo.getId(), savedTodo.getId());
    }

    @Test
    public void testFindByUsername() {
        Todo todo = new Todo("usernameTest", "Test Task 1", LocalDate.now(), true);
        Todo savedTodo = todoRepository.save(todo);

        List<Todo> foundTodos= todoRepository.findByUsername(savedTodo.getUsername());

        assertNotNull(foundTodos);
    }

}
