import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MenuPersonnage extends JFrame{
	
	private static final long serialVersionUID = -4632300498076393035L;

	public MenuPersonnage(){
		final ImageIcon aide = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ressources/aide.png"));
		final JLabel fond1 = new JLabel(aide);
		
		this.setSize(500, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fond1);
		this.setBounds(0,0,500,300);
		
		this.setVisible(true);
	}

}
