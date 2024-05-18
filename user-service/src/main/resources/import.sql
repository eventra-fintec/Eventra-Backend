-- Insertar datos iniciales en typeofuser
INSERT INTO roles (role) VALUES ('ROLE_USER');
INSERT INTO roles (role) VALUES ('ROLE_ESTHUSIASTIC');
INSERT INTO roles (role) VALUES ('ROLE_roles');

-- Insertar datos iniciales en users
INSERT INTO users (first_name, last_name, email, password, type_id, created_at, updated_at) VALUES ('John', 'Doe', 'john@example.com', 'password', 1, '2024-01-13', '2024-01-13');
INSERT INTO users (first_name, last_name, email, password, type_id, created_at, updated_at) VALUES ('Jane', 'Doe', 'jane@example.com', 'password', 2, '2024-01-13', '2024-01-13');
INSERT INTO users (first_name, last_name, email, password, type_id, created_at, updated_at) VALUES ('James', 'Doe', 'james@example.com', 'password', 3, '2024-01-13', '2024-01-13');