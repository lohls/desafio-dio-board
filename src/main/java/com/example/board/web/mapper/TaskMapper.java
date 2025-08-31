package com.example.board.web.mapper;

import com.example.board.domain.TaskEntity;
import com.example.board.web.dto.*;

public class TaskMapper {
    public static TaskEntity toEntity(TaskRequest req) {
        var e = new TaskEntity();
        e.setTitulo(req.titulo);
        e.setDescricao(req.descricao);
        if (req.status != null) e.setStatus(req.status);
        if (req.prioridade != null) e.setPrioridade(req.prioridade);
        e.setDueDate(req.dueDate);
        return e;
    }

    public static void merge(TaskRequest req, TaskEntity e) {
        if (req.titulo != null) e.setTitulo(req.titulo);
        if (req.descricao != null) e.setDescricao(req.descricao);
        if (req.status != null) e.setStatus(req.status);
        if (req.prioridade != null) e.setPrioridade(req.prioridade);
        if (req.dueDate != null) e.setDueDate(req.dueDate);
    }

    public static TaskResponse toResponse(TaskEntity e) {
        var r = new TaskResponse();
        r.id = e.getId();
        r.titulo = e.getTitulo();
        r.descricao = e.getDescricao();
        r.status = e.getStatus();
        r.prioridade = e.getPrioridade();
        r.dueDate = e.getDueDate();
        r.createdAt = e.getCreatedAt();
        r.updatedAt = e.getUpdatedAt();
        return r;
    }
}
