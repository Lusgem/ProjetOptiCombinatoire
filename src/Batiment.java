import java.util.Comparator;

public class Batiment {
    private int hauteur;
    private int largeur;
    private int numeroBat;
    private Coordonnees coinHautGauche;
    private Coordonnees coinBasDroite;

    public Batiment(int hauteur, int largeur, int numeroBat) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.numeroBat = numeroBat;
    }

    public Batiment (Batiment bat){
        this.hauteur = bat.hauteur;
        this.largeur = bat.largeur;
        this.numeroBat = bat.numeroBat;
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }*/

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int longueur) {
        this.hauteur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public Coordonnees getCoinHautGauche() {
        return coinHautGauche;
    }

    public void setCoinHautGauche(Coordonnees coinHautGauche) {
        this.coinHautGauche = coinHautGauche;
        setCoinBasDroite();
    }

    public Coordonnees getCoinBasDroite() {
        return coinBasDroite;
    }

    public void setCoinBasDroite() {
        this.coinBasDroite = new Coordonnees(coinHautGauche.getX()+hauteur-1,coinHautGauche.getY()+largeur-1);
    }

    @Override
    public String toString() {
        return "Batiment n"+numeroBat+" {" +
                "hauteur=" + hauteur +
                ", largeur=" + largeur +
                ", coinHautGauche=" + coinHautGauche +
                ", coinBasDroite=" + coinBasDroite +
                '}';
    }

    public int getNumeroBat() {
        return numeroBat;
    }

    public void setNumeroBat(int numeroBat) {
        this.numeroBat = numeroBat;
    }

    public static Comparator<Batiment> comparatorAire = new Comparator<Batiment>() {
        @Override
        public int compare(Batiment o1, Batiment o2) {
            int a1 = o1.hauteur*o1.largeur;
            int a2 = o2.hauteur*o2.largeur;
            return a2-a1;
        }
    };

    public static Comparator<Batiment> comparatorEncombrement = new Comparator<Batiment>() {
        @Override
        public int compare(Batiment o1, Batiment o2) {
            int a1 = o1.hauteur+o1.largeur;
            int a2 = o2.hauteur+o2.largeur;
            return a2-a1;
        }
    };
}
