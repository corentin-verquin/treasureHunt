import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;


public class AffichageEnergie extends JPanel {
	private static final long serialVersionUID = -8154887848305671550L;
	JProgressBar barEnergie;
	JLabel textEnergie, textNom, textPosition, cf, cl, sa;
	JPanel content, contentImage1, contentImage2, contentText, inventaire; 
	JTextArea info;
	ImageIcon explorateur = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ressources/exploVide.png"));
	ImageIcon voleur = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ressources/voleurVide.png"));
	ImageIcon soldat = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ressources/soldatVide.png"));
	ImageIcon piegeur = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ressources/piegeurVide.png"));
	ImageIcon coffre = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ressources/coffre.png"));
	ImageIcon clef = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ressources/cle.png"));
	ImageIcon sabre = new ImageIcon(Toolkit.getDefaultToolkit().getImage("ressources/sabre.png"));
	
	/**
	 * Initialise l'overlay de l'energie des personnages.
	 */
	public AffichageEnergie() {
		this.setBackground(Color.gray);
		this.setLayout(new GridLayout(4,1));
		
		content = new JPanel();
		content.setPreferredSize(new Dimension(148,200));
		content.setBackground(Color.gray);
		content.setBorder(BorderFactory.createTitledBorder("Stat personnage"));
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		textEnergie = new JLabel("1");
		textNom = new JLabel("2");
		textPosition = new JLabel("3");
		barEnergie = new JProgressBar(0,100);
		content.add(new JLabel(" "));
		content.add(textNom);
		content.add(textPosition);
		content.add(new JLabel(" "));
		content.add(barEnergie);
		content.add(textEnergie);
		this.add(content);
		
		contentText = new JPanel();
		contentText.setPreferredSize(new Dimension(148,200));
		contentText.setBackground(Color.gray);
		contentText.setBorder(BorderFactory.createTitledBorder("Information"));
		contentText.setLayout(new BoxLayout(contentText, BoxLayout.Y_AXIS));
		contentText.add(new JLabel(" "));
		inventaire = new JPanel();
		inventaire.setBackground(Color.gray);
		cf= new JLabel(coffre);
		cf.setVisible(false);
		cl = new JLabel(clef);
		cl.setVisible(false);
		sa = new JLabel(sabre);
		sa.setVisible(false);
		inventaire.add(cf,BorderLayout.WEST);
		inventaire.add(cl,BorderLayout.CENTER);
		inventaire.add(sa,BorderLayout.EAST);
		contentText.add(inventaire);
		contentText.add(new JLabel(" "));
		info = new JTextArea();
		info.setEditable(false);
		info.setFocusable(false);
		info.setBackground(Color.gray);
		contentText.add(info);
		this.add(contentText);

		contentImage1 = new JPanel();
		this.add(contentImage1);
		contentImage2 = new JPanel();
		this.add(contentImage2);
	}
	
	/**
	 * Initialise l'overlay de l'equipe 1.
	 * 
	 * @param equipe1
	 */
	public void setEquipe1(ArrayList<Personnage> equipe1){
		contentImage1.setPreferredSize(new Dimension(148,200));
		contentImage1.setBackground(Color.gray);
		contentImage1.setBorder(BorderFactory.createTitledBorder("Equipe 1"));
		contentImage1.setLayout(new GridLayout(3,2));
		for(Personnage p : equipe1){
			if(p.getTypePerso()==8){
				contentImage1.add(new JLabel(explorateur));
				contentImage1.add(new JLabel("Explorateur"));
			}else if(p.getTypePerso()==9){
				contentImage1.add(new JLabel(voleur));
				contentImage1.add(new JLabel("Voleur"));
			}else if(p.getTypePerso()==10){
				contentImage1.add(new JLabel(soldat));
				contentImage1.add(new JLabel("Soldat"));
			}else if(p.getTypePerso()==11){
				contentImage1.add(new JLabel(piegeur));
				contentImage1.add(new JLabel("Piegeur"));
			}
		}
	}
	
	/**
	 * Initialise l'overlay de l'equipe 2.
	 * 
	 * @param equipe2
	 */
	public void setEquipe2(ArrayList<Personnage> equipe2){
		contentImage2.setPreferredSize(new Dimension(148,200));
		contentImage2.setBackground(Color.gray);
		contentImage2.setBorder(BorderFactory.createTitledBorder("Equipe 2"));
		contentImage2.setLayout(new GridLayout(3,2));
		for(Personnage p : equipe2){
			if(p.getTypePerso()==8){
				contentImage2.add(new JLabel(explorateur));
				contentImage2.add(new JLabel("Explorateur"));
			}else if(p.getTypePerso()==9){
				contentImage2.add(new JLabel(voleur));
				contentImage2.add(new JLabel("Voleur"));
			}else if(p.getTypePerso()==10){
				contentImage2.add(new JLabel(soldat));
				contentImage2.add(new JLabel("Soldat"));
			}else if(p.getTypePerso()==11){
				contentImage2.add(new JLabel(piegeur));
				contentImage2.add(new JLabel("Piegeur"));
			}
		}
	}
}