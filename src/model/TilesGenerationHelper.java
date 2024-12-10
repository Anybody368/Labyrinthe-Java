package model;

import helpers.ImageHelper;
import model.tuiles.Shape;
import model.tuiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static model.Direction.*;
import static model.Game.BOARD_SIZE;
import static model.tuiles.TileFactory.*;

// Compilation de méthodes statiques utiles pour la génération du tableau 2D des tuiles, ainsi que les joueurs
public class TilesGenerationHelper {

    /**
     * Créé la liste des tuiles fixes du jeu, de bas en haut et de droite à gauche pour pouvoir lire séquentiellement par la suite
     * @param players : Tableau des joueurs de la partie pour les casses départ
     * @param treasures : Liste des trésors de la partie (les trésors ajoutés aux cases seront supprimés de la liste pour éviter les doublons)
     * @return : Liste de tuiles ordonnées pour être ajoutées au plateau avec des "removeLast" successifs
     */
    public static ArrayList<Tile> generateFixedTiles(Player[] players, ArrayList<Treasure> treasures)
    {
        ArrayList<Tile> tuilesFixes = new ArrayList<>(16);
        tuilesFixes.add(makeTileBase(WEST, Shape.L, players[3]));
        tuilesFixes.add(makeTileTreasure(SOUTH, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(SOUTH, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileBase(SOUTH, Shape.L, players[2]));
        tuilesFixes.add(makeTileTreasure(WEST, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(SOUTH, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(EAST, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(EAST, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(WEST, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(WEST, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(NORTH, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(EAST, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileBase(NORTH, Shape.L, players[1]));
        tuilesFixes.add(makeTileTreasure(NORTH, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileTreasure(NORTH, Shape.T, treasures.removeLast()));
        tuilesFixes.add(makeTileBase(EAST, Shape.L, players[0]));

        return tuilesFixes;
    }

    /**
     * Créé la liste des tuiles amovibles du jeu de manière aléatoire (en respectant juste le nombre de cases de chaque type)
     * @param treasures : Liste des trésors restants après avoir généré les tuiles fixes (normalement, la liste devrait être vide à la fin de la fonction)
     * @return : Liste des tuiles amovibles dans un ordre et une orientation aléatoire
     */
    public static ArrayList<Tile> generateMovableTiles(ArrayList<Treasure> treasures)
    {
        ArrayList<Tile> tuilesAmov = new ArrayList<>(33);

        for(int i = 0; i < 6; i++)
        {
            tuilesAmov.add(makeTileTreasure(Direction.getRandom(), Shape.T, treasures.removeLast()));
        }
        for(int i = 0; i < 6; i++)
        {
            tuilesAmov.add(makeTileTreasure(Direction.getRandom(), Shape.L, treasures.removeLast()));
        }
        for(int i = 0; i < 10; i++)
        {
            tuilesAmov.add(makeTileDefault(Direction.getRandom(), Shape.L));
        }
        for(int i = 0; i < 12; i++)
        {
            tuilesAmov.add(makeTileDefault(Direction.getRandom(), Shape.I));
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
    public static Tile[][] generateTilesTable(ArrayList<Tile> fixes, ArrayList<Tile> amovibles)
    {
        Tile[][] tableau = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < 3; i++)
        {
            tableau[2*i] = fixedColumn(fixes, amovibles);
            tableau[(2*i)+1] = movableColumn(amovibles);
        }
        tableau[BOARD_SIZE -1] = fixedColumn(fixes, amovibles);
        return tableau;
    }

    /**
     * Génère une colonne ne contenant que des cases amovibles (index impair)
     * @param amovibles : Liste des tuiles amovibles
     * @return Tableau contenant les tuiles pour une colonne du tableau 2D
     */
    private static Tile[] movableColumn(ArrayList<Tile> amovibles)
    {
        Tile[] colonne = new Tile[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
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
    private static Tile[] fixedColumn(ArrayList<Tile> fixes, ArrayList<Tile> amovibles)
    {
        Tile[] colonne = new Tile[BOARD_SIZE];
        for (int i = 0; i < 3; i++)
        {
            colonne[2*i] = fixes.removeLast();
            colonne[(2*i)+1] = amovibles.removeLast();
        }
        colonne[BOARD_SIZE -1] = fixes.removeLast();
        return colonne;
    }
}
