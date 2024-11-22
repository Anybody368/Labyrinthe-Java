package model.tuiles;

import model.Direction;
import model.Joueur;
import model.Tresor;

public class TuileFactory {
    /**
     * Méthode pour créer une Tuile basique
     * @param dirs : Tableau de directions représentant les côtés ouverts de la tuile
     * @return une nouvelle Tuile
     */
    public static TuileDefault makeTuileDefaut(Direction[] dirs)
    {
        return new TuileDefault(dirs);
    }

    /**
     * Méthode pour créer une Tuile ou commence un joueur
     * @param dirs : Tableau de directions représentant les côtés ouverts de la tuile
     * @param j : le Joueur qui va commencer sur cette case
     * @return une nouvelle Tuile de joueur
     */
    public static TuileDepart makeTuileDepart(Direction[] dirs, Joueur j)
    {
        return new TuileDepart(dirs, j);
    }

    /**
     * Méthode pour créer une Tuile ou commence un joueur
     * @param dirs : Tableau de directions représentant les côtés ouverts de la tuile
     * @param t : le Tresor qui va se trouver sur cette case
     * @return une nouvelle tuile avec un tresor
     */
    public static TuileTresor makeTuileTresor(Direction[] dirs, Tresor t)
    {
        return new TuileTresor(dirs, t);
    }
}
