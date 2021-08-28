package persistencia.dao.interfaz;

import dto.GeneroDTO;
import java.util.List;

public interface GeneroDAO {
	
	public boolean insert(GeneroDTO genero);
	
	public boolean delete(GeneroDTO genero);
	
	public boolean update(GeneroDTO genero);
	
	public List<GeneroDTO> readAll(GeneroDTO genero);
	
	public GeneroDTO readOne(int id);

}
