package model.observers;

import model.tuiles.Tile;

public interface GameObserver {
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

    /**
     * Est appelé quand on change de phase de jeu (placement de tuile, ou déplacement) pour indiquer si le joueur peut se déplacer ou non
     * @param bool : Le joueur est-il dans sa phase de déplacement ?
     */
    void updateCanPlayerMove(boolean bool);
}
