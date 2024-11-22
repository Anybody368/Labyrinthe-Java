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
}
