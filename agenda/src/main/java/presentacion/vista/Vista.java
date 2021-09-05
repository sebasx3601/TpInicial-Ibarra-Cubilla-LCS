package presentacion.vista;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista
{
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre y apellido","Telefono","Calle","altura","piso","departamento","pais","provincia","localidad","E-Mail","cumplea�os","Tipo de contacto", "Genero"};

	private JButton btnEditar;
	
	private JButton btnUbicaciones;
	private JButton btnTipoContactos;
	private JButton btnGenero;

	public Vista() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 996, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 970, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 950, 182);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(2).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(3).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(4).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(5).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(5).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(6).setResizable(false);
		
		
		spPersonas.setViewportView(tablaPersonas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(307, 228, 89, 23);
		panel.add(btnReporte);
		
		btnUbicaciones = new JButton("ABM ubicaciones");
		btnUbicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUbicaciones.setBounds(455, 228, 125, 23);
		panel.add(btnUbicaciones);
		
		btnTipoContactos = new JButton("ABM tipo contactos");
		btnTipoContactos.setBounds(590, 228, 139, 23);
		panel.add(btnTipoContactos);
		
		btnGenero = new JButton("AMB genero");
		btnGenero.setBounds(739, 228, 125, 23);
		panel.add(btnGenero);
	}
	
	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir de la Agenda?", 
		             "Confirmación", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}
	
	public void llenarTabla(List<PersonaDTO> personasEnTabla, List<PaisDTO> paises, List<ProvinciaDTO> provincias, List<LocalidadDTO> localidades, List<TipoContactoDTO> tipos) {
		this.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla)
		{
			String nombre = p.getNombre();
			String tel = p.getTelefono();
			String calle= p.getCalle(); //aca
			String altura= p.getAltura(); 
			String piso = p.getPiso();
			String departamento = p.getDepto();
			String cumple= p.getFechaCumple();
			int IDlocalidad = p.getLocalidad();
			String direccion = p.getDireccionEmail();
			
			String tipoCont = "nada";
			int intTipoCont = p.getTipoContacto();
			for(TipoContactoDTO tip: tipos) {
				if(tip.getIdContacto() == intTipoCont) {
					tipoCont = tip.getNombreTipoContacto();
				}
			}
			
			String pais = "";
			String provincia = "";
			String localidad = "";
			
			//List<PaisDTO> paises, List<ProvinciaDTO> provincias, List<LocalidadDTO> localidades
			for(LocalidadDTO loc: localidades) {
				if(loc.getId() == IDlocalidad) {
					localidad = loc.getNombreLocalidad();
					for(ProvinciaDTO pro: provincias) {
						if(pro.getId() == loc.getIdProvincia()) {
							provincia = pro.getNombreProvincia();
							for(PaisDTO pai: paises) {
								if(pai.getId() == pro.getIdPais()) {
									pais = pai.getNombrePais();
								}
							}
						}
					}
				}
			}
			String genero = p.getDescrGenero();
			Object[] fila = {nombre, tel,calle,altura,piso,departamento,pais,provincia,localidad, direccion, cumple, tipoCont, genero};
			this.getModelPersonas().addRow(fila);
		}
	}
	
	public JButton getBtnUbicaciones() {
		return btnUbicaciones;
	}


	public JButton getBtnTipoContactos() {
		return btnTipoContactos;
	}


	public JButton getBtnGenero() {
		return btnGenero;
	}
	
	public void cerrarVentana() {
		frame.dispose();
	}
}
