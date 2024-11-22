package model;

import model.observers.ObserverPlateau;
import model.tuiles.Tuile;

import java.util.ArrayList;

public class Plateau {

    private Tuile[][] m_tuiles;
    private Joueur[] m_joueurs;
    private ArrayList<ObserverPlateau> m_observeurs = new ArrayList<>();

    /**
     * Constructeur du plateau de jeu
     * @param tuiles : Tableau en 2D représentant la disposition des tuiles
     */
    public Plateau(Tuile[][] tuiles)
    {
        m_tuiles = tuiles;
        //Ici créer les nouveaux joueurs
    }

    /**
     * Ajout d'une instance qui pourra observer les changements sur le PLateau
     * @param observeur : instance qui doit observer le Plateau
     */
    public void addObserveur(ObserverPlateau observeur)
    {
        m_observeurs.add(observeur);
    }

    /**
     * À appeler quand une tuile est placée sur une ligne/colonne pour prévenir les observeurs
     * @param dir : Indique si c'est une ligne (EST/OUEST) ou colonne (NORD/SUD)
     * @param index : Index de la ligne/colonne
     */
    private void notifyDisposition(Direction dir, int index)
    {

    }

    /**
     * À appeler quand un trésor est récupéré sur une tuile (optionnel, si on y arrive)
     */
    private void notifyRecupTresor()
    {

    }
}
