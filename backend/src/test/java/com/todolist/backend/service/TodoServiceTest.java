package com.todolist.backend.service;

import com.todolist.backend.dto.TodoListResponse;
import com.todolist.backend.dto.TodoRequest;
import com.todolist.backend.dto.TodoResponse;
import com.todolist.backend.model.Todo;
import com.todolist.backend.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    
    @Mock
    private TodoRepository todoRepository;
    
    @InjectMocks
    private TodoService todoService;
    
    private Todo testTodo;
    private TodoRequest testTodoRequest;
    
    @BeforeEach
    void setUp() {
        testTodo = Todo.builder()
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
    }
    
    @Test
    void getAllTodos_ShouldReturnAllTodos() {
        // Given
        List<Todo> todos = Arrays.asList(testTodo);
        when(todoRepository.findAll()).thenReturn(todos);
        
        // When
        TodoListResponse response = todoService.getAllTodos(null, 50, 0);
        
        // Then
        assertNotNull(response);
        assertEquals(1, response.getItems().size());
        assertEquals(1L, response.getTotal());
        verify(todoRepository).findAll();
    }
    
    @Test
    void getAllTodos_WithCompletedFilter_ShouldReturnFilteredTodos() {
        // Given
        List<Todo> todos = Arrays.asList(testTodo);
        when(todoRepository.findByCompleted(false)).thenReturn(todos);
        
        // When
        TodoListResponse response = todoService.getAllTodos("false", 50, 0);
        
        // Then
        assertNotNull(response);
        assertEquals(1, response.getItems().size());
        verify(todoRepository).findByCompleted(false);
    }
    
    @Test
    void getTodoById_ShouldReturnTodo() {
        // Given
        when(todoRepository.findById(1L)).thenReturn(Optional.of(testTodo));
        
        // When
        TodoResponse response = todoService.getTodoById(1L);
        
        // Then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Todo", response.getTitle());
        verify(todoRepository).findById(1L);
    }
    
    @Test
    void getTodoById_WhenTodoNotFound_ShouldThrowException() {
        // Given
        when(todoRepository.findById(999L)).thenReturn(Optional.empty());
        
        // When & Then
        assertThrows(RuntimeException.class, () -> todoService.getTodoById(999L));
        verify(todoRepository).findById(999L);
    }
    
    @Test
    void createTodo_ShouldReturnCreatedTodo() {
        // Given
        when(todoRepository.save(any(Todo.class))).thenReturn(testTodo);
        
        // When
        TodoResponse response = todoService.createTodo(testTodoRequest);
        
        // Then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Test Todo", response.getTitle());
        verify(todoRepository).save(any(Todo.class));
    }
    
    @Test
    void updateTodo_ShouldReturnUpdatedTodo() {
        // Given
        when(todoRepository.findById(1L)).thenReturn(Optional.of(testTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(testTodo);
        
        TodoRequest updateRequest = new TodoRequest();
        updateRequest.setTitle("Updated Todo");
        updateRequest.setCompleted(true);
        
        // When
        TodoResponse response = todoService.updateTodo(1L, updateRequest);
        
        // Then
        assertNotNull(response);
        verify(todoRepository).findById(1L);
        verify(todoRepository).save(any(Todo.class));
    }
    
    @Test
    void updateTodo_WhenTodoNotFound_ShouldThrowException() {
        // Given
        when(todoRepository.findById(999L)).thenReturn(Optional.empty());
        
        // When & Then
        assertThrows(RuntimeException.class, () -> todoService.updateTodo(999L, testTodoRequest));
        verify(todoRepository).findById(999L);
        verify(todoRepository, never()).save(any(Todo.class));
    }
    
    @Test
    void deleteTodo_ShouldDeleteTodo() {
        // Given
        when(todoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(todoRepository).deleteById(1L);
        
        // When
        todoService.deleteTodo(1L);
        
        // Then
        verify(todoRepository).existsById(1L);
        verify(todoRepository).deleteById(1L);
    }
    
    @Test
    void deleteTodo_WhenTodoNotFound_ShouldThrowException() {
        // Given
        when(todoRepository.existsById(999L)).thenReturn(false);
        
        // When & Then
        assertThrows(RuntimeException.class, () -> todoService.deleteTodo(999L));
        verify(todoRepository).existsById(999L);
        verify(todoRepository, never()).deleteById(any());
    }
    
    @Test
    void deleteCompletedTodos_ShouldDeleteCompletedTodos() {
        // Given
        doNothing().when(todoRepository).deleteCompletedTodos();
        
        // When
        todoService.deleteCompletedTodos();
        
        // Then
        verify(todoRepository).deleteCompletedTodos();
    }
    
    @Test
    void deleteAllTodos_ShouldDeleteAllTodos() {
        // Given
        doNothing().when(todoRepository).deleteAll();
        
        // When
        todoService.deleteAllTodos();
        
        // Then
        verify(todoRepository).deleteAll();
    }
} 