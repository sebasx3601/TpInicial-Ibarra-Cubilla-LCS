package main;

import dto.PersonaDTO;
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
		modelo.agregarPersona(new PersonaDTO(0,"Sebastian","550436","","","","",1,"","1999-05-27",1,1));
		modelo.agregarPersona(new PersonaDTO(0,"Pehuen","02320 456123","","","","",2,"","1995-03-13",1,1));
		modelo.agregarPersona(new PersonaDTO(0,"Norma","123572","","","","",5,"","1998-01-12",1,1));
		modelo.agregarPersona(new PersonaDTO(0,"Juan","442923","","","","",6,"","1995-09-22",1,1));
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