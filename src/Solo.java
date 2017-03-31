import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JOptionPane;

public class Solo extends PlateauJeu {

	private static final long serialVersionUID = 4432006864927249241L;

	private Random alea = new Random();

	/*
	 * Permet d'instancier un jeu contre une IA
	 * 
	 * @param gifs
	 * 
	 * @param taille
	 */
	public Solo(String[] gifs, int taille) {
		super(gifs, taille);

	}

	/*
	 * Prise de touches en continu
	 */
	public void keyTyped(KeyEvent e) {
		int[] position = new int[2];
		int cptMort = 0;

		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			maj.run();
			equipe1Joue = false;
			nbMouvementEffectue = 0;
			maj.run();
			go();
			try {
				Thread.sleep(100);
			} catch (Exception exc) {

			}
			equipe1Joue = true;
			nbMouvementEffectue = 0;
			maj.run();
			this.repaint();
			nrj.info.setText(" ");
		}

		while (nbMouvementEffectue < equipe1.size()
				&& !equipe1.get(nbMouvementEffectue).getEstVivant()) {
			nbMouvementEffectue++;
			cptMort++;
			maj.run();
		}
		if (cptMort == equipe1.size()) {
			JOptionPane.showMessageDialog(null, "L'equipe 2 a gagne");
			this.dispose();
			Main.g.setVisible(true);
		} else {
			cptMort = 0;
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
				activeVisibilite(tmp.getPosition());
				if (!tmp.getEstVivant()) {
					plateau[tmp.getPosition()[0]][tmp.getPosition()[1]] = 12;
				}
				equipe1.set(nbMouvementEffectue, tmp);
				nbMouvementEffectue++;
				maj.run();
				this.repaint();
			}
		} else {
			nrj.info.setText("Le nombre de mouvement\na ete atteint");
		}
	}

	/*
	 * Genere les actions de l'IA
	 */
	/**
	 * 
	 */
	public void go() {
		int[] position = new int[2];
		int max = equipe1.size();
		// int nbMouvementEffectue = 0;
		char touche = ':';
		int cptMort = 0;

		while (nbMouvementEffectue < equipe2.size()) {
			while (nbMouvementEffectue < equipe2.size()
					&& !equipe2.get(nbMouvementEffectue).getEstVivant()) {
				nbMouvementEffectue++;
				cptMort++;
				maj.run();
			}
			if (cptMort == equipe2.size()) {
				JOptionPane.showMessageDialog(null, "L'equipe 1 a gagne");
				this.dispose();
				Main.g.setVisible(true);
			} else {
				cptMort = 0;
			}
			if (nbMouvementEffectue < equipe2.size()) {
				if (alea.nextInt() < 0.5) {
					touche = this.equipe2.get(nbMouvementEffectue)
							.getTouchePrev();
				} else {
					touche = this.equipe2.get(nbMouvementEffectue)
							.getTouchePrev();
					while (touche == this.equipe2.get(nbMouvementEffectue)
							.getTouchePrev()) {
						touche = this.equipe2.get(nbMouvementEffectue)
								.getToucheIndice(
										alea.nextInt(this.equipe2.get(
												nbMouvementEffectue)
												.getNbrTouches()));
					}
				}
				this.equipe2.get(nbMouvementEffectue).setTouchePrev(touche);

				position = equipe2.get(nbMouvementEffectue).getPosition();
				if (touche == 'z') {
					if (moove(new int[] { position[0], position[1] },
							new int[] { position[0], position[1] - 1 }, 2,
							equipe2.get(nbMouvementEffectue).getTypePerso())) {

						equipe2.get(nbMouvementEffectue).setPosition(
								new int[] { position[0], position[1] - 1 });
						nbMouvementEffectue++;
						maj.run();
					}
				} else if (touche == 's') {
					if (moove(new int[] { position[0], position[1] },
							new int[] { position[0], position[1] + 1 }, 2,
							equipe2.get(nbMouvementEffectue).getTypePerso())) {

						equipe2.get(nbMouvementEffectue).setPosition(
								new int[] { position[0], position[1] + 1 });
						nbMouvementEffectue++;
						maj.run();
					}
				} else if (touche == 'd') {
					if (moove(new int[] { position[0], position[1] },
							new int[] { position[0] + 1, position[1] }, 2,
							equipe2.get(nbMouvementEffectue).getTypePerso())) {

						equipe2.get(nbMouvementEffectue).setPosition(
								new int[] { position[0] + 1, position[1] });
						nbMouvementEffectue++;
						maj.run();
					}
				} else if (touche == 'q') {
					if (moove(new int[] { position[0], position[1] },
							new int[] { position[0] - 1, position[1] }, 2,
							equipe2.get(nbMouvementEffectue).getTypePerso())) {

						equipe2.get(nbMouvementEffectue).setPosition(
								new int[] { position[0] - 1, position[1] });
						nbMouvementEffectue++;
						maj.run();
					}
				} else if (touche == 'a'
						&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 9 || equipe2
								.get(nbMouvementEffectue).getTypePerso() == 10)) {
					if (moove(new int[] { position[0], position[1] },
							new int[] { position[0] - 1, position[1] - 1 }, 2,
							equipe2.get(nbMouvementEffectue).getTypePerso())) {

						equipe2.get(nbMouvementEffectue).setPosition(
								new int[] { position[0] - 1, position[1] - 1 });
						nbMouvementEffectue++;
						maj.run();
					}
				} else if (touche == 'e'
						&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 9 || equipe2
								.get(nbMouvementEffectue).getTypePerso() == 10)) {
					if (moove(new int[] { position[0], position[1] },
							new int[] { position[0] + 1, position[1] - 1 }, 2,
							equipe2.get(nbMouvementEffectue).getTypePerso())) {

						equipe2.get(nbMouvementEffectue).setPosition(
								new int[] { position[0] + 1, position[1] - 1 });
						nbMouvementEffectue++;
						maj.run();
					}

				} else if (touche == 'x'
						&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 9 || equipe2
								.get(nbMouvementEffectue).getTypePerso() == 10)) {
					if (moove(new int[] { position[0], position[1] },
							new int[] { position[0] + 1, position[1] + 1 }, 2,
							equipe2.get(nbMouvementEffectue).getTypePerso())) {

						equipe2.get(nbMouvementEffectue).setPosition(
								new int[] { position[0] + 1, position[1] + 1 });
						nbMouvementEffectue++;
						maj.run();
					}
				} else if (touche == 'w'
						&& (equipe2.get(nbMouvementEffectue).getTypePerso() == 9 || equipe2
								.get(nbMouvementEffectue).getTypePerso() == 10)) {
					if (moove(new int[] { position[0], position[1] },
							new int[] { position[0] - 1, position[1] + 1 }, 2,
							equipe2.get(nbMouvementEffectue).getTypePerso())) {

						equipe2.get(nbMouvementEffectue).setPosition(
								new int[] { position[0] - 1, position[1] + 1 });
						nbMouvementEffectue++;
						maj.run();
					}
				} else if (touche == 'p'
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
					} else if (plateau[tmp.getPosition()[0] - 1][tmp
							.getPosition()[1]] == 5
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
					}
					equipe2.set(nbMouvementEffectue, tmp);
					nbMouvementEffectue++;
					maj.run();
					this.repaint();
				}

			}
		}

	}
}
