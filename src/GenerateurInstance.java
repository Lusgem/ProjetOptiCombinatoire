import java.util.Scanner;

public class GenerateurInstance {

    /**
     *
     * @return la nouvelle instance aléatoire en fonction d'une valeur saisie au clavier, cette valeur définit la hauteur, la largeur et le nombre de batiments générés
     */
    public static Plateau generer() {
        Scanner in = new Scanner(System.in);
        int valeur;
        Plateau plateau;
        System.out.println("Entrez la hauteur de la map \n" +
                "La largeur de la map sera égale à cette hauteur\n" +
                "Le nombre de batiment sera aussi égal à cette valeur :");
        valeur = in.nextInt();
        plateau = new Plateau(valeur,valeur);
        plateau.setNbBatiments(valeur);
        int min = 1, max = (int)(2 * Math.sqrt(plateau.getHauteur()));
        for(int i = 1; i <= plateau.getNbBatiments(); i++) {
            plateau.getBatiments().add(new Batiment((int)(min + (Math.random() * (max - min) + 1)),(int)(min + (Math.random() * (max - min) + 1)),i));
        }
        return plateau;
    }

}
