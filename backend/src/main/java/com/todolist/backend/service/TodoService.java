package com.todolist.backend.service;

import com.todolist.backend.dto.TodoListResponse;
import com.todolist.backend.dto.TodoRequest;
import com.todolist.backend.dto.TodoResponse;
import com.todolist.backend.model.Todo;
import com.todolist.backend.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoService {
    
    private static final Logger log = LoggerFactory.getLogger(TodoService.class);
    
    private final TodoRepository todoRepository;
    
    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    
    public TodoListResponse getAllTodos(String completed, Integer limit, Integer offset) {
        log.info("Getting todos with completed={}, limit={}, offset={}", completed, limit, offset);
        
        // Parse completed parameter
        Boolean completedFilter = null;
        if (completed != null && !completed.equals("all")) {
            completedFilter = Boolean.parseBoolean(completed);
        }
        
        // Apply limits
        int actualLimit = Math.min(limit != null ? limit : 50, 100);
        int actualOffset = offset != null ? offset : 0;
        
        List<Todo> todos;
        if (completedFilter != null) {
            todos = todoRepository.findByCompleted(completedFilter);
        } else {
            todos = todoRepository.findAll();
        }
        
        // Apply pagination
        List<Todo> paginatedTodos = todos.stream()
                .skip(actualOffset)
                .limit(actualLimit)
                .collect(Collectors.toList());
        
        return TodoListResponse.builder()
                .items(paginatedTodos.stream().map(this::convertToResponse).collect(Collectors.toList()))
                .total((long) todos.size())
                .limit(actualLimit)
                .offset(actualOffset)
                .build();
    }
    
    public TodoResponse getTodoById(Long id) {
        log.info("Getting todo with id: {}", id);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        return convertToResponse(todo);
    }
    
    public TodoResponse createTodo(TodoRequest request) {
        log.info("Creating new todo: {}", request);
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(request.getCompleted() != null ? request.getCompleted() : false)
                .build();
        
        Todo savedTodo = todoRepository.save(todo);
        return convertToResponse(savedTodo);
    }
    
    public TodoResponse updateTodo(Long id, TodoRequest request) {
        log.info("Updating todo with id: {}", id);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        
        if (request.getTitle() != null) {
            todo.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            todo.setDescription(request.getDescription());
        }
        if (request.getCompleted() != null) {
            todo.setCompleted(request.getCompleted());
        }
        
        Todo updatedTodo = todoRepository.save(todo);
        return convertToResponse(updatedTodo);
    }
    
    public void deleteTodo(Long id) {
        log.info("Deleting todo with id: {}", id);
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
    
    public void deleteCompletedTodos() {
        log.info("Deleting all completed todos");
        todoRepository.deleteCompletedTodos();
    }
    
    public void deleteAllTodos() {
        log.info("Deleting all todos");
        todoRepository.deleteAll();
    }
    
    private TodoResponse convertToResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.getCompleted())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
} 