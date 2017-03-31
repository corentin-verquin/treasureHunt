import java.util.Random;

public class Voleur extends Personnage {

	/**
	 * Permet d'instancier un voleur.
	 * 
	 * @param equipe
	 * @param typePerso
	 * @param position
	 */
	public Voleur(int equipe, int[] position) {
		super(equipe, 9, position);
		this.touches = new char[] { 'z', 'q', 's', 'd', 'a', 'e', 'w', 'x' };
	}

	/**
	 * Permet de voler un personnage et de récupérer ce qu'il possède. Un vol
	 * n'a qu'une chance sur deux de réussir.
	 * 
	 * @param autrePerso
	 * @return
	 */
	public Personnage voler(Personnage autrePerso, int[] pos) {
		Personnage res;
		Random rng = new Random();
		this.retirerEnergie(10);
		if (this.getEquipe() == 1) {
			if (autrePerso.getTypePerso() == 8) {
				res = new Explorateur(2, pos);
			} else if (autrePerso.getTypePerso() == 9) {
				res = new Voleur(2, pos);
			} else if (autrePerso.getTypePerso() == 10) {
				res = new Guerrier(2, pos);
			} else {
				res = new Piegeur(2, pos);
			}
		} else {
			if (autrePerso.getTypePerso() == 8) {
				res = new Explorateur(1, pos);
			} else if (autrePerso.getTypePerso() == 9) {
				res = new Voleur(1, pos);
			} else if (autrePerso.getTypePerso() == 10) {
				res = new Guerrier(2, pos);
			} else {
				res = new Piegeur(2, pos);
			}
		}
		res.setenergie(autrePerso.getEnergie());
		if (autrePerso.getClef()) {
			res.setClef(true);
		}
		if (autrePerso.getCoffre()) {
			res.setCoffre(true);
		}
		if (autrePerso.getArme()) {
			res.setArme(true);
		}else{
			res.setArme(false);	
		}
		if (autrePerso != null
				&& autrePerso.getEquipe() != this.getEquipe()
				&& (autrePerso.getTypePerso() == 8
						|| autrePerso.getTypePerso() == 9 || autrePerso
						.getTypePerso() == 10)) {
			System.out.print("");

			if (rng.nextInt(2) == 1) {
				if (!autrePerso.getClef() && !autrePerso.getCoffre()
						&& !autrePerso.getArme()) {
					Main.g.PlateauJeu.nrj.info.setText("Il n'y a rien a voler");
				}
				if (autrePerso.getClef()) {
					res.setClef(false);
					this.setClef(true);
					Main.g.PlateauJeu.nrj.info
							.setText("Le voleur vole\nla clef");
				}
				if (autrePerso.getCoffre()) {
					System.out.print("");
					res.setCoffre(false);
					this.setCoffre(true);
					Main.g.PlateauJeu.nrj.info
							.setText("Le voleur vole\nle coffre");
				}
				if (autrePerso.getArme()) {
					res.setArme(false);
					Main.g.PlateauJeu.nrj.info
							.setText("Le voleur vole\nl'arme");
				}
			} else {
				Main.g.PlateauJeu.nrj.info.setText("Le vole a echoue");
			}
		}

		return res;
	}

}