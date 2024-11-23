package model;


import helpers.Utilitaire;
import model.observers.ObserverJoueur;
import model.observers.ObserverPartie;
import model.tuiles.Tuile;

import java.util.ArrayList;
import java.util.Collections;

import static model.Plateau.NBR_JOUEURS;

public class Partie implements ObserverJoueur {

    public static final int TAILLE_PLATEAU = 7;
            
    private int m_tour;
    private Plateau m_plateau;
    private Joueur m_joueurEnCours;
    private Tuile m_tuileRab;
    private ArrayList<ObserverPartie> m_observeurs = new ArrayList<>();
    private String m_gagnant = null;

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
        m_tour = 0;

        m_plateau = new Plateau(null);
        m_joueurEnCours = m_plateau.getJoueurSuivant(null);

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
        m_tuileRab.rotation();
        notifyRotation();
    }

    /**
     * Méthode à appeler pour que le joueur en cours essaie de se déplacer dans une direction donnée
     */
    public void deplacementJoueur(Direction dir){
        int[] position = m_joueurEnCours.getPosition();
        if(m_plateau.deplacementPossible(position[0], position[1], dir))
        {
            m_joueurEnCours.deplacement(dir);
        }
    }

    /**
     * PAS FINI Créé le tableau 2D contenant toutes les tuiles du tableau, et initialise la tuile en rab en passant
     * @return tableau 2D contenant 7*7 tuiles dans notre cas
     */
    private Tuile[][] genererTuiles()
    {
        int nbr = (TAILLE_PLATEAU*TAILLE_PLATEAU)+1;
        Tuile[] tuiles = new Tuile[nbr];
        ArrayList<Tresor> tresors = Utilitaire.getRandomTreasureList();

        return null;
    }

    @Override
    public void updatePosition(int x, int y) {
        notifyPositions();
    }

    @Override
    public void updateTresor(Tresor tresor, int tRestants) {

    }

    @Override
    public void updateVictoire(String nom) {
        m_gagnant = nom;
    }

    /**
     * À appeler quand la tuile en rab est tournée pour prévenir les observeurs
     */
    private void notifyRotation()
    {
        for(ObserverPartie observeurs : m_observeurs)
        {
            observeurs.updateRotation(m_tuileRab);
        }
    }

    /**
     * À appeler quand un joueur change de position pour prévenir les observeurs
     */
    private void notifyPositions()
    {
        int[][] positions = m_plateau.getPositionJoueurs();
        int[] xs = new int[NBR_JOUEURS], ys = new int[NBR_JOUEURS];
        for(int i = 0; i < NBR_JOUEURS; i++)
        {
            xs[i] = positions[i][0];
            ys[i] = positions[i][1];
        }
        for(ObserverPartie observeurs : m_observeurs)
        {
            observeurs.updatePositions(xs, ys);
        }
    }
}
