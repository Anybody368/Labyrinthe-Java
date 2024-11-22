package Model;

import java.awt.*;

public class Joueur {

private String m_nom;
private Color m_couleur;
private int m_x;
private int m_y;
private boolean m_aFini = false;
private Tresor[] m_objectif;
//ajout d'une arrayList d'observeur ici

    public Joueur(String nom, Color couleur, int x, int y, Tresor[] objectif) {

        m_nom = nom;
        m_couleur = couleur;
        m_x = x;
        m_y = y;
        m_objectif = objectif;
    }

    public int[] getPosition(){

        return new int[]{m_x, m_y};
    }

    public Tresor getObjectif(){

        return m_objectif[0]; // temporaire le temps que ça me fasse pas d'erreur
    }

    public void deplacement(Direction dir){


    }

    public void validerObjectif(Tresor tresor){


    }

    public void caseDepart(){


    }

    //Partie observeur

    private void notifyPosition(){

    }

    private void notifytresorSuivant(){

    }

    private void notifyVictoire(){

    }
}
