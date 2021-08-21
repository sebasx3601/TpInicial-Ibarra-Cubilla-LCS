package modelo;

import java.util.List;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;



public class Agenda 
{
	private PersonaDAO persona;	

	
	private TipoContactoDAO tiposDeContacto; //el merge por alguna razon lo borra
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tiposDeContacto =  metodo_persistencia.createTipoContactoDAO();
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
	
}
