--liquibase formatted sql

--changeset alexk:001-create-users-table
--comment: Создание таблицы пользователей
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--rollback DROP TABLE users;

--changeset alexk:002-create-users-indexes
--comment: Создание индексов для таблицы users
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);

--rollback DROP INDEX idx_users_username; DROP INDEX idx_users_email;
