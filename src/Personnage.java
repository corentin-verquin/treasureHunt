
public class Personnage {

	private int energie = 100;
	private int equipe;
	private boolean estVivant = true;
	private int typePerso;
	private boolean clef = false;
	private boolean coffre = false;
	private int[] position = new int[2];
	private char touchePrev;
	private boolean peuxSeDeplacer;
	protected char[]touches;
	private boolean arme = false;

	/**
	 * Permet d'instancier un personnage.
	 * 
	 * @param equipe
	 * @param typePerso
	 * @param position
	 */
	public Personnage(int equipe, int typePerso, int[] position) {
		this.equipe = equipe;
		this.typePerso = typePerso;
		this.position= position;
		this.peuxSeDeplacer = true;
		if (this.typePerso == 10){
			this.arme = true;
		}
	}

	/**
	 * Permet de retirer e energie à un personnage.
	 * 
	 * @param e
	 */
	public void retirerEnergie(int e) {
		this.energie -= e;
		if (this.energie <= 0){ 
			this.estVivant = false;
			if(Main.g.PlateauJeu.equipe1Joue){
				Personnage tmp = Main.g.PlateauJeu.equipe1.get(Main.g.PlateauJeu.nbMouvementEffectue);
				if(tmp.getClef()){
					Main.g.ile.getParcelle(tmp.getPosition()[0], tmp.getPosition()[1]).cle=true;
				}
				if(tmp.getCoffre()){
					Main.g.ile.getParcelle(tmp.getPosition()[0], tmp.getPosition()[1]).coffre=true;
				}
				Main.g.PlateauJeu.plateau[tmp.getPosition()[0]][tmp.getPosition()[1]]=12;
				Main.g.PlateauJeu.repaint();
			}else{
				Personnage tmp = Main.g.PlateauJeu.equipe2.get(Main.g.PlateauJeu.nbMouvementEffectue);
				if(tmp.getClef()){
					Main.g.ile.getParcelle(tmp.getPosition()[0], tmp.getPosition()[1]).cle=true;
				}
				if(tmp.getCoffre()){
					Main.g.ile.getParcelle(tmp.getPosition()[0], tmp.getPosition()[1]).coffre=true;
				}
				Main.g.PlateauJeu.plateau[tmp.getPosition()[0]][tmp.getPosition()[1]]=12;
				Main.g.PlateauJeu.repaint();
			}
		}
	}
	
	/**
	 * Permet de retirer e energie au personnage en ième  position.
	 * 
	 * @param e
	 * @param i
	 */
	public void retirerEnergie(int e,int i) {
		this.energie -= e;
		if (this.energie <= 0){ 
			this.estVivant = false;
			if(Main.g.PlateauJeu.equipe1Joue){
				Personnage tmp = Main.g.PlateauJeu.equipe2.get(i);
				if(tmp.getClef()){
					Main.g.ile.getParcelle(tmp.getPosition()[0], tmp.getPosition()[1]).cle=true;
				}
				if(tmp.getCoffre()){
					Main.g.ile.getParcelle(tmp.getPosition()[0], tmp.getPosition()[1]).coffre=true;
				}
				Main.g.PlateauJeu.plateau[tmp.getPosition()[0]][tmp.getPosition()[1]]=12;
				Main.g.PlateauJeu.repaint();
			}else{
				Personnage tmp = Main.g.PlateauJeu.equipe1.get(i);
				if(tmp.getClef()){
					Main.g.ile.getParcelle(tmp.getPosition()[0], tmp.getPosition()[1]).cle=true;
				}
				if(tmp.getCoffre()){
					Main.g.ile.getParcelle(tmp.getPosition()[0], tmp.getPosition()[1]).coffre=true;
				}
				Main.g.PlateauJeu.plateau[tmp.getPosition()[0]][tmp.getPosition()[1]]=12;
				Main.g.PlateauJeu.repaint();
			}
		}
	}

	/**
	 * Permet d'ajouter e energie à un personnage.
	 * 
	 * @param e
	 */
	public void ajouterEnergie(int e) {
		this.energie += e;
		if (this.energie > 100){
			this.energie = 100;
		}
	}

	/**
	 * Permet d'obtenir l'energie courante du personnage.
	 * 
	 * @return
	 */
	public int getEnergie() {
		return this.energie;
	}

	/**
	 * Permet d'obtenir l'équipe du personnage.
	 * 
	 * @return
	 */
	public int getEquipe() {
		return this.equipe;
	}

	/**
	 * Permet de savoir si un personnage est vivant.
	 * 
	 * @return
	 */
	public boolean getEstVivant() {
		return this.estVivant;
	}

	/**
	 * Permet de savoir le type d'un personnage.
	 * 
	 * @return
	 */
	public int getTypePerso() {
		return this.typePerso;
	}

	/**
	 * Permet de changer l'etat d'un coffre.
	 * 
	 * @param b
	 */
	public void setCoffre(boolean b) {
		this.coffre = b;
	}

	/**
	 * Permet de savoir si la clef est présente ou non.
	 * 
	 * @return
	 */
	public boolean getClef() {
		return this.clef;
	}

	/**
	 * Permet de savoir si le coffre est présent ou non.
	 * 
	 * @return
	 */
	public boolean getCoffre() {
		return this.coffre;
	}

	/**
	 * Permet de changer l'état d'une clef.
	 * 
	 * @param b
	 */
	public void setClef(boolean b) {
		this.clef = b;
	}

	/**
	 * Permet de connaitre la position d'un personnage.
	 * 
	 * @return
	 */
	public int[] getPosition(){
		return this.position;
	}
	
	/**
	 * Permet de changer la position d'un personnage.
	 * 
	 * @param pos
	 */
	public void setPosition(int[] pos){
		this.position=pos;
	}
	
	/**
	 * Permet d'autoriser ou non le déplacement à un personnage.
	 * 
	 * @param b
	 */
	public void setSeDeplacer(boolean b){
		this.peuxSeDeplacer=b;
	}
	
	/**
	 * Permet de savoir si un personnage peut se déplacer ou non.
	 * 
	 * @return
	 */
	public boolean getSeDeplacer(){
		return this.peuxSeDeplacer;
	}
	
	/**
	 * Permet de Set l'energie
	 * @param i
	 */
	public void setenergie(int i){
		this.energie=i;
	}
	
	/**
	 * Permet de recuperer la touche precedente
	 * @return char 
	 */
	public char getTouchePrev(){
		return this.touchePrev;
	}
	
	/**
	 * Permet de changer la touche precedente
	 * @param char touche
	 */
	public void setTouchePrev(char touche){
		this.touchePrev=touche;
	}
	
	/**
	 * Methode abstraite qui retourne une touche en fonction de l'indice en parametre
	 * @param int indice
	 * @return char touche
	 */
	public char getToucheIndice(int indice){
		return this.touches[indice];
	}
	
	/**
	 * Methode abstraite qui retourne le nombre de touches
	 * @return int
	 */
	public int getNbrTouches(){
		return this.touches.length;
	}
	
	/**
	 * Permet de savoir si le personnage possede ou non une arme.
	 * 
	 * @return
	 */
	public boolean getArme(){
		return this.arme;
	}
	
	/**
	 * Permet de donner une arme a un personnage.
	 * 
	 * @param b
	 */
	public void setArme(boolean b){
		this.arme = b;
	}
	
}