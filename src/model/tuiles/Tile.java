package model.tuiles;

import model.Direction;
import model.Player;

public abstract class Tile {

    private boolean m_north = false;
    private boolean m_south = false;
    private boolean m_est = false;
    private boolean m_west = false;

    /**
     * Constructeur générique d'une tuile
     * @param dirs : Tableau de directions représentant les côtés ouverts de la tuile
     */
    protected Tile(Direction[] dirs) {

        for (Direction dir : dirs) {
            switch (dir) {
                case EAST -> m_est = true;
                case NORTH -> m_north = true;
                case SOUTH -> m_south = true;
                case WEST -> m_west = true;
            }
        }
    }

    /**
     * Vérifie si un déplacement depuis cette tuile vers dest (adjacent) dans le sens indiqué par dir est possible
     * @param dest : Tuile sur laquelle on veut se déplacer
     * @param dir : Sens dans lequel se fait le déplacement
     * @return true si le chemin est possible, false sinon
     */
    public boolean moveIsValid(Tile dest, Direction dir){

        return (isOpen(dir) && dest.isOpen(dir.getOpposite()));
    }

    /**
     * "Tourne" la tuile de 90° sens horaire (donc quelles directions sont ouvertes)
     */
    public void rotation(){

        boolean temp = m_north;
        m_north = m_west;
        m_west = m_south;
        m_south = m_est;
        m_est = temp;
    }

    /**
     * Vérifie si le chemin allant vers dir est ouvert
     * @param dir : Direction à vérifier
     * @return true si ouvert, false sinon
     */
    private boolean isOpen(Direction dir){

        return switch (dir)
        {
            case NORTH -> m_north;
            case EAST -> m_est;
            case SOUTH -> m_south;
            case WEST -> m_west;
        };
    }

    /**
     * Une fois qu'un joueur est arrêté sur cette case, on fait l'action associée en fonction du type de Tuile
     */
    public abstract void action(Player player);

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Ouvertures : ");
        if(m_north) string.append("Nord ");
        if(m_est) string.append("Est ");
        if(m_south) string.append("Sud ");
        if(m_west) string.append("Ouest ");

        return string.toString();
    }
}