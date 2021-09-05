package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import dto.GeneroDTO;
import dto.PersonaDTO;
import modelo.Agenda;
import modelo.inicializador;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.vista.InicializadorVisual;
import presentacion.vista.Vista;
import presentacion.vista.VistaContacto;
import presentacion.vista.VistaDomicilio;

public class ControladorInicializador implements ActionListener{
	
	String usuario;
	String contra;
	String servidor;
	String puerto;
	
	Agenda modelo;
	
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
		//vista.getBtnEmpezarDeNuevo().addActionListener(r->botonEmpezarDeNuevo(r));
		vista.getBtnIniciar().addActionListener(r->botonConectar(r));
	}
	
	public void botonConectar(ActionEvent a) {
		String usuario = vista.getTxtUsuario().getText();
		String contra = vista.getTxtContra().getText();
		String servidor = vista.getTxtServidor().getText();
		String puerto = vista.getTxtPuerto().getText();
		
		Map<String, String> datosConexion = new HashMap<String, String>();
		datosConexion.put("servidor", servidor);
		datosConexion.put("puerto", puerto);
		Conexion.guardarDatosInicio(datosConexion);
		
		modelo = new Agenda(new DAOSQLFactory());
		//abrirVentanas();
		iniciarAgenda();
	}
	
	public void botonEmpezarDeNuevo(ActionEvent a) {
		String usuario = vista.getTxtUsuario().getText();
		String contra = vista.getTxtContra().getText();
		String servidor = vista.getTxtServidor().getText();
		String puerto = vista.getTxtPuerto().getText();
		
		//2do comentado inicializador proto = new inicializador(usuario, contra, servidor, puerto);
		//proto.borrarBaseDatos(); las funciones borrar y crear fueron comentadas
		//proto.crearBaseDatos();
		//2do comentado proto.crearTodaLaBaseDeDatos();
		
		modelo = new Agenda(new DAOSQLFactory());
		//2do comentado insertarDatosIniciales();
		iniciarAgenda();
	}
	
	public void abrirVentanaInicio() {
		vista.show();
	}
	
	public void insertarDatosIniciales() {
		modelo.agregarPersona(new PersonaDTO(0,"Sebastian","550436","Jose Hernandez","1234","piso 1","No dept",1,"sebas@hotmail.com","1999-05-27",2,2));
		modelo.agregarPersona(new PersonaDTO(0,"Pehuen","02320 456123","Ituzaingo","1009","3","-",2,"Pehuen@gmail.com","1995-03-13",3,2));
		modelo.agregarPersona(new PersonaDTO(0,"Norma","123572","Calle falsa","1274","Uno","Dos",5,"NormaMaria@hotmail.com","1998-01-12",3,3));
		modelo.agregarPersona(new PersonaDTO(0,"Juan","442923","Tres tristes","6833","1","",7,"JuanJose@gmail.com","1995-09-22",4,2));
		modelo.agregarPersona(new PersonaDTO(0,"Francisco","442923","Green Hills","1232","1","",7,"NoMeHables@gmail.com","1995-09-22",3,4));
		modelo.agregarPersona(new PersonaDTO(0,"Manuel","442923","Uruguay","135","","",7,"No tiene","1995-09-22",1,1));
		
		modelo.agregarGenero(new GeneroDTO(1,"Sin especificar"));
		modelo.agregarGenero(new GeneroDTO(2,"Masculino"));
		modelo.agregarGenero(new GeneroDTO(3,"Femenino"));
		modelo.agregarGenero(new GeneroDTO(4,"Otro"));
	}
	
	public void iniciarAgenda() {
		this.vista.cerrar();
		Vista vista = new Vista();
		
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		/*
		ControladorTipoContacto contro = new ControladorTipoContacto(new VistaContacto(), modelo);
		contro.inicializar();
		
		ControladorUbicacion lastContro = new ControladorUbicacion(new VistaDomicilio(), modelo);
		lastContro.inicializar();
		
		
		ControladorGenero ep = new ControladorGenero(modelo);
		ep.inicializar();
		*/
	}

}
