import java.util.ArrayList;

public class Plateau {



    private int hauteur, largeur, nbBatiments;
    private ArrayList<Batiment> batiments;

    public Plateau(int largeur,int hauteur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.batiments = new ArrayList<>();
    }

    public ArrayList<Batiment> getBatiments() {
        return batiments;
    }

    public void setBatiments(ArrayList<Batiment> batiments) {
        this.batiments = batiments;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getNbBatiments() {
        return nbBatiments;
    }

    public void setNbBatiments(int nbBatiments) {
        this.nbBatiments = nbBatiments;
    }
}
