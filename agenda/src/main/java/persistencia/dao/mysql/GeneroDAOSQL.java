package persistencia.dao.mysql;

import dto.GeneroDTO;
import persistencia.dao.interfaz.GeneroDAO;

import java.util.ArrayList;
import java.util.List;

public class GeneroDAOSQL implements GeneroDAO {
	
	
	public boolean insert(GeneroDTO genero) {
		return true;
	}
	
	public boolean delete(GeneroDTO genero) {
		return true;
	}
	
	public boolean update(GeneroDTO genero) {
		return true;
	}
	
	public List<GeneroDTO> readAll(GeneroDTO genero) {
		return new ArrayList<GeneroDTO>();
	}
	
	public GeneroDTO readOne(int id) {
		return new GeneroDTO(1,"no");
	}

}
