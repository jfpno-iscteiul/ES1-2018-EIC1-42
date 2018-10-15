import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class SignUp {

	public JFrame frame;
	private JTextField nomeField;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passField;
	private JPasswordField confirmarpassField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("Bom Dia Academia");
		frame.setBounds(100, 100, 779, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(SystemColor.window);
		frame.getContentPane().setLayout(null);
		
		Image icon = new ImageIcon(this.getClass().getResource("/logof.png")).getImage();
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(icon));
		label.setBounds(42, 11, 305, 337);
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(393, 23, 334, 337);
		frame.getContentPane().add(panel);
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
		emailField.setText("xxx@iscte-iul.pt");
		emailField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				emailField.setText(" ");
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
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(confirmarpassField.getPassword().equals(passField.getPassword())) {
					
				} else {
					
				}
			}
		});
		
	}
	
}
