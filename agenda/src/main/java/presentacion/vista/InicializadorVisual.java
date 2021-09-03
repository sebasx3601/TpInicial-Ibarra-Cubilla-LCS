package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InicializadorVisual
{
	private JFrame frame;
	
	private JLabel lblContrasea;
	private JLabel lblServidor;
	private JLabel lblPuerto;
	private JLabel lblNewLabel;

	private JTextField txtUsuario;
	private JTextField txtContra;
	private JTextField txtServidor;
	private JTextField txtPuerto;
	
	private JButton btnIniciar;
	private JButton btnEmpezarDeNuevo;

	public InicializadorVisual() 
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
		
		btnIniciar = new JButton("Conectar");
		btnIniciar.setBounds(23, 214, 89, 23);
		panel.add(btnIniciar);
		
		lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(10, 24, 102, 14);
		panel.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(173, 21, 136, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 69, 102, 14);
		panel.add(lblContrasea);
		
		txtContra = new JTextField();
		txtContra.setColumns(10);
		txtContra.setBounds(173, 66, 136, 20);
		panel.add(txtContra);
		
		lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(10, 118, 102, 14);
		panel.add(lblServidor);
		
		txtServidor = new JTextField();
		txtServidor.setColumns(10);
		txtServidor.setBounds(173, 115, 136, 20);
		panel.add(txtServidor);
		
		lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(10, 167, 102, 14);
		panel.add(lblPuerto);
		
		txtPuerto = new JTextField();
		txtPuerto.setColumns(10);
		txtPuerto.setBounds(173, 164, 136, 20);
		panel.add(txtPuerto);
		
		btnEmpezarDeNuevo = new JButton("Empezar de nuevo");
		btnEmpezarDeNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEmpezarDeNuevo.setBounds(280, 214, 144, 23);
		panel.add(btnEmpezarDeNuevo);
		
		inicializarTxtFields();
		inicializarBotones();
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

	public JButton getBtnIniciar() 
	{
		return btnIniciar;
	}
	
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtContra() {
		return txtContra;
	}

	public JTextField getTxtServidor() {
		return txtServidor;
	}

	public JTextField getTxtPuerto() {
		return txtPuerto;
	}

	public JButton getBtnEmpezarDeNuevo() {
		return btnEmpezarDeNuevo;
	}
	
	public void inicializarTxtFields() {
		this.getTxtUsuario().setText("root");
		this.getTxtContra().setText("root");
		//"jdbc:mysql://localhost:3306/agenda"
		this.getTxtServidor().setText("localhost");
		this.getTxtPuerto().setText("3306");
	}
	
	public void inicializarBotones() {
		
	}

	public void inicializarPrograma() {
		
	}
	
	public void cerrar()
	{
		frame.dispose();
	}
	
}
