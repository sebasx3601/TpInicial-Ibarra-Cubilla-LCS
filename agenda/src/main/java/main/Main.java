package main;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorTipoContacto;
import presentacion.vista.VentanaAgregarTipoContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaContacto;


public class Main 
{

	public static void main(String[] args) 
	{
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		
		ControladorTipoContacto contro = new ControladorTipoContacto(new VistaContacto(), modelo);
		contro.inicializar();
		
	}
}
