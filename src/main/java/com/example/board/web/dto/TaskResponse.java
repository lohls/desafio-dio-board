package com.example.board.web.dto;

import com.example.board.domain.*;
import java.time.*;

public class TaskResponse {
    public Long id;
    public String titulo;
    public String descricao;
    public TaskStatus status;
    public TaskPriority prioridade;
    public LocalDate dueDate;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
