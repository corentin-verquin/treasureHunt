
public class Piegeur extends Personnage{

	
	
	/**
	 * Permet d'instancier un piégeur.
	 * 
	 * @param equipe
	 * @param position
	 */
	public Piegeur(int equipe, int[] position) {
		super(equipe, 11, position);
		this.touches=new char[]{'z','q','s','d','p'};
	}

	/**
	 * Permet de piéger une parcelle.
	 * 
	 * @param p
	 * @return
	 */
	public void pieger(int i,Parcelle p){
		this.retirerEnergie(10);
		if(i==1){
			p.piege++;
		}else if(i==2){
			p.piege+=2;
		}
	}
	
	/**
	 * Permet d'annuler l'action pieger si la case ciblee est non valide.
	 * 
	 * @param p
	 */
	public void antiPiege(Parcelle p){
		this.ajouterEnergie(10);
		p.piege=0;
		Main.g.PlateauJeu.nrj.info.setText("Zone impossible\na pieger");
	}

}