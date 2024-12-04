package model.tuiles;

import model.Direction;
import model.Player;
import model.Treasure;

public class TileFactory {
    /**
     * Méthode pour créer une Tuile basique
     *
     * @param dir   : Première ouverture (sens horaire)
     * @param shape : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @return une nouvelle Tuile
     */
    public static TileDefault makeTileDefault(Direction dir, Shape shape)
    {
        return new TileDefault(shape, dir);
    }

    /**
     * Méthode pour créer une Tuile ou commence un joueur
     *
     * @param dir   : Première ouverture (sens horaire)
     * @param shape : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @return une nouvelle Tuile de joueur
     */
    public static TileBase makeTileBase(Direction dir, Shape shape, Player j)
    {
        return new TileBase(shape, dir, j);
    }

    /**
     * Méthode pour créer une Tuile ou commence un joueur
     *
     * @param dir   : Première ouverture (sens horaire)
     * @param shape : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @param t     : le Tresor qui va se trouver sur cette case
     * @return une nouvelle tuile avec un tresor
     */
    public static TileTreasure makeTileTreasure(Direction dir, Shape shape, Treasure t)
    {
        return new TileTreasure(shape, dir, t);
    }
}
