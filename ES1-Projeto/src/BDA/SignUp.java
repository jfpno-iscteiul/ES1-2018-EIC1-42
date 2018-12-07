package BDA;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Cursor;

public class SignUp {
	
	/**
	 * This class allows the user to create an account in this application
	 */

	private JTextField nomeField;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passField;
	private JPasswordField confirmarpassField;
	private JFrame frame;
	private XMLFile xml;
	 
	
	
	/** @param   frame parameter gives an instance of the frame of the main page.
	 */
	public SignUp(JFrame frame) {
		this.frame = frame;
		xml = new XMLFile();
		initialize();
		setVisible(true);
	}

	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		JLabel labelBackground = new JLabel();
		//imagem retirada do site https://www.univercidade.net/wp-content/uploads/2015/12/Inaugurac%CC%A7a%CC%83o-EDIFI%CC%81CIO-CONVI%CC%81VIO_ISCTE-768x510.jpg
		Image imagem3 = new ImageIcon(this.getClass().getResource("/iscte_wm_1.png")).getImage();
		labelBackground.setIcon(new ImageIcon(imagem3));
		labelBackground.setForeground(SystemColor.window);
		labelBackground.setBackground(SystemColor.activeCaption);
		labelBackground.setBounds(0, 0, 863, 594);
		
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 779, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setIconImage((new ImageIcon("Imagens/frameImage.png").getImage()));
		
		Image icon = new ImageIcon(this.getClass().getResource("/logof.png")).getImage();
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(icon));
		label.setBounds(42, 11, 305, 337);
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(393, 23, 334, 337);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome ");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(10, 11, 46, 14);
		panel.add(lblNome);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(10, 66, 83, 14);
		panel.add(lblUsername);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 123, 71, 14);
		panel.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 179, 113, 14);
		panel.add(lblPassword);
		
		JLabel lblConfirmePassword = new JLabel("Confirmar Password");
		lblConfirmePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmePassword.setBounds(10, 235, 144, 14);
		panel.add(lblConfirmePassword);
		
		nomeField = new JTextField();
		nomeField.setBounds(10, 35, 314, 20);
		panel.add(nomeField);
		nomeField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(10, 91, 314, 20);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		emailField.setText("xxxx@iscte-iul.pt");
		emailField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				emailField.setText("");
			}
		});
		emailField.setBounds(10, 148, 314, 20);
		panel.add(emailField);
		emailField.setColumns(10);
		
		
		passField = new JPasswordField();
		passField.setBounds(10, 204, 314, 20);
		panel.add(passField);
		passField.setColumns(10);
		passField.setEchoChar('*');
		
		confirmarpassField = new JPasswordField("");
		confirmarpassField.setBounds(10, 260, 314, 20);
		panel.add(confirmarpassField);
		confirmarpassField.setColumns(10);
		confirmarpassField.setEchoChar('*');
		
		Image signUp = new ImageIcon(this.getClass().getResource("/button_sign-up.png")).getImage();
		JButton btnSignUp = new JButton("");
		btnSignUp.setFont(new Font("Calibri Light", Font.BOLD, 19));
		btnSignUp.setBounds(94, 291, 151, 35);
		panel.add(btnSignUp);
		btnSignUp.setIcon(new ImageIcon(signUp));
		btnSignUp.setForeground(SystemColor.window);
		btnSignUp.setBackground(SystemColor.activeCaption);
		
		/**
		 * Checks if a registered user already exists with the email entered, if the entered passwords are the same and if the email is the right type
		 */
		
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password1 = new String(passField.getPassword());
				String password2 = new String(confirmarpassField.getPassword());
				if(emailField.getText().contains("@iscte-iul.pt")) {
					if(password1.equals(password2)) {
						if(xml.checkIfUserExistsByEmail(emailField.getText())) {
							JOptionPane optionPane = new JOptionPane("O email inserido já está associado a um utilizador.", JOptionPane.ERROR_MESSAGE);    
							JDialog dialog = optionPane.createDialog("ERRO");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
						}else {
						xml.addUsers(emailField.getText(), password1, nomeField.getText(),usernameField.getText(), "vazio",  "vazio",  "vazio", "vazio", "vazio", "vazio");
						frame.getContentPane().removeAll();
						frame.repaint();
						@SuppressWarnings("unused")
						Configurations conf= new Configurations(frame, emailField.getText());
						}
					} else {
						JOptionPane optionPane = new JOptionPane("As password inseridas não coincidem.", JOptionPane.ERROR_MESSAGE);    
						JDialog dialog = optionPane.createDialog("ERRO");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
				}else {
					JOptionPane optionPane = new JOptionPane("O email inserido não é do dominio IscteIul.", JOptionPane.ERROR_MESSAGE);    
					JDialog dialog = optionPane.createDialog("ERRO");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
			}
		});
		frame.getContentPane().add(panel);
		frame.getContentPane().add(labelBackground);
	}

	public JFrame getFrame() {
		return frame;
	}
}
