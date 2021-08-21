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
  `DireccionEmail` varchar(20) NOT NULL,
  `tipoContacto` int(20) NOT NULL,
  `fechaCumple` DATE NOT NULL,
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
