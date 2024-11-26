package model.observers;

import model.tuiles.Tile;

public interface ObserverGame {
    void updateRotation(Tile tile);
    void updatePositions(int[] xs, int[] ys);
}
