package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {

	private static final String insert = "INSERT INTO tipo_contacto(IdContacto, NombreContacto) VALUES(?, ?)";
	private static final String delete = "DELETE FROM tipo_contacto WHERE IdContacto = ?";
	private static final String readall = "SELECT * FROM tipo_contacto";
	
	public boolean insert(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, tipoContacto.getIdContacto());
			statement.setString(2, tipoContacto.getNombreTipoContacto());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
			
		}catch (SQLException e) 
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
	
	public boolean delete(TipoContactoDTO tipoContactoAEliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(tipoContactoAEliminar.getIdContacto()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public List<TipoContactoDTO> readAll() {
		ArrayList<TipoContactoDTO> personas = new ArrayList<TipoContactoDTO>();
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tiposContactos = new ArrayList<TipoContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				tiposContactos.add(getTipoContactoDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tiposContactos;
	}
	
	private TipoContactoDTO getTipoContactoDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idContacto");
		String nombre = resultSet.getString("NombreContacto");
		return new TipoContactoDTO(id,nombre);
	}
	
	
	public static void main(String[] args) 
	{
		TipoContactoDAOSQL hola = new TipoContactoDAOSQL();
		//hola.insert(new TipoContactoDTO(4,"Enemigo"));
		hola.delete(new TipoContactoDTO(4,"Enemigo"));
		//List<TipoContactoDTO> lista = hola.readAll();
		//for(TipoContactoDTO dato : lista) {
		//	System.out.println(dato.getIdContacto() +" "+dato.getNombreTipoContacto());
		//}
		
		
	}
}
