import java.awt.EventQueue;
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
	
	private JFrame frame2;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame2.setVisible(true);
					window.frame2.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 *  @author All the members of the team ES1-EIC1-42
	 */
	
	private void initialize() {
		frame2 = new JFrame("Bom Dia Academia");
		frame2.getContentPane().setBackground(SystemColor.window);
		frame2.setBounds(100, 100, 779, 410);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);

		JButton btnSignUp = new JButton("");
		Image signUp = new ImageIcon(this.getClass().getResource("/button_sign-up.png")).getImage();
		btnSignUp.setIcon(new ImageIcon(signUp));
		btnSignUp.setForeground(SystemColor.window);
		btnSignUp.setBackground(SystemColor.activeCaption);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.getContentPane().removeAll();
				frame2.repaint();
				SignUp signUp= new SignUp();
				signUp.initialize(frame2);
			}
		});
		
		btnSignUp.setFont(new Font("Calibri Light", Font.BOLD, 19));
		btnSignUp.setBounds(472, 272, 151, 43);
		frame2.getContentPane().add(btnSignUp);
		
		/**
		 * Add imagens which work as buttons
		 */
		
		Image icon = new ImageIcon(this.getClass().getResource("/logof.png")).getImage();
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(icon));
		lblNewLabel.setBounds(42, 11, 305, 337);
		frame2.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(393, 39, 313, 215);
		frame2.getContentPane().add(panel);
		panel.setLayout(null);
		
		/**
		 * Create a login form
		 */

		txtEmail = new JTextField();
		txtEmail.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtEmail.setText("xxx@iscte-iul.pt");

		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				txtEmail.setText("");
			}
		});
		txtEmail.setBounds(21, 36, 264, 20);

		panel.add(txtEmail);

		txtEmail.setToolTipText("");
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setColumns(10);

		JCheckBox chckbxMostrarPassword = new JCheckBox("Mostrar password");
		chckbxMostrarPassword.setBounds(151, 119, 134, 23);
		panel.add(chckbxMostrarPassword);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(21, 11, 46, 14);
		panel.add(lblEmail);

		passwordField = new JPasswordField();
		passwordField.setBounds(21, 92, 264, 20);
		panel.add(passwordField);
		passwordField.setEchoChar('*');

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(21, 67, 70, 14);
		panel.add(lblPassword);

		JButton btnLogin = new JButton("");
		btnLogin.setBounds(76, 161, 151, 43);
		panel.add(btnLogin);
		Image logIn = new ImageIcon(this.getClass().getResource("/button_login.png")).getImage();
		btnLogin.setIcon(
				new ImageIcon(logIn));
		btnLogin.setForeground(SystemColor.window);
		btnLogin.setBackground(SystemColor.window);
		
		/**
		 * Checks if the email and password are registered in the xml file
		 */
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass = new String(passwordField.getPassword());
				if(XMLFile.checkIfUserExists(txtEmail.getText(), pass)) {
					frame2.getContentPane().removeAll();
					frame2.repaint();
					Timeline timeline= new Timeline();
					timeline.initialize(frame2);
				} else {
					JOptionPane optionPane = new JOptionPane("Os dados que inseriu estão incorretos!", JOptionPane.ERROR_MESSAGE);    
					JDialog dialog = optionPane.createDialog("ERRO!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 7));
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
