package BDA;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Label;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;

public class Notification {

	private JPanel contentPane;
	private JFrame frame;
	

	/**
	 * Create the frame.
	 *
	 * @param Plataform the plataform
	 * @param Sender the sender
	 * @param Date the date
	 * @param subject the subject
	 * @param content the content
	 */
	public Notification(String Plataform, String Sender, String Date, String subject, String content) {
		frame = new JFrame("Bom dia Academia");
		frame.setVisible(true);
		frame.setResizable(false);

		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 858, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 204));
		panel.setBounds(0, 0, 863, 60);

		JLabel logo = new JLabel("");
		Image logop = new ImageIcon(this.getClass().getResource("/logonot.png")).getImage();
		logo.setIcon(new ImageIcon(logop));
		logo.setBounds(0, 0, 863, 60);
		panel.add(logo);

		Label label_1 = new Label(Plataform);
		label_1.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(label_1);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(112, 219, 219));
		panel_1.setBounds(0, 60, 863, 40);

		Label user = new Label(Sender);
		user.setFont(new Font("Serif", Font.PLAIN, 20));
		panel_1.add(user);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(173, 235, 235));
		panel_2.setBounds(0, 100, 863, 40);

		Label date = new Label(Date);
		date.setFont(new Font("Serif", Font.PLAIN, 20));
		panel_2.add(date);
		contentPane.add(panel_2);

//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(708, 151, -706, 229);
//		contentPane.add(scrollPane);

		if (Plataform.equals("Email")) {
			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font("Serif", Font.PLAIN, 18));
			textArea.setBounds(1, 140, 863, 200);
			textArea.setEditable(false);
			textArea.setText(subject);
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);

			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setBounds(1, 140, 850, 68);
			contentPane.add(scrollPane);
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(1, 219, 850, 312);
			contentPane.add(scrollPane_2);

			JTextArea textArea_1 = new JTextArea();
			textArea_1.setEditable(false);
			textArea_1.setText(content);
			textArea_1.setEditable(false);
			textArea_1.setLineWrap(true);
			textArea_1.setWrapStyleWord(true);
			scrollPane_2.setViewportView(textArea_1);
		
		} else {
			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font("Serif", Font.PLAIN, 18));
			textArea.setBounds(1, 140, 863, 400);
			textArea.setEditable(false);
			textArea.setText(subject);
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			
			JScrollPane scrollPane = new JScrollPane(textArea);
			scrollPane.setBounds(1, 140, 863, 400);
			contentPane.add(scrollPane);
			
		}
		

		frame.getContentPane().add(panel_2);
		frame.getContentPane().add(panel_1);
		frame.getContentPane().add(panel);

		
	}
}
