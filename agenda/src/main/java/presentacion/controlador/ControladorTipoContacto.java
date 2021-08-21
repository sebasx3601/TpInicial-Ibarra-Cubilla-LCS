package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.vista.VentanaAgregarTipoContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaContacto;

public class ControladorTipoContacto {
	
	private VistaContacto vista;
	private List<TipoContactoDTO> personasEnTabla;
	private Agenda agenda;
	private VentanaAgregarTipoContacto ventanaContacto;
	
	public ControladorTipoContacto(VistaContacto vista, Agenda agenda)
	{
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a->mostrarVentanaAgregarTipoContacto(a));
		this.vista.getBtnBorrar().addActionListener(s->borrarTipoContacto(s));
		this.ventanaContacto = VentanaAgregarTipoContacto.getInstance();
		this.ventanaContacto.getBtnAgregarContacto().addActionListener(p->guardarTipoContacto(p));
		this.agenda = agenda;
	}
	
	private void mostrarVentanaAgregarTipoContacto(ActionEvent a) {
		 ventanaAgregarPersona();
	}
	
	private void guardarTipoContacto(ActionEvent p) {
		String contacto = this.ventanaContacto.getTxtContacto().getText();
		if(contacto.isEmpty() || contacto.equals(" ")) {
			return;
		}
		if(existeTipoContacto(contacto)) {
			return;
		}
		TipoContactoDTO nuevoContacto = new TipoContactoDTO(0,contacto);
		this.agenda.agregarTipoContacto(nuevoContacto);
		this.ventanaContacto.cerrar();
	}
	
	private boolean existeTipoContacto(String nombreTipoContacto) {
		boolean existe = false;
		personasEnTabla = agenda.obtenerTiposDeContacto();
		for(TipoContactoDTO t: agenda.obtenerTiposDeContacto()) {
			if(t.getNombreTipoContacto().equals(nombreTipoContacto)) {
				existe = true;
			}
		}
		return existe;
	}
	
	private void borrarTipoContacto(ActionEvent a) {
		
	}
	
	private void ventanaAgregarPersona() {
		this.ventanaContacto.mostrarVentana();
	}
	
	public void inicializar()
	{
		this.vista.show();
	}

}
