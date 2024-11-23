package model.observers;

import model.Direction;
import model.tuiles.Tuile;

public interface ObserverPlateau {
    void updateDisposition(Direction dir, int index, Tuile[] tuiles);
    void updateRecupTresor(int x, int y);
}
