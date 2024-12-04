package model;

import model.observers.ObserverBoard;
import model.tuiles.Tile;

import java.util.ArrayList;

import static model.Direction.*;
import static model.Game.BOARD_SIZE;

public class Board {

    public static final int AMOUNT_PLAYERS = 4;

    private Tile[][] m_tiles;
    private Player[] m_players;
    private ArrayList<ObserverBoard> m_observers = new ArrayList<>();

    /**
     * Constructeur du plateau de jeu
     * @param tiles : Tableau en 2D représentant la disposition des tuiles
     */
    public Board(Tile[][] tiles, Player[] players)
    {
        m_tiles = tiles;
        m_players = players;
    }

    /**
     * Ajout d'une instance qui pourra observer les changements sur le PLateau
     * @param observer : instance qui doit observer le Plateau
     */
    public void addObserver(ObserverBoard observer)
    {
        m_observers.add(observer);
    }

    /**
     * Place la tuile en rab du côté dir à la ligne/colonne index, et modifie la position des joueurs si nécessaire
     * @param tile : Tuile à ajouter au plateau
     * @param dir : Côté du plateau ou la tuile est insérée
     * @param index : index de la colonne/ligne à modifier
     * @return la tuile en rab si modification n'a pas pu être fait, la tuile qui a été sortie du plateau sinon
     */
    public Tile placeTile(Tile tile, Direction dir, int index) {
        Tile temp = tile;
        switch (dir)
        {
            case NORTH:
                temp = m_tiles[index][BOARD_SIZE -1];
                for(int i = BOARD_SIZE -1; i > 0; i--)
                {
                    m_tiles[index][i] = m_tiles[index][i-1];
                }
                m_tiles[index][0] = tile;
                break;
            case SOUTH:
                temp = m_tiles[index][0];
                for(int i = 0; i < BOARD_SIZE -1; i++)
                {
                    m_tiles[index][i] = m_tiles[index][i+1];
                }
                m_tiles[index][BOARD_SIZE -1] = tile;
                break;
            case WEST:
                temp = m_tiles[BOARD_SIZE -1][index];
                for(int i = BOARD_SIZE -1; i > 0; i--)
                {
                    m_tiles[i][index] = m_tiles[i-1][index];
                }
                m_tiles[0][index] = tile;
                break;
            case EAST:
                temp = m_tiles[0][index];
                for(int i = 0; i < BOARD_SIZE -1; i++)
                {
                    m_tiles[i][index] = m_tiles[i+1][index];
                }
                m_tiles[BOARD_SIZE -1][index] = tile;
                break;
        }
        notifyTilesArrangement(dir, index);

        for(Player player : m_players)
        {
            if((dir == EAST || dir == WEST) && player.getPosition()[0] == index)
            {
                player.moving(dir);
            }
            else if(player.getPosition()[1] == index)
            {
                player.moving(dir);
            }
        }

        return temp;
    }

    /**
     * Vérifie si un déplacement est possible entre deux cases du plateau à partir des coordonnées initiales et de la direction
     * @param x : Colonne de départ
     * @param y : Ligne de départ
     * @param dir : Direction du déplacement voulu
     * @return false si déplacement en dehors des limites du tableau ou si bloqué, true sinon
     */
    public boolean moveIsPossible(int x, int y, Direction dir)
    {
        Tile destination = null;
        switch (dir) {
            case NORTH:
                if (x > 0) {
                    destination = m_tiles[x][y - 1];
                }
                break;
            case EAST:
                if (y < BOARD_SIZE - 1) {
                    destination = m_tiles[x + 1][y];
                }
                break;
            case SOUTH:
                if (x < BOARD_SIZE - 1) {
                    destination = m_tiles[x][y + 1];
                }
                break;
            case WEST:
                if (y > 0) {
                    destination = m_tiles[x - 1][y];
                }
                break;
        }

        if(destination == null)
        {
            return false;
        }
        return m_tiles[x][y].moveIsValid(destination, dir);
    }

    /**
     * Méthode à appeler quand le joueur courant termine son tour, afin de vérifier si la case sur laquelle il se trouve produit un effet spécial
     * @param player : Joueur dont le tour est fini
     */
    public void endTurn(Player player)
    {
        int[] position = player.getPosition();
        m_tiles[position[0]][position[1]].action(player);
    }

    public Player[] getAllPlayers()
    {
        return m_players;
    }

    /**
     * Permet de récupérer le prochain joueur à qui c'est le tour
     * @param previous : joueur aillant fini son tour, null en début de partie
     * @return le joueur suivant (en bouclant)
     */
    public Player getNextPlayer(Player previous)
    {
        for (int i = 0; i < AMOUNT_PLAYERS; i++)
        {
            if(m_players[i] == previous)
            {
                return m_players[(i+1)% AMOUNT_PLAYERS];
            }
        }
        return m_players[0];
    }

    /**
     * Permet de récupérer les positions de l'ensemble des joueurs de la partie
     * @return un tableau 2D contenant toutes les coordonnées x et y
     */
    public int[][] getPlayersPositions()
    {
        int[][] positions = new int[AMOUNT_PLAYERS][2];
        for (int i = 0; i < AMOUNT_PLAYERS; i++)
        {
            positions[i] = m_players[i].getPosition();
        }
        return positions;
    }


    /**
     * À appeler quand une tuile est placée sur une ligne/colonne pour prévenir les observers
     * @param dir : Indique si c'est une ligne (EST/OUEST) ou colonne (NORD/SUD)
     * @param index : Index de la ligne/colonne
     */
    private void notifyTilesArrangement(Direction dir, int index)
    {
        for(ObserverBoard observer : m_observers)
        {
            if(dir == EAST || dir == WEST) {
                observer.updateTilesArrangement(dir, index, m_tiles[index]);
            }
            else
            {
                Tile[] tiles = new Tile[BOARD_SIZE];
                for(int i = 0; i < BOARD_SIZE; i++)
                {
                    tiles[i] = m_tiles[i][index];
                }
                observer.updateTilesArrangement(dir, index, tiles);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("État du plateau :");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                string.append("\nTuile [").append(i).append(", ").append(j).append("]").append(" : ").append(m_tiles[i][j].toString());
            }
        }

        string.append("\n");
        for (Player j : m_players)
        {
            string.append("\n").append(j.toString());
        }

        return string.toString();
    }
}
