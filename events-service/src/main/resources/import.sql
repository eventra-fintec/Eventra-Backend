-- Insertar datos iniciales en typeofuser
INSERT INTO categories (category) VALUES ('Deportes');
INSERT INTO categories (category) VALUES ('Tecnologias');
INSERT INTO categories (category) VALUES ('Cultura');

-- Insertar datos iniciales en events
INSERT INTO events (title, description, start_date, end_date, location, organizer_id, category_id, created_at, updated_at) VALUES ('Concierto de Rock', 'Descripción del concierto de rock', '2024-05-01 19:00:00', '2024-05-01 23:00:00', 'Estadio Nacional', 1, 1, '2024-01-13', '2024-01-13');
INSERT INTO events (title, description, start_date, end_date, location, organizer_id, category_id, created_at, updated_at) VALUES ('Obra de Teatro', 'Descripción de la obra de teatro', '2024-05-05 20:00:00', '2024-05-05 22:00:00', 'Teatro Municipal', 2, 2, '2024-01-13', '2024-01-13');
INSERT INTO events (title, description, start_date, end_date, location, organizer_id, category_id, created_at, updated_at) VALUES ('Partido de Fútbol', 'Descripción del partido de fútbol', '2024-05-10 15:00:00', '2024-05-10 17:00:00', 'Estadio Monumental', 3, 3, '2024-01-13', '2024-01-13');
