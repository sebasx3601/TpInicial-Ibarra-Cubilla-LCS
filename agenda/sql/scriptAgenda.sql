CREATE DATABASE `agenda`;
USE agenda;
CREATE TABLE `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Calle` varchar(20) NOT NULL,
  `Altura` varchar(20) NOT NULL,
  `Piso` varchar(20) NOT NULL,
  `Depto` varchar(20) NOT NULL,
  `Localidad` int(11) NOT NULL,
  `DireccionEmail` varchar(100) NOT NULL,
  `tipoContacto` int(20) NOT NULL,
  `fechaCumple` varchar(20) NOT NULL,
  PRIMARY KEY (`idPersona`)
);
CREATE TABLE `tipo_contacto`
(
	`IdContacto` int(11) NOT NULL AUTO_INCREMENT,
	`NombreContacto` varchar(20) NOT NULL,
	PRIMARY KEY (`IdContacto`)
);
insert into tipo_contacto values(1,'trabajo');
insert into tipo_contacto values(2,'familia');
insert into tipo_contacto values(3,'amigos');

CREATE TABLE `pais`
(
	`IdPais` int(11) NOT NULL AUTO_INCREMENT,
	`NombrePais` varchar(20) NOT NULL,
	PRIMARY KEY (`IdPais`)
);
CREATE TABLE `provincia`
(
	`IdProvincia` int(11) NOT NULL AUTO_INCREMENT,
	`NombreProvincia` varchar(20) NOT NULL,
	`IdPais` varchar(20) NOT NULL,
	PRIMARY KEY (`IdProvincia`)
);
CREATE TABLE `localidad`
(
	`IdLocalidad` int(11) NOT NULL AUTO_INCREMENT,
	`NombreLocalidad` varchar(20) NOT NULL,
	`IdProvincia` varchar(20) NOT NULL,
	PRIMARY KEY (`IdLocalidad`)
);
