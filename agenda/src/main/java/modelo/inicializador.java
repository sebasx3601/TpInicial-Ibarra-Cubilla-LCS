package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class inicializador {
	
	public static inicializador instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(inicializador.class);
	
	public inicializador()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver"); // quitar si no es necesario
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","root");
			this.connection.setAutoCommit(false);
			log.info("ConexiÃ³n exitosa");
		}
		catch(Exception e)
		{
			log.error("ConexiÃ³n fallida", e);
		}
	}
	
	public static inicializador getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new inicializador();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
			log.info("Conexion cerrada");
		}
		catch (SQLException e) 
		{
			log.error("Error al cerrar la conexiÃ³n!", e);
		}
		instancia = null;
	}
	
	private void conectarConBase()
	{
		try
		{
			cerrarConexion();
			Class.forName("com.mysql.cj.jdbc.Driver"); // quitar si no es necesario
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","root");
			this.connection.setAutoCommit(false);
			log.info("ConexiÃ³n exitosa");
		}
		catch(Exception e)
		{
			log.error("ConexiÃ³n fallida", e);
		}
	}
	
	private static final String eliminarBase = "drop database if exists `agenda`;";
	private static final String crearBase = "CREATE DATABASE `agenda`;";
	private static final String crearTablaPersona = "CREATE TABLE `personas` "
			+ "(`idPersona` int(11) NOT NULL AUTO_INCREMENT, "
			+ "`Nombre` varchar(45) NOT NULL, `Telefono` varchar(20) NOT NULL, "
			+ "`Calle` varchar(30) NOT NULL, `Altura` varchar(20) NOT NULL, `Piso` varchar(20) NOT NULL, "
			+ "`Depto` varchar(30) NOT NULL, `Localidad` int(11) NOT NULL, "
			+ "`DireccionEmail` varchar(100) NOT NULL, `tipoContacto` int(20) NOT NULL, "
			+ "`IdGenero` int(20) NOT NULL, "
			+ "`fechaCumple` DATE NOT NULL, PRIMARY KEY (`idPersona`));";
	
	private static final String crearTablaTipoContacto = "CREATE TABLE `tipo_contacto`\r\n"
			+ "(\r\n"
			+ "	`IdContacto` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombreContacto` varchar(30) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdContacto`)\r\n"
			+ ");";
	
	private static final String crearTablaPais = "CREATE TABLE `pais`\r\n"
			+ "(\r\n"
			+ "	`IdPais` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombrePais` varchar(30) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdPais`)\r\n"
			+ ");";
	
	private static final String crearTablaProvincia = "CREATE TABLE `provincia`\r\n"
			+ "(\r\n"
			+ "	`IdProvincia` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombreProvincia` varchar(30) NOT NULL,\r\n"
			+ "	`IdPais` int(11) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdProvincia`)\r\n"
			+ ");";
	
	private static final String crearTablaLocalidad = "CREATE TABLE `localidad`\r\n"
			+ "(\r\n"
			+ "	`IdLocalidad` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombreLocalidad` varchar(30) NOT NULL,\r\n"
			+ "	`IdProvincia` int(11) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdLocalidad`)\r\n"
			+ ");";
	
	private static final String crearTablaGenero = "CREATE TABLE `genero`\r\n"
			+ "(\r\n"
			+ "	`Id` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`Descrip` varchar(30) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`Id`)\r\n"
			+ ");";
	
	public boolean crearBaseDatos() {
		PreparedStatement statement;
		Connection conexion = inicializador.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(crearBase);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}
	
	public boolean borrarBaseDatos() {
		PreparedStatement statement;
		Connection conexion = inicializador.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(eliminarBase);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}
	
	public boolean ejecutarInstruccion(String instruccion) {
		PreparedStatement statement;
		Connection conexion = inicializador.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(instruccion);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}
	
	public boolean crearTablaPersona() {
		return ejecutarInstruccion(crearTablaPersona);
	}
	
	public boolean crearTablaTipoContacto() {
		return ejecutarInstruccion(crearTablaTipoContacto);
	}
	
	public boolean crearTablaPais() {
		return ejecutarInstruccion(crearTablaPais);
	}
	
	public boolean crearTablaProvincia() {
		return ejecutarInstruccion(crearTablaProvincia);
	}
	
	public boolean crearTablaLocalidad() {
		return ejecutarInstruccion(crearTablaLocalidad);
	}
	
	public boolean crearTablaGenero() {
		return ejecutarInstruccion(crearTablaGenero);
	}
	
	public void crearTodaLaBaseDeDatos() {
		conectarConBase();
		this.ejecutarInstruccion("USE `agenda`;");
		crearTablaPersona();
		crearTablaTipoContacto();
		crearTablaPais();
		crearTablaProvincia();
		crearTablaLocalidad();
		
		crearTablaGenero();
		
		insertarTiposDeContacto();
		insertarPaises();
		insertarProvincia();
		insertarLocalidades();
	}
	
	public void insertarTiposDeContacto() {
		ejecutarInstruccion("insert into tipo_contacto values(1,'Sin especificar');");
		ejecutarInstruccion("insert into tipo_contacto values(2,'trabajo');");
		ejecutarInstruccion("insert into tipo_contacto values(3,'familia');");
		ejecutarInstruccion("insert into tipo_contacto values(4,'amigos');");
		ejecutarInstruccion("insert into tipo_contacto values(5,'Compañeros de tp');");
		ejecutarInstruccion("insert into tipo_contacto values(6,'Ayudantes');");
	}
	
	public void insertarPaises() {
		ejecutarInstruccion("insert into pais values(1,'Sin especificar');");
		ejecutarInstruccion("insert into pais values(2,'argentina');");
		ejecutarInstruccion("insert into pais values(3,'brasil');");
	}
	
	public void insertarProvincia() {
		ejecutarInstruccion("insert into provincia values(1,'Sin especificar',1);");
		ejecutarInstruccion("insert into provincia values(2,'Buenos Aires',2);");
		ejecutarInstruccion("insert into provincia values(3,'Catamarca',2);");
		ejecutarInstruccion("insert into provincia values(4,'Chaco',2);");
		ejecutarInstruccion("insert into provincia values(5,'Chubut',2);");
		
		ejecutarInstruccion("insert into provincia values(6,'Arapiraca',3);");
		ejecutarInstruccion("insert into provincia values(7,'Coruripe',3);");
		ejecutarInstruccion("insert into provincia values(8,'Maceió',3);");
	}
	
	public void insertarLocalidades() {
		ejecutarInstruccion("insert into localidad values(1,'Sin especificar',1);");
		ejecutarInstruccion("insert into localidad values(2,'Alejandro Petión',2);");
		ejecutarInstruccion("insert into localidad values(3,'Alto Los Cardales',2);");
		ejecutarInstruccion("insert into localidad values(4,'Arribeños',2);");
		ejecutarInstruccion("insert into localidad values(5,'Ascensión',2);");
		ejecutarInstruccion("insert into localidad values(6,'El Remanso',2);");
		
		ejecutarInstruccion("insert into localidad values(7,'Aconquija',3);");
		ejecutarInstruccion("insert into localidad values(8,'Alijilan',3);");
		ejecutarInstruccion("insert into localidad values(9,'Ancasti',3);");
		ejecutarInstruccion("insert into localidad values(10,'Andalgala',3);");
		ejecutarInstruccion("insert into localidad values(11,'Antofagasta',3);");
		
		ejecutarInstruccion("insert into localidad values(12,'Campo Largo',4);");
		ejecutarInstruccion("insert into localidad values(13,'Charata',4);");
		ejecutarInstruccion("insert into localidad values(14,'Colonia Benítez',4);");
		ejecutarInstruccion("insert into localidad values(15,'Colonia Elisa',4);");
		ejecutarInstruccion("insert into localidad values(16,'Colonias Unidas',4);");
		
		ejecutarInstruccion("insert into localidad values(17,'Alto Río Senguer',5);");
		ejecutarInstruccion("insert into localidad values(18,'Bahía Bustamante',5);");
		ejecutarInstruccion("insert into localidad values(19,'Camarones',5);");
		ejecutarInstruccion("insert into localidad values(20,'Cholila',5);");
		ejecutarInstruccion("insert into localidad values(21,'Comodoro Rivadavia',5);");
	}
}
