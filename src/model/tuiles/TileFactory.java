package model.tuiles;

import model.Direction;
import model.Player;
import model.Treasure;

public class TileFactory {
    /**
     * Méthode pour créer une Tuile basique
     * @param dir : Première ouverture (sens horaire)
     * @param mode : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @return une nouvelle Tuile
     */
    public static TileDefault makeTileDefault(Direction dir, int mode)
    {
        return new TileDefault(getDirections(dir, mode));
    }

    /**
     * Méthode pour créer une Tuile ou commence un joueur
     * @param dir : Première ouverture (sens horaire)
     * @param mode : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @return une nouvelle Tuile de joueur
     */
    public static TileBase makeTileBase(Direction dir, int mode, Player j)
    {
        return new TileBase(getDirections(dir, mode), j);
    }

    /**
     * Méthode pour créer une Tuile ou commence un joueur
     * @param dir : Première ouverture (sens horaire)
     * @param mode : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @param t : le Tresor qui va se trouver sur cette case
     * @return une nouvelle tuile avec un tresor
     */
    public static TileTreasure makeTileTreasure(Direction dir, int mode, Treasure t)
    {
        return new TileTreasure(getDirections(dir, mode), t);
    }

    private static Direction[] getDirections(Direction dir, int mode)
    {
        return switch (mode)
        {
            case 1 -> new Direction[]{dir, dir.getOpposite()};
            case 2 -> new Direction[]{dir, dir.getNext()};
            case 3 -> new Direction[]{dir, dir.getNext(), dir.getOpposite()};
            default -> throw new IllegalArgumentException("Mode should either be 1, 2, or 3");
        };
    }
}
