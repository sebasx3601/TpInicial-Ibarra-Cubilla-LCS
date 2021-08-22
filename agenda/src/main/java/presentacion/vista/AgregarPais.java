package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.TipoContactoDTO;
import java.awt.Font;

public class AgregarPais extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombrePais;
	private JButton btnAgregarPais;
	private static AgregarPais INSTANCE;
	
	public static AgregarPais getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new AgregarPais(); 	
			return new AgregarPais();
		}
		else
			return INSTANCE;
	}

	private AgregarPais() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 464, 110);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAgregarPais = new JLabel("Agrega el Pais");
		lblAgregarPais.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblAgregarPais.setBounds(157, 11, 126, 31);
		panel.add(lblAgregarPais);
		
		txtNombrePais = new JTextField();
		txtNombrePais.setBounds(80, 77, 246, 20);
		panel.add(txtNombrePais);
		txtNombrePais.setColumns(10);
		
		btnAgregarPais = new JButton("Agregar");
		btnAgregarPais.setBounds(365, 76, 89, 23);
		panel.add(btnAgregarPais);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 80, 53, 14);
		panel.add(lblNombre);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombrePais() 
	{
		return txtNombrePais;
	}

	public JButton getBtnAgregarPais() 
	{
		return btnAgregarPais;
	}
	
	public void cerrar()
	{
		this.txtNombrePais.setText(null);
		this.dispose();
	}
}

