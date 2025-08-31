package com.example.board;

import com.example.board.domain.TaskEntity;
import com.example.board.service.TaskService;
import com.example.board.web.dto.TaskRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskServiceTest {

    @Autowired
    TaskService service;

    @Test
    void deveCriarEBuscar() {
        var req = new TaskRequest();
        req.titulo = "Testar criação";
        req.descricao = "Criar e buscar task";
        TaskEntity criado = service.criar(req);
        assertNotNull(criado.getId());

        var achado = service.buscarPorId(criado.getId());
        assertEquals("Testar criação", achado.getTitulo());
    }
}
