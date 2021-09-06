package persistencia.conexion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import dto.GeneroDTO;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);

	private Conexion() {
		try {
			String servidor = Conexion.leerDatosInicio("servidor");
			String puerto = Conexion.leerDatosInicio("puerto");
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			//Class.forName("com.mysql.cj.jdbc.Driver"); // PARA VERSIONES MAS AVANZADAS
			this.connection = DriverManager.getConnection("jdbc:mysql://"+servidor+":"+puerto+"/agenda", "root", "root");
			this.connection.setAutoCommit(false);
		} catch (Exception e) {
			log.error("Conexión fallida", e);
		}
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}

	public void cerrarConexion() {
		try {
			this.connection.close();
			log.info("Conexion cerrada");
		} catch (SQLException e) {
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
	}

	public static void guardarDatosInicio(Map<String, String> datos) {
		Properties nuevosDatosInicio = new Properties();
		OutputStream ret = null;
		try {
			ret = new FileOutputStream("datos.properties");
			for (Map.Entry<String, String> i : datos.entrySet()) {
				nuevosDatosInicio.setProperty(i.getKey(), i.getValue());
			}
			nuevosDatosInicio.store(ret, null);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            if (ret != null) {
                try {
                    ret.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	}

	public static String leerDatosInicio(String dato) {
		Properties datosInicio = new Properties();
		InputStream ent = null;
		String ret = null;
		try {
			ent = new FileInputStream("datos.properties");
			datosInicio.load(ent);
			ret = datosInicio.getProperty(dato);

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            if (ent != null) {
                try {
                	ent.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
		return ret;
	}
	
	private static final String consultar = "SELECT contra FROM usuario WHERE nombre = ?;";
	
	public static boolean esUsuarioValido(String nombre, String contra) {
		ArrayList<GeneroDTO> ret = new ArrayList<GeneroDTO>();
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		String contraTabla = "";
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(consultar);
			//statement.setString(1, Conexion.leerDatosInicio("usuario"));
			statement.setString(1, nombre);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				contraTabla = resultSet.getString("contra");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		//return contraTabla.equals(Conexion.leerDatosInicio("contra"));
		return contraTabla.equals(contra);
	}
}
