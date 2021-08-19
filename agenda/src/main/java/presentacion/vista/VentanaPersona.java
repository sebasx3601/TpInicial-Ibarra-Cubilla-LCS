package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private static VentanaPersona INSTANCE;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private JTextField txtDireccionEmail;
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		else
			return INSTANCE;
	}

	private VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 443);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, 409, 89, 23);
		panel.add(btnAgregarPersona);
		
		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(133, 80, 164, 20);
		panel.add(txtCalle);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 83, 113, 14);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 114, 113, 14);
		panel.add(lblAltura);
		
		txtAltura = new JTextField();
		txtAltura.setColumns(10);
		txtAltura.setBounds(133, 111, 164, 20);
		panel.add(txtAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 142, 113, 14);
		panel.add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(133, 139, 164, 20);
		panel.add(txtPiso);
		
		JLabel lbldepto = new JLabel("Departamento");
		lbldepto.setBounds(10, 170, 113, 14);
		panel.add(lbldepto);
		
		txtDepto = new JTextField();
		txtDepto.setColumns(10);
		txtDepto.setBounds(133, 167, 164, 20);
		panel.add(txtDepto);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 304, 113, 14);
		panel.add(lblLocalidad);
		
		JComboBox comboBoxLocalidad = new JComboBox();
		comboBoxLocalidad.setBounds(133, 300, 164, 22);
		panel.add(comboBoxLocalidad);
		
		JLabel lblDireccionCorreo = new JLabel("Direccion de correo");
		lblDireccionCorreo.setBounds(10, 329, 113, 14);
		panel.add(lblDireccionCorreo);
		
		txtDireccionEmail = new JTextField();
		txtDireccionEmail.setColumns(10);
		txtDireccionEmail.setBounds(133, 326, 164, 20);
		panel.add(txtDireccionEmail);
		
		JLabel lbltipoContacto = new JLabel("Tipo de contacto");
		lbltipoContacto.setBounds(10, 379, 113, 14);
		panel.add(lbltipoContacto);
		
		JComboBox comboBoxTipoContacto = new JComboBox();
		comboBoxTipoContacto.setBounds(133, 376, 164, 22);
		panel.add(comboBoxTipoContacto);
		
		JLabel lblFechaCumple = new JLabel("Fecha de cumpleanios");
		lblFechaCumple.setBounds(10, 354, 113, 14);
		panel.add(lblFechaCumple);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 274, 113, 14);
		panel.add(lblProvincia);
		
		JComboBox comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(133, 267, 164, 22);
		panel.add(comboBoxProvincia);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(10, 226, 113, 14);
		panel.add(lblPais);
		
		JComboBox comboBoxPais = new JComboBox();
		comboBoxPais.setBounds(133, 222, 164, 22);
		panel.add(comboBoxPais);
		
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

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	public JTextField getTxtCalle() 
	{
		return txtCalle;
	}
	
	public JTextField getTxtAltura() 
	{
		return txtAltura;
	}
	
	public JTextField getTxtPiso() 
	{
		return txtPiso;
	}
	
	public JTextField getTxtDepto() 
	{
		return txtDepto;
	}
	
	public JTextField getTxtDireccionEmail() 
	{
		return txtDireccionEmail;
	}
	
	//FALTA PROVINCIA PAIS LOCALIDAD FECHA DE CUMPLEAÑOS Y TIPO DE CONTACTO
	
	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}
}

