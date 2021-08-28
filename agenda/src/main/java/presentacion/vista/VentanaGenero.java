package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.TipoContactoDTO;

public class VentanaGenero extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt;
	private JButton btn;
	private static VentanaGenero INSTANCE;
	
	private JLabel lbl;
	
	public static VentanaGenero getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaGenero(); 	
			return new VentanaGenero();
		}
		else
			return INSTANCE;
	}

	private VentanaGenero() 
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
		
		lbl = new JLabel("Nombre del nuevo tipo de contacto");
		lbl.setBounds(10, 11, 183, 14);
		panel.add(lbl);
		
		txt = new JTextField();
		txt.setBounds(208, 8, 246, 20);
		panel.add(txt);
		txt.setColumns(10);
		
		btn = new JButton("Agregar");
		btn.setBounds(365, 39, 89, 23);
		panel.add(btn);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxt() 
	{
		return txt;
	}

	public JButton getBtn() 
	{
		return btn;
	}
	
	public JLabel getLbl() {
		return lbl;
	}
	
	public void cerrar()
	{
		this.txt.setText(null);
		this.dispose();
	}
}

