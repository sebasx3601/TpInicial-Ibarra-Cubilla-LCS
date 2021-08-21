package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;

public interface UbicacionDAO {
	
	public boolean insertPais(PaisDTO pais);

	public boolean deletePais(PaisDTO pais_a_eliminar);
	
	public List<PaisDTO> readAllPais();
	
	public boolean insertProvincia(ProvinciaDTO provincia);

	public boolean deleteP(ProvinciaDTO provincia_a_eliminar);
	
	public List<ProvinciaDTO> readAllProvincia();
	
	public boolean insertLocalidad(LocalidadDTO localidad);

	public boolean deleteLocalidad(LocalidadDTO localidad_a_eliminar);
	
	public List<LocalidadDTO> readAllLocalidad();

}