package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
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
	
	private static final String insertP = "INSERT INTO localidad(IdLocalidad, NombreLocalidad, IdProvincia) VALUES(?, ?, ?)";
	private static final String deleteP = "DELETE FROM localidad WHERE IdLocalidad = ?";
	private static final String readallP = "SELECT * FROM localidad";

	public boolean insertPais(PaisDTO pais) {
		return true;
	}
	
	public boolean deletePais(PaisDTO pais_a_eliminar) {
		return true;
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
				System.out.println(getPersonaDTO(resultSet).getId() + " " + getPersonaDTO(resultSet).getNombrePais());
				res.add(getPersonaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return res;
	}
	
	private PaisDTO getPersonaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("IdPais");
		String nombre = resultSet.getString("NombrePais");
		return new PaisDTO(id, nombre);
	}
	
	public boolean insertProvincia(ProvinciaDTO provincia) {
		return true;
	}

	public boolean deleteP(ProvinciaDTO provincia_a_eliminar) {
		return true;
	}
	
	public List<ProvinciaDTO> readAllProvincia(){
		List<ProvinciaDTO> res = new ArrayList<ProvinciaDTO>();
		return res;
	}
	
	public boolean insertLocalidad(LocalidadDTO localidad) {
		return true;
	}

	public boolean deleteLocalidad(LocalidadDTO localidad_a_eliminar) {
		return true;
	}
	
	public List<LocalidadDTO> readAllLocalidad(){
		List<LocalidadDTO> res = new ArrayList<LocalidadDTO>();
		return res;
	}
}
