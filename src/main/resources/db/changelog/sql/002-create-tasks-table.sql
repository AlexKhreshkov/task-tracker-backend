--liquibase formatted sql

--changeset alexk:003-create-tasks-table
--comment: Создание таблицы задач
CREATE TABLE task (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text TEXT,
    status VARCHAR(50) NOT NULL CHECK (status IN ('CREATED', 'IN_PROGRESS', 'DONE')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    done_at TIMESTAMP
);

--rollback DROP TABLE task;