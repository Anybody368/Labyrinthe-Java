package model.tuiles;

import model.Direction;
import model.Tresor;

public class TuileTresor extends Tuile {

    private Tresor m_tresor;

    public TuileTresor(Direction[] dirs, Tresor tresor) {

        super(dirs);
        m_tresor = tresor;
    }

    @Override
    public void action() {

    }

    @Override
    public String toString() {
        return "Trésor : " + m_tresor.getNom() + ", " + super.toString();
    }
}
