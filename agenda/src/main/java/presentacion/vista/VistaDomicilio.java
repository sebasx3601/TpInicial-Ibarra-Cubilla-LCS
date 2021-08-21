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

import dto.PersonaDTO;

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
	private  String[] domicilioColumnas = {"Pais"};
	private  String[] provinciasColumnas = {"Provincias"};
	private JScrollPane spProvincia;
	private JScrollPane spLocalidad;
	private JTable tablaProvincia;
	private JTable tablaLocalidad;
	private JButton btnAgregarProvincia;
	private JButton btnEditarProvincia;
	private JButton btnBorrarProvincia;
	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	private JButton btnEditarLocalidad;
	private JButton btnBorrarLocalidad;

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
		
		JScrollPane spPais = new JScrollPane();
		spPais.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spPais.setBounds(10, 11, 130, 337);
		panel.add(spPais);
		
		modelPais = new DefaultTableModel(null,domicilioColumnas);
		tablaPais = new JTable(modelPais);
		
		tablaPais.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPais.getColumnModel().getColumn(0).setResizable(false);
		tablaPais.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPais.getColumnModel().getColumn(1).setResizable(false);
		
		spPais.setViewportView(tablaPais);
		
		modelProvincia = new DefaultTableModel(null,provinciasColumnas);
		tablaProvincia = new JTable(modelProvincia);
		
		tablaProvincia.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProvincia.getColumnModel().getColumn(0).setResizable(false);
		tablaProvincia.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProvincia.getColumnModel().getColumn(1).setResizable(false);
		
		spPais.setViewportView(tablaPais);
		
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
		
		spProvincia = new JScrollPane();
		spProvincia.setBounds(168, 11, 130, 337);
		panel.add(spProvincia);
		
		tablaProvincia = new JTable();
		tablaProvincia.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Provincia"
			}
		));
		spProvincia.setViewportView(tablaProvincia);
		
		spLocalidad = new JScrollPane();
		spLocalidad.setBounds(318, 11, 130, 337);
		panel.add(spLocalidad);
		
		tablaLocalidad = new JTable();
		tablaLocalidad.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Localidad"
			}
		));
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
	
	public JTable getTablaPais()
	{
		return tablaPais;
	}

	public String[] getNombreColumnas() 
	{
		return domicilioColumnas;
	}


/*	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO p : personasEnTabla)
		{
			String nombre = p.getNombre();
			String tel = p.getTelefono();
			Object[] fila = {nombre, tel};
			this.getModelPersonas().addRow(fila);
		}
		
	}*/
}
