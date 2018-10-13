import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
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
	 */
	private void initialize() {
		frame2 = new JFrame("Bom Dia Academia");
		frame2.getContentPane().setBackground(SystemColor.window);
		frame2.setBounds(100, 100, 779, 410);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);

		JButton btnSignUp = new JButton("");
		btnSignUp.setIcon(
				new ImageIcon("C:\\Users\\rmsc1\\Desktop\\Faculdade\\ESI\\ES1-Projeto\\img\\button_sign-up.png"));
		btnSignUp.setForeground(SystemColor.window);
		btnSignUp.setBackground(SystemColor.activeCaption);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame2.getContentPane().removeAll();
				frame2.repaint();
			}
		});
		btnSignUp.setFont(new Font("Calibri Light", Font.BOLD, 19));
		btnSignUp.setBounds(465, 287, 158, 43);
		frame2.getContentPane().add(btnSignUp);
		Image icon = new ImageIcon(this.getClass().getResource("/logop.png")).getImage();

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rmsc1\\Desktop\\Faculdade\\ESI\\ES1-Projeto\\img\\Imagem.png"));
		lblNewLabel.setBounds(54, 11, 305, 337);
		frame2.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(393, 53, 298, 216);
		frame2.getContentPane().add(panel);
		panel.setLayout(null);

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

		passwordField = new JPasswordField("");
		passwordField.setBounds(21, 92, 264, 20);
		panel.add(passwordField);
		passwordField.setEchoChar('*');

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(21, 67, 70, 14);
		panel.add(lblPassword);
		/// frame.setIconImage(arg0);

		JButton btnLogin = new JButton("");
		btnLogin.setBounds(69, 150, 158, 43);
		panel.add(btnLogin);
		btnLogin.setIcon(
				new ImageIcon("C:\\Users\\rmsc1\\Desktop\\Faculdade\\ESI\\ES1-Projeto\\img\\button_login.png"));
		btnLogin.setForeground(SystemColor.window);
		btnLogin.setBackground(SystemColor.window);
		btnLogin.addActionListener(new ActionListener() {

			Image icon = new ImageIcon(this.getClass().getResource("/button_login.png")).getImage();

			public void actionPerformed(ActionEvent arg0) {
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
