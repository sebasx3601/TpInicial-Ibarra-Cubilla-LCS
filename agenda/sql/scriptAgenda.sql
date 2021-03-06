drop database if exists `agenda`;
CREATE DATABASE `agenda`;
USE agenda;
CREATE TABLE `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Calle` varchar(30) NOT NULL,
  `Altura` varchar(20) NOT NULL,
  `Piso` varchar(20) NOT NULL,
  `Depto` varchar(30) NOT NULL,
  `Localidad` int(11) NOT NULL,
  `DireccionEmail` varchar(100) NOT NULL,
  `tipoContacto` int(20) NOT NULL,
  `IdGenero` int(20) NOT NULL,
  `fechaCumple` DATE NOT NULL,
  PRIMARY KEY (`idPersona`)
);
CREATE TABLE `tipo_contacto`
(
	`IdContacto` int(11) NOT NULL AUTO_INCREMENT,
	`NombreContacto` varchar(30) NOT NULL,
	PRIMARY KEY (`IdContacto`)
);
insert into tipo_contacto values(1,'Sin especificar');
insert into tipo_contacto values(2,'trabajo');
insert into tipo_contacto values(3,'familia');
insert into tipo_contacto values(4,'amigos');
insert into tipo_contacto values(5,'Compañeros de tp');
insert into tipo_contacto values(6,'Ayudantes');

CREATE TABLE `pais`
(
	`IdPais` int(11) NOT NULL AUTO_INCREMENT,
	`NombrePais` varchar(30) NOT NULL,
	PRIMARY KEY (`IdPais`)
);
insert into pais values(1,'Sin especificar');
insert into pais values(2,'argentina');
insert into pais values(3,'brasil');

CREATE TABLE `provincia`
(
	`IdProvincia` int(11) NOT NULL AUTO_INCREMENT,
	`NombreProvincia` varchar(30) NOT NULL,
	`IdPais` varchar(20) NOT NULL,
	PRIMARY KEY (`IdProvincia`)
);
insert into provincia values(1,'Sin especificar',1);
insert into provincia values(2,'Buenos Aires',2);
insert into provincia values(3,'Catamarca',2);
insert into provincia values(4,'Chaco',2);
insert into provincia values(5,'Chubut',2);
insert into provincia values(6,'Arapiraca',3);
insert into provincia values(7,'Coruripe',3);
insert into provincia values(8,'Maceió',3);

CREATE TABLE `localidad`
(
	`IdLocalidad` int(11) NOT NULL AUTO_INCREMENT,
	`NombreLocalidad` varchar(30) NOT NULL,
	`IdProvincia` varchar(11) NOT NULL,
	PRIMARY KEY (`IdLocalidad`)
);

insert into localidad values(1,'Sin especificar',1);
insert into localidad values(2,'Alejandro Petión',2);
insert into localidad values(3,'Alto Los Cardales',2);
insert into localidad values(4,'Arribeños',2);
insert into localidad values(5,'Ascensión',2);
insert into localidad values(6,'El Remanso',2);
		
insert into localidad values(7,'Aconquija',3);
insert into localidad values(8,'Alijilan',3);
insert into localidad values(9,'Ancasti',3);
insert into localidad values(10,'Andalgala',3);
insert into localidad values(11,'Antofagasta',3);
		
insert into localidad values(12,'Campo Largo',4);
insert into localidad values(13,'Charata',4);
insert into localidad values(14,'Colonia Benítez',4);
insert into localidad values(15,'Colonia Elisa',4);
insert into localidad values(16,'Colonias Unidas',4);
		
insert into localidad values(17,'Alto Río Senguer',5);
insert into localidad values(18,'Bahía Bustamante',5);
insert into localidad values(19,'Camarones',5);
insert into localidad values(20,'Cholila',5);
insert into localidad values(21,'Comodoro Rivadavia',5);


CREATE TABLE `genero` (`Id` int(11) NOT NULL AUTO_INCREMENT, `Descrip` varchar(30) NOT NULL, PRIMARY KEY (`Id`));

insert into genero values(1,'Sin especificar');
insert into genero values(2,'Masculino');
insert into genero values(3,'Femenino');
insert into genero values(4,'Otro');

		
INSERT INTO personas(idPersona, nombre, telefono, calle, altura, piso, depto, localidad, direccionEmail, fechaCumple, tipoContacto, idgenero) VALUES(0, 'Sebastian', '550436', 'Jose Hernandez', '1234', 'piso 1', 'No dept', 1, 'sebas@hotmail.com', '1999-05-27', 2, 2);
INSERT INTO personas(idPersona, nombre, telefono, calle, altura, piso, depto, localidad, direccionEmail, fechaCumple, tipoContacto, idgenero) VALUES(0, 'Pehuen', '02320 456123', 'Ituzaingo', '1009', '3', '-', 2, 'Pehuen@gmail.com', '1995-03-13', 3, 2);
INSERT INTO personas(idPersona, nombre, telefono, calle, altura, piso, depto, localidad, direccionEmail, fechaCumple, tipoContacto, idgenero) VALUES(0, 'Norma', '123572', 'Calle falsa', '1274', 'Uno', 'Dos', 5, 'NormaMaria@hotmail.com', '1998-01-12', 3, 3);
INSERT INTO personas(idPersona, nombre, telefono, calle, altura, piso, depto, localidad, direccionEmail, fechaCumple, tipoContacto, idgenero) VALUES(0, 'Juan', '442923', 'Tres tristes', '6833', '1', '', 7, 'JuanJose@gmail.com', '1995-09-22', 4, 2);
INSERT INTO personas(idPersona, nombre, telefono, 
calle, altura, piso, 
depto, localidad, direccionEmail, 
fechaCumple, tipoContacto, idgenero) VALUES(0, 'Francisco', '442923', 
'Green Hills', '1232', '1', 
'', 7, 'NoMeHables@gmail.com', 
'1995-09-22', 3, 4);

INSERT INTO personas(idPersona, nombre, telefono, 
calle, altura, piso, 
depto, localidad, direccionEmail, 
fechaCumple, tipoContacto, idgenero) VALUES(0, 'Manuel', '442923', 
'Uruguay', '135', '', 
'', 7, 'No tiene', 
'1995-09-22', 1, 1);

CREATE TABLE `usuario`
(
	`nombre`  varchar(30) NOT NULL,
	`contra` varchar(30) NOT NULL,
	PRIMARY KEY (`nombre`)
);

INSERT INTO usuario(nombre, contra) VALUES('Sebas', 'no');