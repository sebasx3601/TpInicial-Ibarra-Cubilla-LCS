package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Agenda;
import presentacion.vista.Vista;
import presentacion.vista.VistaDomicilio;

public class ControladorUbicacion implements ActionListener {
	
	private List<PaisDTO> paisEnTablas;
	private VistaDomicilio vista;
	private Agenda agenda;
	private List<ProvinciaDTO> provinciaEnTablas;
	private List<LocalidadDTO> localidadEnTablas;
	
	public ControladorUbicacion(VistaDomicilio vista, Agenda agenda) {
		this.vista = vista;
		this.agenda = agenda;
		this.vista.getBtnMostrarProvincias().addActionListener(s->seleccionPais(s));
		this.vista.getBtnMostrarLocalidades().addActionListener(s->seleccionProvincia(s));
		
		this.vista.getBtnBorrarPais().addActionListener(s->borrarPaisBoton(s));
		
	}
	
	public void inicializar()
	{
		this.refrescarTablaPais();
		this.vista.show();
	}
	
	private void refrescarTablaPais()
	{
		this.paisEnTablas = agenda.obtenerPaises();
		this.vista.llenarTablaPais(this.paisEnTablas);
	}
	
	
	private void seleccionPais(ActionEvent p) {
		int[] filasSeleccionadas = this.vista.getTablaPais().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		refrescarTablaProvincia(paisEnTablas.get(filasSeleccionadas[0]).getId());
		refrescarTablaLocalidad(-1);
	}
	
	private void refrescarTablaProvincia(int idPais)
	{
		this.provinciaEnTablas = agenda.obtenerProvinciaDePaises(idPais);
		this.vista.llenarTablaProvincia(this.provinciaEnTablas);
	}
	
	private void seleccionProvincia(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaProvincia().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		refrescarTablaLocalidad(paisEnTablas.get(filasSeleccionadas[0]).getId());
	}
	
	private void refrescarTablaLocalidad(int idProvincia)
	{
		this.localidadEnTablas = agenda.obtenerLocalidadDeProvincia(idProvincia);
		this.vista.llenarTablaLocalidad(this.localidadEnTablas);
	}
	
	//Acciones
	private void agregarPais(ActionEvent s) {
		//programa para agregar un pais
		//getTxtNombre().getText();
		String nombre = "";
		if(nombre == null || nombre.equals("")) {
			return;
		}
		if(yaExistePaisConNombre(nombre)) {
			return;
		}
		agenda.agregarPais(new PaisDTO(0,nombre));
		refrescarTablaPais();
	}
	
	private boolean yaExistePaisConNombre(String nombrePais) {
		boolean yaExiste = false;
		List<PaisDTO> listaPaises = agenda.obtenerPaises();
		for(PaisDTO p: listaPaises) {
			if(p.getNombrePais().equals(nombrePais)) {
				yaExiste = true;
			}
		}
		return yaExiste;
	}
	
	private void borrarPaisBoton(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPais().getSelectedRows();
		for (int fila : filasSeleccionadas)
		{
			int idPais = this.paisEnTablas.get(fila).getId();
			//FALTA DECLARAR EN INTERFACE Y EN DAOSQL LA QUERY PARA BORRAR PROVINCIAS POR IDPAIS
			this.agenda.borrarPais(this.paisEnTablas.get(fila));
		}
		refrescarTablaPais();
		refrescarTablaProvincia(-1);
		refrescarTablaLocalidad(-1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { }

}
