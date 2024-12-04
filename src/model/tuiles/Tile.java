package model.tuiles;

import model.Direction;
import model.Player;

public abstract class Tile {

    private Shape m_shape;
    private Direction m_orientation;

    protected Tile(Shape shape, Direction dir)
    {
        m_shape = shape;
        m_orientation = dir;
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

        m_orientation = m_orientation.getNext();
    }

    /**
     * Vérifie si le chemin allant vers dir est ouvert
     * @param dir : Direction à vérifier
     * @return true si ouvert, false sinon
     */
    private boolean isOpen(Direction dir){

        for(Direction opening : getOpenings())
        {
            if(dir == opening)
            {
                return true;
            }
        }
        return false;
    }

    private Direction[] getOpenings()
    {
        return switch (m_shape)
        {
            case I -> new Direction[]{m_orientation, m_orientation.getOpposite()};
            case L -> new Direction[]{m_orientation, m_orientation.getNext()};
            case T -> new Direction[]{m_orientation, m_orientation.getNext(), m_orientation.getOpposite()};
        };
    }

    public Shape getShape() {
        return m_shape;
    }

    public Direction getOrientation() {
        return m_orientation;
    }

    /**
     * Une fois qu'un joueur est arrêté sur cette case, on fait l'action associée en fonction du type de Tuile
     */
    public abstract void action(Player player);

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Forme : " + m_shape + ", Ouvertures : ");
        for(Direction dir : getOpenings())
        {
            string.append(dir.toString()).append(" ");
        }

        return string.toString();
    }
}