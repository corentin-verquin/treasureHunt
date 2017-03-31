import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * class qui permet d'afficher une interface graphique
 * 
 * @author corentin
 */
public class Gui extends JFrame {
	private static final long serialVersionUID = -8914198129686410046L;
	private int taille;
	private double pourcentage;
	public PlateauJeu PlateauJeu;
	public static Ile ile;
	int i1 = 0, i2 = 0;

	/**
	 * Constructeur de l'interface
	 */
	public Gui() {
		this.taille = 10;
		this.pourcentage = 0.1;
		this.PlateauJeu = null;// car l'ile n'est pas instancier
		this.ile = null;// pour eviter plusieur generation de la map
	}

	/**
	 * Permet d'afficher le menu d'accueil du jeu et regler le jeu
	 */
	public void menu() {
		// ensemble des objet present sur la fenetre
		final JButton b1 = new JButton("Multi joueur");
		final JButton b2 = new JButton("Parametres");
		final JButton b3 = new JButton("Valider");
		final JButton b4 = new JButton("Valider");
		final JButton b5 = new JButton("Jouer");
		final JButton b6 = new JButton("Solo");
		final JButton b7 = new JButton("Valider");
		final JLabel equi1 = new JLabel("Equipe 1");
		final JLabel equi2 = new JLabel("Equipe 2");
		final JLabel nom = new JLabel("Explorateur Piegeur Voleur Soldat");
		ImageIcon explorateur = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("ressources/exploVide.png"));
		ImageIcon voleur = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"ressources/voleurVide.png"));
		ImageIcon soldat = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"ressources/soldatVide.png"));
		ImageIcon piegeur = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"ressources/piegeurVide.png"));
		final JButton exp1 = new JButton(explorateur);
		final JButton exp2 = new JButton(explorateur);
		final JButton vol1 = new JButton(voleur);
		final JButton vol2 = new JButton(voleur);
		final JButton sol1 = new JButton(soldat);
		final JButton sol2 = new JButton(soldat);
		final JButton pie1 = new JButton(piegeur);
		final JButton pie2 = new JButton(piegeur);
		final JTextField t1 = new JTextField("", 30);
		final JTextField t2 = new JTextField("", 30);
		final JLabel l1 = new JLabel(
				"<html>Taille de la carte<br>comprise entre 10 et 20 </html>");
		final JLabel l2 = new JLabel(
				"<html>Pourcentage de rochers<br>compris entre 10% et 35% </htlm>");
		// fond d'ecran
		final ImageIcon fond1 = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("ressources/font.jpg"));
		final JLabel lfond = new JLabel(fond1);
		final ImageIcon fond2 = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("ressources/fontSetting.jpg"));
		final JLabel lfond2 = new JLabel(fond2);
		final ImageIcon fond3 = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("ressources/aide.png"));
		final JLabel lfond3 = new JLabel(fond3);

		// reglage des options de la fenetre et ajout des objets
		this.setSize(500, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(nom);
		this.add(equi1);
		this.add(equi2);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(b6);
		this.add(b7);
		this.add(exp1);
		this.add(exp2);
		this.add(vol1);
		this.add(vol2);
		this.add(sol1);
		this.add(sol2);
		this.add(pie1);
		this.add(pie2);
		this.add(t1);
		this.add(t2);
		this.add(l1);
		this.add(l2);
		this.add(lfond);
		this.add(lfond2);
		this.add(lfond3);

		// positionement des fond d'ecran
		lfond.setBounds(0, 0, 500, 300);
		lfond2.setVisible(false);
		lfond2.setBounds(0, 0, 500, 300);
		lfond3.setVisible(false);
		lfond3.setBounds(0, 0, 500, 300);
		
		// positionement des textbox
		t1.setVisible(false);
		t1.setBounds(40, 50, 100, 30);
		l1.setVisible(false);
		l1.setForeground(new Color(0, 0, 0));
		l1.setBounds(10, 10, 200, 30);

		t2.setVisible(false);
		t2.setBounds(350, 50, 100, 30);
		l2.setVisible(false);
		l2.setForeground(new Color(0, 0, 0));
		l2.setBounds(310, 10, 190, 30);
		nom.setBounds(120, 95, 500, 40);
		nom.setVisible(false);

		equi1.setBounds(40, 60, 200, 30);
		equi1.setVisible(false);
		equi2.setBounds(40, 140, 200, 30);
		equi2.setVisible(false);

		// bouton qui permet de jouer
		b1.setBounds(175, 200, 150, 25);
		b1.addActionListener(new ActionListener() {
			// action au clique sur le bouton
			public void actionPerformed(ActionEvent e) {
				String[] png = new String[] { "ressources/eau.png",
						"ressources/navire1.png", "ressources/navire2.png",
						"ressources/roche.jpg", "ressources/herbe.jpg",
						"ressources/cle.png", "ressources/coffre.png",
						"ressources/1.explorateur.png",
						"ressources/voleur.png", "ressources/soldat.jpg",
						"ressources/piegeur.jpg", "ressources/rip.jpg",
						"ressources/piege.jpg",

						"ressources/brouillard.png" };
				ile = new Ile(Gui.this.taille, Gui.this.pourcentage);// instancie
																		// l'ile
				PlateauJeu = new PlateauJeu(png, Gui.this.taille);// instancie
																	// le
																	// PlateauJeu
				PlateauJeu.setJeu(Gui.this.ile.getTableau());
				PlateauJeu.setBateau(1, Gui.this.ile.getBateau(1));
				PlateauJeu.setBateau(2, Gui.this.ile.getBateau(2));

				lfond.setVisible(false);
				b1.setVisible(false);
				b2.setVisible(false);
				b6.setVisible(false);
				lfond2.setVisible(true);
				b4.setVisible(true);
				exp1.setVisible(true);
				exp2.setVisible(true);
				vol1.setVisible(true);
				vol2.setVisible(true);
				pie1.setVisible(true);
				pie2.setVisible(true);
				sol1.setVisible(true);
				sol2.setVisible(true);
				equi1.setVisible(true);
				equi2.setVisible(true);
				nom.setVisible(true);
			}
		});

		// bouton qui permet d'aller dans les parametres
		b2.setBounds(175, 235, 150, 25);
		b2.addActionListener(new ActionListener() {
			// action au clique sur le bouton
			public void actionPerformed(ActionEvent e) {
				// masque les composent de l'acceuil
				b1.setVisible(false);
				b2.setVisible(false);
				b6.setVisible(false);
				lfond.setVisible(false);
				// affiche les composent des parametres
				lfond2.setVisible(true);
				b3.setVisible(true);
				t1.setText(Gui.this.taille + "");// affiche la taille actuelle
				t2.setText((int) (Gui.this.pourcentage * 100) + "");// affiche
																	// le
																	// pourcentage
																	// actuelle
				t1.setVisible(true);
				t2.setVisible(true);
				l1.setVisible(true);
				l2.setVisible(true);
			}
		});

		// bouton valider pour enregistre et revenir a l'acceuil
		b3.setVisible(false);
		b3.setBounds(175, 235, 150, 25);
		b3.addActionListener(new ActionListener() {
			// action au clique sur le bouton
			public void actionPerformed(ActionEvent e) {
				if (verifSaisie(t1.getText(), t2.getText())) {// verifie la
																// saisie
					// affiche les ellements de l'acceuil
					b1.setVisible(true);
					b2.setVisible(true);
					lfond.setVisible(true);
					// masque les composents des parametres
					lfond2.setVisible(false);
					b3.setVisible(false);
					b6.setVisible(true);
					t1.setVisible(false);
					t2.setVisible(false);
					l1.setVisible(false);
					l2.setVisible(false);

					// fixe les attributs taille et pourcent au choix de
					// l'utilisateur
					Gui.this.taille = Integer.parseInt(t1.getText());
					Gui.this.pourcentage = (Integer.parseInt(t2.getText()) / 100.0);

					JOptionPane.showMessageDialog(null,
							"Modification enregistre");
				} else {
					JOptionPane.showMessageDialog(null, "Saisie incorect");
				}
			}
		});

		b4.setBounds(175, 235, 150, 25);
		b4.setVisible(false);
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (PlateauJeu.equipe1.size() == 0) {
					JOptionPane.showMessageDialog(null, "L'equipe 1 est vide");
				} else if (PlateauJeu.equipe2.size() == 0) {
					JOptionPane.showMessageDialog(null, "L'equipe 2 est vide");
				} else {
					b4.setVisible(false);
					exp1.setVisible(false);
					exp2.setVisible(false);
					vol1.setVisible(false);
					vol2.setVisible(false);
					pie1.setVisible(false);
					pie2.setVisible(false);
					sol1.setVisible(false);
					sol2.setVisible(false);
					equi1.setVisible(false);
					equi2.setVisible(false);
					lfond2.setVisible(false);
					lfond3.setVisible(true);
					nom.setVisible(false);
					b5.setVisible(true);
				}
			}
		});

		b5.setBounds(410, 10, 80, 25);
		b5.setVisible(false);
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				b5.setVisible(false);
				lfond3.setVisible(false);
				lfond.setVisible(true);
				b1.setVisible(true);
				b2.setVisible(true);
				b6.setVisible(true);
				i1 = 0;
				i2 = 0;
				Gui.this.setVisible(false);
				PlateauJeu.affichage();
			}
		});

		// bouton qui permet de jouer
		b6.setBounds(175, 165, 150, 25);
		b6.addActionListener(new ActionListener() {
			// action au clique sur le bouton
			public void actionPerformed(ActionEvent e) {
				String[] png = new String[] { "ressources/eau.png",
						"ressources/navire1.png", "ressources/navire2.png",
						"ressources/roche.jpg", "ressources/herbe.jpg",
						"ressources/cle.png", "ressources/coffre.png",
						"ressources/1.explorateur.png",
						"ressources/voleur.png", "ressources/soldat.jpg",
						"ressources/piegeur.jpg", "ressources/rip.jpg",
						"ressources/piege.jpg",

						"ressources/brouillard.png" };
				Gui.this.ile = new Ile(Gui.this.taille, Gui.this.pourcentage);// instancie
																				// l'ile
				Gui.this.PlateauJeu = new Solo(png, Gui.this.taille);// instancie
																		// le
																		// PlateauJeu
				Gui.this.PlateauJeu.setJeu(Gui.this.ile.getTableau());
				Gui.this.PlateauJeu.setBateau(1, Gui.this.ile.getBateau(1));
				Gui.this.PlateauJeu.setBateau(2, Gui.this.ile.getBateau(2));

				lfond.setVisible(false);
				b1.setVisible(false);
				b2.setVisible(false);
				b6.setVisible(false);
				lfond2.setVisible(true);
				b7.setVisible(true);
				exp1.setVisible(true);
				vol1.setVisible(true);
				pie1.setVisible(true);
				sol1.setVisible(true);
				equi1.setVisible(true);
				nom.setVisible(true);
			}
		});

		b7.setBounds(175, 235, 150, 25);
		b7.setVisible(false);
		b7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (PlateauJeu.equipe1.size() == 0) {
					JOptionPane.showMessageDialog(null, "L'equipe 1 est vide");
				} else {
					for (int i = 0; i < PlateauJeu.equipe1.size(); i++) {
						if(i==0){
							PlateauJeu.equipe2.add(new Explorateur(2,PlateauJeu.Bateau2));
						}else{
							Random alea=new Random();
							int nbrAlea=alea.nextInt(70);
							if(nbrAlea<20){
								PlateauJeu.equipe2.add(new Voleur(2,PlateauJeu.Bateau2));
							}else if(nbrAlea<40){
								PlateauJeu.equipe2.add(new Piegeur(2,PlateauJeu.Bateau2));
							}else if(nbrAlea<60){
								PlateauJeu.equipe2.add(new Guerrier(2,PlateauJeu.Bateau2));
							}else{
								PlateauJeu.equipe2.add(new Explorateur(2,PlateauJeu.Bateau2));
							}
						}
					}
					b7.setVisible(false);
					exp1.setVisible(false);
					exp2.setVisible(false);
					vol1.setVisible(false);
					vol2.setVisible(false);
					pie1.setVisible(false);
					pie2.setVisible(false);
					sol1.setVisible(false);
					sol2.setVisible(false);
					equi1.setVisible(false);
					equi2.setVisible(false);
					lfond2.setVisible(false);
					lfond3.setVisible(true);
					b5.setVisible(true);
					nom.setVisible(false);
				}
			}
		});

		exp1.setBounds(140, 50, 45, 45);
		exp1.setVisible(false);
		exp1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (i1 < 3) {
					PlateauJeu.equipe1.add(new Explorateur(1,
							PlateauJeu.Bateau1));
					i1++;
				}
			}
		});

		pie1.setBounds(195, 50, 45, 45);
		pie1.setVisible(false);
		pie1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (i1 < 3) {
					PlateauJeu.equipe1.add(new Piegeur(1, PlateauJeu.Bateau1));
					i1++;
				}
			}
		});

		vol1.setBounds(250, 50, 45, 45);
		vol1.setVisible(false);
		vol1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (i1 < 3) {
					PlateauJeu.equipe1.add(new Voleur(1, PlateauJeu.Bateau1));
					i1++;
				}
			}
		});

		sol1.setBounds(305, 50, 45, 45);
		sol1.setVisible(false);
		sol1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (i1 < 3) {
					PlateauJeu.equipe1.add(new Guerrier(1, PlateauJeu.Bateau1));
					i1++;
				}
			}
		});

		exp2.setBounds(140, 135, 45, 45);
		exp2.setVisible(false);
		exp2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (i2 < 3) {
					PlateauJeu.equipe2.add(new Explorateur(2,
							PlateauJeu.Bateau2));
					i2++;
				}
			}
		});

		pie2.setBounds(195, 135, 45, 45);
		pie2.setVisible(false);
		pie2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (i2 < 3) {
					PlateauJeu.equipe2.add(new Piegeur(2, PlateauJeu.Bateau2));
					i2++;
				}
			}
		});

		vol2.setBounds(250, 135, 45, 45);
		vol2.setVisible(false);
		vol2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (i2 < 3) {
					PlateauJeu.equipe2.add(new Voleur(2, PlateauJeu.Bateau2));
					i2++;
				}
			}
		});

		sol2.setBounds(305, 135, 45, 45);
		sol2.setVisible(false);
		sol2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (i2 < 3) {
					PlateauJeu.equipe2.add(new Guerrier(2, PlateauJeu.Bateau2));
					i2++;
				}
			}
		});

		// permet d'afficher la fenetre
		this.setVisible(true);
	}

	/**
	 * verifie si la saisie de l'utilisateur est correcte
	 * 
	 * @param String
	 *            Staille
	 * @param String
	 *            Spourcent
	 * @return boolean
	 */
	private boolean verifSaisie(String Staille, String Spourcent) {
		try {// test si le String est un nombre
			if (Integer.parseInt(Staille) < 10
					|| Integer.parseInt(Staille) > 20) { // test les bornes de
															// taille
				return false;
			} else if (Integer.parseInt(Spourcent) < 10 // test les bornes de
														// pourcent
					|| Integer.parseInt(Spourcent) > 35) {
				return false;
			}
		} catch (Exception e) {// si ce n'est pas un nombre
			return false;
		}
		return true;
	}
}
