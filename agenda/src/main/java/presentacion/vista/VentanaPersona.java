package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


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
	
	private JComboBox comboBoxPais;
	private JComboBox comboBoxProvincia;
	private JComboBox comboBoxLocalidad;
	
	
	private JComboBox<String> comboBoxTipoContacto;
	
	private JLabel lblFechaCumpleElegido;
	private JButton btnCumple;
	
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

	public VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 408, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 372, 443);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(157, 8, 180, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(157, 49, 180, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, 409, 89, 23);
		panel.add(btnAgregarPersona);
		
		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(157, 80, 180, 20);
		panel.add(txtCalle);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 83, 113, 14);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 114, 113, 14);
		panel.add(lblAltura);
		
		txtAltura = new JTextField();
		txtAltura.setColumns(10);
		txtAltura.setBounds(157, 111, 180, 20);
		panel.add(txtAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 142, 113, 14);
		panel.add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(157, 139, 180, 20);
		panel.add(txtPiso);
		
		JLabel lbldepto = new JLabel("Departamento");
		lbldepto.setBounds(10, 170, 113, 14);
		panel.add(lbldepto);
		
		txtDepto = new JTextField();
		txtDepto.setColumns(10);
		txtDepto.setBounds(157, 167, 180, 20);
		panel.add(txtDepto);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 304, 113, 14);
		panel.add(lblLocalidad);
		
		comboBoxLocalidad = new JComboBox();
		comboBoxLocalidad.setBounds(157, 300, 180, 22);
		panel.add(comboBoxLocalidad);
		
		JLabel lblDireccionCorreo = new JLabel("Direccion de correo");
		lblDireccionCorreo.setBounds(10, 329, 113, 14);
		panel.add(lblDireccionCorreo);
		
		txtDireccionEmail = new JTextField();
		txtDireccionEmail.setColumns(10);
		txtDireccionEmail.setBounds(157, 326, 180, 20);
		panel.add(txtDireccionEmail);
		
		JLabel lbltipoContacto = new JLabel("Tipo de contacto");
		lbltipoContacto.setBounds(10, 379, 113, 14);
		panel.add(lbltipoContacto);
		
		comboBoxTipoContacto = new JComboBox();
		comboBoxTipoContacto.setBounds(157, 376, 180, 22);
		panel.add(comboBoxTipoContacto);
		
		JLabel lblFechaCumple = new JLabel("Fecha de cumpleanios");
		lblFechaCumple.setBounds(10, 354, 113, 14);
		panel.add(lblFechaCumple);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 274, 113, 14);
		panel.add(lblProvincia);
		
		comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(157, 267, 180, 22);
		panel.add(comboBoxProvincia);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(10, 226, 113, 14);
		panel.add(lblPais);
		
		comboBoxPais = new JComboBox();
		comboBoxPais.setBounds(157, 222, 180, 22);
		panel.add(comboBoxPais);
		
		lblFechaCumpleElegido = new JLabel("YYYY-MM-DD");
		lblFechaCumpleElegido.setBounds(262, 351, 75, 14);
		panel.add(lblFechaCumpleElegido);
		
		btnCumple = new JButton("Agregar");
		btnCumple.setBounds(157, 350, 89, 23);
		panel.add(btnCumple);
		
		
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

	public JComboBox getComboBoxPais() {
		return comboBoxPais;
	}
	
	public JComboBox getComboBoxProvincia() {
		return comboBoxProvincia;
	}

	public JComboBox getComboBoxLocalidad() {
		return comboBoxLocalidad;
	}
	
	public JComboBox<String> getComboBoxTipoContacto() {//Parece que lo ignora github
		return comboBoxTipoContacto;
	}
	
	public void llenarComboBoxTipoContacto(List<TipoContactoDTO> tiposDeContacto) {//Parece que lo ignora github
		JComboBox<String> comboBox = this.getComboBoxTipoContacto();
		for (TipoContactoDTO t : tiposDeContacto)
		{
			comboBox.addItem(t.getNombreTipoContacto());
			System.out.println(t.getIdContacto() +" "+t.getNombreTipoContacto());
		}
	}
	
	public int getValorSeleccionadoTipoContacto() {//Parece que lo ignora github
		JComboBox<String> comboBox = this.getComboBoxTipoContacto();
		return comboBox.getSelectedIndex()+1;
	}
	
	public String getSeleccionadoTipoContacto() {//Parece que lo ignora github
		JComboBox<String> comboBox = this.getComboBoxTipoContacto();
		return String.valueOf(comboBox.getSelectedItem());
	}
	
	public void reiniciarComboBoxTipoContacto() {//Parece que lo ignora github
		this.comboBoxTipoContacto.removeAllItems();
	}
	
	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}
	
	
	
	
	
	
	public void llenarComboBoxPais(List<PaisDTO> paises) {
		JComboBox com = getComboBoxPais();
		com.removeAllItems();
		for (PaisDTO p: paises) {
			com.addItem(p.getNombrePais());
		}
	}
	
	public void llenarComboBoxProvincia(List<ProvinciaDTO> provincias) {
		JComboBox com = this.getComboBoxProvincia();
		com.removeAllItems();
		for (ProvinciaDTO p: provincias) {
			com.addItem(p.getNombreProvincia());
		}
	}
	
	public void llenarComboBoxLocalidad(List<LocalidadDTO> localidad) {
		JComboBox com = getComboBoxLocalidad();
		com.removeAllItems();
		for (LocalidadDTO p: localidad) {
			com.addItem(p.getNombreLocalidad());
		}
	}
	
	public JLabel getLblFechaCumpleElegido() {
		return lblFechaCumpleElegido;
	}

	public JButton getBtnCumple() {
		return btnCumple;
	}
}

