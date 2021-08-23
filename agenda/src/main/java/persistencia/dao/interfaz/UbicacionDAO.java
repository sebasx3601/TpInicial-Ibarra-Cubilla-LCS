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

	public boolean deleteProvincia(ProvinciaDTO provincia_a_eliminar);
	
	public List<ProvinciaDTO> readAllProvincia();
	
	public boolean insertLocalidad(LocalidadDTO localidad);

	public boolean deleteLocalidad(LocalidadDTO localidad_a_eliminar);
	
	public List<LocalidadDTO> readAllLocalidad();
	
	public List<ProvinciaDTO> readProvincia(int idPais);
	
	public List<LocalidadDTO> readLocalidad(int idProvincia);
	
	public boolean editLocalidad(LocalidadDTO localidad);
	
	public boolean editProvincia(ProvinciaDTO provincia);
	
	public boolean editPais(PaisDTO pais);
	
	public LocalidadDTO readOneLocalidad(int idLocalidad);
	
	public ProvinciaDTO readOneProvincia(int idProvincia);
	
	public PaisDTO readOnePais(int idPais);

}
