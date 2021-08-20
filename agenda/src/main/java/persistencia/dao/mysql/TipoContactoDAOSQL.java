package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {

	private static final String insert = "INSERT INTO tipo_contacto(IdContacto, NombreContacto) VALUES(?, ?)";
	private static final String delete = "DELETE FROM tipo_contacto WHERE IdContacto = ?";
	
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
		
		return personas;
	}
	
	public static void main(String[] args) 
	{
		TipoContactoDAOSQL hola = new TipoContactoDAOSQL();
		//hola.insert(new TipoContactoDTO(5,"Enemigo"));
		//hola.delete(new TipoContactoDTO(5,"Enemigo"));
	}
}
