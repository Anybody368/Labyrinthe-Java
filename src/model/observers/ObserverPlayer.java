package model.observers;

import model.Treasure;

public interface ObserverPlayer {
    void updatePosition(int x, int y);
    void updateTreasure(Treasure treasure, int tRemaining);
    void updateVictory(String name);
}
