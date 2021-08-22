package presentacion.vista;

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
import modelo.Agenda;

import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AgregarLocalidad extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLocalidad;
	private JButton btnAgregarLocalidad;
	private static AgregarLocalidad INSTANCE;
	
	private JComboBox comboBoxProvincia;
	private List<ProvinciaDTO> allProvincias;
	
	private JComboBox comboBoxPais;
	private List<PaisDTO> todosLosPaises;
	private Agenda agenda;
	
	public static AgregarLocalidad getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new AgregarLocalidad(); 	
			return new AgregarLocalidad();
		}
		else
			return INSTANCE;
	}

	private AgregarLocalidad() 
	{
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 464, 197);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAgregarLocalidad = new JLabel("Agrega la localidad");
		lblAgregarLocalidad.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblAgregarLocalidad.setBounds(157, 11, 169, 31);
		panel.add(lblAgregarLocalidad);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(80, 77, 246, 20);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(365, 76, 89, 23);
		panel.add(btnAgregarLocalidad);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 80, 53, 14);
		panel.add(lblNombre);
		
		JLabel lblProvincia = new JLabel("Provincias");
		lblProvincia.setBounds(10, 168, 60, 14);
		panel.add(lblProvincia);
		
		comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(80, 164, 164, 22);
		panel.add(comboBoxProvincia);
		
		comboBoxPais = new JComboBox();
		comboBoxPais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				JComboBox com = getComboBoxPais();
				if(com.getItemCount() == 0) {
					vaciarComboBoxProvincia();
					return;
				}
				llenarComboBoxProvincia(agenda.obtenerProvinciaDePaises(getIdPaisSeleccionado()));
			}
		});
		comboBoxPais.setBounds(80, 120, 164, 22);
		panel.add(comboBoxPais);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(10, 124, 60, 14);
		panel.add(lblPais);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtLocalidad() 
	{
		return txtLocalidad;
	}

	public JButton getBtnAgregarLocalidad() 
	{
		return btnAgregarLocalidad;
	}
	
	public JComboBox getComboBoxProvincia() {
		return comboBoxProvincia;
	}
	
	public JComboBox getComboBoxPais() {
		return comboBoxPais;
	}

	public void llenarComboBoxProvincia(List<ProvinciaDTO> provincias) {
		this.allProvincias = provincias;
		JComboBox com = getComboBoxProvincia();
		com.removeAllItems();
		for (ProvinciaDTO p: provincias) {
			com.addItem(p.getNombreProvincia());
		}
	}
	
	public void vaciarComboBoxProvincia() {
		JComboBox com = getComboBoxProvincia();
		com.removeAllItems();
	}
	
	public int getIdProvinciaSeleccionado() {
		JComboBox com = getComboBoxProvincia();
		if(com.getSelectedIndex() == -1) {
			return -1;
		}
		return allProvincias.get(com.getSelectedIndex()).getId();
	}
	
	public void llenarComboBoxPais(List<PaisDTO> paises) {
		todosLosPaises = paises;
		JComboBox com = getComboBoxPais();
		com.removeAllItems();
		for (PaisDTO p: paises) {
			com.addItem(p.getNombrePais());
		}
	}
	
	public int getIdPaisSeleccionado() {
		return todosLosPaises.get(getComboBoxPais().getSelectedIndex()).getId();
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public void cerrar()
	{
		this.txtLocalidad.setText(null);
		this.dispose();
	}
}

