package model;


import model.observers.ObserverJoueur;
import model.observers.ObserverPartie;

import java.util.ArrayList;

public class Partie implements ObserverJoueur {

    public static final int TAILLE_PLATEAU = 7;
            
    private int m_tour;
    private Plateau m_plateau;
    private Joueur m_joueurEnCours;
    private ArrayList<ObserverPartie> m_observeurs = new ArrayList<>();

    /**
     * Ajout d'une instance qui pourra observer les changements de la Partie
     * @param observeur : instance qui doit observer la Partie
     */
    public void addObserveur(ObserverPartie observeur)
    {
        m_observeurs.add(observeur);
    }

    /**
     * Méthode à appeler pour initialiser et démarrer une partie
     */
    public void lancerPartie() {


    }

    /**
     * Méthode pour gérer les actions d'un joueur pendant sont tour
     */
    private void tourdeJeu(){


    }

    /**
     * Méthode pour faire tourner la Tuile en rab de 90° sens horaire
     */
    public void tourneTuile(){


    }

    /**
     * Méthode à appeler pour que le joueur en cours essaie de se déplacer dans une direction donnée
     */
    public void deplacementJoueur(Direction dir){


    }

    @Override
    public void updatePosition(int x, int y) {

    }

    @Override
    public void updateTresor(Tresor tresor, int tRestants) {

    }

    @Override
    public void updateVictoire(String nom) {

    }

    /**
     * À appeler quand la tuile en rab est tournée pour prévenir les observeurs
     */
    private void notifyRotation()
    {

    }

    /**
     * À appeler quand un joueur change de position pour prévenir les observeurs
     */
    private void notifyPositions()
    {

    }
}
