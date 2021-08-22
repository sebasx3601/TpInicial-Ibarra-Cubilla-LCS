package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.TipoContactoDTO;
import java.awt.Font;
import javax.swing.JComboBox;

public class AgregarLocalidad extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLocalidad;
	private JButton btnAgregarLocalidad;
	private static AgregarLocalidad INSTANCE;
	
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
		setBounds(100, 100, 500, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 464, 159);
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
		lblProvincia.setBounds(10, 112, 60, 14);
		panel.add(lblProvincia);
		
		JComboBox comboBoxProvincia = new JComboBox();
		comboBoxProvincia.setBounds(80, 108, 164, 22);
		panel.add(comboBoxProvincia);
		
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
	
	public void cerrar()
	{
		this.txtLocalidad.setText(null);
		this.dispose();
	}
}

