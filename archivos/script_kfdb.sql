/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Samuel Reyes
 * Created: 17-may-2019
 */
DROP DATABASE IF EXISTS kfdb;
CREATE DATABASE kfdb;
USE kfdb;

CREATE TABLE IF NOT EXISTS jugador (
    id_jugador INT NOT NULL,
    nombre VARCHAR(15) NULL,
    nivel INT NULL,
    exp_acumulada INT NULL,
    puntos_no_usados INT NULL,
    oro_actual INT NULL,
    CONSTRAINT jugador_pk PRIMARY KEY (id_jugador)
);

CREATE TABLE IF NOT EXISTS equipo (
    id_equipo INT NOT NULL,
    tipo_equipo VARCHAR(15) NULL,
    nivel INT NULL,
    CONSTRAINT equipo_pk PRIMARY KEY (id_equipo)
);

CREATE TABLE IF NOT EXISTS inventario (
    id_inventario INT NOT NULL,
    id_jugador INT NULL,
    id_equipo INT NULL,
    potenciado INT NULL,
    precio INT NULL,
    equipado TINYINT NULL,
    en_venta TINYINT NULL,
    CONSTRAINT inventario_pk PRIMARY KEY (id_inventario),
    CONSTRAINT inventario_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT inventario_equipo_fk FOREIGN KEY (id_equipo) REFERENCES equipo (id_equipo)
);

CREATE TABLE IF NOT EXISTS atributo (
    id_atributo INT NOT NULL,
    tipo_atributo VARCHAR(15) NULL,
    CONSTRAINT atributo_pk PRIMARY KEY (id_atributo)
);

CREATE TABLE IF NOT EXISTS estado (
    id_estado INT NOT NULL,
    id_jugador INT NULL,
    id_atributo INT NULL,
    potenciado INT NULL,
    CONSTRAINT estado_pk PRIMARY KEY (id_estado),
    CONSTRAINT estado_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT estado_atributo_fk FOREIGN KEY (id_atributo) REFERENCES atributo (id_atributo)
);

CREATE TABLE IF NOT EXISTS tarea (
    id_tarea INT NOT NULL,
    descripcion VARCHAR(250) NULL,
    CONSTRAINT tarea_pk PRIMARY KEY (id_tarea)
);

CREATE TABLE IF NOT EXISTS mision (
    id_mision INT NOT NULL,
    id_jugador INT NULL,
    id_tarea INT NULL,
    inicio DATE NULL,
    fin DATE NULL,
    recompensa INT NULL,
    completada TINYINT NULL,
    CONSTRAINT mision_pk PRIMARY KEY (id_mision),
    CONSTRAINT mision_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT mision_tarea_fk FOREIGN KEY (id_tarea) REFERENCES tarea (id_tarea)
);

CREATE TABLE IF NOT EXISTS estadisticas (
    id_estadisticas INT NOT NULL,
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
    correo VARCHAR(150) NOT NULL,
    clave VARCHAR(150) NULL,
    id_jugador INT NULL,
    CONSTRAINT acceso_pk PRIMARY KEY (correo),
    CONSTRAINT acceso_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS registro_combate (
    id_combate INT NOT NULL,
    id_jugador INT NULL,
    id_contrario INT NULL,
    fecha DATE NULL,
    resultado INT NULL,
    CONSTRAINT combate_pk PRIMARY KEY (id_combate),
    CONSTRAINT combate_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT combate_contrario_fk FOREIGN KEY (id_contrario) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS registro_bazar (
    id_bazar INT NOT NULL,
    id_comprador INT NULL,
    id_vendedor INT NULL,
    id_equipo INT NULL,
    potenciado INT NULL,
    precio INT NULL,
    fecha DATE NULL,
    CONSTRAINT bazar_pk PRIMARY KEY (id_bazar),
    CONSTRAINT bazar_comprador_fk FOREIGN KEY (id_comprador) REFERENCES jugador (id_jugador),
    CONSTRAINT bazar_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES jugador (id_jugador)
);