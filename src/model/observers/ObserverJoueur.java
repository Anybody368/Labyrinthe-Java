package model.observers;

import model.Tresor;

public interface ObserverJoueur {
    void updatePosition(int x, int y);
    void updateTresor(Tresor tresor, int tRestants);
    void updateVictoire(String nom);
}
