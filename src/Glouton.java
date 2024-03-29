import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Glouton {


	/**
	 * Fonction permettant de tester si le batiment rentrerait dans la limite du plateau
	 * @param i
	 * @param j
	 * @param b
	 * @param plateau
	 * @return
	 */
	private boolean testLimites(int i, int j, Batiment b, Plateau plateau) {
		if((i + b.getHauteur() - 1 <= plateau.getHauteur()) && (j + b.getLargeur() - 1 <= plateau.getLargeur())) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Fonction permettant de tester si le batiment se superpose à un autre batiment présent sur le plateau
	 * @param i
	 * @param j
	 * @param b
	 * @param plateau
	 * @return
	 */
	private boolean testSuperposition(int i, int j, Batiment b, Plateau plateau) {
		if(plateau.getBatiments().isEmpty()) {
			return true;
		}else {
			for(Batiment bat : plateau.getBatiments()) {
				int coinHautGaucheX = bat.getCoinHautGauche().getX();
				int coinHautGaucheY = bat.getCoinHautGauche().getY();
				int coinBasDroiteX = bat.getCoinBasDroite().getX();
				int coinBasDroiteY = bat.getCoinBasDroite().getY();
				if(coinHautGaucheX <= i && i <= coinBasDroiteX && coinHautGaucheY <= j && j <= coinBasDroiteY) {
					return false;
				}
				int bCoinHautGaucheX = i;
				int bCoinHautDroiteY = j + b.getLargeur() - 1;
				if(coinHautGaucheY <= bCoinHautDroiteY && bCoinHautDroiteY <= coinBasDroiteY && coinHautGaucheX <= bCoinHautGaucheX && bCoinHautGaucheX <= coinBasDroiteX) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 *
	 * @param instance le plateau vide
	 * @param batiments la liste des batiments triée ou non
	 * @param dejaTrie permet d'empecher la proposition de tri si l'algo a été lancé par l'heuristique
	 * @return
	 */
	public Plateau algoGlouton(Plateau instance, ArrayList<Batiment> batiments, boolean dejaTrie) {
		ArrayList<Batiment> tmpBatiments = new ArrayList<>();
		Plateau resultat = new Plateau(instance.getHauteur(), instance.getLargeur());
		Batiment tmpBatiment = null;
		int nbBatAjoutes = 0;
		if(!dejaTrie) {
			batiments = tri(instance.getBatiments());
		}
		for(Batiment b : batiments) {
			tmpBatiments.add(new Batiment(b));
		}
		for(int i = 1; i <= instance.getHauteur(); i++) {
			for(int j = 1; j <= instance.getLargeur(); j++) {
				for(Batiment b : tmpBatiments) {
					if(testLimites(i, j, b, resultat) && testSuperposition(i, j, b, resultat)) {
						tmpBatiment = b;
						b.setCoinHautGauche(new Coordonnees(i,j));
						resultat.getBatiments().add(b);
						nbBatAjoutes++;
						break;
					}
				}
				tmpBatiments.remove(tmpBatiment);
			}
		}
		resultat.setNbBatiments(nbBatAjoutes);
		return resultat;
	}

	/**
	 *
	 * @param instance
	 * @return le plateau correspondant à la solution optimale trouvée par l'heuristique
	 */
	public Plateau heuristique(Plateau instance) {
		Plateau plateauTmp ;
		Plateau plateauResultat = null;
		int aireMax = 0;
		for(int i = 0; i < 1000; i++) {
			int aireTmp = 0;
			plateauTmp = algoGlouton(instance, triAleatoire(instance.getBatiments()),true);
			for(Batiment b : plateauTmp.getBatiments()) {
				aireTmp += b.getHauteur() * b.getLargeur();
			}
			if(aireTmp > aireMax) {
				aireMax = aireTmp;
				plateauResultat = plateauTmp;
			}
		}
		return plateauResultat;
	}

	/**
	 * Menu permettant de chosir le tri qu'on souhaite effectuer
	 * @param batiments
	 * @return la liste triée en fonction du choix réalisé
	 */
	public ArrayList<Batiment> tri(ArrayList<Batiment> batiments){
		while(true) {
			System.out.println("Quel tri souhaitez vous effectuer ? :\n" +
					"1 - Tri par aire\n" +
					"2 - Tri par encombrement\n" +
					"3 - Tri aléatoire\n" +
					"4 - Pas de tri\n");
			Scanner in = new Scanner(System.in);
			int choix = in.nextInt();
			switch (choix) {
				case 1:
					return triAire(batiments);
				case 2:
					return triEncombrement(batiments);
				case 3:
					return triAleatoire(batiments);
				case 4:
					return batiments;
				default:
					System.out.println("Ce choix n'est pas valide !!!");
					break;

			}
		}


	}

	/**
	 *
	 * @param batiments
	 * @return la liste triée par aire
	 */
	public ArrayList<Batiment> triAire(ArrayList<Batiment> batiments){
		ArrayList<Batiment> resultat = new ArrayList();
		for(Batiment b : batiments){
			resultat.add(new Batiment(b));
		}
		Collections.sort(resultat,Batiment.comparatorAire);
		return resultat;
	}

	/**
	 *
	 * @param batiments
	 * @return la liste triée par encombrement
	 */
	public ArrayList<Batiment> triEncombrement(ArrayList<Batiment> batiments){
		ArrayList<Batiment> resultat = new ArrayList();
		for(Batiment b : batiments){
			resultat.add(new Batiment(b));
		}
		Collections.sort(resultat,Batiment.comparatorEncombrement);
		return resultat;
	}

	/**
	 *
	 * @param batiments
	 * @return la liste des batiments triée aléatoirement
	 */
	public ArrayList<Batiment> triAleatoire(ArrayList<Batiment> batiments){
		ArrayList<Batiment> resultat = new ArrayList<Batiment>();
		ArrayList<Batiment> tmp = new ArrayList();
		Random random = new Random();
		Batiment bat;
		for(Batiment b : batiments) {
			tmp.add(new Batiment(b));
		}
		for(int i = 0; i < batiments.size(); i++) {
			bat = tmp.get(random.nextInt(tmp.size()));
			tmp.remove(bat);
			resultat.add(bat);
		}
		return resultat;
	}
}
