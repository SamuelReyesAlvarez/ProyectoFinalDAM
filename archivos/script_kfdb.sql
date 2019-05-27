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
    nombre VARCHAR(15) NOT NULL,
    nivel INT NOT NULL,
    exp_acumulada INT NOT NULL,
    puntos_no_usados INT NOT NULL,
    oro_actual INT NOT NULL,
    CONSTRAINT jugador_pk PRIMARY KEY (id_jugador)
);

CREATE TABLE IF NOT EXISTS equipo (
    id_equipo INT NOT NULL,
    tipo_equipo VARCHAR(15) NOT NULL,
    nivel INT NOT NULL,
    CONSTRAINT equipo_pk PRIMARY KEY (id_equipo)
);

CREATE TABLE IF NOT EXISTS inventario (
    id_inventario INT NOT NULL,
    id_jugador INT NULL,
    id_equipo INT NULL,
    potenciado INT NOT NULL,
    precio INT NOT NULL,
    equipado TINYINT NOT NULL,
    en_venta TINYINT NOT NULL,
    CONSTRAINT inventario_pk PRIMARY KEY (id_inventario),
    CONSTRAINT inventario_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT inventario_equipo_fk FOREIGN KEY (id_equipo) REFERENCES equipo (id_equipo)
);


CREATE TABLE IF NOT EXISTS estado (
    id_estado INT NOT NULL,
    id_jugador INT NULL,
    tipo_atributo VARCHAR(15) NOT NULL,
    potenciado INT NOT NULL,
    CONSTRAINT estado_pk PRIMARY KEY (id_estado),
    CONSTRAINT estado_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS mision (
    id_mision INT NOT NULL,
    id_jugador INT NULL,
    descripcion VARCHAR(250) NOT NULL,
    inicio DATE NOT NULL,
    fin DATE NOT NULL,
    recompensa INT NOT NULL,
    completada TINYINT NOT NULL,
    CONSTRAINT mision_pk PRIMARY KEY (id_mision),
    CONSTRAINT mision_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS estadisticas (
    id_estadisticas INT NOT NULL,
    id_jugador INT NULL,
    victorias INT NOT NULL,
    derrotas INT NOT NULL,
    ataque_total INT NOT NULL,
    defensa_total INT NOT NULL,
    misiones INT NOT NULL,
    recaudacion INT NOT NULL,
    CONSTRAINT estadisticas_pk PRIMARY KEY (id_estadisticas),
    CONSTRAINT estadisticas_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS acceso (
    correo VARCHAR(150) NOT NULL,
    clave VARCHAR(150) NOT NULL,
    id_jugador INT NULL,
    CONSTRAINT acceso_pk PRIMARY KEY (correo),
    CONSTRAINT acceso_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS registro_combate (
    id_combate INT NOT NULL,
    id_jugador INT NULL,
    id_contrario INT NULL,
    fecha DATE NOT NULL,
    resultado TINYINT NOT NULL,
    CONSTRAINT combate_pk PRIMARY KEY (id_combate),
    CONSTRAINT combate_jugador_fk FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
    CONSTRAINT combate_contrario_fk FOREIGN KEY (id_contrario) REFERENCES jugador (id_jugador)
);

CREATE TABLE IF NOT EXISTS registro_bazar (
    id_bazar INT NOT NULL,
    id_comprador INT NULL,
    id_vendedor INT NULL,
    id_equipo INT NULL,
    potenciado INT NOT NULL,
    precio INT NOT NULL,
    fecha DATE NOT NULL,
    CONSTRAINT bazar_pk PRIMARY KEY (id_bazar),
    CONSTRAINT bazar_comprador_fk FOREIGN KEY (id_comprador) REFERENCES jugador (id_jugador),
    CONSTRAINT bazar_vendedor_fk FOREIGN KEY (id_vendedor) REFERENCES jugador (id_jugador)
);
