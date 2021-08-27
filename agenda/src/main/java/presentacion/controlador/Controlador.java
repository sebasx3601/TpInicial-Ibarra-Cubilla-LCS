package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import presentacion.vista.fecha;
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
		
		private fecha ventanaParaCumple;
		
		private String anioCumpleCreado = "";
		private String mesCumpleCreado = "";
		private String diaCumpleCreado = "";
		
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
			
			this.ventanaParaCumple = fecha.getInstance();
			
			this.ventanaPersona.getBtnCumple().addActionListener(r->seleccionarAnio(r));
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
			
			anioCumpleCreado = "";
			mesCumpleCreado = "";
			diaCumpleCreado = "";
			this.ventanaPersona.getLblFechaCumpleElegido().setText("YYYY-MM-DD");
		}
		
		private void abrirEditarUnaPersona(ActionEvent a) {
			anioCumpleCreado = "";
			mesCumpleCreado = "";
			diaCumpleCreado = "";
			this.ventanaPersona.getLblFechaCumpleElegido().setText("YYYY-MM-DD");
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
			this.ventanaPersona.getLblFechaCumpleElegido().setText(personaEditando.getFechaCumple());
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
			//String fechaCumple= ventanaPersona.TomarCombobox();
			String fechaCumple= ventanaPersona.getLblFechaCumpleElegido().getText();
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
			//String fechaCumple= ventanaPersona.TomarCombobox();
			String fechaCumple= this.formarFecha();
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
		
		public void seleccionarAnio(ActionEvent a) {
			ventanaParaCumple.getLblFecha().setText("Seleccione año");
			ventanaParaCumple.getComboBox().removeAllItems();
			DefaultComboBoxModel valores = new DefaultComboBoxModel();
			ArrayList<String> val = new ArrayList<String>();
			for(int x = 0; x < 121; x++) {
				val.add(Integer.toString(x + 1900));
			}
			valores.addAll(val);
			ventanaParaCumple.getComboBox().setModel(valores);
			ventanaParaCumple.getComboBox().setSelectedIndex(1);
			
			for(ActionListener i: ventanaParaCumple.getBtnElegir().getActionListeners()) {
				ventanaParaCumple.getBtnElegir().removeActionListener(i);
			}
			
			ventanaParaCumple.getBtnElegir().addActionListener(e->elegirAnio(e));
			ventanaParaCumple.mostrarVentana();
		}
		
		public void elegirAnio(ActionEvent a) {
			anioCumpleCreado = ventanaParaCumple.getComboBox().getSelectedItem().toString();
			System.out.println(anioCumpleCreado);
			
			seleccionarMes();
		}
		
		public void seleccionarMes() {
			ventanaParaCumple.getLblFecha().setText("Seleccione mes");
			ventanaParaCumple.getComboBox().removeAllItems();
			DefaultComboBoxModel valores = new DefaultComboBoxModel();
			ventanaParaCumple.getComboBox().setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
			ventanaParaCumple.getComboBox().setSelectedIndex(0);
			
			for(ActionListener i: ventanaParaCumple.getBtnElegir().getActionListeners()) {
				ventanaParaCumple.getBtnElegir().removeActionListener(i);
			}
			ventanaParaCumple.getBtnElegir().addActionListener(e->elegirMes(e));
			
			ventanaParaCumple.mostrarVentana();
		}
		
		public void elegirMes(ActionEvent a) {
			mesCumpleCreado = Integer.toString(ventanaParaCumple.getComboBox().getSelectedIndex()+1);
			System.out.println(anioCumpleCreado);
			seleccionarDia();
		}
		
		public void seleccionarDia() {
			ventanaParaCumple.getLblFecha().setText("Seleccione dia");
			ventanaParaCumple.getComboBox().removeAllItems();
			
			int diaMaximo = 0;
			switch(mesCumpleCreado) {
				case("1"):
					diaMaximo = 31;
					break;
				case("2"):
					//28 o 29
					if(anioBisiesto(Integer.parseInt(anioCumpleCreado))) {
						diaMaximo = 29;
					}else {
						diaMaximo = 28;
					}
					break;
				case("3"):
					diaMaximo = 31;
					break;
				case("4"):
					diaMaximo = 30;
					break;
				case("5"):
					diaMaximo = 31;
					break;
				case("6"):
					diaMaximo = 30;
					break;
				case("7"):
					diaMaximo = 31;
					break;
				case("8"):
					diaMaximo = 31;
					break;
				case("9"):
					diaMaximo = 30;
					break;
				case("10"):
					diaMaximo = 31;
					break;
				case("11"):
					diaMaximo = 30;
					break;
				case("12"):
					diaMaximo = 31;
					break;
			}
			DefaultComboBoxModel valores = new DefaultComboBoxModel();
			ArrayList<String> val = new ArrayList<String>();
			for(int x = 1; x <= diaMaximo; x++) {
				val.add(Integer.toString(x));
			}
			valores.addAll(val);
			ventanaParaCumple.getComboBox().setModel(valores);
			ventanaParaCumple.getComboBox().setSelectedIndex(0);
			
			for(ActionListener i: ventanaParaCumple.getBtnElegir().getActionListeners()) {
				ventanaParaCumple.getBtnElegir().removeActionListener(i);
			}
			ventanaParaCumple.getBtnElegir().addActionListener(e->elegirDia(e));
			
			ventanaParaCumple.mostrarVentana();
		}
		
		public void elegirDia(ActionEvent a) {
			diaCumpleCreado = Integer.toString(ventanaParaCumple.getComboBox().getSelectedIndex()+1);
			System.out.println(anioCumpleCreado + " " + mesCumpleCreado + " " + diaCumpleCreado);
			ventanaParaCumple.cerrar();
			this.ventanaPersona.getLblFechaCumpleElegido().setText(formarFecha());
		}
		
		public boolean anioBisiesto(int anio) {
			if(anio % 4 == 0) {
				if(anio % 100 == 0) {
					if(anio % 400 == 0) {
						return true;
					}
					return false;
				}
				return true;
			}
			return false;
		}
		
		public String formarFecha() {
			if(diaCumpleCreado.equals("")) {
				return "0000-00-00";
			}
			return anioCumpleCreado+"-"+mesCumpleCreado+"-"+diaCumpleCreado;
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
}
