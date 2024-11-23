package model.observers;

import model.tuiles.Tuile;

public interface ObserverPartie {
    void updateRotation(Tuile tuile);
    void updatePositions(int[] xs, int[] ys);
}
