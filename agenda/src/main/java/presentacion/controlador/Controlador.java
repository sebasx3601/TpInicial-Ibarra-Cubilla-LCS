package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;


public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		
		private List<TipoContactoDTO> tiposDeContacto;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.agenda = agenda;
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.reiniciarComboBoxTipoContacto();
			llenarComboBoxTipoContacto();
			this.ventanaPersona.mostrarVentana();
		}

		private void guardarPersona(ActionEvent p) { //Parece que lo ignora github
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String calle = ventanaPersona.getTxtCalle().getText();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String depto = ventanaPersona.getTxtDepto().getText();
			int localidad = 1; //FALTA
			String direccionEmail = ventanaPersona.getTxtDireccionEmail().getText();
			Date fechaCumple = new Date(); //FALTA
			
			//String tipoContacto = ventanaPersona.getValorSeleccionadoTipoContacto();
			String tipoContacto = ventanaPersona.getSeleccionadoTipoContacto(); //Parece que lo ignora github
			int intTipoContacto = obtenerIdTipoContacto(tipoContacto);
			
			
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel, calle, altura, piso, depto, localidad, 
					direccionEmail, fechaCumple, intTipoContacto);
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}
		
		private int obtenerIdTipoContacto(String tipoContacto) {
			int intTipoContacto = 0;
			List<TipoContactoDTO> listaDeTiposContacto = agenda.obtenerTiposDeContacto();
			for(TipoContactoDTO l: listaDeTiposContacto) {
				if(tipoContacto.equals(l.getNombreTipoContacto())) {
					intTipoContacto = l.getIdContacto();
				}
			}
			return intTipoContacto;
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			
			this.refrescarTabla();
		}
		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.vista.llenarTabla(this.personasEnTabla);
		}
		
		private void llenarComboBoxTipoContacto()//Parece que lo ignora github
		{
			this.tiposDeContacto = agenda.obtenerTiposDeContacto();
			this.ventanaPersona.llenarComboBoxTipoContacto(tiposDeContacto);
		}
		
		public LocalidadDTO getLocalidad(int idLocalidad) {
			return agenda.getLocalidad(idLocalidad);
		}
		
		public ProvinciaDTO getProvincias(int idProvincia) {
			return agenda.getProvincias(idProvincia);
		}
		
		public PaisDTO getPais(int idPais) {
			return agenda.getPais(idPais);
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
