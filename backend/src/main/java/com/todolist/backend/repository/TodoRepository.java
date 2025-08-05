package com.todolist.backend.repository;

import com.todolist.backend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
    List<Todo> findByCompleted(Boolean completed);
    
    @Query("SELECT t FROM Todo t WHERE (:completed IS NULL OR t.completed = :completed)")
    List<Todo> findByCompletedOptional(@Param("completed") Boolean completed);
    
    @Query("SELECT COUNT(t) FROM Todo t WHERE t.completed = true")
    Long countCompletedTodos();
    
    @Modifying
    @Query("DELETE FROM Todo t WHERE t.completed = true")
    void deleteCompletedTodos();
} 