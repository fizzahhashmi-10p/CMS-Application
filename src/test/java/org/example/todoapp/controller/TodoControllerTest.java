package org.example.todoapp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.todoapp.MockData;
import org.example.todoapp.service.TodoService;
import org.example.todoapp.model.Todo;
import org.example.todoapp.model.User;

import java.util.List;
import java.time.LocalDate;


@Transactional @Rollback
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TodoService todoServiceMock;
    
    @InjectMocks
    private TodoController todoControllerMock;

    private User user = new MockData().getUsersList().get(0);
    private List<Todo> todos = new MockData().getTodosList();

    private MockHttpSession session;

    public TodoControllerTest(){
        this.session = new MockHttpSession();
        this.session.setAttribute("user", this.user);
    }

    @Test
    public void testListTodos() throws Exception {

        mockMvc.perform(get("/todos").session(this.session)) 
                .andExpect(status().isOk()) 
                .andExpect(view().name("TodoPage"))
                .andExpect(model().attributeExists("todos"));
    }

    @Test
    public void testOpenUpdateTodoPage() throws Exception {

        Todo todo = todos.get(0);
        int id = todo.getId();

        when(todoServiceMock.findItem(id)).thenReturn(todo);

        mockMvc.perform(get("/todos/update/"+id))
                .andExpect(status().isOk()) 
                .andExpect(view().name("UpdatePage"));
    }

    @Test
    public void testDeleteTodos() throws Exception {

        Todo todo = todos.get(0);
        int id = todo.getId();

        doNothing().when(todoServiceMock).deleteItem(id);

        mockMvc.perform(get("/todos/delete/"+id))
                .andExpect(status().is3xxRedirection()) 
                .andExpect(redirectedUrl("/todos"));
    }

    @Test
    public void testAddItem() throws Exception {
        Todo todo = new Todo("username2", "Task New 2", LocalDate.now().plusYears(1), false);

        when(todoServiceMock.saveItem(todo)).thenReturn(todo);

        mockMvc.perform(post("/todos").session(this.session)
                .param("username", todo.getUsername())
                .param("description", todo.getDescription())
                .param("targetDate", todo.getTargetDate().toString()) // Convert to string
                .param("done", Boolean.toString(todo.getDone()))) //convert to string
                .andExpect(status().is3xxRedirection()) 
                .andExpect(redirectedUrl("/todos"));
    }
}
