package model;

import model.tuiles.Tuile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static model.Direction.*;
import static model.Partie.TAILLE_PLATEAU;
import static model.tuiles.TuileFactory.*;

// Compilation de méthodes statiques utiles pour la génération du tableau 2D des tuiles, ainsi que les joueurs
public class FacadeGeneration {

    private static Tuile m_tuileRabStockee = null;

    /**
     * Initialise le tableau de joueurs avec des nouveaux joueurs avec une liste de trésors fixe
     */
    public static Joueur[] creationJoueurs()
    {
        ArrayList<Tresor> tresors = Tresor.getRandomTreasureList();
        Joueur[] joueurs = new Joueur[4];
        joueurs[0] = new Joueur("J1", Color.RED, 0, 0, new ArrayList<>(tresors.subList(0, 6)));
        joueurs[1] = new Joueur("J2", Color.YELLOW, 0, TAILLE_PLATEAU-1, new ArrayList<>(tresors.subList(6, 12)));
        joueurs[2] = new Joueur("J3", Color.BLUE, TAILLE_PLATEAU-1, 0, new ArrayList<>(tresors.subList(12, 18)));
        joueurs[3] = new Joueur("J4", Color.GREEN, TAILLE_PLATEAU-1, TAILLE_PLATEAU-1, new ArrayList<>(tresors.subList(18, 24)));
        return joueurs;
    }

    /**
     * Créé le tableau 2D contenant toutes les tuiles du tableau, et initialise la tuile en rab en passant
     * @return tableau 2D contenant 7*7 tuiles dans notre cas
     */
    public static Tuile[][] genererTuiles(Joueur[] joueurs)
    {
        ArrayList<Tresor> tresors = Tresor.getRandomTreasureList();
        ArrayList<Tuile> tuilesFixes = genererTuilesFixes(joueurs, tresors);
        ArrayList<Tuile> tuilesAmov = genererTuilesAmovibles(tresors);

        m_tuileRabStockee = tuilesAmov.removeLast();
        return genererTableauTuiles(tuilesFixes, tuilesAmov);
    }

    /**
     * Créé la liste des tuiles fixes du jeu, de bas en haut et de droite à gauche pour pouvoir lire séquentiellement par la suite
     * @param joueurs : Tableau des joueurs de la partie pour les casses départ
     * @param tresors : Liste des trésors de la partie (les trésors ajoutés aux cases seront supprimés de la liste pour éviter les doublons)
     * @return : Liste de tuiles ordonnées pour être ajoutées au plateau avec des "removeLast" successifs
     */
    private static ArrayList<Tuile> genererTuilesFixes(Joueur[] joueurs, ArrayList<Tresor> tresors)
    {
        ArrayList<Tuile> tuilesFixes = new ArrayList<>(16);
        tuilesFixes.add(makeTuileDepart(OUEST, 2, joueurs[3]));
        tuilesFixes.add(makeTuileTresor(SUD, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(SUD, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileDepart(SUD, 2, joueurs[2]));
        tuilesFixes.add(makeTuileTresor(OUEST, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(SUD, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(EST, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(EST, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(OUEST, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(OUEST, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(NORD, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(EST, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileDepart(NORD, 2, joueurs[1]));
        tuilesFixes.add(makeTuileTresor(NORD, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileTresor(NORD, 3, tresors.removeLast()));
        tuilesFixes.add(makeTuileDepart(EST, 2, joueurs[0]));

        return tuilesFixes;
    }

    /**
     * Créé la liste des tuiles amovibles du jeu de manière aléatoire (en respectant juste le nombre de cases de chaque type)
     * @param tresors : Liste des trésors restants après avoir généré les tuiles fixes (normalement, la liste devrait être vide à la fin de la fonction)
     * @return : Liste des tuiles amovibles dans un ordre et une orientation aléatoire
     */
    private static ArrayList<Tuile> genererTuilesAmovibles(ArrayList<Tresor> tresors)
    {
        ArrayList<Tuile> tuilesAmov = new ArrayList<>(33);

        for(int i = 0; i < 6; i++)
        {
            tuilesAmov.add(makeTuileTresor(Direction.getRandom(), 3, tresors.removeLast()));
        }
        for(int i = 0; i < 6; i++)
        {
            tuilesAmov.add(makeTuileTresor(Direction.getRandom(), 2, tresors.removeLast()));
        }
        for(int i = 0; i < 10; i++)
        {
            tuilesAmov.add(makeTuileDefaut(Direction.getRandom(), 2));
        }
        for(int i = 0; i < 12; i++)
        {
            tuilesAmov.add(makeTuileDefaut(Direction.getRandom(), 1));
        }

        Collections.shuffle(tuilesAmov);

        return tuilesAmov;
    }

    /**
     * Génère le tableau 2D attendu à partir des listes de tuiles fixes et amovibles
     * @param fixes : Liste des tuiles fixes (vide à la fin de la fonction)
     * @param amovibles : Liste des tuiles amovibles (Reste la tuile rab à la fin de la fonction)
     * @return Tableau 2D prêt à être utilisé pour le plateau
     */
    private static Tuile[][] genererTableauTuiles(ArrayList<Tuile> fixes, ArrayList<Tuile> amovibles)
    {
        Tuile[][] tableau = new Tuile[TAILLE_PLATEAU][TAILLE_PLATEAU];
        for (int i = 0; i < 3; i++)
        {
            tableau[2*i] = colonneFixe(fixes, amovibles);
            tableau[(2*i)+1] = colonneAmovible(amovibles);
        }
        tableau[TAILLE_PLATEAU-1] = colonneFixe(fixes, amovibles);
        return tableau;
    }

    /**
     * Génère une colonne ne contenant que des cases amovibles (index impair)
     * @param amovibles : Liste des tuiles amovibles
     * @return Tableau contenant les tuiles pour une colonne du tableau 2D
     */
    private static Tuile[] colonneAmovible(ArrayList<Tuile> amovibles)
    {
        Tuile[] colonne = new Tuile[TAILLE_PLATEAU];
        for (int i = 0; i < TAILLE_PLATEAU; i++)
        {
            colonne[i] = amovibles.removeLast();
        }
        return colonne;
    }

    /**
     * Génère une colonne contenant des cases fixes et amovibles (index pair)
     * @param amovibles : Liste des tuiles amovibles
     * @param fixes : Liste des tuiles fixes
     * @return Tableau contenant les tuiles pour une colonne du tableau 2D
     */
    private static Tuile[] colonneFixe(ArrayList<Tuile> fixes, ArrayList<Tuile> amovibles)
    {
        Tuile[] colonne = new Tuile[TAILLE_PLATEAU];
        for (int i = 0; i < 3; i++)
        {
            colonne[2*i] = fixes.removeLast();
            colonne[(2*i)+1] = amovibles.removeLast();
        }
        colonne[TAILLE_PLATEAU-1] = fixes.removeLast();
        return colonne;
    }

    public static Tuile getTuileRab()
    {
        return m_tuileRabStockee;
    }
}
