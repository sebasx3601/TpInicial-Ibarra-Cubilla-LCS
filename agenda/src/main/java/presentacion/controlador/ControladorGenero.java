package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import dto.GeneroDTO;
import modelo.Agenda;
import presentacion.vista.VentanaGenero;
import presentacion.vista.VistaGenero;

public class ControladorGenero {
	
	Agenda agenda;
	VistaGenero vista;
	VentanaGenero ventanaAux;
	private List<GeneroDTO> listaGenero;
	
	public ControladorGenero(Agenda agenda) {
		this.agenda = agenda;
		vista = new VistaGenero();
		this.ventanaAux = VentanaGenero.getInstance();
	}
	
	public void inicializarBotones() {
		this.vista.getBtnEditar().addActionListener(p->ejemplo(p));
		this.vista.getBtnAgregar().addActionListener(p->abrirVentanaAgregar(p));
		this.vista.getBtnBorrar().addActionListener(p->borrarGenero(p));
	}
	
	public void inicializar()
	{
		this.refrescarTablaGenero();
		this.vista.show();
		inicializarBotones();
	}
	
	public void reiniciarBoton(JButton boton) {
		for(ActionListener l: boton.getActionListeners()) {
			boton.removeActionListener(l);
		}
	}
	
	public void ejemplo(ActionEvent a) {
		
	}
	
	public void refrescarTablaGenero() {
		listaGenero = agenda.getGenero();
		vista.llenarTabla(agenda.getGenero());
	}
	
	public void abrirVentanaAgregar(ActionEvent a) {
		ventanaAux.getLbl().setText("Nuevo genero");
		ventanaAux.getBtn().setText("Agregar");
		ventanaAux.getTxt().setText("");
		reiniciarBoton(ventanaAux.getBtn());
		ventanaAux.getBtn().addActionListener(p->agregarGenero(p));
		ventanaAux.mostrarVentana();
	}
	
	public void agregarGenero(ActionEvent a) {
		String nuevoNombre = ventanaAux.getTxt().getText();
		if(!esNombreValido(nuevoNombre)) {
			return;
		}
		agenda.agregarGenero(new GeneroDTO(0,nuevoNombre));
		refrescarTablaGenero();
		ventanaAux.cerrar();
	}
	
	public boolean esNombreValido(String nombre) {
		if(nombre == null) {
			return false;
		}
		if(nombre.equals("")) {
			return false;
		}
		List<GeneroDTO> generos = agenda.getGenero();
		boolean ret = true;
		for(GeneroDTO g: generos) {
			if(g.getNombre().equals(nombre)) {
				ret = false;
			}
		}
		
		return ret;		
	}
	
	public void borrarGenero(ActionEvent s)
	{
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas)
		{
			this.agenda.borrarGenero(this.listaGenero.get(fila));
		}
		this.refrescarTablaGenero();
	}

}
