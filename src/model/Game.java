package model;

import model.observers.ObserverGame;
import model.tuiles.Tile;

import java.util.ArrayList;

import static model.Board.AMOUNT_PLAYERS;

public class Game {

    public static final int BOARD_SIZE = 7;
            
    private int m_turn;
    private Board m_board;
    private Player m_currentPlayer;
    private Tile m_extraTile;
    private ArrayList<ObserverGame> m_observers = new ArrayList<>();
    //private String m_gagnant = null;

    /**
     * Ajout d'une instance qui pourra observer les changements de la Partie
     * @param observer : instance qui doit observer la Partie
     */
    public void addObserver(ObserverGame observer)
    {
        m_observers.add(observer);
    }

    /**
     * Méthode à appeler pour initialiser et démarrer une partie
     */
    public void startGame() {
        m_turn = 0;
        Player[] players = FacadeGeneration.createGamePlayers();

        Tile[][] tiles = FacadeGeneration.generateTiles(players);
        m_board = new Board(tiles, players);
        m_extraTile = FacadeGeneration.getExtraTile();
        m_currentPlayer = m_board.getNextPlayer(null);
    }

    /**
     * Méthode pour faire tourner la Tuile en rab de 90° sens horaire
     */
    public void turnTile(){
        m_extraTile.rotation();
        notifyRotation();
    }

    public void placeTile(Direction dir, int index)
    {
        if(index%2 == 0)
        {
            return;
        }

        m_extraTile = m_board.placeTile(m_extraTile, dir, index);
    }

    /**
     * Méthode à appeler pour que le joueur en cours essaie de se déplacer dans une direction donnée
     */
    public void movePlayer(Direction dir){
        int[] position = m_currentPlayer.getPosition();
        if(m_board.moveIsPossible(position[0], position[1], dir))
        {
            m_currentPlayer.deplacement(dir);
        }
    }

    public void endTurn()
    {
        m_board.endTurn(m_currentPlayer);
        m_turn++;
        m_currentPlayer = m_board.getNextPlayer(m_currentPlayer);
    }

    public void updatePosition(int x, int y) {
        notifyPositions();
    }

    public void updateTresor(Treasure treasure, int tRestants) {

    }

    public void updateVictoire(String nom) {

    }

    /**
     * À appeler quand la tuile en rab est tournée pour prévenir les observeurs
     */
    private void notifyRotation()
    {
        for(ObserverGame observeurs : m_observers)
        {
            observeurs.updateRotation(m_extraTile);
        }
    }

    /**
     * À appeler quand un joueur change de position pour prévenir les observeurs
     */
    private void notifyPositions()
    {
        int[][] positions = m_board.getPlayersPositions();
        int[] xs = new int[AMOUNT_PLAYERS], ys = new int[AMOUNT_PLAYERS];
        for(int i = 0; i < AMOUNT_PLAYERS; i++)
        {
            xs[i] = positions[i][0];
            ys[i] = positions[i][1];
        }
        for(ObserverGame observeurs : m_observers)
        {
            observeurs.updatePositions(xs, ys);
        }
    }

    @Override
    public String toString() {

        return "Tour " + m_turn + "\n" +
                "Tour de " + m_currentPlayer.getName() + "\n" +
                "Tuile en rab : " + m_extraTile.toString() + "\n\n" +
                m_board.toString() + "\n";
    }
}
