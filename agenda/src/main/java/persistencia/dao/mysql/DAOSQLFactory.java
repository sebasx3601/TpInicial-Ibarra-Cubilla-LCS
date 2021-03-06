/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.GeneroDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.interfaz.UbicacionDAO;

public class DAOSQLFactory implements DAOAbstractFactory 
{
	/* (non-Javadoc)
	 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
	 */
	public PersonaDAO createPersonaDAO() 
	{
				return new PersonaDAOSQL();
	}
	
	public TipoContactoDAO createTipoContactoDAO()
	{
				return new TipoContactoDAOSQL();
	}
	
	public UbicacionDAO createUbicacionDAO()
	{
				return new UbicacionDAOSQL();
	}
	
	public GeneroDAO createGeneroDAO()
	{
				return new GeneroDAOSQL();
	}

}
