package persistencia.dao.mysql;

import dto.GeneroDTO;
import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.GeneroDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAOSQL implements GeneroDAO {
	
	private static final String insert = "INSERT INTO genero(Id, Descrip) VALUES(?, ?)";
	private static final String delete = "DELETE FROM genero WHERE Id = ?";
	private static final String readall = "SELECT * FROM genero";
	private static final String readone = "SELECT * FROM genero WHERE Id = ?";
	
	public boolean insert(GeneroDTO genero) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, genero.getId());
			statement.setString(2, genero.getNombre());
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
	
	public boolean delete(GeneroDTO genero) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(genero.getId()));
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
	
	public List<GeneroDTO> readAll(GeneroDTO genero) {
		ArrayList<GeneroDTO> ret = new ArrayList<GeneroDTO>();
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				ret.add(getGeneroDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	private GeneroDTO getGeneroDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("Id");
		String nombre = resultSet.getString("Descrip");
		return new GeneroDTO(id,nombre);
	}
	
	public GeneroDTO readOne(int id) {
		return new GeneroDTO(1,"no");
	}
	
	public boolean update(GeneroDTO genero) {
		return true;
	}

}
