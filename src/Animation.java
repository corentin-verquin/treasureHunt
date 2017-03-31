import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Animation extends JFrame {
	private static final long serialVersionUID = -6202322305125787691L;
	private JLabel j1;
	private JLabel j2;
	final ImageIcon image1 = new ImageIcon(Toolkit.getDefaultToolkit()
			.getImage("ressources/equipe1.jpg"));
	final ImageIcon image2 = new ImageIcon(Toolkit.getDefaultToolkit()
			.getImage("ressources/equipe2.jpg"));
	
	public Animation() {
		this.setSize(350, 232);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setVisible(false);
		
		j1=new JLabel(image1);
		j1.setBounds(0, 0, 350, 232);
		j1.setVisible(false);
		this.add(j1);

		j2=new JLabel(image1);
		j2.setBounds(0, 0, 350, 232);
		j2.setVisible(false);
		this.add(j1);
	}

	public void equipe1() {
		this.setVisible(true);
		j1.setVisible(true);
		this.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		for (int i = 100; i > 0; i--) {
			try {
				Thread.sleep(20);
				this.setOpacity((float) (i / 100.0));	
			} catch (Exception e) {
			}
		}
		j1.setVisible(false);
		this.setVisible(false);
	}

	public void equipe2() {
		j2.setVisible(true);
		this.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		for (int i = 100; i > 0; i--) {
			try {
				this.setOpacity((float) (i / 100.0));	
			} catch (Exception e) {
			}
			
			try {
				Thread.sleep(20);
			} catch (Exception e) {
			}
		}
		j2.setVisible(true);
	}
}
