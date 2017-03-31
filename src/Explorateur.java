
public class Explorateur extends Personnage {
    
	
	/**
	 * Permet d'instancier un explorateur.
	 * 
	 * @param equipe
	 * @param typePerso
	 * @param position
	 */
	
	Explorateur(int equipe, int[] position) {
		super(equipe, 8, position);
		this.touches=new char[]{'z','q','s','d'};
	}
    
     /**
	 * Permet de soulever un rocher et récupérer ce qu'il y a en dessous.
	 * 
	 * @param p
	 * @return
	 */	
	public int soulever(Parcelle p){
		int res = 0;
		this.retirerEnergie(5);
		if (p.cle){
			this.setClef(true);
			p.cle = false;
			Main.g.PlateauJeu.nrj.info.setText("CLEF");
			res = 1;
		}else if (p.coffre){
			Main.g.PlateauJeu.nrj.info.setText("COFFRE");
			if(this.getClef()){
				this.setCoffre(true);
				p.coffre = false;	
				res = 2;
			}else{
				res = 3;	
			}
		}
		return res;
	}

    	
	/**
	 * Permet de vérifier si un déplacement est valide ou non.
	 * 
	 * @param e
	 * @return
	 */
	public boolean deplacementValide(char e){
		if (e == 'z' || e == 'q' || e == 's' || e == 'd' && this.getSeDeplacer()){
			return true;
		}else{
			return false;
		}
	}
	
}