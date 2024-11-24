package model.tuiles;

import model.Direction;
import model.Joueur;
import model.Tresor;

public class TuileFactory {
    /**
     * Méthode pour créer une Tuile basique
     * @param dir : Première ouverture (sens horaire)
     * @param mode : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @return une nouvelle Tuile
     */
    public static TuileDefault makeTuileDefaut(Direction dir, int mode)
    {
        return new TuileDefault(getDirections(dir, mode));
    }

    /**
     * Méthode pour créer une Tuile ou commence un joueur
     * @param dir : Première ouverture (sens horaire)
     * @param mode : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @return une nouvelle Tuile de joueur
     */
    public static TuileDepart makeTuileDepart(Direction dir, int mode, Joueur j)
    {
        return new TuileDepart(getDirections(dir, mode), j);
    }

    /**
     * Méthode pour créer une Tuile ou commence un joueur
     * @param dir : Première ouverture (sens horaire)
     * @param mode : Format de la tuile : 1 = I, 2 = L, 3 = T
     * @param t : le Tresor qui va se trouver sur cette case
     * @return une nouvelle tuile avec un tresor
     */
    public static TuileTresor makeTuileTresor(Direction dir, int mode, Tresor t)
    {
        return new TuileTresor(getDirections(dir, mode), t);
    }

    private static Direction[] getDirections(Direction dir, int mode)
    {
        return switch (mode)
        {
            case 1 -> new Direction[]{dir, dir.getOpposite()};
            case 2 -> new Direction[]{dir, dir.getNext()};
            case 3 -> new Direction[]{dir, dir.getNext(), dir.getOpposite()};
            default -> throw new IllegalArgumentException("Le mode demandé est invalide");
        };
    }
}
