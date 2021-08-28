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

import dto.TipoContactoDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaGenero
{
	private JFrame frame;
	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private DefaultTableModel modelTipoContacto;
	private  String[] nombreColumnas = {"Id genero","Nombre de genero"};
	
	private JButton btnEditar;

	public VistaGenero() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane sp = new JScrollPane();
		sp.setBounds(10, 11, 414, 182);
		panel.add(sp);
		
		modelTipoContacto = new DefaultTableModel(null,nombreColumnas);
		tabla = new JTable(modelTipoContacto);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(103);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabla.getColumnModel().getColumn(1).setResizable(false);
		
		sp.setViewportView(tabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);
	}
	
	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir de tipo de contacto?", 
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
	
	public DefaultTableModel getModelTipoContacto() 
	{
		return modelTipoContacto;
	}
	
	public JTable getTablaPersonas()
	{
		return tabla;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void llenarTabla(List<TipoContactoDTO> TipoContactoEnTabla) {
		/*
		this.getModelTipoContacto().setRowCount(0);
		this.getModelTipoContacto().setColumnCount(0);
		this.getModelTipoContacto().setColumnIdentifiers(this.getNombreColumnas());

		for (TipoContactoDTO p : TipoContactoEnTabla)
		{
			String idTipoContacto = String.valueOf(p.getIdContacto());
			String nombreContacto = p.getNombreTipoContacto();
			Object[] fila = {idTipoContacto, nombreContacto};
			this.getModelTipoContacto().addRow(fila);
		}
		*/
	}
}
