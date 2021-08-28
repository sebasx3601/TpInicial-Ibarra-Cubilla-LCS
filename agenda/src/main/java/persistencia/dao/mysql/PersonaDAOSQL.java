package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.PersonaDTO;

import java. util. Date;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, calle, altura, "
			+ "piso, depto, localidad, direccionEmail, fechaCumple, tipoContacto, idgenero) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
		
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			
			statement.setString(4, persona.getCalle());
			statement.setString(5, persona.getAltura());
			statement.setString(6, persona.getPiso());
			statement.setString(7, persona.getDepto());
			statement.setInt(8, persona.getLocalidad());
			statement.setString(9, persona.getDireccionEmail());
			//java. sql. Date fecha;
			statement.setString(10, persona.getFechaCumple()); //+persona.getFechaCumple().getYear()+"-"+persona.getFechaCumple().getMonth()+"-"+persona.getFechaCumple().getDay()
			statement.setInt(11, persona.getTipoContacto());
			statement.setInt(12, persona.getIdGenero());
			
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
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
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
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		
		String calle = resultSet.getString("Calle");
		String altura = resultSet.getString("Altura");
		String piso = resultSet.getString("Piso");
		String depto = resultSet.getString("Depto");
		int localidad = resultSet.getInt("Localidad");
		String direccionEmail = resultSet.getString("DireccionEmail");
		String fechaCumple = resultSet.getString("fechaCumple");
		int tipoContacto = resultSet.getInt("tipoContacto");
		
		int idGenero = resultSet.getInt("IdGenero");
		
		return new PersonaDTO(id, nombre, tel, calle, altura, piso, depto, localidad, direccionEmail, fechaCumple, tipoContacto, idGenero);
	}
	
	private static final String editPersona = "UPDATE personas SET Nombre = ?, Telefono = ?, Calle = ?,"
			+ " Altura = ?, Piso = ?, Depto = ?, Localidad = ?, DireccionEmail = ?, tipoContacto = ?, "
			+ "fechaCumple = ?, IdGenero = ? WHERE idPersona = ?";
	
	public boolean edit(PersonaDTO persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(editPersona);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			
			statement.setString(3, persona.getCalle());
			statement.setString(4, persona.getAltura());
			statement.setString(5, persona.getPiso());
			statement.setString(6, persona.getDepto());
			statement.setInt(7, persona.getLocalidad());
			statement.setString(8, persona.getDireccionEmail());
			//java. sql. Date fecha;
			statement.setString(10, persona.getFechaCumple()); //+persona.getFechaCumple().getYear()+"-"+persona.getFechaCumple().getMonth()+"-"+persona.getFechaCumple().getDay()
			statement.setInt(9, persona.getTipoContacto());
			statement.setInt(11, persona.getIdGenero());
			
			statement.setInt(12, persona.getIdPersona());
			
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
