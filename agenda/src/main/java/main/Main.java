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
		modelo.agregarPersona(new PersonaDTO(0,"Sebastian","550436","Jose Hernandez","1234","piso 1","No dept",1,"sebas@hotmail.com","1999-05-27",1,1));
		modelo.agregarPersona(new PersonaDTO(0,"Pehuen","02320 456123","Ituzaingo","1009","3","-",2,"Pehuen@gmail.com","1995-03-13",1,1));
		modelo.agregarPersona(new PersonaDTO(0,"Norma","123572","Calle falsa","1274","Uno","Dos",5,"NormaMaria@hotmail.com","1998-01-12",1,1));
		modelo.agregarPersona(new PersonaDTO(0,"Juan","442923","Tres tristes","6833","1","",7,"JuanJose@gmail.com","1995-09-22",1,1));
		modelo.agregarPersona(new PersonaDTO(0,"Francisco","442923","Green Hills","1232","1","",7,"NoMeHables@gmail.com","1995-09-22",1,1));
		modelo.agregarPersona(new PersonaDTO(0,"Manuel","442923","Uruguay","135","","",7,"No tiene","1995-09-22",1,1));
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