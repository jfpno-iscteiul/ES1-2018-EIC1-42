package BDA;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Rectangle;
import javax.swing.SpringLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;


public class SendEmailPage {

	private JFrame frame;
	private JTextField to;
	private JTextField subject;
	private String email;
	private Email mail;

	/**
	 * Instantiates a new send email page.
	 *
	 * @param email the email
	 */
	public SendEmailPage(String email) {
		this.email = email;
		mail = new Email();
		initialize();
	}
	
	
	public JFrame getFrame() {
		return frame;
	}
	

	/**
	 * Initialize.
	 */
	private void initialize() {
		frame = new JFrame("Enviar email");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(new Rectangle(100, 100, 594, 400));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		Image imagem = new ImageIcon(this.getClass().getResource("/Imagens/frameImage.png")).getImage();
		frame.setIconImage(imagem);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel.setPreferredSize(new Dimension(10, 360));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblNewLabel = new JLabel("Para:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel, -333, SpringLayout.SOUTH, panel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(new Rectangle(1, 1, 20, 20));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Assunto:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 13, SpringLayout.SOUTH, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblNewLabel_1, -495, SpringLayout.EAST, panel);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);
		
		to = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, to, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, to, 6, SpringLayout.EAST, lblNewLabel);
		sl_panel.putConstraint(SpringLayout.EAST, to, 485, SpringLayout.EAST, lblNewLabel);
		panel.add(to);
		to.setColumns(10);
		
		subject = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, subject, 7, SpringLayout.SOUTH, to);
		sl_panel.putConstraint(SpringLayout.WEST, subject, 6, SpringLayout.EAST, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.EAST, subject, 0, SpringLayout.EAST, to);
		panel.add(subject);
		subject.setColumns(10);
		
		TextArea content = new TextArea();
		sl_panel.putConstraint(SpringLayout.NORTH, content, 18, SpringLayout.SOUTH, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.WEST, content, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, content, 242, SpringLayout.SOUTH, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.EAST, content, 0, SpringLayout.EAST, to);
		panel.add(content);
		
		Button button = new Button("Enviar");
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(Color.BLACK);
		sl_panel.putConstraint(SpringLayout.NORTH, button, 14, SpringLayout.SOUTH, content);
		sl_panel.putConstraint(SpringLayout.WEST, button, -88, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, button, -9, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, button, -10, SpringLayout.EAST, panel);
		button.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		panel.add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mail.sendEmail(email, to.getText(), subject.getText(), content.getText());
				JOptionPane optionPane = new JOptionPane("Email enviado!",
						JOptionPane.INFORMATION_MESSAGE);
				JDialog dialog = optionPane.createDialog("OK");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				frame.dispose();
			}
		});
		
	}
}
