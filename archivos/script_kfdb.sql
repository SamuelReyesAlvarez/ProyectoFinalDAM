/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Samuel
 * Created: 17-may-2019
 */
DROP DATABASE IF EXISTS kfdb;
CREATE DATABASE kfdb;
USE kfdb;

CREATE TABLE kfdb.jugador (
    id_jugador INT NULL,
    nombre VARCHAR(15) NULL,
    nivel INT NULL,
    exp_acumulada INT NULL,
    puntos_no_usados INT NULL,
    oro_actual INT NULL,
    CONSTRAINT jugador_pk PRIMARY KEY (id_jugador)
);

CREATE TABLE IF NOT EXISTS equipo (
    id_equipo INT NULL,
    tipo_equipo VARCHAR(15) NULL,
    nivel INT NULL,
    CONSTRAINT equipo_pk PRIMARY KEY (id_equipo)
);

CREATE TABLE IF NOT EXISTS inventario (
    id_jugador INT NULL,
    id_equipo INT NULL,
    potenciado INT NULL,
    precio INT NULL,
    equipado TINYINT NULL,
    en_venta TINYINT NULL,
    CONSTRAINT inventario_pk PRIMARY KEY (id_jugador, id_equipo),
    CONSTRAINT inventario_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT inventario_equipo_fk FOREIGN KEY (id_equipo) REFERENCES equipo (id_equipo)
);

CREATE TABLE IF NOT EXISTS atributo (
    id_atributo INT NULL,
    tipo_atributo VARCHAR(15) NULL,
    CONSTRAINT atributo_pk PRIMARY KEY (id_atributo)
);

CREATE TABLE IF NOT EXISTS estado (
    id_jugador INT NULL,
    id_atributo INT NULL,
    potenciado INT NULL,
    CONSTRAINT estado_pk PRIMARY KEY (id_jugador, id_atributo),
    CONSTRAINT estado_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT estado_atributo_fk FOREIGN KEY (id_atributo) REFERENCES atributo (id_atributo)
);

CREATE TABLE IF NOT EXISTS tarea (
    id_tarea INT NULL,
    descripcion VARCHAR(250) NULL,
    CONSTRAINT tarea_pk PRIMARY KEY (id_tarea)
);

CREATE TABLE IF NOT EXISTS mision (
    id_jugador INT NULL,
    id_tarea INT NULL,
    inicio DATE NULL,
    fin DATE NULL,
    recompensa INT NULL,
    completada TINYINT NULL,
    CONSTRAINT mision_pk PRIMARY KEY (id_jugador, id_tarea),
    CONSTRAINT mision_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT mision_tarea_fk FOREIGN KEY (id_tarea) REFERENCES tarea (id_tarea)
);

CREATE TABLE IF NOT EXISTS estadisticas (
    id_estadisticas INT NULL,
    id_jugador INT NULL,
    victorias INT NULL,
    derrotas INT NULL,
    ataque_total INT NULL,
    defensa_total INT NULL,
    misiones INT NULL,
    recaudacion INT NULL,
    CONSTRAINT estadisticas_pk PRIMARY KEY (id_estadisticas),
    CONSTRAINT estadisticas_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS acceso (
    correo VARCHAR(150) NULL,
    clave VARCHAR(150) NULL,
    id_jugador INT NULL,
    CONSTRAINT acceso_pk PRIMARY KEY (correo),
    CONSTRAINT acceso_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);