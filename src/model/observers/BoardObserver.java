package model.observers;

import model.Direction;
import model.tuiles.Tile;

public interface BoardObserver {
    /**
     * Est appelé quand une ligne ou rangée de tuile est modifiée
     * @param dir : Direction du changement (EST/OUEST pour ligne, NORD/SUD pour colonne)
     * @param index : Index de la ligne/colonne
     * @param tiles : Tuiles qui composent la ligne/colonne
     */
    void updateTilesArrangement(Direction dir, int index, Tile[] tiles);
}
