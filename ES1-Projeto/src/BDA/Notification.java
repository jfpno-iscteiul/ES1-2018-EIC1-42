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

public class Notification {

	private JPanel contentPane;
	private JFrame frame;
	

	/**
	 * Create the frame.
	 */
	public Notification(String Plataform, String Sender, String Date, String Contend) {
		try {
			frame= new JFrame("Bom dia Academia");
			frame.setBounds(100, 100, 868, 594);
			frame.setVisible(true);
			frame.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 755, 457);
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
		panel.add(label_1);
		contentPane.add(panel);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(112, 219, 219));
		panel_1.setBounds(0, 60, 863, 40);
		
		Label user = new Label(Sender);
		panel_1.add(user);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(173, 235, 235));
		panel_2.setBounds(0, 100, 863, 40);
		
		Label date = new Label(Date);
		panel_2.add(date);
		contentPane.add(panel_2);
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(708, 151, -706, 229);
//		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 140, 863, 400);
		textArea.setEditable(false);
		textArea.setText(Contend);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 140, 863, 400);
		contentPane.add(scrollPane);

	frame.add(panel_2);
	frame.add(panel_1);
	frame.add(panel);
	}
}
