/**
 * class qui permet de creer et gerer les parcelles
 */
public class Parcelle {
	private int nombre;
	public boolean cle;
	int piege=0;
	public boolean coffre;
	int joueur1=0;
	int joueur2=0;
	
	int visibilite=0;

	/**
	 * Constructeur qui permet d'affecter le type
	 * à une parcelle
	 * @param int type
	 */
	public Parcelle(int type) {
		
		// Eau
		if (type == 0) {
			this.nombre = 1;
			cle = false;
			coffre = false;
			// Navire1
		} else if (type == 1) {
			this.nombre = 2;
			cle = false;
			coffre = false;
			// Navire2
		} else if (type == 2) {
			this.nombre = 3;
			cle = false;
			coffre = false;
			// Rocher
		} else if (type == 3) {
			this.nombre = 4;
			cle = false;
			coffre = false;
		} else {
			this.nombre = 5;
			cle=false;
			coffre=false;
		}
	}
	/**
	 * Renvoie le type de la parcelle
	 * @return int
	 */
	public int getNombre() {
		return this.nombre;
	}

	
	/**
	 * modifie le type de la parcelle
	 * @param int nombre
	 */
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Permet de modifier un personnage en fonction de son equipe.
	 * 
	 * @param i
	 * @param j
	 */
	public void setPersonage(int i, int j){
		if(i==1){
			this.joueur1=j;
		}else{
			this.joueur2=j;
		}
	}
	
	/**
	 * Permet d'obtenir un personnage en fonction de son equipe.
	 * 
	 * @param i
	 * @return
	 */
	public  int getPersonage(int i){
		if(i==1){
			return this.joueur1;
		}else{
			return this.joueur2;
		}
	}
}
