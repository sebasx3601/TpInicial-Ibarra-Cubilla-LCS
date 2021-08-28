package modelo;

import java.util.List;

import dto.GeneroDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.GeneroDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.interfaz.UbicacionDAO;



public class Agenda 
{
	private PersonaDAO persona;	

	
	private TipoContactoDAO tiposDeContacto; //el merge por alguna razon lo borra
	
	private UbicacionDAO ubicacion;
	
	private GeneroDAO genero;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tiposDeContacto =  metodo_persistencia.createTipoContactoDAO();
		this.ubicacion = metodo_persistencia.createUbicacionDAO();
		this.genero = metodo_persistencia.createGeneroDAO();
		
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}
	
	public List<TipoContactoDTO> obtenerTiposDeContacto()//Parece que lo ignora github
	{
		return this.tiposDeContacto.readAll();		
	}
	
	public void agregarTipoContacto(TipoContactoDTO tipoContacto_a_eliminar)
	{
		this.tiposDeContacto.insert(tipoContacto_a_eliminar);
	}

	public void borrarTipoContacto(TipoContactoDTO persona_a_eliminar) 
	{
		this.tiposDeContacto.delete(persona_a_eliminar);
	}
	
	public void editarTipoContacto(TipoContactoDTO tipoContacto_a_editar)
	{
		this.tiposDeContacto.edit(tipoContacto_a_editar);
	}
	
	// PAIS
	public List<PaisDTO> obtenerPaises(){
		return this.ubicacion.readAllPais();
	}
	
	public void agregarPais(PaisDTO pais_a_agregar)
	{
		this.ubicacion.insertPais(pais_a_agregar);
	}
	
	public void borrarPais(PaisDTO pais_a_borrar)
	{
		this.ubicacion.deletePais(pais_a_borrar);
	}
	
	public void editarPais(PaisDTO pais_a_editar) {
		this.ubicacion.editPais(pais_a_editar);
	}
	
	//PROVINCIA
	public List<ProvinciaDTO> obtenerProvincia(){
		return this.ubicacion.readAllProvincia();
	}
	
	public void agregarProvincia(ProvinciaDTO provincia_a_agregar)
	{
		this.ubicacion.insertProvincia(provincia_a_agregar);
	}
	
	public void borrarProvincia(ProvinciaDTO provincia_a_borrar)
	{
		this.ubicacion.deleteProvincia(provincia_a_borrar);
	}
	
	public List<ProvinciaDTO> obtenerProvinciaDePaises(int idPais){
		return this.ubicacion.readProvincia(idPais);
	}
	
	public void editarProvincia(ProvinciaDTO provincia) {
		this.ubicacion.editProvincia(provincia);
	}
	
	//Localidad
	public List<LocalidadDTO> obtenerLocalidad(){
		return this.ubicacion.readAllLocalidad();
	}
	
	public void agregarLocalidad(LocalidadDTO localidad_a_agregar)
	{
		this.ubicacion.insertLocalidad(localidad_a_agregar);
	}
	
	public void borrarLocalidad(LocalidadDTO localidad_a_borrar)
	{
		this.ubicacion.deleteLocalidad(localidad_a_borrar);
	}
	
	public List<LocalidadDTO> obtenerLocalidadDeProvincia(int idProvincia){
		return this.ubicacion.readLocalidad(idProvincia);
	}
	
	public void editarLocalidad(LocalidadDTO localidad) {
		this.ubicacion.editLocalidad(localidad);
	}
	
	public LocalidadDTO getLocalidad(int idLocalidad) {
		return this.ubicacion.readOneLocalidad(idLocalidad);
	}
	
	public ProvinciaDTO getProvincias(int idProvincia) {
		return this.ubicacion.readOneProvincia(idProvincia);
	}
	
	public PaisDTO getPais(int idPais) {
		return this.ubicacion.readOnePais(idPais);
	}
	
	public void editarPersona(PersonaDTO persona) {
		this.persona.edit(persona);
	}
	
	//Genero
	public void agregarGenero(GeneroDTO genero) {
		this.genero.insert(genero);
	}
	
	public void borrarGenero(GeneroDTO genero) {
		this.genero.delete(genero);
	}
	
	public List<GeneroDTO> getGenero() {
		return this.genero.readAll();
	}
	
	public void editarGenero(GeneroDTO genero) {
		this.genero.update(genero);
	}
	
	
}
