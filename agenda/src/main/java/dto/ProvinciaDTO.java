package dto;

public class ProvinciaDTO {
	
	private int id;
	private int idPais;
	private String nombreProvincia;
	
	public ProvinciaDTO(int id, int idPais, String nombreProvincia) {
		this.id = id;
		this.idPais = idPais;
		this.nombreProvincia = nombreProvincia;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}


}
