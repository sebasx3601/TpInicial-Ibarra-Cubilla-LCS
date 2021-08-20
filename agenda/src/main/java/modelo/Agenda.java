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

	private TipoContactoDAO tiposDeContacto;
	
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
	
	public List<TipoContactoDTO> obtenerTiposDeContacto()
	{
		return this.tiposDeContacto.readAll();		
	}
	
	
}
