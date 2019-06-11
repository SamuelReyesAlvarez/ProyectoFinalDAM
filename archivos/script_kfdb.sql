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
    id_acceso INT NOT NULL AUTO_INCREMENT,
    correo VARCHAR(150) NOT NULL,
    clave VARCHAR(150) NOT NULL,
    CONSTRAINT acceso_pk PRIMARY KEY (id_acceso)
);

CREATE TABLE IF NOT EXISTS jugador (
    id_jugador INT NOT NULL,
    nombre VARCHAR(15) NOT NULL,
    imagen VARCHAR(250) NOT NULL,
    nivel INT NOT NULL,
    exp_acumulada INT NOT NULL,
    puntos_no_usados INT NOT NULL,
    oro_actual INT NOT NULL,
    CONSTRAINT jugador_pk PRIMARY KEY (id_jugador)
);


CREATE TABLE IF NOT EXISTS estado (
    id_estado INT NOT NULL,
    id_jugador INT NULL,
    tipo_atributo VARCHAR(15) NOT NULL,
    potenciado INT NOT NULL,
    CONSTRAINT estado_pk PRIMARY KEY (id_estado),
    CONSTRAINT estado_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS inventario (
    id_inventario INT NOT NULL,
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
    id_mision INT NOT NULL,
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
    id_estadisticas INT NOT NULL,
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
    id_combate INT NOT NULL,
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
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT combate_pk PRIMARY KEY (id_combate),
    CONSTRAINT combate_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT combate_contrario_fk FOREIGN KEY (id_contrario) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS registro_bazar (
    id_bazar INT NOT NULL,
    id_comprador INT NULL,
    id_vendedor INT NULL,
    tipo_equipo VARCHAR(15) NOT NULL,
    nivel INT NOT NULL,
    potenciado INT NOT NULL,
    tipo_atributo VARCHAR(15) NOT NULL,
    potenciado_atributo INT NOT NULL,
    precio INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT bazar_pk PRIMARY KEY (id_bazar),
    CONSTRAINT bazar_comprador_fk FOREIGN KEY (id_comprador) REFERENCES jugador (id_jugador),
    CONSTRAINT bazar_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES jugador (id_jugador)
);

/******************************************************************************/

INSERT INTO acceso (id_acceso, correo, clave) VALUES
(0, 'knight.fight.pi@gmail.com', '!Q2w3e4r5t6y7u8i9o0p'),
(1, 'Rodrigo', 'Rodrigo'),
(2, 'Alberto', 'Alberto'),
(3, 'Alfonso', 'Alfonso'),
(4, 'Francisco', 'Francisco'),
(5, 'Tomas', 'Tomas'),
(6, 'Juan', 'Juan'),
(7, 'Raul', 'Raul'),
(8, 'Bernardo', 'Bernardo'),
(9, 'Carlos', 'Carlos'),
(10, 'Victor', 'Victor');

INSERT INTO jugador (id_jugador, nombre, imagen, nivel, exp_acumulada, puntos_no_usados, oro_actual) VALUES
(1, 'Rodrigo', 'src/imagenes/foto01.png', 1, 0, 0, 0),
(2, 'Alberto', 'src/imagenes/foto02.png', 1, 0, 0, 0),
(3, 'Alfonso', 'src/imagenes/foto03.png', 1, 0, 0, 0),
(4, 'Francisco', 'src/imagenes/foto04.png', 1, 0, 0, 0),
(5, 'Tomas', 'src/imagenes/foto05.png', 1, 0, 0, 0),
(6, 'Juan', 'src/imagenes/foto06.png', 1, 0, 0, 0),
(7, 'Raul', 'src/imagenes/foto13.png', 1, 0, 0, 0),
(8, 'Bernardo', 'src/imagenes/foto14.png', 1, 0, 0, 0),
(9, 'Carlos', 'src/imagenes/foto15.png', 1, 0, 0, 0),
(10, 'Victor', 'src/imagenes/foto16.png', 1, 0, 0, 0);

INSERT INTO estado (id_estado, id_jugador, tipo_atributo, potenciado) VALUES
(1, 1, 'FUERZA', 10),
(2, 1, 'ARMADURA', 10),
(3, 1, 'DESTREZA', 10),
(4, 1, 'CONSTITUCION', 10),
(5, 2, 'FUERZA', 10),
(6, 2, 'ARMADURA', 10),
(7, 2, 'DESTREZA', 10),
(8, 2, 'CONSTITUCION', 10),
(9, 3, 'FUERZA', 10),
(10, 3, 'ARMADURA', 10),
(11, 3, 'DESTREZA', 10),
(12, 3, 'CONSTITUCION', 10),
(13, 4, 'FUERZA', 10),
(14, 4, 'ARMADURA', 10),
(15, 4, 'DESTREZA', 10),
(16, 4, 'CONSTITUCION', 10),
(17, 5, 'FUERZA', 10),
(18, 5, 'ARMADURA', 10),
(19, 5, 'DESTREZA', 10),
(20, 5, 'CONSTITUCION', 10),
(21, 6, 'FUERZA', 10),
(22, 6, 'ARMADURA', 10),
(23, 6, 'DESTREZA', 10),
(24, 6, 'CONSTITUCION', 10),
(25, 7, 'FUERZA', 10),
(26, 7, 'ARMADURA', 10),
(27, 7, 'DESTREZA', 10),
(28, 7, 'CONSTITUCION', 10),
(29, 8, 'FUERZA', 10),
(30, 8, 'ARMADURA', 10),
(31, 8, 'DESTREZA', 10),
(32, 8, 'CONSTITUCION', 10),
(33, 9, 'FUERZA', 10),
(34, 9, 'ARMADURA', 10),
(35, 9, 'DESTREZA', 10),
(36, 9, 'CONSTITUCION', 10),
(37, 10, 'FUERZA', 10),
(38, 10, 'ARMADURA', 10),
(39, 10, 'DESTREZA', 10),
(40, 10, 'CONSTITUCION', 10);
