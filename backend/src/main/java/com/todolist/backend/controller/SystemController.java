package com.todolist.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "System", description = "System health and info APIs")
@CrossOrigin(origins = "*")
public class SystemController {
    
    private static final Logger log = LoggerFactory.getLogger(SystemController.class);
    
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Check API service status")
    public ResponseEntity<Map<String, String>> healthCheck() {
        log.info("Health check requested");
        Map<String, String> response = new HashMap<>();
        response.put("status", "healthy");
        response.put("service", "TodoListV2 API");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/")
    @Operation(summary = "Root path", description = "Get API basic information")
    public ResponseEntity<Map<String, String>> getApiInfo() {
        log.info("API info requested");
        Map<String, String> response = new HashMap<>();
        response.put("message", "欢迎使用 TodoListV2 API");
        response.put("version", "0.1.0");
        response.put("docs", "/docs");
        response.put("redoc", "/redoc");
        return ResponseEntity.ok(response);
    }
} 