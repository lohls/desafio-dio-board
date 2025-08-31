package com.example.board.web;

import com.example.board.service.TaskService;
import com.example.board.web.dto.*;
import com.example.board.web.mapper.TaskMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse criar(@Valid @RequestBody TaskRequest req) {
        var saved = service.criar(req);
        return TaskMapper.toResponse(saved);
    }

    @GetMapping("/{id}")
    public TaskResponse buscar(@PathVariable Long id) {
        return TaskMapper.toResponse(service.buscarPorId(id));
    }

    @GetMapping
    public Page<TaskResponse> listar(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String prioridade,
            @RequestParam(required = false, name = "q") String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return service.listar(status, prioridade, q, pageable)
                .map(TaskMapper::toResponse);
    }

    @PutMapping("/{id}")
    public TaskResponse atualizar(@PathVariable Long id, @Valid @RequestBody TaskRequest req) {
        return TaskMapper.toResponse(service.atualizar(id, req));
    }

    @PatchMapping("/{id}")
    public TaskResponse patch(@PathVariable Long id, @RequestBody TaskRequest req) {
        return TaskMapper.toResponse(service.atualizarParcial(id, req));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
