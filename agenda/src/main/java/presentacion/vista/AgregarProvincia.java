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

public class AgregarProvincia extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProvincia;
	private JButton btnAgregarProvincia;
	private static AgregarProvincia INSTANCE;
	
	public static AgregarProvincia getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new AgregarProvincia(); 	
			return new AgregarProvincia();
		}
		else
			return INSTANCE;
	}

	private AgregarProvincia() 
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
		
		JLabel lblAgregarProvincia = new JLabel("Agrega la provincia");
		lblAgregarProvincia.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblAgregarProvincia.setBounds(157, 11, 169, 31);
		panel.add(lblAgregarProvincia);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(80, 77, 246, 20);
		panel.add(txtProvincia);
		txtProvincia.setColumns(10);
		
		btnAgregarProvincia = new JButton("Agregar");
		btnAgregarProvincia.setBounds(365, 76, 89, 23);
		panel.add(btnAgregarProvincia);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 80, 53, 14);
		panel.add(lblNombre);
		
		JComboBox comboBoxPais = new JComboBox();
		comboBoxPais.setBounds(80, 108, 164, 22);
		panel.add(comboBoxPais);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(10, 112, 46, 14);
		panel.add(lblPais);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtProvincia() 
	{
		return txtProvincia;
	}

	public JButton getBtnAgregarProvincia() 
	{
		return btnAgregarProvincia;
	}
	
	public void cerrar()
	{
		this.txtProvincia.setText(null);
		this.dispose();
	}
}

