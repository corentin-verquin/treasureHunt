
public class Guerrier extends Personnage {

	/**
	 * Permet d'instancier un guerrier.
	 * 
	 * @param equipe
	 * @param position
	 */
	public Guerrier(int equipe, int[] position) {
		super(equipe, 10, position);
		this.touches = new char[] { 'z', 'q', 's', 'd' , 'a', 'e', 'w', 'x'};
	}

	/**
	 * Permet d'agresser un personnage dans le but de lui retirer de la vie ou
	 * de le tuer.
	 * 
	 * @param autrePerso
	 * @return
	 */
	
	public Personnage agresser(Personnage autrePerso, int[] pos,int i) {
		Personnage res;
		this.retirerEnergie(10);
		if (this.getEquipe() == 1) {
			if (autrePerso.getTypePerso() == 8) {
				res = new Explorateur(2, pos);
			} else if (autrePerso.getTypePerso() == 9) {
				res = new Voleur(2, pos);
			} else if (autrePerso.getTypePerso() == 10){
				res = new Guerrier(2, pos);
			} else {
				res = new Piegeur(2, pos);
			}
		} else {
			if (autrePerso.getTypePerso() == 8) {
				res = new Explorateur(1, pos);
			} else if (autrePerso.getTypePerso() == 9) {
				res = new Voleur(1, pos);
			} else if (autrePerso.getTypePerso() == 10){
				res = new Guerrier(2, pos);
			} else {
				res = new Piegeur(2, pos);
			}
		}
		res.setenergie(autrePerso.getEnergie());
		if(autrePerso.getClef()){
			res.setClef(true);
		}
		if(autrePerso.getCoffre()){
			res.setCoffre(true);
		}
		if(autrePerso.getArme()){
			res.setArme(true);
		}else{
			res.setArme(false);
		}
		res.retirerEnergie(25,i);
		return res;
	}

	/**
	 * Permet de vérifier si un déplacement est valide ou non.
	 * 
	 * @param e
	 * @return
	 */
	public boolean deplacementValide(char e) {
		if (e == 'z' || e == 'q' || e == 's' || e == 'd' || e == 'a'
				|| e == 'e' || e == 'w' || e == 'x' && this.getSeDeplacer()) {
			return true;
		} else {
			return false;
		}
	}
}