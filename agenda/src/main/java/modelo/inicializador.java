package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import persistencia.conexion.Conexion;

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
			log.info("Conexión exitosa");
		}
		catch(Exception e)
		{
			log.error("Conexión fallida", e);
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
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
	}
	
	private static final String eliminarBase = "drop database if exists `agenda`;";
	private static final String crearBase = "CREATE DATABASE `agenda`;";
	private static final String crearTablaPersona = "CREATE TABLE `personas`\r\n"
			+ "(\r\n"
			+ "  `idPersona` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "  `Nombre` varchar(45) NOT NULL,\r\n"
			+ "  `Telefono` varchar(20) NOT NULL,\r\n"
			+ "  `Calle` varchar(20) NOT NULL,\r\n"
			+ "  `Altura` varchar(20) NOT NULL,\r\n"
			+ "  `Piso` varchar(20) NOT NULL,\r\n"
			+ "  `Depto` varchar(20) NOT NULL,\r\n"
			+ "  `Localidad` int(11) NOT NULL,\r\n"
			+ "  `DireccionEmail` varchar(20) NOT NULL,\r\n"
			+ "  `tipoContacto` int(20) NOT NULL,\r\n"
			+ "  `fechaCumple` DATE NOT NULL,\r\n"
			+ "  PRIMARY KEY (`idPersona`)\r\n"
			+ ");";
	private static final String crearTablaTipoContacto = "CREATE TABLE `tipo_contacto`\r\n"
			+ "(\r\n"
			+ "	`IdContacto` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombreContacto` varchar(20) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdContacto`)\r\n"
			+ ");";
	
	private static final String crearTablaPais = "CREATE TABLE `pais`\r\n"
			+ "(\r\n"
			+ "	`IdPais` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombrePais` varchar(20) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdPais`)\r\n"
			+ ");";
	
	private static final String crearTablaProvincia = "CREATE TABLE `provincia`\r\n"
			+ "(\r\n"
			+ "	`IdProvincia` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombreProvincia` varchar(20) NOT NULL,\r\n"
			+ "	`IdPais` varchar(20) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdProvincia`)\r\n"
			+ ");";
	
	private static final String crearTablaLocalidad = "CREATE TABLE `localidad`\r\n"
			+ "(\r\n"
			+ "	`IdLocalidad` int(11) NOT NULL AUTO_INCREMENT,\r\n"
			+ "	`NombreLocalidad` varchar(20) NOT NULL,\r\n"
			+ "	`IdProvincia` varchar(20) NOT NULL,\r\n"
			+ "	PRIMARY KEY (`IdLocalidad`)\r\n"
			+ ");";
	
	public boolean crearBaseDatos() {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
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
		Connection conexion = Conexion.getConexion().getSQLConexion();
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
	
	
	
}
