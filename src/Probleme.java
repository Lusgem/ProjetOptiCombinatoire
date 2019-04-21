import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Probleme {

    public Probleme() {
        Plateau plateauVide = null;
        Plateau resultat = null;
        Scanner in = new Scanner(System.in);
        System.out.println("Algo Glouton :\n" +
                "1 - Générer une instance aléatoire\n" +
                "2 - Générer l'instance de test (instance du TP)");
        int choix = in.nextInt();
        switch (choix){
            case 1:
                plateauVide = GenerateurInstances.generer();
                break;
            case 2:
                plateauVide = creerInstanceDeTest();
                break;
        }


        Glouton algo = new Glouton();

        System.out.println("Que voulez vous faire ?\n" +
                "1 - Tester l'heuristique (1000 tri aléatoires)\n" +
                "2 - Tester différentes sortes de tri");

        choix = in.nextInt();

        switch (choix){
            case 1:
                resultat = algo.heuristique(plateauVide);
                break;
            case 2:
                resultat = algo.algoGlouton(plateauVide, plateauVide.getBatiments(),false);
                break;
        }
        afficherPlateau(resultat);

    }


    public void afficherPlateau(Plateau plateau) {
        Batiment tmp = null;

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

                if(batiment) {
                    System.out.print(tmp.getNumeroBat()+" ");
                }else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

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
