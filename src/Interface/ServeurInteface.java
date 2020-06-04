package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import application.CorrectionServer;
import application.IconManipulator;

import javax.swing.JSeparator;

public class ServeurInteface extends JFrame {

	private JPanel contentPane;
	private int height, width;
	private JLabel textField;
	private final JSeparator separator = new JSeparator();
	private CorrectionServer serveur;
	private JLabel nombre, etudiants;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServeurInteface frame = new ServeurInteface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServeurInteface() 
	{
		serveur = new CorrectionServer(this);
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Exam 2.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ServeurInteface.class.getResource("/Ressources/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		height = 600;
		width = 400;
		int screenHeight  = Toolkit.getDefaultToolkit().getScreenSize().height;
		int screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().width;
		int x = (screenWidth - width) / 2;
		int y = (screenHeight - height) / 2;
		setBounds(x, y, width, height);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel northPan = new JPanel();
		northPan.setBackground(new Color(172,219,223));
		contentPane.add(northPan, BorderLayout.NORTH);
		
		textField = new JLabel();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
		ImageIcon logo = new ImageIcon(ServeurInteface.class.getResource("/Ressources/logo.png"));
		logo = IconManipulator.resizeIcon(logo, 50, 50);
		textField.setIcon(logo);
		textField.setText("Serveur de Correction");
		northPan.add(textField);
		
		JPanel southPan = new JPanel();
		contentPane.add(southPan, BorderLayout.SOUTH);
		southPan.setBackground(new Color(172,219,223));
		
		JLabel lblNewLabel = new JLabel("Developed By : Yassine El Allali");
		lblNewLabel.setFont(new Font("Segoe Script", Font.PLAIN, 18));
		southPan.add(lblNewLabel);
		
		JPanel mainPan = new JPanel();
		contentPane.add(mainPan, BorderLayout.CENTER);
		mainPan.setBackground(new Color(172,219,223));
		mainPan.setLayout(null);
		separator.setBounds(0, 11, 394, 2);
		mainPan.add(separator);
		
		nombre = new JLabel("Etudiants Connect\u00E9s:" + serveur.getNumberClients());
		nombre.setForeground(Color.BLACK);
		nombre.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		nombre.setBounds(36, 24, 300, 23);
		mainPan.add(nombre);
		
		etudiants = new JLabel(serveur.getConnectes());
		etudiants.setVerticalAlignment(SwingConstants.TOP);
		etudiants.setForeground(Color.BLACK);
		etudiants.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		etudiants.setBounds(36, 58, 300, 335);
		mainPan.add(etudiants);
	}
	
	public JLabel getNombreLabel()
	{
		return nombre;
	}
	
	public JLabel getEtudiantsLabel()
	{
		return etudiants;
	}
	
}
