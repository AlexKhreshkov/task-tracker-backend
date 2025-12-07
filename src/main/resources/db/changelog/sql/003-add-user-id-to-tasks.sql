--liquibase formatted sql

--changeset alexk:004-add-user-id-to-tasks
--comment: Добавление колонки user_id в таблицу task для связи с пользователем
ALTER TABLE task ADD COLUMN user_id BIGINT;

--comment: Добавление внешнего ключа для связи с таблицей users
ALTER TABLE task ADD CONSTRAINT fk_task_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL;

--rollback ALTER TABLE task DROP CONSTRAINT fk_task_user;
--rollback ALTER TABLE task DROP COLUMN user_id;
