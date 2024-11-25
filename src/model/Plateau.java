package model;

import model.observers.ObserverPlateau;
import model.tuiles.Tuile;

import java.awt.*;
import java.util.ArrayList;

import static model.Direction.*;
import static model.Partie.TAILLE_PLATEAU;

public class Plateau {

    public static final int NBR_JOUEURS = 4;

    private Tuile[][] m_tuiles;
    private Joueur[] m_joueurs = new Joueur[NBR_JOUEURS];
    private ArrayList<ObserverPlateau> m_observeurs = new ArrayList<>();

    /**
     * Constructeur du plateau de jeu
     * @param tuiles : Tableau en 2D représentant la disposition des tuiles
     */
    public Plateau(Tuile[][] tuiles, Joueur[] joueurs)
    {
        m_tuiles = tuiles;
        m_joueurs = joueurs;
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
     * Place la tuile en rab du côté dir à la ligne/colonne indew
     * @param tuile : Tuile à ajouter au plateau
     * @param dir : Côté du plateau ou la tuile est insérée
     * @param index : index de la colonne/ligne à modifier
     * @return la tuile en rab si modification n'a pas pu être fait, la tuile qui a été sortie du plateau sinon
     */
    public Tuile placementTuile(Tuile tuile, Direction dir, int index) {
        Tuile temp = tuile;
        switch (dir)
        {
            case NORD:
                temp = m_tuiles[index][TAILLE_PLATEAU-1];
                for(int i = TAILLE_PLATEAU-1; i > 0; i--)
                {
                    m_tuiles[index][i] = m_tuiles[index][i-1];
                }
                m_tuiles[index][0] = tuile;
                break;
            case SUD:
                temp = m_tuiles[index][0];
                for(int i = 0; i < TAILLE_PLATEAU-1; i++)
                {
                    m_tuiles[index][i] = m_tuiles[index][i+1];
                }
                m_tuiles[index][TAILLE_PLATEAU-1] = tuile;
                break;
            case OUEST:
                temp = m_tuiles[TAILLE_PLATEAU-1][index];
                for(int i = TAILLE_PLATEAU-1; i > 0; i--)
                {
                    m_tuiles[i][index] = m_tuiles[i-1][index];
                }
                m_tuiles[0][index] = tuile;
                break;
            case EST:
                temp = m_tuiles[0][index];
                for(int i = 0; i < TAILLE_PLATEAU-1; i++)
                {
                    m_tuiles[i][index] = m_tuiles[i+1][index];
                }
                m_tuiles[TAILLE_PLATEAU-1][index] = tuile;
                break;
        }
        notifyDisposition(dir, index);
        return temp;
    }

    /**
     * Vérifie si un déplacement est possible entre deux cases du plateau à partir des coordonnées initiales et de la direction
     * @param x : Colonne de départ
     * @param y : Ligne de départ
     * @param dir : Direction du déplacement voulu
     * @return false si déplacement en dehors des limites du tableau ou si bloqué, true sinon
     */
    public boolean deplacementPossible(int x, int y, Direction dir)
    {
        Tuile destination = null;
        switch (dir) {
            case NORD:
                if (x > 0) {
                    destination = m_tuiles[x][y - 1];
                }
                break;
            case EST:
                if (y < TAILLE_PLATEAU - 1) {
                    destination = m_tuiles[x + 1][y];
                }
                break;
            case SUD:
                if (x < TAILLE_PLATEAU - 1) {
                    destination = m_tuiles[x][y + 1];
                }
                break;
            case OUEST:
                if (y > 0) {
                    destination = m_tuiles[x - 1][y];
                }
                break;
        }

        if(destination == null)
        {
            return false;
        }
        return m_tuiles[x][y].deplacementEstvalide(destination, dir);
    }

    /**
     * Méthode à appeler quand le joueur courant termine son tour, afin de vérifier si la case sur laquelle il se trouve produit un effet spécial
     * @param joueur : Joueur dont le tour est fini
     */
    public void finTour(Joueur joueur)
    {
        int[] position = joueur.getPosition();
        m_tuiles[position[0]][position[1]].action();
    }

    public Joueur[] getAllJoueurs()
    {
        return m_joueurs;
    }

    /**
     * Permet de récupérer le prochain joueur à qui c'est le tour
     * @param precedent : joueur aillant fini son tour, null en début de partie
     * @return le joueur suivant (en bouclant)
     */
    public Joueur getJoueurSuivant(Joueur precedent)
    {
        for (int i = 0; i < NBR_JOUEURS; i++)
        {
            if(m_joueurs[i] == precedent)
            {
                return m_joueurs[(i+1)%NBR_JOUEURS];
            }
        }
        return m_joueurs[0];
    }

    /**
     * Permet de récupérer les positions de l'ensemble des joueurs de la partie
     * @return un tableau 2D contenant toutes les coordonnées x et y
     */
    public int[][] getPositionJoueurs()
    {
        int[][] positions = new int[NBR_JOUEURS][2];
        for (int i = 0; i < NBR_JOUEURS; i++)
        {
            positions[i] = m_joueurs[i].getPosition();
        }
        return positions;
    }


    /**
     * À appeler quand une tuile est placée sur une ligne/colonne pour prévenir les observeurs
     * @param dir : Indique si c'est une ligne (EST/OUEST) ou colonne (NORD/SUD)
     * @param index : Index de la ligne/colonne
     */
    private void notifyDisposition(Direction dir, int index)
    {
        for(ObserverPlateau observeur : m_observeurs)
        {
            if(dir == EST || dir == OUEST) {
                observeur.updateDisposition(dir, index, m_tuiles[index]);
            }
            else
            {
                Tuile[] tuiles = new Tuile[TAILLE_PLATEAU];
                for(int i = 0; i < TAILLE_PLATEAU; i++)
                {
                    tuiles[i] = m_tuiles[i][index];
                }
                observeur.updateDisposition(dir, index, tuiles);
            }
        }
    }

    /**
     * À appeler quand un trésor est récupéré sur une tuile (optionnel, si on y arrive)
     */
    private void notifyRecupTresor()
    {

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("État du plateau :");
        for (int i = 0; i < TAILLE_PLATEAU; i++) {
            for (int j = 0; j < TAILLE_PLATEAU; j++) {
                string.append("\nTuile [").append(i).append(", ").append(j).append("]").append(" : ").append(m_tuiles[i][j].toString());
            }
        }

        string.append("\n");
        for (Joueur j : m_joueurs)
        {
            string.append("\n").append(j.toString());
        }

        return string.toString();
    }
}