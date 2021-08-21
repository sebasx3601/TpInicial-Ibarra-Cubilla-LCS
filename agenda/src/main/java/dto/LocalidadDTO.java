package dto;

public class LocalidadDTO {
	
	private int id;
	private int idProvincia;
	private String nombreLocalidad;
	
	public LocalidadDTO(int id, String nombreLocalidad, int idProvincia) {
		this.id = id;
		this.idProvincia = idProvincia;
		this.nombreLocalidad = nombreLocalidad;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

}
