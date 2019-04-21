import java.util.Scanner;

public class GenerateurInstance {

    /**
     *
     * @return la nouvelle instance aléatoire
     */
    public static Plateau generer() {
        Scanner in = new Scanner(System.in);
        int hauteur,largeur, nbBat;
        Plateau plateau;
        System.out.println("Entrez la hauteur de la map : ");
        hauteur = in.nextInt();
        System.out.println("Entrez la largeur : ");
        largeur = in.nextInt();
        plateau = new Plateau(hauteur,largeur);
        System.out.println("Combien de batiments voulez vous générer : ");
        nbBat = in.nextInt();
        plateau.setNbBatiments(nbBat);
        int min = 1, max = (int)(2 * Math.sqrt(plateau.getHauteur()));
        for(int i = 1; i <= plateau.getNbBatiments(); i++) {
            plateau.getBatiments().add(new Batiment((int)(min + (Math.random() * (max - min) + 1)),(int)(min + (Math.random() * (max - min) + 1)),i));
        }
    return plateau;
    }

}
