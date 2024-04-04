package org.example.todoapp.service;

import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.*;

import org.example.todoapp.repository.TodoRepository;
import org.example.todoapp.model.Todo;
import org.example.todoapp.MockData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.time.LocalDate;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TodoServiceTest{

    @Mock
    private TodoRepository todoRepositoryMock;

    @InjectMocks
    private TodoService todoServiceMock;

    private List<Todo> todoData;

    public TodoServiceTest(){
        this.todoData = new MockData().getTodosList();
    }

    @Test
    void testGetAllItems(){
        when(todoRepositoryMock.findAll()).thenReturn(this.todoData);
        List<Todo> result = todoServiceMock.getAllItems();

        assertNotNull(result);
        assertIterableEquals(result, this.todoData);
    }

    @Test
    void testGetUserItems(){
        String username = "username1";
        List<Todo> userItems= List.of(this.todoData.get(0),this.todoData.get(2));

        when(todoRepositoryMock.findByUsername(username)).thenReturn(userItems);
        List<Todo> result = todoServiceMock.getUserItems(username);

        assertNotNull(result);
        assertEquals(result.size(), 2);

        verify(todoRepositoryMock, times(1)).findByUsername(username);
    }

    @Test
    public void testSaveItem() {
        Todo todo = new Todo("username2", "Task New 2", LocalDate.now().plusYears(1), false);

        when(todoRepositoryMock.save(todo)).thenReturn(todo);
        Todo savedTodo = todoServiceMock.saveItem(todo);
        
        assertNotNull(savedTodo);
        verify(todoRepositoryMock, times(1)).save(todo);
    }

    @Test
    public void testUpdateTodo() {
        // Updates if existing id is provided
        Todo existingTodo = this.todoData.get(1);
        Todo todo = new Todo(
                            existingTodo.getId(),
                            existingTodo.getUsername(),
                            "Task Updated 2", // Update Description
                            existingTodo.getTargetDate(),
                            existingTodo.getDone()
        );

        when(todoRepositoryMock.save(todo)).thenReturn(todo);

        Todo updateTodo = todoServiceMock.saveItem(todo);

        verify(todoRepositoryMock, times(1)).save(todo);
        assertEquals(updateTodo.getDescription(), todo.getDescription());
    }

    @Test
    public void testDeleteItem() {
        doNothing().when(todoRepositoryMock).deleteById(1);
        todoServiceMock.deleteItem(1);

        verify(todoRepositoryMock, times(1)).deleteById(1);
    }
}