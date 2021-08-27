package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
		
		private List<PaisDTO> paisEnLista;
		
		private List<LocalidadDTO> localidadEnLista;
		
		private List<ProvinciaDTO> provinciaEnLista;
		
		private PersonaDTO personaEditando;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.ventanaPersona = VentanaPersona.getInstance();
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.agenda = agenda;
			asignarCodigoAComboBoxes();
			
			this.vista.getBtnEditar().addActionListener(p->abrirEditarUnaPersona(p));
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.reiniciarComboBoxTipoContacto();
			llenarComboBoxTipoContacto();
			ventanaPersona.llenarComboBoxPais(agenda.obtenerPaises());
			this.ventanaPersona.mostrarVentana();
			
			this.ventanaPersona.getBtnAgregarPersona().setText("Agregar");
			
			for (ActionListener listener : this.ventanaPersona.getBtnAgregarPersona().getActionListeners())
		    {
				this.ventanaPersona.getBtnAgregarPersona().removeActionListener(listener);
		    }
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
		}
		
		private void abrirEditarUnaPersona(ActionEvent a) {
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			if(filasSeleccionadas.length == 0) {
				return;
			}
			personaEditando = personasEnTabla.get(filasSeleccionadas[0]);
			
			ventanaPersona.getTxtAltura().setText(personaEditando.getAltura());
			ventanaPersona.getTxtCalle().setText(personaEditando.getCalle());
			ventanaPersona.getTxtDepto().setText(personaEditando.getDepto());
			ventanaPersona.getTxtDireccionEmail().setText(personaEditando.getDireccionEmail());
			ventanaPersona.getTxtNombre().setText(personaEditando.getNombre());
			ventanaPersona.getTxtPiso().setText(personaEditando.getPiso());
			ventanaPersona.getTxtTelefono().setText(personaEditando.getTelefono());
			/*
			for(ActionListener i: ventanaPersona.getComboBoxPais().getActionListeners()) {
				ventanaPersona.getComboBoxPais().removeActionListener(i);
			}
			for(ActionListener i: ventanaPersona.getComboBoxProvincia().getActionListeners()) {
				ventanaPersona.getComboBoxProvincia().removeActionListener(i);
			}
			*/
			
			int provinciaId = agenda.getLocalidad(personaEditando.getLocalidad()).getIdProvincia();
			int paisId = agenda.getProvincias(provinciaId).getIdPais();
			this.ventanaPersona.llenarComboBoxPais(agenda.obtenerPaises());
			ventanaPersona.getComboBoxPais().setSelectedItem(agenda.getPais(paisId).getNombrePais());
			
			this.ventanaPersona.llenarComboBoxProvincia(agenda.obtenerProvinciaDePaises(paisId));
			ventanaPersona.getComboBoxProvincia().setSelectedItem(agenda.getProvincias(provinciaId).getNombreProvincia());
			
			this.ventanaPersona.llenarComboBoxLocalidad(agenda.obtenerLocalidadDeProvincia(provinciaId));
			ventanaPersona.getComboBoxLocalidad().setSelectedItem(agenda.getLocalidad(personaEditando.getLocalidad()).getNombreLocalidad());
			
			/*
			ventanaPersona.getComboBoxMes().setSelectedIndex(personaEditando.ge);
			ventanaPersona.getComboBoxAnio().setText();
			ventanaPersona.getcBDia().setText();
			*/
			for (ActionListener listener : this.ventanaPersona.getBtnAgregarPersona().getActionListeners())
		    {
				this.ventanaPersona.getBtnAgregarPersona().removeActionListener(listener);
		    }
			this.ventanaPersona.getBtnAgregarPersona().setText("Editar");
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->editarPersona(p));
			this.ventanaPersona.mostrarVentana();
		}
		
		private void editarPersona(ActionEvent p) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String calle = ventanaPersona.getTxtCalle().getText();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String depto = ventanaPersona.getTxtDepto().getText();
			
			int localidad = 0;
			if(ventanaPersona.getComboBoxLocalidad().getSelectedIndex() == -1 || localidadEnLista.size() == 0) {
				localidad = 0;
			}else {
				localidad = this.localidadEnLista.get(ventanaPersona.getComboBoxLocalidad().getSelectedIndex()).getId();
			}
			
			String direccionEmail = ventanaPersona.getTxtDireccionEmail().getText();
			String fechaCumple= ventanaPersona.TomarCombobox();
			//p.ll
			
			//String tipoContacto = ventanaPersona.getValorSeleccionadoTipoContacto();
			String tipoContacto = ventanaPersona.getSeleccionadoTipoContacto();
			int intTipoContacto = obtenerIdTipoContacto(tipoContacto);
			
			personaEditando.setAltura(altura);
			personaEditando.setCalle(calle);
			personaEditando.setDepto(depto);
			personaEditando.setDireccionEmail(direccionEmail);
			personaEditando.setFechaCumple(fechaCumple);
			personaEditando.setLocalidad(localidad);
			personaEditando.setNombre(nombre);
			personaEditando.setPiso(piso);
			personaEditando.setTelefono(tel);
			personaEditando.setTipoContacto(intTipoContacto);
			
			agenda.editarPersona(personaEditando);
			
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}

		private void guardarPersona(ActionEvent p) { //Parece que lo ignora github
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			String calle = ventanaPersona.getTxtCalle().getText();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String depto = ventanaPersona.getTxtDepto().getText();
			
			int localidad = 0;
			if(ventanaPersona.getComboBoxLocalidad().getSelectedIndex() == -1 || localidadEnLista.size() == 0) {
				localidad = 0;
			}else {
				localidad = this.localidadEnLista.get(ventanaPersona.getComboBoxLocalidad().getSelectedIndex()).getId();
			}
			
			String direccionEmail = ventanaPersona.getTxtDireccionEmail().getText();
			String fechaCumple= ventanaPersona.TomarCombobox();
			//p.ll
			
			//String tipoContacto = ventanaPersona.getValorSeleccionadoTipoContacto();
			String tipoContacto = ventanaPersona.getSeleccionadoTipoContacto();
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
			List<PaisDTO> paises = agenda.obtenerPaises();
			List<ProvinciaDTO> provincias = agenda.obtenerProvincia();
			List<LocalidadDTO> localidades = agenda.obtenerLocalidad();
			this.vista.llenarTabla(this.personasEnTabla, paises, provincias, localidades, agenda.obtenerTiposDeContacto());
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
		
		public void cambioUnPais(ItemEvent e) {
			int idPaisSeleccionado = ventanaPersona.getComboBoxPais().getSelectedIndex();
			if(idPaisSeleccionado == -1) {
				return;
			}
			paisEnLista = agenda.obtenerPaises();
			if(paisEnLista.size() == 0) {
				return;
			}
			int idPais = paisEnLista.get(idPaisSeleccionado).getId();
			
			ventanaPersona.llenarComboBoxProvincia(agenda.obtenerProvinciaDePaises(idPais));
			provinciaEnLista = agenda.obtenerProvinciaDePaises(idPais);
			if(provinciaEnLista.size() == 0) {
				ventanaPersona.llenarComboBoxLocalidad(agenda.obtenerLocalidadDeProvincia(-1));
				this.localidadEnLista = agenda.obtenerLocalidadDeProvincia(-1);
				return;
			}
			ventanaPersona.llenarComboBoxLocalidad(agenda.obtenerLocalidadDeProvincia(provinciaEnLista.get(0).getId()));
			this.localidadEnLista = agenda.obtenerLocalidadDeProvincia(provinciaEnLista.get(0).getId());
			/*
			int idProvincia = ventanaPersona.getComboBoxProvincia().getSelectedIndex();
			if(idProvincia == -1) {
				return;
			}
			ventanaPersona.llenarComboBoxLocalidad(agenda.obtenerLocalidadDeProvincia(provinciaEnLista.get(idProvincia).getId()));
			*/
		}
		
		public void cambioUnProvincia(ItemEvent e) {
			/*
			int idProvincia = ventanaPersona.getComboBoxProvincia().getSelectedIndex();
			if(idProvincia == -1) {
				return;
			}
			localidadEnLista = agenda.obtenerLocalidadDeProvincia(agenda.getProvincias(idProvincia+1).getId());
			ventanaPersona.llenarComboBoxLocalidad(localidadEnLista);
			*/
			int idProvincia = ventanaPersona.getComboBoxProvincia().getSelectedIndex();
			if(provinciaEnLista == null) {
				return;
			}
			if(idProvincia == -1) {
				return;
			}
			idProvincia = provinciaEnLista.get(idProvincia).getId();
			localidadEnLista = agenda.obtenerLocalidadDeProvincia(idProvincia);
			ventanaPersona.llenarComboBoxLocalidad(localidadEnLista);
		}
		
		public void asignarCodigoAComboBoxes() {
			ventanaPersona.getComboBoxPais().addItemListener(e->cambioUnPais(e));
			ventanaPersona.getComboBoxProvincia().addItemListener(e->cambioUnProvincia(e));
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
