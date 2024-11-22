package Model;

public abstract class Tuile {

    private boolean m_nord = false;
    private boolean m_sud = false;
    private boolean m_est = false;
    private boolean m_ouest = false;

    public Tuile(Direction[] dirs) {

        for (Direction dir : dirs) {
            switch (dir) {
                case EST -> m_est = true;
                case NORD -> m_nord = true;
                case SUD -> m_sud = true;
                case OUEST -> m_ouest = true;
            }
        }
    }

    public boolean deplacementEstvalide(){

        return true;

    }

    public void rotation(){

    }

    private boolean estOuvert(){

        return true;
    }

    public void action(){


    }

}