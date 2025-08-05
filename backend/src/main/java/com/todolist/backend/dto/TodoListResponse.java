package com.todolist.backend.dto;

import java.util.List;

public class TodoListResponse {
    
    private List<TodoResponse> items;
    private Long total;
    private Integer limit;
    private Integer offset;
    
    // Default constructor
    public TodoListResponse() {}
    
    // Builder constructor
    public TodoListResponse(List<TodoResponse> items, Long total, Integer limit, Integer offset) {
        this.items = items;
        this.total = total;
        this.limit = limit;
        this.offset = offset;
    }
    
    // Builder pattern
    public static TodoListResponseBuilder builder() {
        return new TodoListResponseBuilder();
    }
    
    public static class TodoListResponseBuilder {
        private List<TodoResponse> items;
        private Long total;
        private Integer limit;
        private Integer offset;
        
        public TodoListResponseBuilder items(List<TodoResponse> items) {
            this.items = items;
            return this;
        }
        
        public TodoListResponseBuilder total(Long total) {
            this.total = total;
            return this;
        }
        
        public TodoListResponseBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }
        
        public TodoListResponseBuilder offset(Integer offset) {
            this.offset = offset;
            return this;
        }
        
        public TodoListResponse build() {
            return new TodoListResponse(items, total, limit, offset);
        }
    }
    
    // Getters and Setters
    public List<TodoResponse> getItems() {
        return items;
    }
    
    public void setItems(List<TodoResponse> items) {
        this.items = items;
    }
    
    public Long getTotal() {
        return total;
    }
    
    public void setTotal(Long total) {
        this.total = total;
    }
    
    public Integer getLimit() {
        return limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
} 