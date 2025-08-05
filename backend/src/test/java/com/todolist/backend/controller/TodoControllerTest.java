package com.todolist.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.backend.dto.TodoListResponse;
import com.todolist.backend.dto.TodoRequest;
import com.todolist.backend.dto.TodoResponse;
import com.todolist.backend.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private TodoService todoService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private TodoResponse testTodoResponse;
    private TodoRequest testTodoRequest;
    private TodoListResponse testTodoListResponse;
    
    @BeforeEach
    void setUp() {
        testTodoResponse = TodoResponse.builder()
                .id(1L)
                .title("Test Todo")
                .description("Test Description")
                .completed(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        testTodoRequest = new TodoRequest();
        testTodoRequest.setTitle("Test Todo");
        testTodoRequest.setDescription("Test Description");
        testTodoRequest.setCompleted(false);
        
        testTodoListResponse = TodoListResponse.builder()
                .items(Arrays.asList(testTodoResponse))
                .total(1L)
                .limit(50)
                .offset(0)
                .build();
    }
    
    @Test
    void getAllTodos_ShouldReturnTodoList() throws Exception {
        // Given
        when(todoService.getAllTodos(any(), any(), any())).thenReturn(testTodoListResponse);
        
        // When & Then
        mockMvc.perform(get("/api/v1/todos/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.total").value(1))
                .andExpect(jsonPath("$.limit").value(50))
                .andExpect(jsonPath("$.offset").value(0));
    }
    
    @Test
    void getAllTodos_WithParameters_ShouldReturnFilteredList() throws Exception {
        // Given
        when(todoService.getAllTodos(eq("true"), eq(10), eq(5))).thenReturn(testTodoListResponse);
        
        // When & Then
        mockMvc.perform(get("/api/v1/todos/")
                        .param("completed", "true")
                        .param("limit", "10")
                        .param("offset", "5"))
                .andExpect(status().isOk());
    }
    
    @Test
    void getTodoById_ShouldReturnTodo() throws Exception {
        // Given
        when(todoService.getTodoById(1L)).thenReturn(testTodoResponse);
        
        // When & Then
        mockMvc.perform(get("/api/v1/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Todo"));
    }
    
    @Test
    void createTodo_ShouldReturnCreatedTodo() throws Exception {
        // Given
        when(todoService.createTodo(any(TodoRequest.class))).thenReturn(testTodoResponse);
        
        // When & Then
        mockMvc.perform(post("/api/v1/todos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTodoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Todo"));
    }
    
    @Test
    void createTodo_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        // Given
        TodoRequest invalidRequest = new TodoRequest();
        invalidRequest.setDescription("Only description, no title");
        
        // When & Then
        mockMvc.perform(post("/api/v1/todos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void updateTodo_ShouldReturnUpdatedTodo() throws Exception {
        // Given
        when(todoService.updateTodo(eq(1L), any(TodoRequest.class))).thenReturn(testTodoResponse);
        
        // When & Then
        mockMvc.perform(put("/api/v1/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTodoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    
    @Test
    void deleteTodo_ShouldReturnNoContent() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/v1/todos/1"))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void deleteCompletedTodos_ShouldReturnNoContent() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/v1/todos/completed"))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void deleteAllTodos_ShouldReturnNoContent() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/v1/todos/all"))
                .andExpect(status().isNoContent());
    }
} 