package com.example.board.service;

import com.example.board.domain.*;
import com.example.board.repository.TaskRepository;
import com.example.board.web.dto.TaskRequest;
import com.example.board.web.mapper.TaskMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) { this.repo = repo; }

    @Transactional
    public TaskEntity criar(TaskRequest req) {
        var entity = TaskMapper.toEntity(req);
        return repo.save(entity);
    }

    @Transactional(readOnly = true)
    public TaskEntity buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() ->
            new IllegalArgumentException("Task não encontrada: id=" + id));
    }

    @Transactional(readOnly = true)
    public Page<TaskEntity> listar(String status, String prioridade, String q, Pageable pageable) {
        if (status != null) {
            return repo.findByStatus(TaskStatus.valueOf(status), pageable);
        }
        if (prioridade != null) {
            return repo.findByPrioridade(TaskPriority.valueOf(prioridade), pageable);
        }
        if (q != null && !q.isBlank()) {
            return repo.findByTituloContainingIgnoreCase(q, pageable);
        }
        return repo.findAll(pageable);
    }

    @Transactional
    public TaskEntity atualizar(Long id, TaskRequest req) {
        var existente = buscarPorId(id);
        if (req.titulo == null || req.titulo.isBlank())
            throw new IllegalArgumentException("título é obrigatório no PUT");
        existente.setTitulo(req.titulo);
        existente.setDescricao(req.descricao);
        if (req.status != null) existente.setStatus(req.status);
        if (req.prioridade != null) existente.setPrioridade(req.prioridade);
        existente.setDueDate(req.dueDate);
        return repo.save(existente);
    }

    @Transactional
    public TaskEntity atualizarParcial(Long id, TaskRequest req) {
        var existente = buscarPorId(id);
        TaskMapper.merge(req, existente);
        return repo.save(existente);
    }

    @Transactional
    public void remover(Long id) {
        var existente = buscarPorId(id);
        repo.delete(existente);
    }
}
