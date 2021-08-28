package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modelo.Agenda;
import presentacion.vista.VistaGenero;

public class ControladorGenero {
	
	Agenda agenda;
	VistaGenero vista;
	
	public ControladorGenero(Agenda agenda) {
		this.agenda = agenda;
		vista = new VistaGenero();
	}
	
	public void inicializarBotones() {
		this.vista.getBtnEditar().addActionListener(p->ejemplo(p));
		this.vista.getBtnAgregar().addActionListener(p->ejemplo(p));
		this.vista.getBtnBorrar().addActionListener(p->ejemplo(p));
	}
	
	public void inicializar()
	{
		this.refrescarTabla();
		this.vista.show();
	}
	
	public void borrarBoton(JButton boton) {
		for(ActionListener l: boton.getActionListeners()) {
			boton.removeActionListener(l);
		}
	}
	
	public void ejemplo(ActionEvent a) {
		
	}
	
	public void refrescarTabla() {
		vista.llenarTabla(agenda.getGenero());
	}

}
