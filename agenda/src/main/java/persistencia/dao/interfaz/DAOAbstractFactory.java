package persistencia.dao.interfaz;


public interface DAOAbstractFactory 
{
	public PersonaDAO createPersonaDAO();
	
	public TipoContactoDAO createTipoContactoDAO();
	
	public UbicacionDAO createUbicacionDAO();
	
	public GeneroDAO createGeneroDAO();
}
