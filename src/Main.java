import model.Direction;
import model.Partie;

public class Main {

    public static void main(String[] args) {

    Partie jeu = new Partie();
    jeu.lancerPartie();
    System.out.println(jeu);
    jeu.placementTuile(Direction.EST, 1);
    jeu.deplacementJoueur(Direction.SUD);
    System.out.println(jeu);
    }
}
