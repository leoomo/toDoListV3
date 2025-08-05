package com.todolist.backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String title;
    
    @Column(length = 1000)
    private String description;
    
    @Column(nullable = false)
    private Boolean completed = false;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    // Default constructor
    public Todo() {}
    
    // Builder constructor
    public Todo(Long id, String title, String description, Boolean completed, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Builder pattern
    public static TodoBuilder builder() {
        return new TodoBuilder();
    }
    
    public static class TodoBuilder {
        private Long id;
        private String title;
        private String description;
        private Boolean completed = false;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
        public TodoBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public TodoBuilder title(String title) {
            this.title = title;
            return this;
        }
        
        public TodoBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        public TodoBuilder completed(Boolean completed) {
            this.completed = completed;
            return this;
        }
        
        public TodoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        
        public TodoBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
        
        public Todo build() {
            return new Todo(id, title, description, completed, createdAt, updatedAt);
        }
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getCompleted() {
        return completed;
    }
    
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
} 