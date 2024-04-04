package org.example.todoapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@SpringBootTest
public class TodoTest {

    @Test
    public void testTodoConstructor() {
        Todo todo = new Todo("username1", "Test Task", LocalDate.now(), true);
        assertTrue(todo instanceof Todo, "Object returned should be an instance of Todo");
    }

    @Test
    public void testTodoSetterGetter() {
        Todo todo = new Todo();

        todo.setDescription("Test Todo");
        assertEquals("Test Todo", todo.getDescription());

        LocalDate date = LocalDate.now();
        todo.setTargetDate(date);
        assertEquals(date, todo.getTargetDate());

        todo.setDone(true);
        assertTrue(todo.getDone());
    }

}
