package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.UbicacionDAO;

public class UbicacionDAOSQL implements UbicacionDAO
{
	private static final String insertPais = "INSERT INTO pais(IdPais, NombrePais) VALUES(?, ?)";
	private static final String deletePais = "DELETE FROM pais WHERE IdPais = ?";
	private static final String readallPais = "SELECT * FROM pais";
	
	private static final String insertProvincia = "INSERT INTO provincia(IdProvincia, NombreProvincia, IdPais) VALUES(?, ?, ?)";
	private static final String deleteProvincia = "DELETE FROM provincia WHERE IdProvincia = ?";
	private static final String readallProvincia = "SELECT * FROM provincia";
	
	private static final String insertLocalidad = "INSERT INTO localidad(IdLocalidad, NombreLocalidad, IdProvincia) VALUES(?, ?, ?)";
	private static final String deleteLocalidad = "DELETE FROM localidad WHERE IdLocalidad = ?";
	private static final String readallLocalidad = "SELECT * FROM localidad";

	public boolean insertPais(PaisDTO pais) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertPais);
			statement.setInt(1, pais.getId());
			statement.setString(2, pais.getNombrePais());
			
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
	
	public boolean deletePais(PaisDTO pais_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(deletePais);
			statement.setString(1, Integer.toString(pais_a_eliminar.getId()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public List<PaisDTO> readAllPais(){
		PreparedStatement statement;
		ResultSet resultSet;
		List<PaisDTO> res = new ArrayList<PaisDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallPais);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				System.out.println(getPaisDTO(resultSet).getId() + " " + getPaisDTO(resultSet).getNombrePais());
				res.add(getPaisDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return res;
	}
	
	private PaisDTO getPaisDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("IdPais");
		String nombre = resultSet.getString("NombrePais");
		return new PaisDTO(id, nombre);
	}
	
	public boolean insertProvincia(ProvinciaDTO provincia) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertProvincia);
			statement.setInt(1, provincia.getId());
			statement.setString(2, provincia.getNombreProvincia());
			statement.setInt(3, provincia.getIdPais());
			
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

	public boolean deleteP(ProvinciaDTO provincia_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(deleteProvincia);
			statement.setString(1, Integer.toString(provincia_a_eliminar.getId()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public List<ProvinciaDTO> readAllProvincia(){
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<ProvinciaDTO> res = new ArrayList<ProvinciaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallProvincia);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				System.out.println(getProvinciaDTO(resultSet).getId() + " " + getProvinciaDTO(resultSet).getNombreProvincia() + " " + getProvinciaDTO(resultSet).getIdPais());
				res.add(getProvinciaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return res;
	}
	
	private ProvinciaDTO getProvinciaDTO(ResultSet resultSet) throws SQLException 
	{
		int id = resultSet.getInt("IdProvincia");
		String nombre = resultSet.getString("NombreProvincia");
		int idPais = resultSet.getInt("IdPais");
		return new ProvinciaDTO(id, idPais, nombre);
	}

	public boolean insertLocalidad(LocalidadDTO localidad) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertLocalidad);
			statement.setInt(1, localidad.getId());
			statement.setString(2, localidad.getNombreLocalidad());
			statement.setInt(3, localidad.getIdProvincia());
			
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

	public boolean deleteLocalidad(LocalidadDTO localidad_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(deleteLocalidad);
			statement.setString(1, Integer.toString(localidad_a_eliminar.getId()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public List<LocalidadDTO> readAllLocalidad(){
		List<LocalidadDTO> res = new ArrayList<LocalidadDTO>();
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallLocalidad);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				res.add(getLocalidadDTO(resultSet));
				System.out.println(getLocalidadDTO(resultSet).getId()+" "+getLocalidadDTO(resultSet).getNombreLocalidad()+" "+getLocalidadDTO(resultSet).getIdProvincia());
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return res;
	}

	private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException {
		String nombre = resultSet.getString("NombreLocalidad");
		int id = resultSet.getInt("IdLocalidad");
		int idProvincia = resultSet.getInt("IdProvincia");
		return new LocalidadDTO(id, nombre, idProvincia);
	}
}
