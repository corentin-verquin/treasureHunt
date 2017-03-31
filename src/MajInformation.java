import java.util.ArrayList;

public class MajInformation {
	public void run() {
		int index;
		ArrayList<Personnage> equipe1 = new ArrayList<>();
		ArrayList<Personnage> equipe2 = new ArrayList<>();

		equipe1 = Main.g.PlateauJeu.equipe1;
		equipe2 = Main.g.PlateauJeu.equipe2;
		index = Main.g.PlateauJeu.nbMouvementEffectue;
		
		if(Main.g.PlateauJeu.equipe1Joue){
			while(index < equipe1.size() && ! equipe1.get(index).getEstVivant()){
				index++;
			}
		}else{
			while(index < equipe2.size() && ! equipe2.get(index).getEstVivant()){
				index++;
			}
		}
		
		if((equipe1.size()==index && Main.g.PlateauJeu.equipe1Joue )|| 
				(equipe2.size()==index && !Main.g.PlateauJeu.equipe1Joue )){
			index--;
		}
		
		if (Main.g.PlateauJeu.equipe1Joue && index < equipe1.size()) {
			Main.g.PlateauJeu.nrj.textNom.setText(setNom(equipe1.get(index)
					.getTypePerso()));
			Main.g.PlateauJeu.nrj.textPosition.setText("X="
					+ (equipe1.get(index).getPosition()[0]+1) + "  Y="
					+ (equipe1.get(index).getPosition()[1]+1));
			Main.g.PlateauJeu.nrj.textEnergie.setText("Energie : "+equipe1.get(index).getEnergie()+"/100");
			Main.g.PlateauJeu.nrj.barEnergie.setValue(equipe1.get(index).getEnergie());
			if(equipe1.get(index).getClef()){
				Main.g.PlateauJeu.nrj.cl.setVisible(true);
			}else{
				Main.g.PlateauJeu.nrj.cl.setVisible(false);
			}
			if(equipe1.get(index).getCoffre()){
				Main.g.PlateauJeu.nrj.cf.setVisible(true);
			}else{
				Main.g.PlateauJeu.nrj.cf.setVisible(false);
			}
			if(equipe1.get(index).getArme()){
				Main.g.PlateauJeu.nrj.sa.setVisible(true);
			}else{
				Main.g.PlateauJeu.nrj.sa.setVisible(false);
			}
		} else if(index< equipe2.size()){
			Main.g.PlateauJeu.nrj.textNom.setText(setNom(equipe2.get(index)
					.getTypePerso()));
			Main.g.PlateauJeu.nrj.textPosition.setText("X="
					+ (equipe2.get(index).getPosition()[0]+1) + "  Y="
					+ (equipe2.get(index).getPosition()[1]+1));
			Main.g.PlateauJeu.nrj.textEnergie.setText("Energie : "+equipe2.get(index).getEnergie()+"/100");
			Main.g.PlateauJeu.nrj.barEnergie.setValue(equipe2.get(index).getEnergie());
			if(equipe2.get(index).getClef()){
				Main.g.PlateauJeu.nrj.cl.setVisible(true);
			}else{
				Main.g.PlateauJeu.nrj.cl.setVisible(false);
			}
			if(equipe2.get(index).getCoffre()){
				Main.g.PlateauJeu.nrj.cf.setVisible(true);
			}else{
				Main.g.PlateauJeu.nrj.cf	.setVisible(false);
			}
			if(equipe2.get(index).getArme()){
				Main.g.PlateauJeu.nrj.sa.setVisible(true);
			}else{
				Main.g.PlateauJeu.nrj.sa.setVisible(false);
			}
		}
	}

	private String setNom(int i) {
		if (i == 8) {
			return "Explorateur";
		} else if (i == 9) {
			return "Voleur";
		} else if (i == 10) {
			return "Soldat";
		} else {
			return "Piegeur";
		}
	}
}
