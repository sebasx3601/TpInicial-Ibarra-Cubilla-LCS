package main;

import modelo.Agenda;
import modelo.inicializador;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorGenero;
import presentacion.controlador.ControladorTipoContacto;
import presentacion.controlador.ControladorUbicacion;
import presentacion.vista.VentanaAgregarTipoContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaContacto;
import presentacion.vista.VistaDomicilio;


public class Main 
{

	public static void main(String[] args) 
	{
		inicializador proto = new inicializador();
		proto.borrarBaseDatos();
		proto.crearBaseDatos();
		proto.crearTodaLaBaseDeDatos();
		
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		
		ControladorTipoContacto contro = new ControladorTipoContacto(new VistaContacto(), modelo);
		contro.inicializar();
		
		ControladorUbicacion lastContro = new ControladorUbicacion(new VistaDomicilio(), modelo);
		lastContro.inicializar();
		
		ControladorGenero ep = new ControladorGenero(modelo);
		ep.inicializar();
	}
}
//prueba para poder pushearlas carpetas que el git no descarga