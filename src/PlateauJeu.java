import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author pelleria
 * 
 */
public class PlateauJeu extends JFrame implements KeyListener {

	private static final long serialVersionUID = 949959404424872936L;
	private int taille;
	private String[] gifs;
	private JComponent jeu;
	public int[][] plateau;
	private JPanel panel;
	private Image[] images;
	private Animation anim = new Animation();
	public AffichageEnergie nrj = new AffichageEnergie();
	public MajInformation maj = new MajInformation();

	public int[] Bateau1 = new int[2]; // spawn de chaque equipe
	public int[] Bateau2 = new int[2];
	protected boolean equipe1Joue = true;
	protected int nbMouvementEffectue = 0;
	protected ArrayList<Personnage> equipe1 = new ArrayList<>();
	protected ArrayList<Personnage> equipe2 = new ArrayList<>();

	/**
	 * @param gifs
	 * @param taille
	 */
	public PlateauJeu(String[] gifs, int taille) {
		this.taille = taille;
		this.gifs = gifs;
		this.setSize((taille * 55) + taille + 148, (taille * 58) + taille - 2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.addKeyListener(this);
		this.setResizable(false);

		images = new Image[gifs.length];
		for (int i = 0; i < gifs.length; i++) {
			images[i] = Toolkit.getDefaultToolkit().getImage(gifs[i]);
		}

		jeu = new JComponent() {

			public void paint(Graphics g1) {
				Graphics2D g = (Graphics2D) g1;
				super.paint(g);
				if (plateau != null) {
					for (int i = 0; i < plateau.length; i++) {
						for (int j = 0; j < plateau[0].length; j++) {
							if (plateau[i][j] != 0
									&& (Main.g.ile.getParcelle(i, j).visibilite == 3
											|| (equipe1Joue && Main.g.ile
													.getParcelle(i, j).visibilite == 1) || (!equipe1Joue && Main.g.ile
											.getParcelle(i, j).visibilite == 2))) {

								if (plateau[i][j] != 0
										&& (Main.g.ile.getParcelle(i, j).piege == 3
												|| (equipe1Joue && Main.g.ile
														.getParcelle(i, j).piege == 1) || (!equipe1Joue && Main.g.ile
												.getParcelle(i, j).piege == 2))) {
									g.drawImage(images[images.length - 2],
											i * 56, j * 56, this);
								} else {
									g.drawImage(images[plateau[i][j] - 1],
											i * 56, j * 56, this);
								}
							} else {
								g.drawImage(images[images.length - 1], i * 56,
										j * 56, this);
							}
						}
					}
					if (equipe1Joue && nbMouvementEffectue < equipe1.size()) {
						ImageIcon cadre = new ImageIcon(Toolkit
								.getDefaultToolkit().getImage(
										"ressources/cadre1.png"));
						int i = nbMouvementEffectue;
						while (i < equipe1.size()) {
							if (equipe1.get(i).getEstVivant()) {
								g.drawImage(cadre.getImage(), equipe1.get(i)
										.getPosition()[0] * 56, equipe1.get(i)
										.getPosition()[1] * 56, this);
								i=equipe1.size();
							} else {
								i++;
							}
						}
					} else if (!equipe1Joue
							&& nbMouvementEffectue < equipe2.size()) {
						ImageIcon cadre = new ImageIcon(Toolkit
								.getDefaultToolkit().getImage(
										"ressources/cadre2.png"));
						int i = nbMouvementEffectue;
						while (i < equipe2.size()) {
							if (equipe2.get(i).getEstVivant()) {
								g.drawImage(cadre.getImage(), equipe2.get(i)
										.getPosition()[0] * 56, equipe2.get(i)
										.getPosition()[1] * 56, this);
								i=equipe2.size();
							} else {
								i++;
							}
						}
					}
				}
			}
		};

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(new BorderLayout(1, 1));
		panel.add(jeu);
		this.add(panel, BorderLayout.CENTER);
		this.add(nrj, BorderLayout.EAST);
	}

	/**
	 * Attribue les coordonnees de chaque spawn
	 * 
	 * @param i
	 * @param j
	 */
	public void setBateau(int i, int[] j) {
		if (i == 1) {
			this.Bateau1 = j;
		} else {
			this.Bateau2 = j;
		}
	}

	/**
	 * Reaffiche le plateau de jeu
	 */
	public void affichage() {
		this.setVisible(true);
		this.repaint();
		maj.run();
		nrj.setEquipe1(equipe1);
		nrj.setEquipe2(equipe2);
	}

	/**
	 * Attribue le tableau de jeu
	 * 
	 * @param tabl
	 */
	public void setJeu(int[][] tabl) {
		this.plateau = tabl;
		this.repaint();
	}

	public void keyTyped(KeyEvent e) {
		int[] position = new int[2];
		int cptMort = 0;

		if (equipe1Joue) {
			while (nbMouvementEffectue < equipe1.size()
					&& !equipe1.get(nbMouvementEffectue).getEstVivant()) {
				nbMouvementEffectue++;
				this.jeu.repaint();
				cptMort++;
				maj.run();
			}
			if (cptMort == equipe1.size()) {
				JOptionPane.showMessageDialog(this, "L'equipe 2 a gagne");
				this.dispose();
				Main.g.setVisible(true);
			} else {
				cptMort = 0;
			}
		} else if (!equipe1Joue) {
			while (nbMouvementEffectue < equipe2.size()
					&& !equipe2.get(nbMouvementEffectue).getEstVivant()) {
				nbMouvementEffectue++;
				this.jeu.repaint();
				cptMort++;
				maj.run();
			}
			if (cptMort == equipe2.size()) {
				JOptionPane.showMessageDialog(this, "L'equipe 1 a gagne");
				this.dispose();
				Main.g.setVisible(true);
			} else {
				cptMort = 0;
			}
		}

		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			equipe1Joue = !equipe1Joue;
			nbMouvementEffectue = 0;
			nrj.info.setText(" ");
			this.repaint();
			maj.run();
		}

		if (equipe1Joue && nbMouvementEffectue < equipe1.size()) {// deplacement
																	// equipe1
			position = equipe1.get(nbMouvementEffectue).getPosition();
			if (e.getKeyChar() == 'z') {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0], position[1] - 1 }, 1,
						equipe1.get(nbMouvementEffectue).getTypePerso())) {

					equipe1.get(nbMouvementEffectue).setPosition(
							new int[] { position[0], position[1] - 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 's') {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0], position[1] + 1 }, 1,
						equipe1.get(nbMouvementEffectue).getTypePerso())) {

					equipe1.get(nbMouvementEffectue).setPosition(
							new int[] { position[0], position[1] + 1 });
					nbMouvementEffectue++;
					maj.run();
				}

			} else if (e.getKeyChar() == 'd') {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] + 1, position[1] }, 1,
						equipe1.get(nbMouvementEffectue).getTypePerso())) {

					equipe1.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] + 1, position[1] });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'q') {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] - 1, position[1] }, 1,
						equipe1.get(nbMouvementEffectue).getTypePerso())) {

					equipe1.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] - 1, position[1] });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'a'
					&& (equipe1.get(nbMouvementEffectue).getTypePerso() == 9 || equipe1
							.get(nbMouvementEffectue).getTypePerso() == 10)) {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] - 1, position[1] - 1 }, 1,
						equipe1.get(nbMouvementEffectue).getTypePerso())) {

					equipe1.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] - 1, position[1] - 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'e'
					&& (equipe1.get(nbMouvementEffectue).getTypePerso() == 9 || equipe1
							.get(nbMouvementEffectue).getTypePerso() == 10)) {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] + 1, position[1] - 1 }, 1,
						equipe1.get(nbMouvementEffectue).getTypePerso())) {

					equipe1.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] + 1, position[1] - 1 });
					nbMouvementEffectue++;
					maj.run();
				}

			} else if (e.getKeyChar() == 'x'
					&& (equipe1.get(nbMouvementEffectue).getTypePerso() == 9 || equipe1
							.get(nbMouvementEffectue).getTypePerso() == 10)) {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] + 1, position[1] + 1 }, 1,
						equipe1.get(nbMouvementEffectue).getTypePerso())) {

					equipe1.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] + 1, position[1] + 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'w'
					&& (equipe1.get(nbMouvementEffectue).getTypePerso() == 9 || equipe1
							.get(nbMouvementEffectue).getTypePerso() == 10)) {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] - 1, position[1] + 1 }, 1,
						equipe1.get(nbMouvementEffectue).getTypePerso())) {

					equipe1.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] - 1, position[1] + 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'p'
					&& equipe1.get(nbMouvementEffectue).getTypePerso() == 11
					&& (equipe1.get(nbMouvementEffectue).getPosition()[0] != Bateau1[0] || equipe1
							.get(nbMouvementEffectue).getPosition()[1] != Bateau1[1])) {
				Piegeur tmp = (Piegeur) equipe1.get(nbMouvementEffectue);
				tmp.pieger(
						1,
						Main.g.ile.getParcelle(tmp.getPosition()[0],
								tmp.getPosition()[1]));
				plateau[tmp.getPosition()[0]][tmp.getPosition()[1]] = 5;
				if (plateau[tmp.getPosition()[0] + 1][tmp.getPosition()[1]] == 5
						&& Main.g.ile.getParcelle(tmp.getPosition()[0] + 1,
								tmp.getPosition()[1]).piege == 0) {
					plateau[tmp.getPosition()[0] + 1][tmp.getPosition()[1]] = 11;
					tmp.setPosition(new int[] { tmp.getPosition()[0] + 1,
							tmp.getPosition()[1] });
				} else if (plateau[tmp.getPosition()[0] - 1][tmp.getPosition()[1]] == 5
						&& Main.g.ile.getParcelle(tmp.getPosition()[0] - 1,
								tmp.getPosition()[1]).piege == 0) {
					plateau[tmp.getPosition()[0] - 1][tmp.getPosition()[1]] = 11;
					tmp.setPosition(new int[] { tmp.getPosition()[0] - 1,
							tmp.getPosition()[1] });
				} else if (plateau[tmp.getPosition()[0]][tmp.getPosition()[1] + 1] == 5
						&& Main.g.ile.getParcelle(tmp.getPosition()[0],
								tmp.getPosition()[1] + 1).piege == 0) {
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1] + 1] = 11;
					tmp.setPosition(new int[] { tmp.getPosition()[0],
							tmp.getPosition()[1] + 1 });
				} else if (plateau[tmp.getPosition()[0]][tmp.getPosition()[1] - 1] == 5
						&& Main.g.ile.getParcelle(tmp.getPosition()[0],
								tmp.getPosition()[1] - 1).piege == 0) {
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1] - 1] = 11;
					tmp.setPosition(new int[] { tmp.getPosition()[0],
							tmp.getPosition()[1] - 1 });
				} else {
					tmp.antiPiege(Main.g.ile.getParcelle(tmp.getPosition()[0],
							tmp.getPosition()[1]));
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1]] = 11;
				}
				if(!tmp.getEstVivant()){
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1]]=12;
				}
				activeVisibilite(tmp.getPosition());
				equipe1.set(nbMouvementEffectue, tmp);
				nbMouvementEffectue++;
				maj.run();
				this.repaint();
			}
		} else if (!equipe1Joue && nbMouvementEffectue < equipe2.size()) {// deplacement
			// equipe2
			position = equipe2.get(nbMouvementEffectue).getPosition();
			if (e.getKeyChar() == 'z') {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0], position[1] - 1 }, 2,
						equipe2.get(nbMouvementEffectue).getTypePerso())) {

					equipe2.get(nbMouvementEffectue).setPosition(
							new int[] { position[0], position[1] - 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 's') {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0], position[1] + 1 }, 2,
						equipe2.get(nbMouvementEffectue).getTypePerso())) {

					equipe2.get(nbMouvementEffectue).setPosition(
							new int[] { position[0], position[1] + 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'd') {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] + 1, position[1] }, 2,
						equipe2.get(nbMouvementEffectue).getTypePerso())) {

					equipe2.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] + 1, position[1] });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'q') {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] - 1, position[1] }, 2,
						equipe2.get(nbMouvementEffectue).getTypePerso())) {

					equipe2.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] - 1, position[1] });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'a'
					&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 9 || equipe2
							.get(nbMouvementEffectue).getTypePerso() == 10)) {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] - 1, position[1] - 1 }, 2,
						equipe2.get(nbMouvementEffectue).getTypePerso())) {

					equipe2.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] - 1, position[1] - 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'e'
					&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 9 || equipe2
							.get(nbMouvementEffectue).getTypePerso() == 10)) {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] + 1, position[1] - 1 }, 2,
						equipe2.get(nbMouvementEffectue).getTypePerso())) {

					equipe2.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] + 1, position[1] - 1 });
					nbMouvementEffectue++;
					maj.run();
				}

			} else if (e.getKeyChar() == 'x'
					&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 9 || equipe2
							.get(nbMouvementEffectue).getTypePerso() == 10)) {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] + 1, position[1] + 1 }, 2,
						equipe2.get(nbMouvementEffectue).getTypePerso())) {

					equipe2.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] + 1, position[1] + 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'w'
					&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 9 || equipe2
							.get(nbMouvementEffectue).getTypePerso() == 10)) {
				if (moove(new int[] { position[0], position[1] }, new int[] {
						position[0] - 1, position[1] + 1 }, 2,
						equipe2.get(nbMouvementEffectue).getTypePerso())) {

					equipe2.get(nbMouvementEffectue).setPosition(
							new int[] { position[0] - 1, position[1] + 1 });
					nbMouvementEffectue++;
					maj.run();
				}
			} else if (e.getKeyChar() == 'p'
					&& equipe2.get(nbMouvementEffectue).getTypePerso() == 11
					&& (equipe2.get(nbMouvementEffectue).getPosition()[0] != Bateau2[0] || equipe2
							.get(nbMouvementEffectue).getPosition()[1] != Bateau2[1])) {
				Piegeur tmp = (Piegeur) equipe2.get(nbMouvementEffectue);
				tmp.pieger(
						2,
						Main.g.ile.getParcelle(tmp.getPosition()[0],
								tmp.getPosition()[1]));
				plateau[tmp.getPosition()[0]][tmp.getPosition()[1]] = 5;
				if (plateau[tmp.getPosition()[0] + 1][tmp.getPosition()[1]] == 5
						&& Main.g.ile.getParcelle(tmp.getPosition()[0] + 1,
								tmp.getPosition()[1]).piege == 0) {
					plateau[tmp.getPosition()[0] + 1][tmp.getPosition()[1]] = 11;
					tmp.setPosition(new int[] { tmp.getPosition()[0] + 1,
							tmp.getPosition()[1] });
				} else if (plateau[tmp.getPosition()[0] - 1][tmp.getPosition()[1]] == 5
						&& Main.g.ile.getParcelle(tmp.getPosition()[0] - 1,
								tmp.getPosition()[1]).piege == 0) {
					plateau[tmp.getPosition()[0] - 1][tmp.getPosition()[1]] = 11;
					tmp.setPosition(new int[] { tmp.getPosition()[0] - 1,
							tmp.getPosition()[1] });
				} else if (plateau[tmp.getPosition()[0]][tmp.getPosition()[1] + 1] == 5
						&& Main.g.ile.getParcelle(tmp.getPosition()[0],
								tmp.getPosition()[1] + 1).piege == 0) {
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1] + 1] = 11;
					tmp.setPosition(new int[] { tmp.getPosition()[0],
							tmp.getPosition()[1] + 1 });
				} else if (plateau[tmp.getPosition()[0]][tmp.getPosition()[1] - 1] == 5
						&& Main.g.ile.getParcelle(tmp.getPosition()[0],
								tmp.getPosition()[1] - 1).piege == 0) {
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1] - 1] = 11;
					tmp.setPosition(new int[] { tmp.getPosition()[0],
							tmp.getPosition()[1] - 1 });
				} else {
					tmp.antiPiege(Main.g.ile.getParcelle(tmp.getPosition()[0],
							tmp.getPosition()[1]));
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1]] = 11;
				}
				activeVisibilite(tmp.getPosition());
				if(!tmp.getEstVivant()){
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1]]=12;
				}
				equipe2.set(nbMouvementEffectue, tmp);
				nbMouvementEffectue++;
				maj.run();
				this.repaint();
			}
		} else {
			nrj.info.setText("Le nombre de \nmouvement a ete\natteint");
			maj.run();
		}
	}

	/**
	 * Prend la case de depart et la case d'arrive ainsi que lequipe et le
	 * personnage ,deplace le personnage sur la case darrive et retourne si le
	 * deplacement a ete fait
	 * @param dep
	 * @param ari
	 * @param equipe
	 * @return
	 */
	public boolean moove(int[] dep, int[] ari, int equipe, int personnage) {
		boolean effectue = false;
		if (plateau[ari[0]][ari[1]] == 5 || plateau[ari[0]][ari[1]] == 12) {
			if ((personnage == 10 || personnage == 11)
					&& plateau[ari[0]][ari[1]] == 12) {
				return false;
			}

			if (Main.g.ile.getParcelle(ari[0], ari[1]).cle) {
				if (equipe1Joue
						&& (equipe1.get(nbMouvementEffectue).getTypePerso() == 8 || equipe1
								.get(nbMouvementEffectue).getTypePerso() == 9)) {
					equipe1.get(nbMouvementEffectue).setClef(true);
					Main.g.ile.getParcelle(ari[0], ari[1]).cle = false;
					nrj.info.setText("Tu as recupere\nla clef");
				} else if (!equipe1Joue
						&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 8 || equipe2
								.get(nbMouvementEffectue).getTypePerso() == 9)) {
					equipe2.get(nbMouvementEffectue).setClef(true);
					Main.g.ile.getParcelle(ari[0], ari[1]).cle = false;
					nrj.info.setText("Tu as recupere\nla clef");
				}
			}
			if (Main.g.ile.getParcelle(ari[0], ari[1]).coffre) {
				if (equipe1Joue
						&& (equipe1.get(nbMouvementEffectue).getTypePerso() == 8 || equipe1
								.get(nbMouvementEffectue).getTypePerso() == 9)) {
					equipe1.get(nbMouvementEffectue).setCoffre(true);
					Main.g.ile.getParcelle(ari[0], ari[1]).coffre = false;
					nrj.info.setText("Tu as recupere\nle coffre");
				} else if (!equipe1Joue
						&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 8 || equipe2
								.get(nbMouvementEffectue).getTypePerso() == 9)) {
					equipe2.get(nbMouvementEffectue).setCoffre(true);
					Main.g.ile.getParcelle(ari[0], ari[1]).coffre = false;
					nrj.info.setText("Tu as recupere\nle coffre");
				}
			}

			if (equipe1Joue) {
				plateau[ari[0]][ari[1]] = equipe1.get(nbMouvementEffectue)
						.getTypePerso();
			} else {
				plateau[ari[0]][ari[1]] = equipe2.get(nbMouvementEffectue)
						.getTypePerso();
			}
			Main.g.ile.getParcelle(ari[0], ari[1]).setPersonage(equipe,
					personnage);

			activeVisibilite(ari);
		
			Main.g.ile.getParcelle(dep[0], dep[1]).setPersonage(equipe, 0);
			if ((dep[0] != Bateau1[0] || dep[1] != Bateau1[1])
					&& (dep[0] != Bateau2[0] || dep[1] != Bateau2[1])) {
				plateau[dep[0]][dep[1]] = 5;
			}
			this.jeu.repaint();
			if (equipe1Joue) {
				equipe1.get(nbMouvementEffectue).setPosition(ari);
				equipe1.get(nbMouvementEffectue).retirerEnergie(2);
				if (Main.g.ile.getParcelle(ari[0], ari[1]).piege == 1) {
					nrj.info.setText("Tu as rebouche\nton piege");
					Main.g.ile.getParcelle(ari[0], ari[1]).piege = 0;
				} else if (Main.g.ile.getParcelle(ari[0], ari[1]).piege == 2
						|| Main.g.ile.getParcelle(ari[0], ari[1]).piege == 3) {
					nrj.info.setText("Tu es tombe\ndans un piege");
					equipe1.get(nbMouvementEffectue).retirerEnergie(15);
					Main.g.ile.getParcelle(ari[0], ari[1]).piege = 0;
				}
			} else {
				equipe2.get(nbMouvementEffectue).setPosition(ari);
				equipe2.get(nbMouvementEffectue).retirerEnergie(2);
				if (Main.g.ile.getParcelle(ari[0], ari[1]).piege == 2) {
					nrj.info.setText("Tu as rebouche\nton piege");
					Main.g.ile.getParcelle(ari[0], ari[1]).piege = 0;
				} else if (Main.g.ile.getParcelle(ari[0], ari[1]).piege == 1
						|| Main.g.ile.getParcelle(ari[0], ari[1]).piege == 3) {
					nrj.info.setText("Tu es tombe\ndans un piege");
					equipe2.get(nbMouvementEffectue).retirerEnergie(15);
					Main.g.ile.getParcelle(ari[0], ari[1]).piege = 0;
				}
			}
			this.repaint();
			effectue = true;
		} else if (plateau[ari[0]][ari[1]] == 4
				&& ((dep[0] != Bateau1[0] || dep[1] != Bateau1[1]) && (dep[0] != Bateau2[0] || dep[1] != Bateau2[1]))
				&& personnage == 8) {
			if (equipe1Joue) {
				Explorateur tmp = (Explorateur) equipe1
						.get(nbMouvementEffectue);
				tmp.setenergie(equipe1.get(nbMouvementEffectue).getEnergie());
				int test = tmp.soulever(Main.g.ile.getParcelle(ari[0], ari[1]));
				if (test == 1) {
					nrj.info.setText("Tu prends la clef");
				} else if (test == 2) {
					nrj.info.setText("Tu prends le coffre");
				} else if (test == 0) {
					nrj.info.setText("Le rocher est vide");
				}
				equipe1.set(nbMouvementEffectue, tmp);
			} else {
				Explorateur tmp = (Explorateur) equipe2
						.get(nbMouvementEffectue);
				tmp.setenergie(equipe2.get(nbMouvementEffectue).getEnergie());
				int test = tmp.soulever(Main.g.ile.getParcelle(ari[0], ari[1]));
				if (test == 1) {
					nrj.info.setText("Tu prends la clef");
				} else if (test == 2) {
					nrj.info.setText("Tu prends le coffre");
				} else if (test == 0) {
					nrj.info.setText("Le rocher est vide");
				}
				equipe2.set(nbMouvementEffectue, tmp);
			}
			this.jeu.repaint();
			effectue = false;
			nbMouvementEffectue++;
			maj.run();
		} else if (equipe1Joue && ari[0] == Bateau1[0] && ari[1] == Bateau1[1]) {
			plateau[dep[0]][dep[1]] = 5;
			this.jeu.repaint();
			if (equipe1.get(nbMouvementEffectue).getCoffre()) {
				JOptionPane.showMessageDialog(null, "L'equipe 1 a gagne");
				this.dispose();
				Main.g.setVisible(true);
			} else if (equipe1.get(nbMouvementEffectue).getClef()
					&& personnage == 9) {
				Main.g.ile.getParcelle(ari[0], ari[1]).cle = true;
				nrj.info.setText("Tu remet la clef au bateau");
				equipe1.get(nbMouvementEffectue).setClef(false);
			} else if (Main.g.ile.getParcelle(ari[0], ari[1]).cle
					&& personnage == 8) {
				Main.g.ile.getParcelle(ari[0], ari[1]).cle = false;
				nrj.info.setText("L'explorateur a reprit la clef");
				equipe1.get(nbMouvementEffectue).setClef(true);
			}
			if (equipe1.get(nbMouvementEffectue).getTypePerso() == 10) {
				equipe1.get(nbMouvementEffectue).setArme(true);
				nrj.info.setText("Le guerrier a repris\nune arme");
			}
			equipe1.get(nbMouvementEffectue).ajouterEnergie(50);
			effectue = true;
		} else if (!equipe1Joue && ari[0] == Bateau2[0] && ari[1] == Bateau2[1]) {
			plateau[dep[0]][dep[1]] = 5;
			this.jeu.repaint();
			if (equipe2.get(nbMouvementEffectue).getCoffre()) {
				JOptionPane.showMessageDialog(null, "L'equipe 2 a gagne");
				this.dispose();
				Main.g.setVisible(true);
			} else if (equipe2.get(nbMouvementEffectue).getClef()
					&& personnage == 9) {
				Main.g.ile.getParcelle(ari[0], ari[1]).cle = true;
				nrj.info.setText("Tu remet la clef au bateau");
				equipe2.get(nbMouvementEffectue).setClef(false);
			} else if (Main.g.ile.getParcelle(ari[0], ari[1]).cle
					&& personnage == 8) {
				Main.g.ile.getParcelle(ari[0], ari[1]).cle = false;
				nrj.info.setText("L'explorateur a reprit la clef");
				equipe2.get(nbMouvementEffectue).setClef(true);
			}
			equipe2.get(nbMouvementEffectue).ajouterEnergie(50);
			effectue = true;
		} else if (plateau[ari[0]][ari[1]] != 1 && plateau[ari[0]][ari[1]] != 4
				&& plateau[ari[0]][ari[1]] != 11 && personnage == 9) {
			if (equipe1Joue) {
				Voleur tmp = (Voleur) equipe1.get(nbMouvementEffectue);
				tmp.setenergie(equipe1.get(nbMouvementEffectue).getEnergie());
				if (persoVoler(ari, 1) != null) {
					equipe2.set(indexPerso(ari, 1),
							tmp.voler(persoVoler(ari, 1), ari));
				}
				equipe1.set(nbMouvementEffectue, tmp);
			} else {
				Voleur tmp = (Voleur) equipe2.get(nbMouvementEffectue);
				tmp.setenergie(equipe2.get(nbMouvementEffectue).getEnergie());
				if (persoVoler(ari, 2) != null) {
					equipe1.set(indexPerso(ari, 2),
							tmp.voler(persoVoler(ari, 2), ari));
				}
				equipe2.set(nbMouvementEffectue, tmp);
			}
			nbMouvementEffectue++;
			this.repaint();
			maj.run();
		} else if (plateau[ari[0]][ari[1]] != 1 && plateau[ari[0]][ari[1]] != 4
				&& plateau[ari[0]][ari[1]] != 11 && personnage == 10) {
			if (equipe1Joue) {
				Guerrier tmp = (Guerrier) equipe1.get(nbMouvementEffectue);
				tmp.setenergie(equipe1.get(nbMouvementEffectue).getEnergie());
				tmp.setArme(equipe1.get(nbMouvementEffectue).getArme());
				if (persoAgresse(ari, 1) != null && tmp.getArme()) {
					equipe2.set(
							indexPerso(ari, 1),
							tmp.agresser(persoAgresse(ari, 1), ari,
									indexPerso(ari, 1)));
				} else if (persoAgresse(ari, 1) != null && !tmp.getArme()) {
					nrj.info.setText("Vous ne pouvez pas\nattaquer sans arme !");
				}
				equipe1.set(nbMouvementEffectue, tmp);
			} else {
				Guerrier tmp = (Guerrier) equipe2.get(nbMouvementEffectue);
				tmp.setenergie(equipe2.get(nbMouvementEffectue).getEnergie());
				tmp.setArme(equipe2.get(nbMouvementEffectue).getArme());
				if (persoAgresse(ari, 2) != null && tmp.getArme()) {
					equipe1.set(
							indexPerso(ari, 2),
							tmp.agresser(persoAgresse(ari, 2), ari,
									indexPerso(ari, 2)));
				} else if (persoAgresse(ari, 2) != null && !tmp.getArme()) {
					nrj.info.setText("Vous ne pouvez pas\nattaquer sans arme !");
				}
				equipe2.set(nbMouvementEffectue, tmp);
			}
			nbMouvementEffectue++;
			this.repaint();
			maj.run();
		}

		return effectue;
	}

	/**
	 * Permet d'obtenir le personnage qui sera vole.
	 * 
	 * @param pos
	 * @param equipe
	 * @return
	 */
	private Personnage persoVoler(int[] pos, int equipe) {
		if (equipe == 1) {
			for (Personnage e : equipe2) {
				if (e.getPosition()[0] == pos[0]
						&& e.getPosition()[1] == pos[1]) {
					return e;
				}
			}
		} else {
			for (Personnage e : equipe1) {
				if (e.getPosition()[0] == pos[0]
						&& e.getPosition()[1] == pos[1]) {
					return e;
				}
			}
		}
		return null;
	}

	/**
	 * Retourne le numero du personnage dans la liste de son equipe en fonction de ses coordonnees.
	 * 
	 * @param pos
	 * @param equipe
	 * @return
	 */
	private int indexPerso(int[] pos, int equipe) {
		if (equipe == 1) {
			for (int i = 0; i < equipe2.size(); i++) {
				if (equipe2.get(i).getPosition()[0] == pos[0]
						&& equipe2.get(i).getPosition()[1] == pos[1]) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < equipe1.size(); i++) {
				if (equipe1.get(i).getPosition()[0] == pos[0]
						&& equipe1.get(i).getPosition()[1] == pos[1]) {
					return i;
				}
			}
		}
		return 0;
	}

	/**
	 * Permet d'obtenir le personnage qui sera agresse.
	 * 
	 * @param pos
	 * @param equipe
	 * @return
	 */
	private Personnage persoAgresse(int[] pos, int equipe) {
		if (equipe == 1) {
			for (Personnage e : equipe2) {
				if (e.getPosition()[0] == pos[0]
						&& e.getPosition()[1] == pos[1]) {
					return e;
				}
			}
		} else {
			for (Personnage e : equipe1) {
				if (e.getPosition()[0] == pos[0]
						&& e.getPosition()[1] == pos[1]) {
					return e;
				}
			}
		}
		return null;
	}
	
	/**
	 * Permet d'actualiser la visibilite.
	 * 
	 * @param ari
	 */
	public void activeVisibilite(int[] ari){
		for (int i = ari[0] - 1; i <= ari[0] + 1; i++) {
			for (int j = ari[1] - 1; j <= ari[1] + 1; j++) {
				if (equipe1Joue
						&& Main.g.ile.getParcelle(i, j).visibilite != 1
						&& Main.g.ile.getParcelle(i, j).visibilite != 3) {
					Main.g.ile.getParcelle(i, j).visibilite += 1;
				} else if (!equipe1Joue
						&& Main.g.ile.getParcelle(i, j).visibilite != 2
						&& Main.g.ile.getParcelle(i, j).visibilite != 3) {
					Main.g.ile.getParcelle(i, j).visibilite += 2;
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
