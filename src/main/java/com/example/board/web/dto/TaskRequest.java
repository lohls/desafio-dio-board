package com.example.board.web.dto;

import com.example.board.domain.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class TaskRequest {
    @NotBlank(message = "título é obrigatório")
    @Size(max = 120)
    public String titulo;

    @Size(max = 1000)
    public String descricao;

    public TaskStatus status;
    public TaskPriority prioridade;
    public LocalDate dueDate;
}
