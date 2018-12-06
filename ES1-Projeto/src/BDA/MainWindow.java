package BDA;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class MainWindow {
	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */


	/** @param   args is the main method.
	 */

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MainWindow window = new MainWindow();
	}


	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */

	public MainWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 *  @author All the members of the team ES1-EIC1-42
	 */

	private void initialize() {
		frame = new JFrame("Bom Dia Academia");
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel labelBackground = new JLabel();
		//imagem retirada do site https://www.univercidade.net/wp-content/uploads/2015/12/Inaugurac%CC%A7a%CC%83o-EDIFI%CC%81CIO-CONVI%CC%81VIO_ISCTE-768x510.jpg
		Image imagemTEST = new ImageIcon(this.getClass().getResource("/iscte_wm.png")).getImage();
		labelBackground.setIcon(new ImageIcon(imagemTEST));
		labelBackground.setForeground(SystemColor.window);
		labelBackground.setBackground(SystemColor.activeCaption);
		labelBackground.setBounds(0, 0, 863, 594);
		
		frame.getContentPane().add(labelBackground);
		frame.setIconImage(imagemTEST);
		
		JButton btnSignUp = new JButton("");
		Image signUp = new ImageIcon(this.getClass().getResource("/button_sign-up.png")).getImage();
		btnSignUp.setIcon(new ImageIcon(signUp));
		btnSignUp.setForeground(SystemColor.window);
		btnSignUp.setBackground(SystemColor.activeCaption);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				
				JLabel labelBackground2 = new JLabel();
				//imagem retirada do site https://www.univercidade.net/wp-content/uploads/2015/12/Inaugurac%CC%A7a%CC%83o-EDIFI%CC%81CIO-CONVI%CC%81VIO_ISCTE-768x510.jpg
				Image imagemTEST2 = new ImageIcon(this.getClass().getResource("/iscte_wm.png")).getImage();
				labelBackground2.setIcon(new ImageIcon(imagemTEST));
				labelBackground2.setForeground(SystemColor.window);
				labelBackground2.setBackground(SystemColor.activeCaption);
				labelBackground2.setBounds(0, 0, 863, 594);
				frame.getContentPane().add(labelBackground2);
				frame.setIconImage(imagemTEST2);
				frame.repaint();
				
				@SuppressWarnings("unused")
				SignUp signUp = new SignUp(frame);
			}
		});

		btnSignUp.setFont(new Font("Calibri Light", Font.BOLD, 19));
		btnSignUp.setBounds(484, 360, 151, 43);
		labelBackground.add(btnSignUp);

		/**
		 * Add images which work as buttons
		 */

		Image icon = new ImageIcon(this.getClass().getResource("/logof.png")).getImage();
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(icon));
		lblNewLabel.setBounds(42, 80, 305, 337);
		
		labelBackground.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(410, 120, 313, 154);
		panel.setLayout(null);
		
		panel.setOpaque(false);
		labelBackground.add(panel);
	
		/**
		 * Create a login form
		 */

		txtEmail = new JTextField();
		txtEmail.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtEmail.setText("xxxx@iscte-iul.pt");

		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				txtEmail.setText("");
			}
		});
		txtEmail.setBounds(21, 45, 264, 20);
		panel.add(txtEmail);
		txtEmail.setToolTipText("");
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setColumns(10);

		JCheckBox chckbxMostrarPassword = new JCheckBox("Mostrar password");
		chckbxMostrarPassword.setBounds(143, 128, 150, 23);
		
		labelBackground.add(chckbxMostrarPassword);
		panel.add(chckbxMostrarPassword);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblEmail.setBounds(21, 21, 46, 14);
		panel.add(lblEmail);

		passwordField = new JPasswordField();
		passwordField.setBounds(21, 104, 264, 20);
		panel.add(passwordField);
		passwordField.setEchoChar('*');

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblPassword.setBounds(21, 80, 70, 14);
		labelBackground.add(lblPassword);
		panel.add(lblPassword);

		JButton buttonLogin= new JButton("");
		Image logInImage = new ImageIcon(this.getClass().getResource("/button_login.png")).getImage();
		buttonLogin.setIcon(new ImageIcon(logInImage));
		buttonLogin.setBounds(484, 290, 151, 43);
		buttonLogin.setForeground(SystemColor.window);
		buttonLogin.setBackground(SystemColor.window);
		labelBackground.add(buttonLogin);

		/**
		 * Checks if the email and password are registered in the xml file
		 */
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass = new String(passwordField.getPassword());
				if(XMLFile.checkIfUserExists(txtEmail.getText(), pass)) {
					frame.getContentPane().removeAll();
					frame.repaint();
					@SuppressWarnings("unused")
					Timeline timeline= new Timeline(frame, txtEmail.getText());
				} else {
					JOptionPane optionPane = new JOptionPane("Os dados que inseriu estão incorretos!", JOptionPane.ERROR_MESSAGE);    
					JDialog dialog = optionPane.createDialog("ERRO!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
			}
		});
		buttonLogin.setFont(new Font("Calibri", Font.PLAIN, 7));
		chckbxMostrarPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxMostrarPassword.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
	}
}