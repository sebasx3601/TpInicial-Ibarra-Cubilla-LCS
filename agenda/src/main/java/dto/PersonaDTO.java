package dto;
import java. util. Date;
import java.util.List;

import javax.swing.JComboBox;


public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	
	private String calle;
	private String altura;
	private String piso;
	private String depto;
	private int localidad;
	private String direccionEmail;
	private String fechaCumple;
	private int tipoContacto;
	
	private int idGenero;
	private String descrGenero;
	
	private int idProv;
	private String descrProv;
	
	private int idPais;
	private String descrPais;
	
	
	public PersonaDTO(int idPersona, String nombre, String telefono, String calle, String altura,
			String piso, String depto, int localidad, String direccionEmail, String fechaCumple2, int tipoContacto, int idGenero)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.direccionEmail = direccionEmail;
		this.fechaCumple = fechaCumple2;
		this.tipoContacto = tipoContacto;
		
		this.idGenero = idGenero;
		descrGenero = "";
	}
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	

	
	
	
	
	
	
	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public int getLocalidad() {
		return localidad;
	}

	public void setLocalidad(int localidad) {
		this.localidad = localidad;
	}

	public String getDireccionEmail() {
		return direccionEmail;
	}

	public void setDireccionEmail(String direccionEmail) {
		this.direccionEmail = direccionEmail;
	}

	public String getFechaCumple() {
		return fechaCumple;
	}

	public void setFechaCumple(String fechaCumple) {
		this.fechaCumple = fechaCumple;
	}

	public int getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(int tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	
	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getDescrGenero() {
		return descrGenero;
	}

	public void setDescrGenero(String descrGenero) {
		this.descrGenero = descrGenero;
	}
	
	
	
	
	
	public int getIdProv() {
		return idProv;
	}

	public void setIdProv(int idProv) {
		this.idProv = idProv;
	}

	public String getDescrProv() {
		return descrProv;
	}

	public void setDescrProv(String descrProv) {
		this.descrProv = descrProv;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public String getDescrPais() {
		return descrPais;
	}

	public void setDescrPais(String descrPais) {
		this.descrPais = descrPais;
	}

}
