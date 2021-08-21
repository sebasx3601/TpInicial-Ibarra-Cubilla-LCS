package persistencia.dao.mysql;

import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import persistencia.dao.interfaz.UbicacionDAO;

public class UbicacionDAOSQL implements UbicacionDAO
{

	public boolean insertPais(PaisDTO pais) {
		return true;
	}
	
	public boolean deletePais(PaisDTO pais_a_eliminar) {
		return true;
	}
	
	public List<PaisDTO> readAllPais(){
		List<PaisDTO> res = new ArrayList<PaisDTO>();
		return res;
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
