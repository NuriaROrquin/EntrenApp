INSERT INTO Disciplina (nombre, descripcion, edad_minima, edad_maxima)
VALUES ('Natación', 'Actividad acuática', 6, 99),
       ('Fútbol', 'Deporte de equipo', 5, 50),
       ('Baloncesto', 'Deporte de cancha', 8, 60),
       ('Karate', 'Arte marcial', 7, 70),
       ('Gimnasia rítmica', 'Actividad artística', 4, 18),
       ('Tenis', 'Deporte individual', 6, 80),
       ('Béisbol', 'Deporte de lanzamiento y bateo', 8, 55),
       ('Atletismo', 'Actividades deportivas', 10, 99),
       ('Voleibol', 'Deporte de equipo', 10, 60),
       ('Judo', 'Arte marcial', 6, 75);


INSERT INTO Dificultad (descripcion)
VALUES ('Fácil'),
       ('Moderado'),
       ('Difícil');

INSERT INTO Lugar (latitud, longitud, descripcion, nombre)
VALUES (-34.615743, -58.503336, 'Cementerio de La Recoleta', 'La Recoleta'),
       (-34.608280, -58.373312, 'Obelisco de Buenos Aires', 'El Obelisco'),
       (-34.583861, -58.437057, 'Plaza de Mayo', 'Plaza de Mayo'),
       (-34.582521, -58.437147, 'Catedral Metropolitana de Buenos Aires', 'Catedral de Buenos Aires'),
       (-34.614846, -58.369218, 'Puerto Madero', 'Puerto Madero'),
       (-34.603738, -58.381570, 'Teatro Colón', 'Teatro Colón'),
       (-34.573226, -58.417232, 'Barrio de San Telmo', 'San Telmo'),
       (-34.591032, -58.392194, 'Barrio de La Boca', 'La Boca'),
       (-34.571952, -58.442219, 'Recoleta Mall', 'Recoleta Mall'),
       (-34.590746, -58.393014, 'Caminito', 'Caminito');

INSERT INTO Detalle (hora_inicio, hora_fin, capacidad)
VALUES ('2023-06-08 09:00:00', '2023-06-08 10:30:00', 15),
       ('2023-06-08 11:00:00', '2023-06-08 12:30:00', 10),
       ('2023-06-08 13:00:00', '2023-06-08 14:30:00', 20),
       ('2023-06-08 15:00:00', '2023-06-08 16:30:00', 12),
       ('2023-06-08 17:00:00', '2023-06-08 18:30:00', 8),
       ('2023-06-08 19:00:00', '2023-06-08 20:30:00', 16),
       ('2023-06-09 10:00:00', '2023-06-09 11:30:00', 10),
       ('2023-06-09 12:00:00', '2023-06-09 13:30:00', 20),
       ('2023-06-09 14:00:00', '2023-06-09 15:30:00', 15),
       ('2023-06-09 16:00:00', '2023-06-09 17:30:00', 8);

INSERT INTO Clase (fecha, isAprobada, isEliminada, fecha_alta, fecha_baja, detail_id_detalle, place_id_lugar,
                   difficulty_id_dificultad,
                   discipline_id_disciplina)
VALUES ('2023-06-08', 1, 0, '2023-06-07', null, 1, 1, 1, 1),
       ('2023-06-08', 1, 0, '2023-06-07', null, 2, 2, 2, 2),
       ('2023-06-08', 1, 0, '2023-06-07', null, 3, 3, 3, 3),
       ('2023-06-08', 1, 0, '2023-06-07', null, 4, 4, 1, 4),
       ('2023-06-08', 1, 0, '2023-06-07', null, 5, 5, 2, 5),
       ('2023-06-09', 1, 0, '2023-06-07', null, 6, 6, 3, 6),
       ('2023-06-09', 1, 0, '2023-06-07', null, 7, 7, 1, 7),
       ('2023-06-09', 1, 0, '2023-06-07', null, 8, 8, 2, 8),
       ('2023-06-09', 1, 0, '2023-06-07', null, 9, 9, 3, 9),
       ('2023-06-09', 1, 0, '2023-06-07', null, 10, 10, 1, 10);

INSERT INTO `usuario` (`id`, `activo`, `email`, `password`, `rol`)
VALUES (1, 1, 'norquin@alumno.unlam.edu.ar', 'nurinuri', '1');

INSERT INTO `usuarioclase` (`id_usuario_clase`, `lesson_id_clase`, `user_id`)
VALUES (15, 1, 1),
       (16, 2, 1),
       (17, 3, 1),
       (18, 4, 1);

INSERT INTO Rol (descripcion)
VALUES ('admin'),
       ('alumno'),
       ('profesor');

INSERT INTO UsuarioRol (user_id, role_id_rol)
VALUES (1, 1);