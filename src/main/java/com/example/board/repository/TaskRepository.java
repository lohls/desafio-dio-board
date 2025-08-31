package com.example.board.repository;

import com.example.board.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Page<TaskEntity> findByStatus(TaskStatus status, Pageable pageable);
    Page<TaskEntity> findByPrioridade(TaskPriority prioridade, Pageable pageable);
    Page<TaskEntity> findByTituloContainingIgnoreCase(String q, Pageable pageable);
}
