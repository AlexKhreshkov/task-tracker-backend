--liquibase formatted sql

--changeset alexk:003-insert-admin-user
--comment: Добавление администратора по умолчанию
INSERT INTO users (username, email, password) 
VALUES ('admin', 'admin@example.com', '123');
--rollback DELETE FROM users WHERE username = 'admin';
