package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.vista.Vista;
import presentacion.vista.VistaContacto;

public class ControladorTipoContacto {
	
	private VistaContacto vista;
	private List<TipoContactoDTO> personasEnTabla;
	private Agenda agenda;
	
	public ControladorTipoContacto(VistaContacto vista, Agenda agenda)
	{
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a->agregarTipoContacto(a));
		this.vista.getBtnBorrar().addActionListener(s->borrarTipoContacto(s));
	}
	
	private void agregarTipoContacto(ActionEvent a) {
		
	}
	
	private void borrarTipoContacto(ActionEvent a) {
		
	}

}
