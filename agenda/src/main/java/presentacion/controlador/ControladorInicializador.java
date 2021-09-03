package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.InicializadorVisual;

public class ControladorInicializador implements ActionListener{
	
	String usuario;
	String contra;
	String servidor;
	String puerto;
	
	private InicializadorVisual vista;
	
	public ControladorInicializador() {
		usuario = "";
		contra = "";
		servidor = "";
		puerto = "";
		vista = new InicializadorVisual();
		inicializarBotones();
		this.abrirVentanaInicio();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { }
	
	public void inicializarBotones() {
		vista.getBtnEmpezarDeNuevo().addActionListener(r->botonEmpezarDeNuevo(r));
		vista.getBtnIniciar().addActionListener(r->botonConectar(r));
	}
	
	public void botonConectar(ActionEvent a) {
		String usuario = vista.getTxtUsuario().getText();
		String contra = vista.getTxtContra().getText();
		String servidor = vista.getTxtServidor().getText();
		String puerto = vista.getTxtPuerto().getText();
		
		abrirVentanas();
	}
	
	public void botonEmpezarDeNuevo(ActionEvent a) {
		String usuario = vista.getTxtUsuario().getText();
		String contra = vista.getTxtContra().getText();
		String servidor = vista.getTxtServidor().getText();
		String puerto = vista.getTxtPuerto().getText();
		
		abrirVentanas();
	}
	
	public void abrirVentanaInicio() {
		vista.show();
	}
	
	public void abrirVentanas() {
		
	}

}
