package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;

import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.vista.VentanaAgregarTipoContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaContacto;

public class ControladorTipoContacto {
	
	private VistaContacto vista;
	private List<TipoContactoDTO> tiposContacto;
	private Agenda agenda;
	private VentanaAgregarTipoContacto ventanaContacto;
	
	private TipoContactoDTO contactoSiendoEditado;
	
	public ControladorTipoContacto(VistaContacto vista, Agenda agenda)
	{
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a->mostrarVentanaAgregarTipoContacto(a));
		this.vista.getBtnBorrar().addActionListener(s->borrarTipoContacto(s));
		this.ventanaContacto = VentanaAgregarTipoContacto.getInstance();
		
		this.agenda = agenda;
		refrescarTabla();
		this.vista.getBtnEditar().addActionListener(p->mostrarVentanaEditarTipoContacto(p));
	}
	
	private void mostrarVentanaAgregarTipoContacto(ActionEvent a) {
		this.ventanaContacto.getLblContacto().setText("Nuevo tipo de Contacto: ");
		this.ventanaContacto.getBtnAgregarContacto().setText("Agregar");
		this.ventanaContacto.getTxtContacto().setText("");
		this.ventanaContacto.getBtnAgregarContacto().addActionListener(p->guardarTipoContacto(p));
		ventanaContacto();
	}
	
	private void guardarTipoContacto(ActionEvent p) {
		String contacto = this.ventanaContacto.getTxtContacto().getText();
		if(!esNombreTipoContactoValido(contacto)) {
			return;
		}
		TipoContactoDTO nuevoContacto = new TipoContactoDTO(0,contacto);
		this.agenda.agregarTipoContacto(nuevoContacto);
		this.ventanaContacto.cerrar();
		refrescarTabla();
	}
	
	private boolean esNombreTipoContactoValido(String contacto) {
		if(contacto.isEmpty() || contacto.equals(" ")) {
			return false;
		}
		if(existeTipoContacto(contacto)) {
			return false;
		}
		return true;
	}
	
	private boolean existeTipoContacto(String nombreTipoContacto) {
		boolean existe = false;
		this.tiposContacto = agenda.obtenerTiposDeContacto();
		for(TipoContactoDTO t: agenda.obtenerTiposDeContacto()) {
			if(t.getNombreTipoContacto().equals(nombreTipoContacto)) {
				existe = true;
			}
		}
		return existe;
	}
	
	private void borrarTipoContacto(ActionEvent a) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas)
		{
			if(fila != 0) {
				this.agenda.borrarTipoContacto(this.tiposContacto.get(fila));
				List<PersonaDTO> personas = agenda.obtenerPersonas();
				for(PersonaDTO p: personas) {
					p.setTipoContacto(1);
					agenda.editarPersona(p);
				}
			}
			
		}
		
		this.refrescarTabla();
	}
	
	private void ventanaContacto() {
		this.ventanaContacto.mostrarVentana();
	}
	
	public void inicializar()
	{
		refrescarTabla();
		this.vista.show();
	}
	
	private void refrescarTabla()
	{
		this.tiposContacto = agenda.obtenerTiposDeContacto();
		this.vista.llenarTabla(this.tiposContacto);
	}
	
	//
	
	private void mostrarVentanaEditarTipoContacto(ActionEvent a) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		if (filasSeleccionadas.length == 0) {
			return;
		}
		this.ventanaContacto.getLblContacto().setText("Nuevo nombre: ");
		this.ventanaContacto.getBtnAgregarContacto().setText("Editar");
		this.ventanaContacto.getTxtContacto().setText(tiposContacto.get(filasSeleccionadas[0]).getNombreTipoContacto());
		
		this.ventanaContacto.getBtnAgregarContacto().addActionListener(p->editarTipoContacto(p));
		
		contactoSiendoEditado = tiposContacto.get(filasSeleccionadas[0]);
		ventanaContacto();
	}
	
	private void editarTipoContacto(ActionEvent a) {
		String contacto = this.ventanaContacto.getTxtContacto().getText();
		if(!esNombreTipoContactoValido(contacto)) {
			return;
		}
		contactoSiendoEditado.setNombreTipoContacto(contacto);
		agenda.editarTipoContacto(contactoSiendoEditado);
		this.ventanaContacto.cerrar();
		refrescarTabla();
	}

}
