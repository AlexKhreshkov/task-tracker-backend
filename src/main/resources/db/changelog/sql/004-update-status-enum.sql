--liquibase formatted sql

--changeset alexk:004-update-status-enum
--comment: Update status enum from CREATED to TODO
-- First, update any existing data
UPDATE task SET status = 'TODO' WHERE status = 'CREATED';

-- Drop the old constraint
ALTER TABLE task DROP CONSTRAINT IF EXISTS task_status_check;

-- Add the new constraint with TODO instead of CREATED
ALTER TABLE task ADD CONSTRAINT task_status_check CHECK (status IN ('TODO', 'IN_PROGRESS', 'DONE'));

--rollback ALTER TABLE task DROP CONSTRAINT task_status_check; ALTER TABLE task ADD CONSTRAINT task_status_check CHECK (status IN ('CREATED', 'IN_PROGRESS', 'DONE')); UPDATE task SET status = 'CREATED' WHERE status = 'TODO';
