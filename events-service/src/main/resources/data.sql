-- Inicialización de la tabla category_event
INSERT INTO category_event (name_category) VALUES
                                               ('Music'),
                                               ('Sports'),
                                               ('Education'),
                                               ('Technology');

-- Inicialización de la tabla events
INSERT INTO events (title, description, start_date, end_date, location, id_organizer, url, id_category) VALUES
                                                                                                       ('Concert of the Year', 'A spectacular music event featuring top artists.', '2024-06-01 18:00:00', '2024-06-01 22:00:00', 'City Arena', 1, 'url', 1),
                                                                                                       ('Marathon 2024', 'Join the city marathon and challenge yourself!', '2024-07-15 06:00:00', '2024-07-15 12:00:00', 'Downtown Streets', 2, 'url', 2),
                                                                                                       ('Tech Conference', 'A conference on the latest advancements in technology.', '2024-08-20 09:00:00', '2024-08-20 17:00:00', 'Tech Hub Convention Center', 1, 'url', 4),
                                                                                                       ('Educational Workshop', 'A workshop focused on innovative teaching methods.', '2024-09-10 10:00:00', '2024-09-10 14:00:00', 'Community Hall', 2, 'url', 3);
