package model.observers;

import model.tuiles.Tile;

public interface ObserverGame {
    /**
     * Est appelé quand on passe au tour suivant
     * @param turn : numéro de tour
     */
    void updateTurn(int turn);

    /**
     * Est appelé quand la tuile en rab est modifiée ou échangée
     * @param tile : Nouvelle tuile en rab
     */
    void updateTile(Tile tile);
}
