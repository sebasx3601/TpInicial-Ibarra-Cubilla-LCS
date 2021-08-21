package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.TipoContactoDTO;

public class VentanaAgregarTipoContacto extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JButton btnAgregarPersona;
	private static VentanaAgregarTipoContacto INSTANCE;
	
	public static VentanaAgregarTipoContacto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaAgregarTipoContacto(); 	
			return new VentanaAgregarTipoContacto();
		}
		else
			return INSTANCE;
	}

	private VentanaAgregarTipoContacto() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 464, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre del nuevo tipo de contacto");
		lblNombreYApellido.setBounds(10, 11, 183, 14);
		panel.add(lblNombreYApellido);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(208, 8, 246, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(365, 39, 89, 23);
		panel.add(btnAgregarPersona);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JButton getBtnAgregarContacto() 
	{
		return btnAgregarPersona;
	}
	
	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.dispose();
	}
}

