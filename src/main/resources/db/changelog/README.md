# Liquibase SQL Changesets - Руководство

## Структура проекта

```
src/main/resources/db/changelog/
├── db.changelog-master.yaml    # Главный файл changelog
├── sql/                        # Папка с SQL changesets
│   ├── 001-create-users-table.sql
│   ├── 002-insert-initial-data.sql
│   └── ...
└── README.md                   # Данное руководство
```

## Формат SQL Changesets

### Основная структура

```sql
--liquibase formatted sql

--changeset author:changeset-id
--comment: Описание изменения
SQL команды здесь...

--rollback SQL команды для отката;
```

### Пример создания таблицы

```sql
--liquibase formatted sql

--changeset alexk:001-create-users-table
--comment: Создание таблицы пользователей
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--rollback DROP TABLE users;
```

## Лучшие практики

### 1. Именование файлов
- Используйте префикс с номером: `001-`, `002-`, etc.
- Описательное имя: `create-users-table`, `add-indexes`, `insert-initial-data`
- Формат: `XXX-description.sql`

### 2. Именование changesets
- Формат: `author:changeset-id`
- Уникальный ID для каждого changeset
- Пример: `alexk:001-create-users-table`

### 3. Комментарии
- Всегда добавляйте `--comment:` с описанием
- Используйте понятные описания на русском или английском

### 4. Rollback команды
- Всегда предусматривайте rollback для каждого changeset
- Тестируйте rollback команды
- Для сложных операций используйте несколько команд

### 5. Организация changesets
- Один changeset = одна логическая операция
- Разделяйте создание таблиц, индексов, данных
- Группируйте связанные изменения в одном файле

## Дополнительные атрибуты

### Условное выполнение
```sql
--changeset alexk:002-add-column
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM information_schema.columns WHERE table_name='users' AND column_name='phone'
ALTER TABLE users ADD COLUMN phone VARCHAR(20);
--rollback ALTER TABLE users DROP COLUMN phone;
```

### Контекст выполнения
```sql
--changeset alexk:003-dev-data
--context:dev
INSERT INTO users (username, email, password) VALUES ('testuser', 'test@example.com', 'password');
--rollback DELETE FROM users WHERE username = 'testuser';
```

### Выполнение только один раз
```sql
--changeset alexk:004-one-time-script
--runOnChange:false
--runAlways:false
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
--rollback DROP INDEX IF EXISTS idx_users_email;
```

## Команды Liquibase

### Применение изменений
```bash
./gradlew bootRun
# или
./gradlew liquibaseUpdate
```

### Откат изменений
```bash
./gradlew liquibaseRollbackCount -PliquibaseCommandValue=1
```

### Просмотр статуса
```bash
./gradlew liquibaseStatus
```

### Генерация SQL для просмотра
```bash
./gradlew liquibaseUpdateSQL
```

## Настройка в application.yaml

```yaml
spring:
  liquibase:
    enabled: true
    change-log: classpath:db/changelog/db.changelog-master.yaml
    contexts: dev,prod  # опционально
    default-schema: public  # опционально
```

## Подключение новых changesets

В файле `db.changelog-master.yaml`:

```yaml
databaseChangeLog:
  - include:
      file: db/changelog/sql/001-create-users-table.sql
  - include:
      file: db/changelog/sql/002-insert-initial-data.sql
  - include:
      file: db/changelog/sql/003-new-changeset.sql
```

## Типичные ошибки

1. **Дублирование changeset ID** - каждый ID должен быть уникальным
2. **Отсутствие rollback** - всегда добавляйте команды отката
3. **Неправильный формат** - соблюдайте синтаксис `--liquibase formatted sql`
4. **Изменение уже примененных changesets** - никогда не изменяйте уже выполненные changesets

## Полезные ссылки

- [Официальная документация Liquibase](https://docs.liquibase.com/)
- [SQL Format](https://docs.liquibase.com/concepts/changelogs/sql-format.html)
- [Changeset Attributes](https://docs.liquibase.com/concepts/changelogs/attributes/home.html)
