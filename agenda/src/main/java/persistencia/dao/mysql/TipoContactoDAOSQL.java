package persistencia.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import dto.TipoContactoDTO;

import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {

	private static final String insert = "INSERT INTO personas(IdContacto, NombreContacto) VALUES(?, ?)";
	
	public boolean insert(TipoContactoDTO tipoContacto) {
		boolean isInsertExitoso = false;
		
		return isInsertExitoso;
	}
	
	public boolean delete(TipoContactoDTO tipoContacto) {
		boolean isdeleteExitoso = false;
		
		return isdeleteExitoso;
	}
	
	public List<TipoContactoDTO> readAll() {
		ArrayList<TipoContactoDTO> personas = new ArrayList<TipoContactoDTO>();
		
		return personas;
	}
}
