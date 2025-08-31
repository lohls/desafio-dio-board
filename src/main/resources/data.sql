INSERT INTO tasks (id, titulo, descricao, status, prioridade, due_date, created_at, updated_at)
VALUES
 (1, 'Estudar Spring Boot', 'Criar projeto base do board', 'TODO', 'HIGH', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (2, 'Modelar entidades', 'Definir Task/Status/Prioridade', 'DOING', 'MEDIUM', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
 (3, 'Implementar API', 'CRUD + filtros', 'DONE', 'LOW', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
