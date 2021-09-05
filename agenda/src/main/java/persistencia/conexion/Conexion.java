package persistencia.conexion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);

	private Conexion() {
		try {
			String servidor = Conexion.leerDatosInicio("servidor");
			String puerto = Conexion.leerDatosInicio("puerto");
			Class.forName("com.mysql.cj.jdbc.Driver"); // quitar si no es necesario
			this.connection = DriverManager.getConnection("jdbc:mysql://"+servidor+":"+puerto+"/agenda", "root", "root");
			this.connection.setAutoCommit(false);
			log.info("Conexión exitosa");
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
		}
		return ret;
	}
}
