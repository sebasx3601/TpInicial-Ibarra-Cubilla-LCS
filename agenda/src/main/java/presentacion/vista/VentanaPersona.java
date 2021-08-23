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
	private JComboBox<String> cBDia;
	

	private JComboBox<String> comboBoxMes;
	private JComboBox<String> comboBoxAnio;
	
	private JComboBox comboBoxPais;
	private JComboBox comboBoxProvincia;
	private JComboBox comboBoxLocalidad;
	
	
	private JComboBox<String> comboBoxTipoContacto;
	
	
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
		
		cBDia = new JComboBox();
		cBDia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cBDia.setBounds(157, 350, 49, 22);
		panel.add(cBDia);
		
		comboBoxMes = new JComboBox();
		comboBoxMes.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diembre"}));
		comboBoxMes.setBounds(208, 350, 61, 22);
		panel.add(comboBoxMes);
		
		comboBoxAnio = new JComboBox();
		comboBoxAnio.setModel(new DefaultComboBoxModel(new String[] {"1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
		comboBoxAnio.setBounds(276, 350, 61, 22);
		panel.add(comboBoxAnio);
		
		
	}
	
	public String TomarCombobox() {
		
		String dias = cBDia.getItemAt(cBDia.getSelectedIndex());
		
		String mes = comboBoxMes.getItemAt(comboBoxMes.getSelectedIndex());
		String anio = comboBoxAnio.getItemAt(comboBoxAnio.getSelectedIndex());
		
		return dias + " / " + mes + " / " + anio;
		
		
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
	
	public JComboBox<String> getComboBoxMes() {
		return comboBoxMes;
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
	
	public JComboBox<String> getcBDia() {
		return cBDia;
	}

	public JComboBox<String> getComboBoxAnio() {
		return comboBoxAnio;
	}
}

