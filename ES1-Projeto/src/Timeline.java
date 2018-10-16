import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timeline {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Timeline window = new Timeline();
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
	public Timeline() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Bom dia Academia");
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Image icon = new ImageIcon(this.getClass().getResource("/logof.png")).getImage();
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(icon));
		label.setBounds(36, 111, 305, 337);
		frame.getContentPane().add(label);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		menuBar.setBounds(0, 0, 881, 47);
		frame.getContentPane().add(menuBar);
		
		JMenu mnOrdenar = new JMenu("Ordenar");
		mnOrdenar.setFont(new Font("Calibri", Font.BOLD, 18));
		mnOrdenar.setForeground(SystemColor.windowBorder);
		menuBar.add(mnOrdenar);
		
		JMenuItem mntmAntigasPrimeiro = new JMenuItem("Antigas Primeiro");
		mntmAntigasPrimeiro.setFont(new Font("Calibri", Font.BOLD, 16));
		mnOrdenar.add(mntmAntigasPrimeiro);
		
		JMenuItem mntmRecentesPrimeiro = new JMenuItem("Recentes Primeiro");
		mntmRecentesPrimeiro.setFont(new Font("Calibri", Font.BOLD, 16));
		mnOrdenar.add(mntmRecentesPrimeiro);
		
		JMenu mnFiltrar = new JMenu("Filtrar");
		mnFiltrar.setFont(new Font("Calibri", Font.BOLD, 18));
		menuBar.add(mnFiltrar);
		
		JMenuItem mntmFacebook = new JMenuItem("Facebook");
		mntmFacebook.setFont(new Font("Calibri", Font.BOLD, 16));
		mntmFacebook.setForeground(new Color(0, 0, 0));
		mnFiltrar.add(mntmFacebook);
		
		JMenuItem mntmElearning = new JMenuItem("Elearning");
		mntmElearning.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFiltrar.add(mntmElearning);
		
		JMenuItem mntmTwitter = new JMenuItem("Twitter");
		mntmTwitter.setFont(new Font("Calibri", Font.BOLD, 16));
		mntmTwitter.setForeground(new Color(0, 0, 0));
		mnFiltrar.add(mntmTwitter);
		
		JMenuItem mntmEmail = new JMenuItem("Email");
		mntmEmail.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFiltrar.add(mntmEmail);
		
		JPanel panel = new JPanel();
		panel.setBounds(395, 75, 402, 431);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		DefaultListModel<String> notificationsList = new DefaultListModel<>();
		JList<String> list = new JList<>(notificationsList);
		list.setBounds(297, 18, 0, 0);
		list.setBackground(SystemColor.desktop);
		for (int i=0; i!=10; i++) {
			notificationsList.addElement("Bom dia Academia");
		}
		
		
		JLabel lblListaDeNotificaes = new JLabel("Lista de Notifica\u00E7\u00F5es");
		lblListaDeNotificaes.setBounds(121, 13, 187, 27);
		lblListaDeNotificaes.setFont(new Font("Calibri", Font.BOLD, 22));
		panel.add(lblListaDeNotificaes);
		list.setModel(notificationsList);
		panel.add(list);
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
