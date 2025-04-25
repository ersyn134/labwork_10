-- DELETE FROM enrollment;
-- DELETE FROM student;
-- DELETE FROM course;
-- DELETE FROM users_roles;
-- DELETE FROM users;
-- DELETE FROM role;

-- -- Вставка ролей
-- INSERT INTO roles (name) VALUES 
-- ('ROLE_ADMIN'),
-- ('ROLE_TEACHER'),
-- ('ROLE_STUDENT');

-- -- Вставка пользователей (пароли должны быть захешированы заранее)
-- INSERT INTO users (username, password, enabled) VALUES 
-- ('admin', '$2a$10$abc12345...', true),  -- 'admin123' (bcrypt)
-- ('teacher1', '$2a$10$def67890...', true), -- 'teacher123' (bcrypt)
-- ('student1', '$2a$10$xyz45678...', true); -- 'student123' (bcrypt)

-- -- Связка пользователей с ролями
-- INSERT INTO users_roles (user_id, role_id) VALUES 
-- (1, 1), -- admin -> ROLE_ADMIN
-- (2, 2), -- teacher1 -> ROLE_TEACHER
-- (3, 3); -- student1 -> ROLE_STUDENT

-- -- Вставка курсов
-- INSERT INTO courses (name, description) VALUES 
-- ('Mathematics', 'Basic Math Course'),
-- ('Physics', 'Basic Physics Course');

-- -- Вставка студентов
-- INSERT INTO students (first_name, last_name, email) VALUES 
-- ('Alice', 'Johnson', 'alice@example.com'),
-- ('Bob', 'Smith', 'bob@example.com'),
-- ('Charlie', 'Brown', 'charlie@example.com');

-- -- Вставка записей на курсы
-- INSERT INTO enrollments (student_id, course_id) VALUES 
-- (1, 1), -- Alice -> Mathematics
-- (2, 2), -- Bob -> Physics
-- (3, 1); -- Charlie -> Mathematics
