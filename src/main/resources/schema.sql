-- -- Удаляем таблицы, если они уже существуют (опционально)
-- DROP TABLE IF EXISTS users_roles;
-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS roles;

-- -- Создание таблицы ролей
-- CREATE TABLE roles (
--     id SERIAL PRIMARY KEY,
--     name VARCHAR(50) UNIQUE NOT NULL
-- );

-- -- Создание таблицы пользователей
-- CREATE TABLE users (
--     id SERIAL PRIMARY KEY,
--     username VARCHAR(50) UNIQUE NOT NULL,
--     password VARCHAR(255) NOT NULL,
--     email VARCHAR(100) UNIQUE NOT NULL,
--     enabled BOOLEAN DEFAULT TRUE
-- );

-- -- Создание таблицы связи пользователей и ролей (многие ко многим)
-- CREATE TABLE users_roles (
--     user_id INT NOT NULL,
--     role_id INT NOT NULL,
--     PRIMARY KEY (user_id, role_id),
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
--     FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
-- );
