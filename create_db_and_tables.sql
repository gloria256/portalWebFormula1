-- Database: Create the database
DROP DATABASE IF EXISTS formula_uno;
CREATE DATABASE IF NOT EXISTS formula_uno;
USE formula_uno;

-- Table: usuario_registrado
CREATE TABLE usuario_registrado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    usuario VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    rol ENUM('Responsable de equipo', 'Administrador') NOT NULL
);

-- Table: equipo
CREATE TABLE equipo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    logo BLOB,
    twitter VARCHAR(255)
);

-- Table: piloto
CREATE TABLE piloto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    siglas CHAR(3) NOT NULL UNIQUE,
    dorsal INT NOT NULL UNIQUE,
    foto BLOB,
    pais VARCHAR(255) NOT NULL,
    twitter VARCHAR(255)
);

-- Table: circuito
CREATE TABLE circuito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    pais VARCHAR(255) NOT NULL,
    trazado BLOB,
    numero_vueltas INT NOT NULL,
    longitud FLOAT NOT NULL,
    curvas_lentas INT NOT NULL,
    curvas_media INT NOT NULL,
    curvas_rapidas INT NOT NULL
);

-- Table: coche
CREATE TABLE coche (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    ers_curva_lenta FLOAT NOT NULL,
    ers_curva_media FLOAT NOT NULL,
    ers_curva_rapida FLOAT NOT NULL,
    consumo FLOAT NOT NULL
);

-- Table: noticia
CREATE TABLE noticia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    permalink VARCHAR(100) NOT NULL UNIQUE,
    titulo VARCHAR(100) NOT NULL,
    imagen BLOB,
    texto TEXT NOT NULL
);

-- Table: votacion
CREATE TABLE votacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    permalink VARCHAR(100) NOT NULL UNIQUE,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    limite DATETIME NOT NULL,
    lista_pilotos TEXT -- This can be a JSON or a serialized list of pilot IDs
);

-- Table: votaciones_emitidas
CREATE TABLE votaciones_emitidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    votacion_id INT NOT NULL,
    nombre_publico VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    piloto_id INT NOT NULL,
    UNIQUE (email, votacion_id),
    FOREIGN KEY (votacion_id) REFERENCES votacion(id),
    FOREIGN KEY (piloto_id) REFERENCES piloto(id)
);