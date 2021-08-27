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
			+ "	`IdPais` varchar(20) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdProvincia`)\r\n"
			+ ");";
	
	private static final String crearTablaLocalidad = "CREATE TABLE `localidad`\r\n"
			+ "(\r\n"
			+ "	`IdLocalidad` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombreLocalidad` varchar(30) NOT NULL,\r\n"
			+ "	`IdProvincia` varchar(20) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdLocalidad`)\r\n"
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
	
	public void crearTodaLaBaseDeDatos() {
		conectarConBase();
		this.ejecutarInstruccion("USE `agenda`;");
		crearTablaPersona();
		crearTablaTipoContacto();
		crearTablaPais();
		crearTablaProvincia();
		crearTablaLocalidad();
		
		insertarTiposDeContacto();
		insertarPaises();
		insertarProvincia();
		insertarLocalidades();
	}
	
	public void insertarTiposDeContacto() {
		ejecutarInstruccion("insert into tipo_contacto values(1,'trabajo');");
		ejecutarInstruccion("insert into tipo_contacto values(2,'familia');");
		ejecutarInstruccion("insert into tipo_contacto values(3,'amigos');");
		ejecutarInstruccion("insert into tipo_contacto values(4,'Compañeros de tp');");
		ejecutarInstruccion("insert into tipo_contacto values(5,'Ayudantes');");
	}
	
	public void insertarPaises() {
		ejecutarInstruccion("insert into pais values(1,'argentina');");
		ejecutarInstruccion("insert into pais values(2,'brasil');");
	}
	
	public void insertarProvincia() {
		ejecutarInstruccion("insert into provincia values(1,'Buenos Aires',1);");
		ejecutarInstruccion("insert into provincia values(2,'Catamarca',1);");
		ejecutarInstruccion("insert into provincia values(3,'Chaco',1);");
		ejecutarInstruccion("insert into provincia values(4,'Chubut',1);");
		
		ejecutarInstruccion("insert into provincia values(5,'Arapiraca',2);");
		ejecutarInstruccion("insert into provincia values(6,'Coruripe',2);");
		ejecutarInstruccion("insert into provincia values(7,'Maceió',2);");
	}
	
	public void insertarLocalidades() {
		ejecutarInstruccion("insert into localidad values(1,'Alejandro Petión',1);");
		ejecutarInstruccion("insert into localidad values(2,'Alto Los Cardales',1);");
		ejecutarInstruccion("insert into localidad values(3,'Arribeños',1);");
		ejecutarInstruccion("insert into localidad values(4,'Ascensión',1);");
		ejecutarInstruccion("insert into localidad values(5,'El Remanso',1);");
		
		ejecutarInstruccion("insert into localidad values(6,'Aconquija',2);");
		ejecutarInstruccion("insert into localidad values(7,'Alijilan',2);");
		ejecutarInstruccion("insert into localidad values(8,'Ancasti',2);");
		ejecutarInstruccion("insert into localidad values(9,'Andalgala',2);");
		ejecutarInstruccion("insert into localidad values(10,'Antofagasta',2);");
		
		ejecutarInstruccion("insert into localidad values(11,'Campo Largo',3);");
		ejecutarInstruccion("insert into localidad values(12,'Charata',3);");
		ejecutarInstruccion("insert into localidad values(13,'Colonia Benítez',3);");
		ejecutarInstruccion("insert into localidad values(14,'Colonia Elisa',3);");
		ejecutarInstruccion("insert into localidad values(15,'Colonias Unidas',3);");
		
		ejecutarInstruccion("insert into localidad values(16,'Alto Río Senguer',4);");
		ejecutarInstruccion("insert into localidad values(17,'Bahía Bustamante',4);");
		ejecutarInstruccion("insert into localidad values(18,'Camarones',4);");
		ejecutarInstruccion("insert into localidad values(19,'Cholila',4);");
		ejecutarInstruccion("insert into localidad values(20,'Comodoro Rivadavia',4);");
	}
}
