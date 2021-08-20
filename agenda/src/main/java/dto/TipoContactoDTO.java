package dto;

public class TipoContactoDTO {
	
	private int idContacto;
	private String nombreTipoContacto;
	
	public TipoContactoDTO(int id, String nombre) {
		idContacto = id;
		nombreTipoContacto = nombre;
	}
	
	public int getIdContacto() {
		return idContacto;
	}
	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}
	public String getNombreTipoContacto() {
		return nombreTipoContacto;
	}
	public void setNombreTipoContacto(String nombreTipoContacto) {
		this.nombreTipoContacto = nombreTipoContacto;
	}
	
}
