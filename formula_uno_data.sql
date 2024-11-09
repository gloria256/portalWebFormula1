USE formula_uno;
-- Insert data into usuario_registrado
INSERT INTO usuario_registrado (nombre, usuario, email, contraseña, rol) VALUES
('John Doe', 'johndoe', 'john.doe@example.com', 'password123', 'Administrador'),
('Jane Smith', 'janesmith', 'jane.smith@example.com', 'password123', 'Responsable de equipo'),
('Alice Johnson', 'alicej', 'alice.johnson@example.com', 'password123', 'Administrador'),
('Bob Brown', 'bobb', 'bob.brown@example.com', 'password123', 'Responsable de equipo'),
('Charlie Davis', 'charlied', 'charlie.davis@example.com', 'password123', 'Administrador');

-- Insert data into equipo
INSERT INTO equipo (nombre, logo, twitter) VALUES
('Team A', NULL, '@teamA'),
('Team B', NULL, '@teamB'),
('Team C', NULL, '@teamC'),
('Team D', NULL, '@teamD'),
('Team E', NULL, '@teamE');

-- Insert data into piloto
INSERT INTO piloto (nombre, apellidos, siglas, dorsal, foto, pais, twitter) VALUES
('Lewis', 'Hamilton', 'HAM', 44, NULL, 'UK', '@lewishamilton'),
('Max', 'Verstappen', 'VER', 33, NULL, 'Netherlands', '@maxverstappen'),
('Sebastian', 'Vettel', 'VET', 5, NULL, 'Germany', '@sebvettel'),
('Charles', 'Leclerc', 'LEC', 16, NULL, 'Monaco', '@charles_leclerc'),
('Fernando', 'Alonso', 'ALO', 14, NULL, 'Spain', '@alo_oficial');

-- Insert data into circuito
INSERT INTO circuito (nombre, ciudad, pais, trazado, numero_vueltas, longitud, curvas_lentas, curvas_media, curvas_rapidas) VALUES
('Circuit de Monaco', 'Monaco', 'Monaco', NULL, 78, 3.337, 6, 10, 4),
('Silverstone Circuit', 'Silverstone', 'UK', NULL, 52, 5.891, 8, 10, 6),
('Circuit de Spa-Francorchamps', 'Stavelot', 'Belgium', NULL, 44, 7.004, 9, 10, 5),
('Suzuka Circuit', 'Suzuka', 'Japan', NULL, 53, 5.807, 7, 11, 6),
('Circuit of the Americas', 'Austin', 'USA', NULL, 56, 5.513, 8, 9, 7);

-- Insert data into coche
INSERT INTO coche (nombre, codigo, ers_curva_lenta, ers_curva_media, ers_curva_rapida, consumo) VALUES
('Car A', 'A001', 1.2, 1.3, 1.4, 2.5),
('Car B', 'B001', 1.1, 1.2, 1.3, 2.4),
('Car C', 'C001', 1.3, 1.4, 1.5, 2.6),
('Car D', 'D001', 1.0, 1.1, 1.2, 2.3),
('Car E', 'E001', 1.4, 1.5, 1.6, 2.7);

-- Insert data into noticia
INSERT INTO noticia (permalink, titulo, imagen, texto) VALUES
('news-1', 'First News', NULL, 'This is the first news article. It contains more than 500 characters to meet the requirement. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'),
('news-2', 'Second News', NULL, 'This is the second news article. It also contains more than 500 characters to meet the requirement. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'),
('news-3', 'Third News', NULL, 'This is the third news article. It contains more than 500 characters to meet the requirement. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'),
('news-4', 'Fourth News', NULL, 'This is the fourth news article. It contains more than 500 characters to meet the requirement. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'),
('news-5', 'Fifth News', NULL, 'This is the fifth news article. It contains more than 500 characters to meet the requirement. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');

-- Insert data into votacion
INSERT INTO votacion (permalink, titulo, descripcion, limite, lista_pilotos) VALUES
('vote-1', 'Best Driver', 'Vote for the best driver of the season', '2023-12-31 23:59:59', '[1, 2]'),
('vote-2', 'Best Team', 'Vote for the best team of the season', '2023-12-31 23:59:59', '[1, 2]'),
('vote-3', 'Best Circuit', 'Vote for the best circuit of the season', '2023-12-31 23:59:59', '[1, 2, 3]'),
('vote-4', 'Best Car', 'Vote for the best car of the season', '2023-12-31 23:59:59', '[1, 2, 3, 4]'),
('vote-5', 'Best News', 'Vote for the best news article of the season', '2023-12-31 23:59:59', '[1, 2, 3, 4, 5]');

-- Insert data into votaciones_emitidas
INSERT INTO votaciones_emitidas (votacion_id, nombre_publico, email, piloto_id) VALUES
(1, 'Alice', 'alice@example.com', 1),
(1, 'Bob', 'bob@example.com', 2),
(2, 'Charlie', 'charlie@example.com', 1),
(2, 'David', 'david@example.com', 2),
(3, 'Eve', 'eve@example.com', 3),
(3, 'Frank', 'frank@example.com', 4),
(4, 'Grace', 'grace@example.com', 1),
(4, 'Heidi', 'heidi@example.com', 2),
(5, 'Ivan', 'ivan@example.com', 3),
(5, 'Judy', 'judy@example.com', 4);