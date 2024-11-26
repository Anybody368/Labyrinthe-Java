package model;

import model.observers.ObserverPlayer;

import java.awt.*;
import java.util.ArrayList;

import static model.Game.BOARD_SIZE;

public class Player {

private String m_name;
private Color m_colour;
private int m_x;
private int m_y;
private ArrayList<Treasure> m_objectives;
private ArrayList<Treasure> m_done;
private ArrayList<ObserverPlayer> m_observers = new ArrayList<>();

    /**
     * Constructeur d'un joueur
     * @param nom : Nom du joueur
     * @param couleur : Couleur du joueur (pour affichage)
     * @param x : Index de la colonne sur laquelle se trouve le joueur, entre 0 et TAILLE-1
     * @param y : Index de la ligne sur laquelle se trouve le joueur, entre 0 et TAILLE-1
     * @param objectifs : Liste des trésors que devra trouver le joueur pour gagner
     */
    public Player(String nom, Color couleur, int x, int y, ArrayList<Treasure> objectifs) {

        m_name = nom;
        m_colour = couleur;
        m_x = x;
        m_y = y;
        m_objectives = objectifs;
        m_done = new ArrayList<>();
    }

    /**
     * Ajout d'une instance qui pourra observer les changements du Joueur
     * @param observeur : instance qui doit observer le Joueur
     */
    public void addObserveur(ObserverPlayer observeur)
    {
        m_observers.add(observeur);
    }

    /**
     * Getter de l'emplacement du joueur
     * @return tableau d'int contenant les coordonnées x et y
     */
    public int[] getPosition(){

        return new int[]{m_x, m_y};
    }

    /**
     * Getter du Trésor actuellement ciblé
     * @return le Trésor ciblé, null si tous trouvés
     */
    public Treasure getObjective(){

        if(!isDone()) {
            return m_objectives.getLast();
        }
        return null;
    }

    /**
     * Getter du nom du joueur
     * @return String contenant le nom
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Permet de modifier les coordonnées du joueur de 1 dans la dir donnée, en bouclant pour ne pas sortir des limites du tableau
     * @param dir : Direction dans laquelle le joueur se déplace
     */
    public void deplacement(Direction dir){

        //Peut être simplifié si tout se passe comme prévu avant
        switch (dir)
        {
            case NORTH -> m_y = (m_y+ BOARD_SIZE -1)% BOARD_SIZE;
            case SOUTH -> m_y = (m_y+1)% BOARD_SIZE;
            case WEST -> m_x = (m_x+ BOARD_SIZE -1)% BOARD_SIZE;
            case EST -> m_x = (m_x+1)% BOARD_SIZE;
        }
        notifyPosition();
    }

    /**
     * Vérifie si tresor est celui actuellement ciblé, et valide l'objectif si oui
     * @param treasure : Trésor de la case sur laquelle se trouve le joueur
     */
    public void validateObjective(Treasure treasure){
        if(treasure == getObjective())
        {
            m_done.add(m_objectives.removeLast());
            notifyNextTreasure();
        }
    }

    /**
     * À appeler quand le joueur se trouve sur sa case départ, vérifie si les objectifs sont finis, et prévient si oui
     */
    public void backHome(String name){

        if(isDone() && m_name.equals(name))
        {
            notifyVictory();
        }
    }

    /**
     * Vérifie si le joueur a trouvé tous ses objectifs
     * @return true si oui, false sinon
     */
    private boolean isDone()
    {
        return (m_objectives.isEmpty());
    }

    //Partie observeur

    /**
     * À appeler quand la position du joueur change pour prévenir les observeurs
     */
    private void notifyPosition(){
        for(ObserverPlayer obs : m_observers)
        {
            obs.updatePosition(m_x, m_y);
        }
    }

    /**
     * À appeler quand le joueur trouve son trésor pour prévenir les observeurs du suivant
     */
    private void notifyNextTreasure(){
        for(ObserverPlayer obs : m_observers)
        {

        }
    }

    /**
     * À appeler quand le joueur gagne pour prévenir les observeurs
     */
    private void notifyVictory(){
        for(ObserverPlayer obs : m_observers)
        {

        }
    }

    @Override
    public String toString() {
        return ("Joueur " + m_name + " en " + m_colour.toString() + ", tuile [" + m_x + ", " + m_y +
                "]\nObjectif actuel : " + getObjective().getName() + "\nNombre de trésors restants : " + m_objectives.size());
    }
}
