import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Probleme {

    Plateau plateauVide;


    public Probleme() {
        plateauVide = null;
        int choix;
        Plateau resultat = null;
        Scanner in = new Scanner(System.in);
        do {
        System.out.println("Algo Glouton :\n" +
                "1 - Générer une instance aléatoire\n" +
                "2 - Générer l'instance de test (instance du TP)");
        choix = in.nextInt();
            switch (choix) {
                case 1:
                    plateauVide = GenerateurInstance.generer();
                    break;
                case 2:
                    plateauVide = creerInstanceDeTest();
                    break;
                    default:
                        System.out.println("Merci d'entrer un choix valide");
            }
        }while(plateauVide==null);
        Glouton algo = new Glouton();
        while(true) {
            System.out.println("Que voulez vous faire ?\n" +
                    "1 - Tester l'heuristique (1000 tri aléatoires)\n" +
                    "2 - Tester différentes sortes de tri\n" +
                    "3 - Sortir");
            choix = in.nextInt();
            switch (choix) {
                case 1:
                    resultat = algo.heuristique(plateauVide);
                    break;
                case 2:
                    resultat = algo.algoGlouton(plateauVide, plateauVide.getBatiments(), false);
                    break;
                case 3:
                    break;
                    default:
                        break;
            }
            if(choix==3){
                break;
            }
            if(resultat!=null) {
                afficherPlateau(resultat);
            }
        }
    }


    /**
     * Permet d'afficher le plateau, l'emplacment des batiments est marqué de leur numéro, si il n'y a pas de batiment, un simple - est écrit
     * @param plateau
     */
    public void afficherPlateau(Plateau plateau) {
        Batiment tmp = null;
        int nb = plateauVide.getNbBatiments()-plateau.getNbBatiments();

        for(int i = 1; i <= plateau.getHauteur(); i++) {
            for(int j = 1; j <= plateau.getLargeur(); j++) {
                boolean batiment = false;
                for(Batiment bat : plateau.getBatiments()) {
                    int coinHautGaucheX = bat.getCoinHautGauche().getX();
                    int coinHautGaucheY = bat.getCoinHautGauche().getY();
                    int coinBasDroiteX = bat.getCoinBasDroite().getX();
                    int coinBasDroiteY = bat.getCoinBasDroite().getY();

                    if(coinHautGaucheX <= i && i <= coinBasDroiteX && coinHautGaucheY <= j && j <= coinBasDroiteY) {
                        batiment = true;
                        tmp = bat;
                    }
                }
                // %3s pour que ça soit toujours lisible pour les numeros de batiments jusqu'à 99
                if(batiment) {
                    System.out.printf("%3s",tmp.getNumeroBat());
                }else {
                    System.out.printf("%3s","-");
                }
            }
            System.out.println();
        }
        System.out.println(nb+" batiments n'ont pas pu être placés !");
    }

    /**
     * Retourne l'instance de test présente dans le sujet de TP et dans problème.txt
     * @return le plateau vide décrit dans probleme.txt
     */
    public Plateau creerInstanceDeTest(){
        Plateau plateau=null;
        try {
            InputStream flux = new FileInputStream("src/probleme.txt");
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            ligne = buff.readLine();
            String[] mots = ligne.split(" ");
            int hauteur = Integer.parseInt(mots[0]);
            int largeur = Integer.parseInt(mots[1]);
            plateau = new Plateau(hauteur,largeur);
            ligne = buff.readLine();
            int nb_batiments = Integer.parseInt(ligne);
            plateau.setNbBatiments(nb_batiments);
            int numerobat = 1;
            while ((ligne = buff.readLine()) != null) {
                String[] tailles = ligne.split(" ");
                plateau.getBatiments().add(new Batiment(Integer.parseInt(tailles[0]), Integer.parseInt(tailles[1]),numerobat));
                numerobat++;
            }
            buff.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return plateau;
    }

}
