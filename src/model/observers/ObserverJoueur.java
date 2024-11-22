package model.observers;

import model.Tresor;

public interface ObserverJoueur {
    public abstract void updatePosition(int x, int y);
    public abstract void updateTresor(Tresor tresor, int tRestants);
    public abstract void updateVictoire(String nom);
}
