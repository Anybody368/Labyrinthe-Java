package model.observers;

import model.Treasure;

public interface PlayerObserver {
    /**
     * Est appelé quand le joueur change de position
     *
     * @param name  : Nom du joueur
     * @param x     : Colonne du joueur
     * @param y     : Ligne du joueur
     * @param old_x
     * @param old_y
     */
    void updatePosition(String name, int x, int y, int old_x, int old_y);

    /**
     * Est appelé quand un joueur trouve son trésor
     * @param name : Nom du joueur
     * @param treasure : Nouveau trésor cherché (null si plus aucun)
     * @param nbTreasureRemaining : Nombre de trésors restants
     */
    void updateTreasure(String name, Treasure treasure, int nbTreasureRemaining);

    /**
     * Est appelé quand le joueur gagne la partie
     * @param name : Nom du joueur
     */
    void updateVictory(String name);
}