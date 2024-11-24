package model.tuiles;

import model.Direction;

import static model.Direction.*;

public abstract class Tuile {

    private boolean m_nord = false;
    private boolean m_sud = false;
    private boolean m_est = false;
    private boolean m_ouest = false;

    /**
     * Constructeur générique d'une tuile
     * @param dirs : Tableau de directions représentant les côtés ouverts de la tuile
     */
    protected Tuile(Direction[] dirs) {

        for (Direction dir : dirs) {
            switch (dir) {
                case EST -> m_est = true;
                case NORD -> m_nord = true;
                case SUD -> m_sud = true;
                case OUEST -> m_ouest = true;
            }
        }
    }

    /**
     * Vérifie si un déplacement depuis cette tuile vers dest (adjacent) dans le sens indiqué par dir est possible
     * @param dest : Tuile sur laquelle on veut se déplacer
     * @param dir : Sens dans lequel se fait le déplacement
     * @return true si le chemin est possible, false sinon
     */
    public boolean deplacementEstvalide(Tuile dest, Direction dir){

        return switch (dir)
        {
            case NORD -> (estOuvert(NORD) && dest.estOuvert(SUD));
            case EST -> (estOuvert(EST) && dest.estOuvert(OUEST));
            case SUD -> (estOuvert(SUD) && dest.estOuvert(NORD));
            case OUEST -> (estOuvert(OUEST) && dest.estOuvert(EST));
        };
    }

    /**
     * "Tourne" la tuile de 90° sens horaire (donc quelles directions sont ouvertes)
     */
    public void rotation(){

        boolean temp = m_nord;
        m_nord = m_ouest;
        m_ouest = m_sud;
        m_sud = m_est;
        m_est = temp;
    }

    /**
     * Vérifie si le chemin allant vers dir est ouvert
     * @param dir : Direction à vérifier
     * @return true si ouvert, false sinon
     */
    private boolean estOuvert(Direction dir){

        return switch (dir)
        {
            case NORD -> m_nord;
            case EST -> m_est;
            case SUD -> m_sud;
            case OUEST -> m_ouest;
        };
    }

    /**
     * Une fois qu'un joueur est arrêté sur cette case, on fait l'action associée en fonction du type de Tuile
     */
    public abstract void action();

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Ouvertures : ");
        if(m_nord) string.append("Nord ");
        if(m_est) string.append("Est ");
        if(m_sud) string.append("Sud ");
        if(m_ouest) string.append("Ouest ");

        return string.toString();
    }
}