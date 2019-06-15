/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author:  Samuel Reyes Alvarez
 */
DROP TABLE IF EXISTS registro_bazar;
DROP TABLE IF EXISTS registro_combate;
DROP TABLE IF EXISTS estadisticas;
DROP TABLE IF EXISTS mision;
DROP TABLE IF EXISTS inventario;
DROP TABLE IF EXISTS estado;
DROP TABLE IF EXISTS jugador;
DROP TABLE IF EXISTS acceso;

DROP DATABASE IF EXISTS TwTcYjCeWV;
CREATE DATABASE TwTcYjCeWV;
USE TwTcYjCeWV;

CREATE TABLE IF NOT EXISTS acceso (
    id_acceso INT AUTO_INCREMENT,
    correo VARCHAR(150) NOT NULL,
    clave VARCHAR(150) NOT NULL,
    CONSTRAINT acceso_pk PRIMARY KEY (id_acceso)
);

CREATE TABLE IF NOT EXISTS jugador (
    id_jugador INT AUTO_INCREMENT,
    nombre VARCHAR(15) NOT NULL,
    imagen VARCHAR(250) NOT NULL,
    nivel INT NOT NULL,
    exp_acumulada INT NOT NULL,
    puntos_no_usados INT NOT NULL,
    oro_actual INT NOT NULL,
    CONSTRAINT jugador_pk PRIMARY KEY (id_jugador)
);


CREATE TABLE IF NOT EXISTS estado (
    id_estado INT AUTO_INCREMENT,
    id_jugador INT NULL,
    tipo_atributo VARCHAR(15) NOT NULL,
    potenciado INT NOT NULL,
    CONSTRAINT estado_pk PRIMARY KEY (id_estado),
    CONSTRAINT estado_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS inventario (
    id_inventario INT AUTO_INCREMENT,
    id_jugador INT NULL,
    id_estado INT NULL,
    tipo_equipo VARCHAR(15) NOT NULL,
    nivel INT NOT NULL,
    potenciado INT NOT NULL,
    precio INT NOT NULL,
    equipado TINYINT NOT NULL,
    en_venta TINYINT NOT NULL,
    CONSTRAINT inventario_pk PRIMARY KEY (id_inventario),
    CONSTRAINT inventario_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT inventario_estado_fk FOREIGN KEY (id_estado) REFERENCES estado (id_estado)
);

CREATE TABLE IF NOT EXISTS mision (
    id_mision INT AUTO_INCREMENT,
    id_jugador INT NULL,
    descripcion VARCHAR(250) NOT NULL,
    duracion INT NOT NULL,
    inicio TIMESTAMP NULL,
    fin TIMESTAMP NULL,
    recompensa INT NOT NULL,
    completada TINYINT NOT NULL,
    CONSTRAINT mision_pk PRIMARY KEY (id_mision),
    CONSTRAINT mision_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS estadisticas (
    id_estadisticas INT AUTO_INCREMENT,
    id_jugador INT NULL,
    puntos_combate INT NOT NULL,
    victorias INT NOT NULL,
    derrotas INT NOT NULL,
    ataque_total INT NOT NULL,
    defensa_total INT NOT NULL,
    misiones INT NOT NULL,
    recaudacion INT NOT NULL,
    CONSTRAINT estadisticas_pk PRIMARY KEY (id_estadisticas),
    CONSTRAINT estadisticas_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS registro_combate (
    id_combate INT AUTO_INCREMENT,
    id_jugador INT NULL,
    id_contrario INT NULL,
    nivel_jugador INT NOT NULL,
    nivel_contrario INT NOT NULL,
    vida_jugador INT NOT NULL,
    vida_contrario INT NOT NULL,
    ataque_total_jugador INT NOT NULL,
    ataque_total_contrario INT NOT NULL,
    defensa_total_jugador INT NOT NULL,
    defensa_total_contrario INT NOT NULL,
    puntos_jugador INT NOT NULL,
    puntos_contrario INT NOT NULL,
    CONSTRAINT combate_pk PRIMARY KEY (id_combate),
    CONSTRAINT combate_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT combate_contrario_fk FOREIGN KEY (id_contrario) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS registro_bazar (
    id_bazar INT AUTO_INCREMENT,
    id_comprador INT NULL,
    id_vendedor INT NULL,
    tipo_equipo VARCHAR(15) NOT NULL,
    nivel INT NOT NULL,
    potenciado INT NOT NULL,
    tipo_atributo VARCHAR(15) NOT NULL,
    potenciado_atributo INT NOT NULL,
    precio INT NOT NULL,
    CONSTRAINT bazar_pk PRIMARY KEY (id_bazar),
    CONSTRAINT bazar_comprador_fk FOREIGN KEY (id_comprador) REFERENCES jugador (id_jugador),
    CONSTRAINT bazar_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES jugador (id_jugador)
);

/******************************************************************************/

INSERT INTO acceso (id_acceso, correo, clave) VALUES
(1, 'knight.fight.pi@gmail.com', '!Q2w3e4r5t6y7u8i9o0p'),
(2, 'Alberto', 'Alberto'),
(3, 'Alfonso', 'Alfonso'),
(4, 'Francisco', 'Francisco'),
(5, 'Tomas', 'Tomas'),
(6, 'Juan', 'Juan'),
(7, 'Raquel', 'Raquel'),
(8, 'Berta', 'Berta'),
(9, 'Carla', 'Carla'),
(10, 'Victoria', 'Victoria'),
(11, 'Rodrigo', 'Rodrigo');

INSERT INTO jugador (id_jugador, nombre, imagen, nivel, exp_acumulada, puntos_no_usados, oro_actual) VALUES
(2, 'Alberto', 'src/imagenes/foto02.png', 1, 0, 0, 0),
(3, 'Alfonso', 'src/imagenes/foto03.png', 1, 0, 0, 0),
(4, 'Francisco', 'src/imagenes/foto04.png', 1, 0, 0, 0),
(5, 'Tomas', 'src/imagenes/foto05.png', 1, 0, 0, 0),
(6, 'Juan', 'src/imagenes/foto06.png', 1, 0, 0, 0),
(7, 'Raquel', 'src/imagenes/foto13.png', 1, 0, 0, 0),
(8, 'Berta', 'src/imagenes/foto14.png', 1, 0, 0, 0),
(9, 'Carla', 'src/imagenes/foto15.png', 1, 0, 0, 0),
(10, 'Victoria', 'src/imagenes/foto16.png', 1, 0, 0, 0),
(11, 'Rodrigo', 'src/imagenes/foto01.png', 1, 0, 0, 0);

INSERT INTO estado (id_estado, id_jugador, tipo_atributo, potenciado) VALUES
(1, 2, 'FUERZA', 10),
(2, 2, 'ARMADURA', 10),
(3, 2, 'DESTREZA', 10),
(4, 2, 'CONSTITUCION', 10),
(5, 2, 'TIERRA', 0),
(6, 2, 'AGUA', 0),
(7, 2, 'FUEGO', 0),
(8, 2, 'VIENTO', 0),

(9, 3, 'FUERZA', 10),
(10, 3, 'ARMADURA', 10),
(11, 3, 'DESTREZA', 10),
(12, 3, 'CONSTITUCION', 10),
(13, 3, 'TIERRA', 0),
(14, 3, 'AGUA', 0),
(15, 3, 'FUEGO', 0),
(16, 3, 'VIENTO', 0),

(17, 4, 'FUERZA', 10),
(18, 4, 'ARMADURA', 10),
(19, 4, 'DESTREZA', 10),
(20, 4, 'CONSTITUCION', 10),
(21, 4, 'TIERRA', 0),
(22, 4, 'AGUA', 0),
(23, 4, 'FUEGO', 0),
(24, 4, 'VIENTO', 0),

(25, 5, 'FUERZA', 10),
(26, 5, 'ARMADURA', 10),
(27, 5, 'DESTREZA', 10),
(28, 5, 'CONSTITUCION', 10),
(29, 5, 'TIERRA', 0),
(30, 5, 'AGUA', 0),
(31, 5, 'FUEGO', 0),
(32, 5, 'VIENTO', 0),

(33, 6, 'FUERZA', 10),
(34, 6, 'ARMADURA', 10),
(35, 6, 'DESTREZA', 10),
(36, 6, 'CONSTITUCION', 10),
(37, 6, 'TIERRA', 0),
(38, 6, 'AGUA', 0),
(39, 6, 'FUEGO', 0),
(40, 6, 'VIENTO', 0),

(41, 7, 'FUERZA', 10),
(42, 7, 'ARMADURA', 10),
(43, 7, 'DESTREZA', 10),
(44, 7, 'CONSTITUCION', 10),
(45, 7, 'TIERRA', 0),
(46, 7, 'AGUA', 0),
(47, 7, 'FUEGO', 0),
(48, 7, 'VIENTO', 0),

(49, 8, 'FUERZA', 10),
(50, 8, 'ARMADURA', 10),
(51, 8, 'DESTREZA', 10),
(52, 8, 'CONSTITUCION', 10),
(53, 8, 'TIERRA', 0),
(54, 8, 'AGUA', 0),
(55, 8, 'FUEGO', 0),
(56, 8, 'VIENTO', 0),

(57, 9, 'FUERZA', 10),
(58, 9, 'ARMADURA', 10),
(59, 9, 'DESTREZA', 10),
(60, 9, 'CONSTITUCION', 10),
(61, 9, 'TIERRA', 0),
(62, 9, 'AGUA', 0),
(63, 9, 'FUEGO', 0),
(64, 9, 'VIENTO', 0),

(65, 10, 'FUERZA', 10),
(66, 10, 'ARMADURA', 10),
(67, 10, 'DESTREZA', 10),
(68, 10, 'CONSTITUCION', 10),
(69, 10, 'TIERRA', 0),
(70, 10, 'AGUA', 0),
(71, 10, 'FUEGO', 0),
(72, 10, 'VIENTO', 0),

(73, 11, 'FUERZA', 10),
(74, 11, 'ARMADURA', 10),
(75, 11, 'DESTREZA', 10),
(76, 11, 'CONSTITUCION', 10),
(77, 11, 'TIERRA', 0),
(78, 11, 'AGUA', 0),
(79, 11, 'FUEGO', 0),
(80, 11, 'VIENTO', 0);

INSERT INTO  estadisticas (id_estadisticas, id_jugador, puntos_combate, victorias,
derrotas, ataque_total, defensa_total, misiones, recaudacion) VALUES
(1, 2, 0, 0, 0, 0, 0, 0, 0),
(2, 3, 0, 0, 0, 0, 0, 0, 0),
(3, 4, 0, 0, 0, 0, 0, 0, 0),
(4, 5, 0, 0, 0, 0, 0, 0, 0),
(5, 6, 0, 0, 0, 0, 0, 0, 0),
(6, 7, 0, 0, 0, 0, 0, 0, 0),
(7, 8, 0, 0, 0, 0, 0, 0, 0),
(8, 9, 0, 0, 0, 0, 0, 0, 0),
(9, 10, 0, 0, 0, 0, 0, 0, 0),
(10, 11, 0, 0, 0, 0, 0, 0, 0);