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
import presentacion.vista.AgregarLocalidad;
import presentacion.vista.AgregarPais;
import presentacion.vista.AgregarProvincia;
import presentacion.vista.Vista;
import presentacion.vista.VistaDomicilio;

public class ControladorUbicacion implements ActionListener {
	
	private List<PaisDTO> paisEnTablas;
	private VistaDomicilio vista;
	private Agenda agenda;
	private List<ProvinciaDTO> provinciaEnTablas;
	private List<LocalidadDTO> localidadEnTablas;
	
	private AgregarPais vistaAgregarPais;
	private AgregarProvincia vistaAgregarProvincia;
	
	private AgregarLocalidad vistaAgregarLocalidad;
	
	private PaisDTO paisEditando;
	private ProvinciaDTO provinciaEditando;
	private LocalidadDTO localidadEditando;
	
	public ControladorUbicacion(VistaDomicilio vista, Agenda agenda) {
		this.vista = vista;
		this.agenda = agenda;
		this.vista.getBtnMostrarProvincias().addActionListener(s->seleccionPais(s));
		this.vista.getBtnMostrarLocalidades().addActionListener(s->seleccionProvincia(s));
		
		this.vista.getBtnBorrarPais().addActionListener(s->borrarPaisBoton(s));
		this.vista.getBtnBorrarProvincia().addActionListener(s->borrarProvinciaBoton(s));
		this.vista.getBtnBorrarLocalidad().addActionListener(s->borrarLocalidadBoton(s));
		
		this.vistaAgregarPais = AgregarPais.getInstance();
		this.vistaAgregarPais.getBtnAgregarPais().addActionListener(s->agregarPais(s));
		this.vista.getBtnAgregarPais().addActionListener(s->abrirVentanaAgregarPais(s));
		
		vistaAgregarProvincia = AgregarProvincia.getInstance();
		this.vista.getBtnAgregarProvincia().addActionListener(s->abrirVentanaAgregarProvincia(s));
		this.vistaAgregarProvincia.getBtnAgregarProvincia().addActionListener(s->agregarProvincia(s));
		
		this.vistaAgregarLocalidad = AgregarLocalidad.getInstance();
		this.vista.getBtnAgregarLocalidad().addActionListener(s->abrirVentanaLocalidad(s));
		this.vistaAgregarLocalidad.getBtnAgregarLocalidad().addActionListener(s->agregarLocalidad(s));
		
		this.vista.getBtnEditarPais().addActionListener(s->abrirVentanaEditarPais(s));
		this.vista.getBtnEditarProvincia().addActionListener(s->editarProvinciaBoton(s));
		
		this.vista.getBtnEditarLocalidad().addActionListener(s->editarLocalidadBoton(s));
		vistaAgregarLocalidad.setAgenda(agenda);
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
		refrescarTablaLocalidad(provinciaEnTablas.get(filasSeleccionadas[0]).getId());
	}
	
	private void refrescarTablaLocalidad(int idProvincia)
	{
		this.localidadEnTablas = agenda.obtenerLocalidadDeProvincia(idProvincia);
		this.vista.llenarTablaLocalidad(this.localidadEnTablas);
	}
	
	//Acciones
	private void abrirVentanaAgregarPais(ActionEvent s) {
		this.vistaAgregarPais.getLblAgregarPais().setText("Agregar pais: ");
		this.vistaAgregarPais.getLblNombre().setText("Nombre");
		this.vistaAgregarPais.getBtnAgregarPais().setText("Agregar");
		this.vistaAgregarPais.getTxtNombrePais().setText("");
		this.vistaAgregarPais.getBtnAgregarPais().addActionListener(a->agregarPais(a));
		this.vistaAgregarPais.mostrarVentana();
	}
	
	private void agregarPais(ActionEvent s) {
		String nombre = this.vistaAgregarPais.getTxtNombrePais().getText();
		if(!esValidoNombrePais(nombre)) {
			return;
		}
		agenda.agregarPais(new PaisDTO(0,nombre));
		refrescarTablaPais();
		this.vistaAgregarPais.cerrar();
	}
	
	private boolean esValidoNombrePais(String nombre) {
		if(nombre == null || nombre.equals("")) {
			return false;
		}
		if(yaExistePaisConNombre(nombre)) {
			return false;
		}
		return true;
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
		int idPais;
		int[] filasSeleccionadas = this.vista.getTablaPais().getSelectedRows();
		for (int fila : filasSeleccionadas)
		{
			idPais = this.paisEnTablas.get(fila).getId();
			borrarProvincias(idPais);
			this.agenda.borrarPais(this.paisEnTablas.get(fila));
		}
		refrescarTablaPais();
		refrescarTablaProvincia(-1);
		refrescarTablaLocalidad(-1);
	}
	
	private void borrarProvincias(int idPais) {
		int idProvincia;
		List<ProvinciaDTO> provinciasABorrar;
		provinciasABorrar = agenda.obtenerProvinciaDePaises(idPais);
		for(ProvinciaDTO p: provinciasABorrar) {
			idProvincia = p.getId();
			borrarLocalidades(idProvincia);
			agenda.borrarProvincia(p);
		}
	}
	
	private void borrarLocalidades(int idProvincia) {
		List<LocalidadDTO> localidadesABorrar;
		localidadesABorrar = agenda.obtenerLocalidadDeProvincia(idProvincia);
		for(LocalidadDTO l: localidadesABorrar) {
			agenda.borrarLocalidad(l);
		}
	}
	
	private void borrarProvinciaBoton(ActionEvent e) {
		int idProvincia;
		int[] filasSeleccionadas = this.vista.getTablaProvincia().getSelectedRows();
		for (int fila : filasSeleccionadas)
		{
			idProvincia = this.provinciaEnTablas.get(fila).getId();
			borrarLocalidades(idProvincia);
			this.agenda.borrarProvincia(this.provinciaEnTablas.get(fila));
		}
		refrescarTablaPais();
		refrescarTablaProvincia(-1);
		refrescarTablaLocalidad(-1);
	}
	
	private void borrarLocalidadBoton(ActionEvent e) {
		int[] filasSeleccionadas = this.vista.getTablaLocalidad().getSelectedRows();
		for (int fila : filasSeleccionadas)
		{
			this.agenda.borrarLocalidad(this.localidadEnTablas.get(fila));
		}
		refrescarTablaPais();
		refrescarTablaProvincia(-1);
		refrescarTablaLocalidad(-1);
	}
	
	public void abrirVentanaAgregarProvincia(ActionEvent e) {
		if(agenda.obtenerPaises().size() == 0) {
			return;
		}
		this.vistaAgregarProvincia.llenarComboBoxPais(agenda.obtenerPaises());
		
		this.vistaAgregarProvincia.getLblAgregarProvincia().setText("Agregar provincia");
		this.vistaAgregarProvincia.getBtnAgregarProvincia().setText("Agregar");
		
		this.vistaAgregarProvincia.mostrarVentana();
		this.vistaAgregarProvincia.getBtnAgregarProvincia().addActionListener(s->agregarProvincia(s));
	}
	
	public void agregarProvincia(ActionEvent e) {
		String nombre = this.vistaAgregarProvincia.getTxtProvincia().getText();
		int idPaisSeleccionado = this.vistaAgregarProvincia.getIdPaisSeleccionado();
		if(!esNombreValidoProvincia(nombre, idPaisSeleccionado)) {
			return;
		}
		
		agenda.agregarProvincia(new ProvinciaDTO(0,idPaisSeleccionado,nombre));
		refrescarTablaPais();
		refrescarTablaProvincia(-1);
		refrescarTablaLocalidad(-1);
	}
	
	private boolean esNombreValidoProvincia(String nombre,int idPais) {
		if(nombre == null || nombre.equals("")) {
			return false;
		}
		if(yaExisteProvinciaConNombre(nombre, idPais)) {
			return false;
		}
		return true;
	}
	
	private boolean yaExisteProvinciaConNombre(String nombreProvincia, int idPais) {
		boolean yaExiste = false;
		List<ProvinciaDTO> listaProvincias = agenda.obtenerProvinciaDePaises(idPais);
		for(ProvinciaDTO p: listaProvincias) {
			if(p.getNombreProvincia().equals(nombreProvincia)) {
				yaExiste = true;
			}
		}
		return yaExiste;
	}
	
	private void abrirVentanaLocalidad(ActionEvent s) {
		this.vistaAgregarLocalidad.getBtnAgregarLocalidad().addActionListener(a->agregarLocalidad(a));
		this.vistaAgregarLocalidad.getLblAgregarLocalidad().setText("Agregar localidad");
		this.vistaAgregarLocalidad.getBtnAgregarLocalidad().setText("Agregar");
		vistaAgregarLocalidad.llenarComboBoxPais(agenda.obtenerPaises());
		this.vistaAgregarLocalidad.mostrarVentana();
	}
	
	private void agregarLocalidad(ActionEvent s) {
		String nombre = this.vistaAgregarLocalidad.getTxtLocalidad().getText();
		int idProvincia = this.vistaAgregarLocalidad.getIdProvinciaSeleccionado();
		if(!esNombreValidoLocalidad(nombre,idProvincia)) {
			return;
		}
		if(idProvincia == -1) {
			return;
		}
		
		agenda.agregarLocalidad(new LocalidadDTO(0,nombre,idProvincia));
		refrescarTablaLocalidad(-1);
		vistaAgregarLocalidad.cerrar();
	}
	
	private boolean esNombreValidoLocalidad(String nombre,int idProvincia) {
		if(nombre == null || nombre.equals("")) {
			return false;
		}
		if(yaExisteLocalidadConNombre(nombre, idProvincia)) {
			return false;
		}
		return true;
	}
	
	private boolean yaExisteLocalidadConNombre(String nombreLocalidad, int idProvincia) {
		boolean yaExiste = false;
		List<LocalidadDTO> listaLocalidad = agenda.obtenerLocalidad();
		for(LocalidadDTO p: listaLocalidad) {
			if(p.getNombreLocalidad().equals(nombreLocalidad) && p.getIdProvincia() == idProvincia) {
				yaExiste = true;
			}
		}
		return yaExiste;
	}
	
	//
	private void abrirVentanaEditarPais(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPais().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		this.vistaAgregarPais.getLblAgregarPais().setText("Editar pais: ");
		this.vistaAgregarPais.getLblNombre().setText("Nuevo nombre");
		this.vistaAgregarPais.getBtnAgregarPais().setText("Editar");
		this.vistaAgregarPais.getTxtNombrePais().setText(paisEnTablas.get(filasSeleccionadas[0]).getNombrePais());
		this.vistaAgregarPais.getBtnAgregarPais().addActionListener(a->editarPais(a));
		paisEditando = paisEnTablas.get(filasSeleccionadas[0]);
		this.vistaAgregarPais.mostrarVentana();
	}
	
	private void editarPais(ActionEvent s) {
		String nombre = this.vistaAgregarPais.getTxtNombrePais().getText();
		if(!esValidoNombrePais(nombre)) {
			return;
		}
		paisEditando.setNombrePais(nombre);
		agenda.editarPais(paisEditando);
		refrescarTablaPais();
		this.vistaAgregarPais.cerrar();
	}
	
	private void editarProvinciaBoton(ActionEvent s) {
		/*
		int idProvincia;
		int[] filasSeleccionadas = this.vista.getTablaProvincia().getSelectedRows();
		ProvinciaDTO p;
		for (int fila : filasSeleccionadas)
		{
			p = this.provinciaEnTablas.get(fila);
			p.setNombreProvincia("");
			
			editarProvincia(idProvincia);
			this.agenda.borrarProvincia(this.provinciaEnTablas.get(fila));
		}
		refrescarTablaPais();
		refrescarTablaProvincia(-1);
		refrescarTablaLocalidad(-1);
		 */
		int idProvincia;
		int[] filasSeleccionadas = this.vista.getTablaProvincia().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		provinciaEditando = this.provinciaEnTablas.get(filasSeleccionadas[0]);
		this.vistaAgregarProvincia.getLblAgregarProvincia().setText("Editar provincia");
		this.vistaAgregarProvincia.getBtnAgregarProvincia().setText("Editar");
		if(agenda.obtenerPaises().size() == 0) {
			return;
		}
		this.vistaAgregarProvincia.llenarComboBoxPais(agenda.obtenerPaises());
		this.vistaAgregarProvincia.getBtnAgregarProvincia().addActionListener(a->editarProvincia(a));
		
		this.vistaAgregarProvincia.getTxtProvincia().setText(provinciaEditando.getNombreProvincia());
		this.vistaAgregarProvincia.getComboBoxPais().setSelectedIndex(provinciaEditando.getIdPais()-1);
		
		this.vistaAgregarProvincia.mostrarVentana();
	}
	
	private void editarProvincia(ActionEvent s) {
		String nombre = this.vistaAgregarProvincia.getTxtProvincia().getText();
		int idPais = this.vistaAgregarProvincia.getIdPaisSeleccionado();
		if(!this.esNombreValidoProvincia(nombre,idPais)) {
			return;
		}
		provinciaEditando.setNombreProvincia(nombre);
		provinciaEditando.setIdPais(idPais);
		agenda.editarProvincia(provinciaEditando);
		refrescarTablaPais();
		refrescarTablaProvincia(-1);
		refrescarTablaLocalidad(-1);
	}
	
	private void editarLocalidadBoton(ActionEvent s) {
		this.vistaAgregarLocalidad.getBtnAgregarLocalidad().addActionListener(a->editarLocalidad(a));
		int[] filasSeleccionadas = this.vista.getTablaLocalidad().getSelectedRows();
		if(filasSeleccionadas.length == 0) {
			return;
		}
		localidadEditando = localidadEnTablas.get(filasSeleccionadas[0]);
		vistaAgregarLocalidad.getLblAgregarLocalidad().setText("Editar localidad");
		vistaAgregarLocalidad.getBtnAgregarLocalidad().setText("Editar");
		vistaAgregarLocalidad.getTxtLocalidad().setText(localidadEditando.getNombreLocalidad());
		vistaAgregarLocalidad.llenarComboBoxPais(agenda.obtenerPaises());
		
		List<ProvinciaDTO> prov = agenda.obtenerProvincia();
		ProvinciaDTO provinciaPerteneciente = prov.get(0);
		int idPais = 0;
		for(ProvinciaDTO pr: prov) {
			if(pr.getId() == localidadEditando.getIdProvincia()) {
				idPais = pr.getIdPais();
				provinciaPerteneciente = pr;
			}
		}
		if(idPais == 0) {	//No hay paises no abrir ventana
			return;
		}
		
		List<PaisDTO> pai = agenda.obtenerPaises();
		int indexPais = 0;
		for(PaisDTO pa: pai) {
			if(pa.getId() == idPais) {
				indexPais = pa.getId();
			}
		}
		if(indexPais == 0) {
			return;
		}
		vistaAgregarLocalidad.getComboBoxPais().setSelectedIndex(indexPais-1);
		vistaAgregarLocalidad.getComboBoxProvincia().setSelectedItem(provinciaPerteneciente.getNombreProvincia());
		vistaAgregarLocalidad.mostrarVentana();
		
	}
	
	private void editarLocalidad(ActionEvent s) {
		String nombre = vistaAgregarLocalidad.getTxtLocalidad().getText();
		int idProv = vistaAgregarLocalidad.getIdProvinciaSeleccionado();
		if(!esNombreValidoLocalidad(nombre, idProv)) {
			return;
		}
		localidadEditando.setNombreLocalidad(nombre);
		localidadEditando.setIdProvincia(idProv);
		agenda.editarLocalidad(localidadEditando);
		refrescarTablaPais();
		refrescarTablaProvincia(-1);
		refrescarTablaLocalidad(-1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { }

}
