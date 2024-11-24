package model.tuiles;

import model.Direction;
import model.Joueur;

public class TuileDepart extends Tuile {

    private Joueur m_joueur;

    public TuileDepart(Direction[] dirs, Joueur joueur) {

        super(dirs);
        m_joueur = joueur;
    }

    @Override
    public void action() {

    }

    @Override
    public String toString() {
        return "Tuile de " + m_joueur.getNom() + ", " + super.toString();
    }
}
