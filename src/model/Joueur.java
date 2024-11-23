package model;

import model.observers.ObserverJoueur;

import java.awt.*;
import java.util.ArrayList;

import static model.Partie.TAILLE_PLATEAU;

public class Joueur {

private String m_nom;
private Color m_couleur;
private int m_x;
private int m_y;
private Tresor[] m_objectif;
private int m_avancementObj = 0;
private ArrayList<ObserverJoueur> m_observeurs = new ArrayList<>();

    /**
     * Constructeur d'un joueur
     * @param nom : Nom du joueur
     * @param couleur : Couleur du joueur (pour affichage)
     * @param x : Index de la colonne sur laquelle se trouve le joueur, entre 0 et TAILLE-1
     * @param y : Index de la ligne sur laquelle se trouve le joueur, entre 0 et TAILLE-1
     * @param objectifs : Liste des trésors que devra trouver le joueur pour gagner
     */
    public Joueur(String nom, Color couleur, int x, int y, Tresor[] objectifs) {

        m_nom = nom;
        m_couleur = couleur;
        m_x = x;
        m_y = y;
        m_objectif = objectifs;
    }

    /**
     * Ajout d'une instance qui pourra observer les changements du Joueur
     * @param observeur : instance qui doit observer le Joueur
     */
    public void addObserveur(ObserverJoueur observeur)
    {
        m_observeurs.add(observeur);
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
    public Tresor getObjectif(){

        if(!aFini()) {
            return m_objectif[m_avancementObj];
        }
        return null;
    }

    /**
     * Permet de modifier les coordonnées du joueur de 1 dans la dir donnée, en bouclant pour ne pas sortir des limites du tableau
     * @param dir : Direction dans laquelle le joueur se déplace
     */
    public void deplacement(Direction dir){

        //Peut être simplifié si tout se passe comme prévu avant
        switch (dir)
        {
            case NORD -> m_y = (m_y+TAILLE_PLATEAU-1)%TAILLE_PLATEAU;
            case SUD -> m_y = (m_y+1)%TAILLE_PLATEAU;
            case OUEST -> m_x = (m_x+TAILLE_PLATEAU-1)%TAILLE_PLATEAU;
            case EST -> m_x = (m_x+1)%TAILLE_PLATEAU;
        }
        notifyPosition();
    }

    /**
     * Vérifie si tresor est celui actuellement ciblé, et valide l'objectif si oui
     * @param tresor : Trésor de la case sur laquelle se trouve le joueur
     */
    public void validerObjectif(Tresor tresor){
        if(tresor == getObjectif())
        {
            m_avancementObj++;
            notifytresorSuivant();
        }
    }

    /**
     * À appeler quand le joueur se trouve sur sa case départ, vérifie si les objectifs sont finis, et prévient si oui
     */
    public void caseDepart(){

        if(aFini())
        {
            notifyVictoire();
        }
    }

    /**
     * Vérifie si le joueur a trouvé tous ses objectifs
     * @return true si oui, false sinon
     */
    private boolean aFini()
    {
        return (m_objectif.length == m_avancementObj);
    }

    //Partie observeur

    /**
     * À appeler quand la position du joueur change pour prévenir les observeurs
     */
    private void notifyPosition(){
        for(ObserverJoueur obs : m_observeurs)
        {
            obs.updatePosition(m_x, m_y);
        }
    }

    /**
     * À appeler quand le joueur trouve son trésor pour prévenir les observeurs du suivant
     */
    private void notifytresorSuivant(){
        for(ObserverJoueur obs : m_observeurs)
        {
            obs.updateTresor(getObjectif(), m_objectif.length-m_avancementObj);
        }
    }

    /**
     * À appeler quand le joueur gagne pour prévenir les observeurs
     */
    private void notifyVictoire(){
        for(ObserverJoueur obs : m_observeurs)
        {
            obs.updateVictoire(m_nom);
        }
    }
}
