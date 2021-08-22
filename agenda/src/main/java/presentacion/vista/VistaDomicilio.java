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

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class VistaDomicilio
{
	private JFrame frame;
	private JTable tablaPais;
	private JButton btnAgregarPais;
	private JButton btnEditarPais;
	private JButton btnBorrarPais;
	private JButton btnSalir;
	private DefaultTableModel modelPais;
	private DefaultTableModel modelProvincia;
	private DefaultTableModel modelLocalidad;
	private  String[] paisColumnas = {"Pais"};
	private  String[] provinciaColumnas = {"Provincia"};
	private  String[] localidadColumnas = {"Pais"};
	private JScrollPane spProvincia;
	private JScrollPane spLocalidad;
	private JTable tablaProvincia;
	private JTable tablaLocalidad;
	private JButton btnAgregarProvincia;
	private JButton btnEditarProvincia;
	private JButton btnBorrarProvincia;
	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	private JButton btnBorrarLocalidad;
	
	private JButton btnMostrarProvincias;
	
	private JScrollPane spPais;
	private JButton btnMostrarLocalidades;

	public VistaDomicilio() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 465, 555);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		spPais = new JScrollPane();
		spPais.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spPais.setBounds(10, 11, 130, 337);
		panel.add(spPais);
		
		modelPais = new DefaultTableModel(null,paisColumnas);
		tablaPais = new JTable(modelPais);
		
		tablaPais.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPais.getColumnModel().getColumn(0).setResizable(false);
		
		spPais.setViewportView(tablaPais);
		
		modelProvincia = new DefaultTableModel(null,provinciaColumnas);
		tablaProvincia = new JTable(modelProvincia);
		
		tablaProvincia.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProvincia.getColumnModel().getColumn(0).setResizable(false);
		
		spProvincia = new JScrollPane();
		spProvincia.setBounds(168, 11, 130, 337);
		panel.add(spProvincia);
		
		spProvincia.setViewportView(tablaProvincia);
		
		modelLocalidad = new DefaultTableModel(null,localidadColumnas);
		tablaLocalidad = new JTable(modelLocalidad);
		
		tablaLocalidad.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaLocalidad.getColumnModel().getColumn(0).setResizable(false);
		
		btnAgregarPais = new JButton("Agregar");
		btnAgregarPais.setBounds(10, 359, 130, 38);
		panel.add(btnAgregarPais);
		
		btnEditarPais = new JButton("Editar");
		btnEditarPais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarPais.setBounds(10, 408, 130, 38);
		panel.add(btnEditarPais);
		
		btnBorrarPais = new JButton("Borrar");
		btnBorrarPais.setBounds(10, 457, 130, 38);
		panel.add(btnBorrarPais);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalir.setBounds(376, 521, 89, 23);
		panel.add(btnSalir);
		
		
		
		spLocalidad = new JScrollPane();
		spLocalidad.setBounds(318, 11, 130, 337);
		panel.add(spLocalidad);
		spLocalidad.setViewportView(tablaLocalidad);
		
		btnAgregarProvincia = new JButton("Agregar");
		btnAgregarProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregarProvincia.setBounds(168, 359, 130, 38);
		panel.add(btnAgregarProvincia);
		
		btnEditarProvincia = new JButton("Editar");
		btnEditarProvincia.setBounds(168, 408, 130, 38);
		panel.add(btnEditarProvincia);
		
		btnBorrarProvincia = new JButton("Borrar");
		btnBorrarProvincia.setBounds(168, 457, 130, 38);
		panel.add(btnBorrarProvincia);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(318, 359, 130, 38);
		panel.add(btnAgregarLocalidad);
		
		btnEditarLocalidad = new JButton("Editar");
		btnEditarLocalidad.setBounds(318, 408, 130, 38);
		panel.add(btnEditarLocalidad);
		
		btnBorrarLocalidad = new JButton("Borrar");
		btnBorrarLocalidad.setBounds(318, 457, 130, 38);
		panel.add(btnBorrarLocalidad);
		
		btnMostrarProvincias = new JButton("Mostrar provincias");
		btnMostrarProvincias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMostrarProvincias.setBounds(10, 506, 130, 38);
		panel.add(btnMostrarProvincias);
		
		btnMostrarLocalidades = new JButton("Mostrar localidades");
		btnMostrarLocalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMostrarLocalidades.setBounds(168, 506, 130, 38);
		panel.add(btnMostrarLocalidades);
	}
	
	public JButton getBtnEditarProvincia() {
		return btnEditarProvincia;
	}


	public JButton getBtnEditarPais() {
		return btnEditarPais;
	}


	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}


	public JButton getBtnAgregarProvincia() {
		return btnAgregarProvincia;
	}

	public JButton getBtnBorrarProvincia() {
		return btnBorrarProvincia;
	}


	public JButton getBtnBorrarLocalidad() {
		return btnBorrarLocalidad;
	}


	public JScrollPane getSpPais() {
		return spPais;
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
	
	public JButton getBtnAgregarPais() 
	{
		return btnAgregarPais;
	}

	public JButton getBtnBorrarPais() 
	{
		return btnBorrarPais;
	}
	
	public JButton getBtnSalir() 
	{
		return btnSalir;
	}
	
	public DefaultTableModel getModelPais() 
	{
		return modelPais;
	}
	
	public DefaultTableModel getModelProvincia() {
		return modelProvincia;
	}
	
	private DefaultTableModel getModelLocalidad() {
		return modelLocalidad;
	}
	
	public JTable getTablaPais()
	{
		return tablaPais;
	}
	
	public JTable getTablaProvincia()
	{
		return tablaProvincia;
	}

	public JTable getTablaLocalidad()
	{
		return tablaLocalidad;
	}
	
	public String[] getNombreColumnas() 
	{
		return paisColumnas;
	}

	public String[] getNombreColumnaPais() 
	{
		return paisColumnas;//pais
	}
	
	public JButton getBtnMostrarProvincias() {
		return btnMostrarProvincias;
	}
	
	public JButton getBtnMostrarLocalidades() {
		return btnMostrarLocalidades;
	}

	
	public void llenarTablaPais(List<PaisDTO> paisEnTablas) {
		this.getModelPais().setRowCount(0); //Para vaciar la tabla
		this.getModelPais().setColumnCount(0);
		this.getModelPais().setColumnIdentifiers(this.getNombreColumnas());
		
		for (PaisDTO p: paisEnTablas) {
			String nombre = p.getNombrePais();
			String [] fila = {nombre};
			this.getModelPais().addRow(fila);
		}
		
	}
	
	public void llenarTablaProvincia(List<ProvinciaDTO> provinciasEnTablas) {
		this.getModelProvincia().setRowCount(0); //Para vaciar la tabla
		this.getModelProvincia().setColumnCount(0);
		this.getModelProvincia().setColumnIdentifiers(this.getNombreColumnas());
		
		for (ProvinciaDTO p: provinciasEnTablas) {
			String nombre = p.getNombreProvincia();
			String [] fila = {nombre};
			this.getModelProvincia().addRow(fila);
		}
	}
	
	public void llenarTablaLocalidad(List<LocalidadDTO> localidadesEnTablas) {
		this.getModelLocalidad().setRowCount(0); //Para vaciar la tabla
		this.getModelLocalidad().setColumnCount(0);
		this.getModelLocalidad().setColumnIdentifiers(this.getNombreColumnas());
		
		for (LocalidadDTO p: localidadesEnTablas) {
			String nombre = p.getNombreLocalidad();
			String [] fila = {nombre};
			this.getModelLocalidad().addRow(fila);
		}
	}
}
