package model.observers;

import model.Direction;
import model.tuiles.Tile;

public interface ObserverBoard {
    void updateTilesArrangement(Direction dir, int index, Tile[] tiles);
    void updateTreasurePicked(int x, int y);
}
