package BDA;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EmailPage {
	
	private JFrame frame;
	private String email;

	public EmailPage(JFrame frame, String email) {
		this.email = email;
		this.frame = frame;
		
		
		
		Image logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		JButton button = new JButton("");
		button.setBounds(774, 499, 59, 35);
		frame.getContentPane().add(button);
		button.setIcon(new ImageIcon(logout));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow.main(null);
			}
		});
	}
}
