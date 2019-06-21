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
    puntos_combate INT NOT NULL,
    recaudacion INT NOT NULL,
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
    victorias INT NOT NULL,
    derrotas INT NOT NULL,
    ataque_total INT NOT NULL,
    defensa_total INT NOT NULL,
    misiones INT NOT NULL,
    CONSTRAINT estadisticas_pk PRIMARY KEY (id_estadisticas)
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
(1, 'antar@mail.es', 'Antar'),
(2, 'brais@mail.es', 'Brais'),
(3, 'cibran@mail.es', 'Cibran'),
(4, 'dagamundo@mail.es', 'Dagamundo'),
(5, 'eraclio@mail.es', 'Eraclio'),
(6, 'fabiano@mail.es', 'Fabiano'),
(7, 'gumersindo@mail.es', 'Gumersindo'),
(8, 'hermogio@mail.es', 'Hermogio'),
(9, 'ilidio@mail.es', 'Ilidio'),
(10, 'juvenciolo@mail.es', 'Juvenciolo'),
(11, 'koldo@mail.es', 'Koldo'),
(12, 'lupercio@mail.es', 'Lupercio'),
(13, 'mirta@mail.es', 'Mirta'),
(14, 'nirvana@mail.es', 'Nirvana'),
(15, 'ovidia@mail.es', 'Ovidia'),
(16, 'porfiria@mail.es', 'Porfiria'),
(17, 'quirina@mail.es', 'Quirina'),
(18, 'ruperta@mail.es', 'Ruperta'),
(19, 'sonsoles@mail.es', 'Sonsoles'),
(20, 'tula@mail,es', 'Tula'),
(21, 'urcisia@mail.es', 'Urcisia'),
(22, 'venus@mail.es', 'Venus'),
(23, 'wenda@mail.es', 'Wenda'),
(24, 'xenia@mail.es', 'Xenia');

INSERT INTO jugador (id_jugador, nombre, imagen, nivel, exp_acumulada,
    puntos_no_usados, oro_actual, puntos_combate, recaudacion) VALUES
(1, 'Antar', 'src/imagenes/foto01.png', 1, 0, 0, 0, 0, 0),
(2, 'Brais', 'src/imagenes/foto02.png', 1, 0, 0, 0, 0, 0),
(3, 'Cibran', 'src/imagenes/foto03.png', 1, 0, 0, 0, 0, 0),
(4, 'Dagamundo', 'src/imagenes/foto04.png', 1, 0, 0, 0, 0, 0),
(5, 'Eraclio', 'src/imagenes/foto05.png', 1, 0, 0, 0, 0, 0),
(6, 'Fabiano', 'src/imagenes/foto06.png', 1, 0, 0, 0, 0, 0),
(7, 'Gumersindo', 'src/imagenes/foto07.png', 1, 0, 0, 0, 0, 0),
(8, 'Hermogio', 'src/imagenes/foto08.png', 1, 0, 0, 0, 0, 0),
(9, 'Ilidio', 'src/imagenes/foto09.png', 1, 0, 0, 0, 0, 0),
(10, 'Juvenciolo', 'src/imagenes/foto10.png', 1, 0, 0, 0, 0, 0),
(11, 'Koldo', 'src/imagenes/foto11.png', 1, 0, 0, 0, 0, 0),
(12, 'Lupercio', 'src/imagenes/foto12.png', 1, 0, 0, 0, 0, 0),
(13, 'Mirta', 'src/imagenes/foto13.png', 1, 0, 0, 0, 0, 0),
(14, 'Nirvana', 'src/imagenes/foto14.png', 1, 0, 0, 0, 0, 0),
(15, 'Ovidia', 'src/imagenes/foto15.png', 1, 0, 0, 0, 0, 0),
(16, 'Porfiria', 'src/imagenes/foto16.png', 1, 0, 0, 0, 0, 0),
(17, 'Quirina', 'src/imagenes/foto17.png', 1, 0, 0, 0, 0, 0),
(18, 'Ruperta', 'src/imagenes/foto18.png', 1, 0, 0, 0, 0, 0),
(19, 'Sonsoles', 'src/imagenes/foto19.png', 1, 0, 0, 0, 0, 0),
(20, 'Tula', 'src/imagenes/foto20.png', 1, 0, 0, 0, 0, 0),
(21, 'Urcisia', 'src/imagenes/foto21.png', 1, 0, 0, 0, 0, 0),
(22, 'Venus', 'src/imagenes/foto22.png', 1, 0, 0, 0, 0, 0),
(23, 'Wenda', 'src/imagenes/foto23.png', 1, 0, 0, 0, 0, 0),
(24, 'Xenia', 'src/imagenes/foto24.png', 1, 0, 0, 0, 0, 0);

INSERT INTO estado (id_estado, id_jugador, tipo_atributo, potenciado) VALUES
(1, 1, 'FUERZA', 20),
(2, 1, 'ARMAD', 0),
(3, 1, 'DESTR', 0),
(4, 1, 'CONST', 0),
(5, 1, 'TIERRA', 0),
(6, 1, 'AGUA', 0),
(7, 1, 'FUEGO', 0),
(8, 1, 'VIENTO', 0),

(9, 2, 'FUERZA', 7),
(10, 2, 'ARMAD', 7),
(11, 2, 'DESTR', 6),
(12, 2, 'CONST', 0),
(13, 2, 'TIERRA', 0),
(14, 2, 'AGUA', 0),
(15, 2, 'FUEGO', 0),
(16, 2, 'VIENTO', 0),

(17, 3, 'FUERZA', 5),
(18, 3, 'ARMAD', 5),
(19, 3, 'DESTR', 0),
(20, 3, 'CONST', 10),
(21, 3, 'TIERRA', 0),
(22, 3, 'AGUA', 0),
(23, 3, 'FUEGO', 0),
(24, 3, 'VIENTO', 0),

(25, 4, 'FUERZA', 5),
(26, 4, 'ARMAD', 0),
(27, 4, 'DESTR', 5),
(28, 4, 'CONST', 10),
(29, 4, 'TIERRA', 0),
(30, 4, 'AGUA', 0),
(31, 4, 'FUEGO', 0),
(32, 4, 'VIENTO', 0),

(33, 5, 'FUERZA', 0),
(34, 5, 'ARMAD', 5),
(35, 5, 'DESTR', 5),
(36, 5, 'CONST', 10),
(37, 5, 'TIERRA', 0),
(38, 5, 'AGUA', 0),
(39, 5, 'FUEGO', 0),
(40, 5, 'VIENTO', 0),

(41, 6, 'FUERZA', 0),
(42, 6, 'ARMAD', 5),
(43, 6, 'DESTR', 10),
(44, 6, 'CONST', 5),
(45, 6, 'TIERRA', 0),
(46, 6, 'AGUA', 0),
(47, 6, 'FUEGO', 0),
(48, 6, 'VIENTO', 0),

(49, 7, 'FUERZA', 5),
(50, 7, 'ARMAD', 0),
(51, 7, 'DESTR', 10),
(52, 7, 'CONST', 5),
(53, 7, 'TIERRA', 0),
(54, 7, 'AGUA', 0),
(55, 7, 'FUEGO', 0),
(56, 7, 'VIENTO', 0),

(57, 8, 'FUERZA', 5),
(58, 8, 'ARMAD', 5),
(59, 8, 'DESTR', 10),
(60, 8, 'CONST', 0),
(61, 8, 'TIERRA', 0),
(62, 8, 'AGUA', 0),
(63, 8, 'FUEGO', 0),
(64, 8, 'VIENTO', 0),

(65, 9, 'FUERZA', 5),
(66, 9, 'ARMAD', 10),
(67, 9, 'DESTR', 0),
(68, 9, 'CONST', 5),
(69, 9, 'TIERRA', 0),
(70, 9, 'AGUA', 0),
(71, 9, 'FUEGO', 0),
(72, 9, 'VIENTO', 0),

(73, 10, 'FUERZA', 5),
(74, 10, 'ARMAD', 10),
(75, 10, 'DESTR', 5),
(76, 10, 'CONST', 0),
(77, 10, 'TIERRA', 0),
(78, 10, 'AGUA', 0),
(79, 10, 'FUEGO', 0),
(80, 10, 'VIENTO', 0),

(81, 11, 'FUERZA', 10),
(82, 11, 'ARMAD', 0),
(83, 11, 'DESTR', 5),
(84, 11, 'CONST', 5),
(85, 11, 'TIERRA', 0),
(86, 11, 'AGUA', 0),
(87, 11, 'FUEGO', 0),
(88, 11, 'VIENTO', 0),

(89, 12, 'FUERZA', 10),
(90, 12, 'ARMAD', 5),
(91, 12, 'DESTR', 0),
(92, 12, 'CONST', 5),
(93, 12, 'TIERRA', 0),
(94, 12, 'AGUA', 0),
(95, 12, 'FUEGO', 0),
(96, 12, 'VIENTO', 0),

(97, 13, 'FUERZA', 10),
(98, 13, 'ARMAD', 5),
(99, 13, 'DESTR', 5),
(100, 13, 'CONST', 0),
(101, 13, 'TIERRA', 0),
(102, 13, 'AGUA', 0),
(103, 13, 'FUEGO', 0),
(104, 13, 'VIENTO', 0),

(105, 14, 'FUERZA', 9),
(106, 14, 'ARMAD', 1),
(107, 14, 'DESTR', 1),
(108, 14, 'CONST', 9),
(109, 14, 'TIERRA', 0),
(110, 14, 'AGUA', 0),
(111, 14, 'FUEGO', 0),
(112, 14, 'VIENTO', 0),

(113, 15, 'FUERZA', 8),
(114, 15, 'ARMAD', 2),
(115, 15, 'DESTR', 8),
(116, 15, 'CONST', 2),
(117, 15, 'TIERRA', 0),
(118, 15, 'AGUA', 0),
(119, 15, 'FUEGO', 0),
(120, 15, 'VIENTO', 0),

(121, 16, 'FUERZA', 3),
(122, 16, 'ARMAD', 3),
(123, 16, 'DESTR', 7),
(124, 16, 'CONST', 7),
(125, 16, 'TIERRA', 0),
(126, 16, 'AGUA', 0),
(127, 16, 'FUEGO', 0),
(128, 16, 'VIENTO', 0),

(129, 17, 'FUERZA', 6),
(130, 17, 'ARMAD', 6),
(131, 17, 'DESTR', 4),
(132, 17, 'CONST', 4),
(133, 17, 'TIERRA', 0),
(134, 17, 'AGUA', 0),
(135, 17, 'FUEGO', 0),
(136, 17, 'VIENTO', 0),

(137, 18, 'FUERZA', 5),
(138, 18, 'ARMAD', 5),
(139, 18, 'DESTR', 5),
(140, 18, 'CONST', 5),
(141, 18, 'TIERRA', 0),
(142, 18, 'AGUA', 0),
(143, 18, 'FUEGO', 0),
(144, 18, 'VIENTO', 0),

(145, 19, 'FUERZA', 10),
(146, 19, 'ARMAD', 0),
(147, 19, 'DESTR', 0),
(148, 19, 'CONST', 10),
(149, 19, 'TIERRA', 0),
(150, 19, 'AGUA', 0),
(151, 19, 'FUEGO', 0),
(152, 19, 'VIENTO', 0),

(153, 20, 'FUERZA', 0),
(154, 20, 'ARMAD', 10),
(155, 20, 'DESTR', 10),
(156, 20, 'CONST', 0),
(157, 20, 'TIERRA', 0),
(158, 20, 'AGUA', 0),
(159, 20, 'FUEGO', 0),
(160, 20, 'VIENTO', 0),

(161, 21, 'FUERZA', 10),
(162, 21, 'ARMAD', 0),
(163, 21, 'DESTR', 10),
(164, 21, 'CONST', 0),
(165, 21, 'TIERRA', 0),
(166, 21, 'AGUA', 0),
(167, 21, 'FUEGO', 0),
(168, 21, 'VIENTO', 0),

(169, 22, 'FUERZA', 0),
(170, 22, 'ARMAD', 10),
(171, 22, 'DESTR', 0),
(172, 22, 'CONST', 10),
(173, 22, 'TIERRA', 0),
(174, 22, 'AGUA', 0),
(175, 22, 'FUEGO', 0),
(176, 22, 'VIENTO', 0),

(177, 23, 'FUERZA', 10),
(178, 23, 'ARMAD', 10),
(179, 23, 'DESTR', 0),
(180, 23, 'CONST', 0),
(181, 23, 'TIERRA', 0),
(182, 23, 'AGUA', 0),
(183, 23, 'FUEGO', 0),
(184, 23, 'VIENTO', 0),

(185, 24, 'FUERZA', 0),
(186, 24, 'ARMAD', 0),
(187, 24, 'DESTR', 10),
(188, 24, 'CONST', 10),
(189, 24, 'TIERRA', 0),
(190, 24, 'AGUA', 0),
(191, 24, 'FUEGO', 0),
(192, 24, 'VIENTO', 0);

INSERT INTO  estadisticas (id_estadisticas, victorias, derrotas, ataque_total,
defensa_total, misiones) VALUES
(1, 0, 0, 0, 0, 0),
(2, 0, 0, 0, 0, 0),
(3, 0, 0, 0, 0, 0),
(4, 0, 0, 0, 0, 0),
(5, 0, 0, 0, 0, 0),
(6, 0, 0, 0, 0, 0),
(7, 0, 0, 0, 0, 0),
(8, 0, 0, 0, 0, 0),
(9, 0, 0, 0, 0, 0),
(10, 0, 0, 0, 0, 0),
(11, 0, 0, 0, 0, 0),
(12, 0, 0, 0, 0, 0),
(13, 0, 0, 0, 0, 0),
(14, 0, 0, 0, 0, 0),
(15, 0, 0, 0, 0, 0),
(16, 0, 0, 0, 0, 0),
(17, 0, 0, 0, 0, 0),
(18, 0, 0, 0, 0, 0),
(19, 0, 0, 0, 0, 0),
(20, 0, 0, 0, 0, 0),
(21, 0, 0, 0, 0, 0),
(22, 0, 0, 0, 0, 0),
(23, 0, 0, 0, 0, 0),
(24, 0, 0, 0, 0, 0);
