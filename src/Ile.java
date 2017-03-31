import java.util.Random;

/**
 * class qui permet de generer l'ile
 */
public class Ile {
	public Parcelle[][] tableau;
	private int[] Bateau1 = new int[2]; // spawn de la premiere equipe
	private int[] Bateau2 = new int[2]; // spawn de la seconde equipe

	/**
	 * constructeur par defaut
	 */
	public Ile() {
		this.tableau = new Parcelle[10][10];
		this.genereIle(0.1);
	}

	/**
	 * constructeur personalisé qui prend la taille de l'ile et la repartition
	 * de rocher
	 * 
	 * @param int taille
	 * @param double pourcent
	 */
	public Ile(int taille, double pourcent) {
		this.tableau = new Parcelle[taille][taille];
		this.genereIle(pourcent);
	}

	/**
	 * Permet de generé les coordonées des rochers que l'on veux creer ainsi que
	 * generé le nombre de rochers voulu
	 * 
	 * @param int nbRochers
	 * @return int[][]
	 */
	public int[][] genereObjets(int nbRochers) {
		int[][] rochers = new int[nbRochers + 4][2];// nombre de rocher voulu +
													// quatre stock pour cle et
													// coffre et les bateaux
		Random alea = new Random();
		// bateau
		rochers[nbRochers + 2][1] = alea.nextInt(this.tableau.length - 2) + 1;
		rochers[nbRochers + 2][0] = 1;
		Bateau1[0] = 1;
		Bateau1[1] = rochers[nbRochers + 2][1];

		rochers[nbRochers + 3][1] = alea.nextInt(this.tableau.length - 2) + 1;
		rochers[nbRochers + 3][0] = this.tableau.length - 2;
		Bateau2[0] = rochers[nbRochers + 3][0];
		Bateau2[1] = rochers[nbRochers + 3][1];
		
		do {
			for (int i = 0; i < nbRochers; i++) {// genere le nombre de rocher
													// voulu
				boolean dedans = true;
				while (dedans) {// verifie que le rocher n'est pas deja present
					dedans = false;
					rochers[i][0] = alea.nextInt(this.tableau.length - 4) + 1;
					rochers[i][1] = alea.nextInt(this.tableau.length - 4) + 1;
					for (int j = 0; j < i; j++) {
						if (rochers[j][0] == rochers[i][0]
								&& rochers[j][1] == rochers[i][1]) {
							dedans = true;
						}
					}
					if ((rochers[i][1] == rochers[nbRochers + 2][1] && rochers[i][0] == 1)
							|| (rochers[i][1] == rochers[nbRochers + 3][1] && rochers[i][0] == this.tableau.length - 2)) {
						dedans = true;
					}
				}
			}
		} while (!estValide(rochers));
		int ligne, ligne2;

		ligne = alea.nextInt(nbRochers);// prend un rocher pour y cacher la cle
		rochers[nbRochers][0] = rochers[ligne][0];
		rochers[nbRochers][1] = rochers[ligne][1];

		do {
			ligne2 = alea.nextInt(nbRochers);// prend un second rocher pour y
												// cacher le coffre
		} while (ligne == ligne2);

		rochers[nbRochers + 1][0] = rochers[ligne2][0];
		rochers[nbRochers + 1][1] = rochers[ligne2][1];

		return rochers;// renvoi les rochers et les objets
	}

	/**
	 * genere les zones vides, les rochers, les navires et la mer et gere le
	 * pourcentage de rochers
	 * 
	 * @param double pourcentage
	 */
	public void genereIle(double pourcentage) {
		int entier = (int) ((this.tableau.length - 2)
				* (this.tableau[0].length - 2) * pourcentage);
		int[][] rochers = this.genereObjets(entier);

		for (int i = 0; i < this.tableau.length; i++) {
			for (int j = 0; j < this.tableau[0].length; j++) {
				// eau
				if (i == 0 || j == 0 || i == this.tableau.length - 1
						|| j == this.tableau[0].length - 1) {
					this.tableau[i][j] = new Parcelle(0);
				} else {
					boolean estDedans = false;
					for (int[] tableau : rochers) {
						if (tableau[0] == i && tableau[1] == j) {
							estDedans = true;
							// rocher
							this.tableau[i][j] = new Parcelle(3);
							// cle
							if (i == rochers[entier][0]
									&& j == rochers[entier][1]) {
								this.tableau[i][j].cle = true;
							}
							// coffre
							else if (i == rochers[entier + 1][0]
									&& j == rochers[entier + 1][1]) {
								this.tableau[i][j].coffre = true;
							}
							// batteau 1
							else if (i == rochers[entier + 2][0]
									&& j == rochers[entier + 2][1]) {
								this.tableau[i][j] = new Parcelle(1);
							}

							// batteau 2
							else if (i == rochers[entier + 3][0]
									&& j == rochers[entier + 3][1]) {
								this.tableau[i][j] = new Parcelle(2);
								
							}

						}
					}
					// vide
					if (!estDedans) {
						this.tableau[i][j] = new Parcelle(-1);
					}
				}
			}
		}
		
		for(int x=Bateau1[0]-1;x<=Bateau1[0]+1;x++){
			for(int y=Bateau1[1]-1;y<=Bateau1[1]+1;y++){
				this.tableau[x][y].visibilite=1;
			}
		}
		for(int x=Bateau2[0]-1;x<=Bateau2[0]+1;x++){
			for(int y=Bateau2[1]-1;y<=Bateau2[1]+1;y++){
				this.tableau[x][y].visibilite=2;
			}
		}
	}

	/**
	 * renvoie le tableau sous forme de int pour faliciter l'affichage
	 * 
	 * @return int[][]
	 */
	public int[][] getTableau() {
		int[][] jeu = new int[this.tableau.length][this.tableau[0].length];
		for (int i = 0; i < this.tableau.length; i++) {
			for (int j = 0; j < this.tableau[0].length; j++) {
				jeu[i][j] = this.tableau[i][j].getNombre();

				// Pour tester la methode d'accessibilite des rochers
				/*if (this.tableau[i][j].cle)
					jeu[i][j] = 6;
				if (this.tableau[i][j].coffre)
					jeu[i][j] = 7;
			*/
			}
		}
		return jeu;
	}

	/**
	 * Renvoie si les rochers sont accessibles
	 * 
	 * @param int[][]
	 * @return boolean
	 */
	boolean estValide(int[][] tab) {
		for (int i = 0; i < tab.length; i++) {
			int compte = 0;
			if (tab[i][0] == 1 || tab[i][0] == tableau.length - 2)
				compte++;
			if (tab[i][1] == 1 || tab[i][1] == tableau.length - 2)
				compte++;

			for (int j = 0; j < tab.length; j++) {
				if (tab[i][0] == tab[j][0] && tab[i][1] == tab[j][1] + 1) {
					compte++;
				} else if (tab[i][0] == tab[j][0] && tab[i][1] == tab[j][1] - 1) {
					compte++;
				} else if (tab[i][0] == tab[j][0] + 1 && tab[i][1] == tab[j][1]) {
					compte++;
				} else if (tab[i][0] == tab[j][0] - 1 && tab[i][1] == tab[j][1]) {
					compte++;
				}
			}

			if (compte >= 4) {
				return false;
			}
		}

		int[][] verifPlateau = new int[this.tableau.length][this.tableau.length];
		for (int i = 0; i < verifPlateau.length; i++) {
			for (int j = 0; j < verifPlateau.length; j++) {
				if (i == 0 || i == this.tableau.length - 1 || j == 0
						|| j == this.tableau[0].length - 1) {
					verifPlateau[i][j] = 3;
				} else {
					boolean trouve = false;
					for (int[] tab2 : tab) {
						if (i == tab2[0] && j == tab2[1] && !trouve) {
							verifPlateau[i][j] = 2;
							trouve = true;
						}
					}
					if (!trouve) {
						verifPlateau[i][j] = 1;
					}
				}
			}
		}

		Random alea = new Random();
		int x = 0, y = 0;
		while (verifPlateau[x][y] != 1) {
			x = alea.nextInt(verifPlateau.length - 2) + 1;
			y = alea.nextInt(verifPlateau.length - 2) + 1;
		}

		int[][] parcour = new int[this.tableau.length][this.tableau[0].length];
		for (int i = 0; i < parcour.length; i++) {
			for (int j = 0; j < parcour[0].length; j++) {
				parcour[i][j] = 0;
			}
		}
		parcour[x][y] = 1;
		boolean deplacement = true;
		while (deplacement) {
			deplacement = false;
			for (int i = 1; i < parcour.length - 1; i++) {
				for (int j = 1; j < parcour[0].length - 1; j++) {
					if (parcour[i][j] == 1) {
						deplacement = true;
						parcour[i][j] = 9;
						if (parcour[i + 1][j] == 0) {
							parcour[i + 1][j] = verifPlateau[i + 1][j];
						}
						if (parcour[i - 1][j] == 0) {
							parcour[i - 1][j] = verifPlateau[i - 1][j];
						}
						if (parcour[i][j + 1] == 0) {
							parcour[i][j + 1] = verifPlateau[i][j + 1];
						}
						if (parcour[i][j - 1] == 0) {
							parcour[i][j - 1] = verifPlateau[i][j - 1];
						}

					}
				}
			}
		}

		int compteur = 0;
		for (int i = 0; i < parcour.length; i++) {
			for (int j = 0; j < parcour.length; j++) {
				// System.out.print(parcour[i][j]+"-");
				if (parcour[i][j] == 2) {
					compteur++;
				}
			}
			// System.out.println();
		}
		if (compteur != tab.length - 2) {
			return false;
		}

		return true;
	}
	

	/**
	 * Permet de recuperer les coordonées du spawn de l'equipe selectione
	 * @param int i
	 * @return int[]
	 */
	public int[] getBateau(int i){
		if(i==1){
			return this.Bateau1;
		}else{
			return this.Bateau2;
		}
	}
	
	
	/**
	 * Permet de récupérer la parcelle aux coordonnees i, j.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Parcelle getParcelle(int i, int j){
		return this.tableau[i][j];
	}
	
}
