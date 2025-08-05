package com.todolist.backend.controller;

import com.todolist.backend.dto.TodoListResponse;
import com.todolist.backend.dto.TodoRequest;
import com.todolist.backend.dto.TodoResponse;
import com.todolist.backend.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todos")
@Tag(name = "Todo", description = "Todo management APIs")
@CrossOrigin(origins = "*")
public class TodoController {
    
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    
    private final TodoService todoService;
    
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping
    @Operation(summary = "Get all todos", description = "Retrieve all todos with optional filtering and pagination")
    public ResponseEntity<TodoListResponse> getAllTodos(
            @Parameter(description = "Filter by completion status: true, false, or all")
            @RequestParam(required = false) String completed,
            @Parameter(description = "Limit number of results (1-100)")
            @RequestParam(required = false, defaultValue = "50") Integer limit,
            @Parameter(description = "Offset for pagination")
            @RequestParam(required = false, defaultValue = "0") Integer offset) {
        
        TodoListResponse response = todoService.getAllTodos(completed, limit, offset);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get todo by ID", description = "Retrieve a specific todo by its ID")
    public ResponseEntity<TodoResponse> getTodoById(
            @Parameter(description = "Todo ID")
            @PathVariable Long id) {
        
        TodoResponse response = todoService.getTodoById(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    @Operation(summary = "Create new todo", description = "Create a new todo item")
    public ResponseEntity<TodoResponse> createTodo(
            @Parameter(description = "Todo data")
            @Valid @RequestBody TodoRequest request) {
        
        TodoResponse response = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update todo", description = "Update an existing todo item")
    public ResponseEntity<TodoResponse> updateTodo(
            @Parameter(description = "Todo ID")
            @PathVariable Long id,
            @Parameter(description = "Updated todo data")
            @Valid @RequestBody TodoRequest request) {
        
        TodoResponse response = todoService.updateTodo(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete todo", description = "Delete a specific todo item")
    public ResponseEntity<Void> deleteTodo(
            @Parameter(description = "Todo ID")
            @PathVariable Long id) {
        
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/completed")
    @Operation(summary = "Delete completed todos", description = "Delete all completed todo items")
    public ResponseEntity<Void> deleteCompletedTodos() {
        
        todoService.deleteCompletedTodos();
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/all")
    @Operation(summary = "Delete all todos", description = "Delete all todo items")
    public ResponseEntity<Void> deleteAllTodos() {
        
        todoService.deleteAllTodos();
        return ResponseEntity.noContent().build();
    }
} 