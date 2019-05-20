CREATE DATABASE IF NOT EXISTS kfdb;

USE kfdb;

CREATE TABLE jugador (
	id_jugador INT PRIMARY KEY,
	nivel INT NULL,
	exp_acumulada INT NULL,
	exp_nivel INT NULL,
	puntos_no_usados INT NULL,
	oro_actual INT NULL
);

CREATE TABLE tipo_equipo (
	id_tipo_equipo INT PRIMARY KEY,
	tipo_equipo VARCHAR(15)
);

CREATE TABLE equipo (
	id_equipo INT PRIMARY KEY,
	id_tipo_equipo INT,
	nivel INT,
	FOREIGN KEY (id_tipo_equipo) REFERENCES tipo_equipo (id_tipo_equipo)
);

CREATE TABLE inventario (
	id_jugador INT PRIMARY KEY,
	id_equipo INT PRIMARY KEY,
	potenciado INT,
	precio INT,
	equipado TINYINT,
	en_venta TINYINT,
	FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
	FOREIGN KEY (id_equipo) REFERENCES equipo (id_equipo)
);

CREATE TABLE tipo_atributo (
	id_tipo_atributo INT PRIMARY KEY,
	tipo_atributo VARCHAR(15)
);

CREATE TABLE atributo (
	id_jugador INT PRIMARY KEY,
	id_tipo_atributo INT PRIMARY KEY,
	potenciado INT,
	FOREIGN KEY (id_jugador) REFERENCES jugador (id_jugador),
	FOREIGN KEY (id_tipo_atributo) REFERENCES tipo_atributo (id_tipo_atributo)
);

CREATE TABLE estadisticas (
	id_estadisticas INT PRIMARY KEY,
	id_jugador INT,
	victorias INT,
	derrotas INT,
	ataque_total INT,
	defensa_total INT,
	misiones INT,
	recaudacion INT,
	FOREIGN KEY id_jugador REFERENCES jugador (id_jugador)
);
