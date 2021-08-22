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
	
	@Override
	public void actionPerformed(ActionEvent e) { }

}
