-- Insertar datos iniciales en typeofuser
INSERT INTO typeofuser (description) VALUES ('Admin');
INSERT INTO typeofuser (description) VALUES ('User');

-- Insertar datos iniciales en users
INSERT INTO users (first_name, last_name, email, password, type_id) VALUES ('John', 'Doe', 'john@example.com', 'password', 1);
INSERT INTO users (first_name, last_name, email, password, type_id) VALUES ('Jane', 'Doe', 'jane@example.com', 'password', 2);