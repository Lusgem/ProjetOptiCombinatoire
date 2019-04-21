import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Que voulez vous faire ?\n" +
                    "1 - Créer une nouvelle instance\n" +
                    "2 - Quitter");

            Scanner in = new Scanner(System.in);
            int choix = in.nextInt();
            switch (choix) {
                case 1:
                    Probleme p = new Probleme();
                    break;
                case 2:
                    System.out.println("A bientot !");
                    System.exit(0);
                    break;

            }

        }

    }
}
