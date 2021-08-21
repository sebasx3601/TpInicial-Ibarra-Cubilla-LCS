package dto;

public class PaisDTO {
	
	private int id;
	private String nombrePais;
	
	public PaisDTO(int id, String nombrePais) {
		this.id = id;
		this.nombrePais = nombrePais;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

}
