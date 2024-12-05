package model;

import model.observers.ObserverBoard;
import model.observers.ObserverGame;
import model.observers.ObserverPlayer;
import model.tuiles.Tile;

import java.util.ArrayList;

public class Game {

    public static final int BOARD_SIZE = 7;
            
    private int m_turn;
    private Board m_board;
    private Player m_currentPlayer;
    private Tile m_extraTile;
    private final ArrayList<ObserverGame> m_observers = new ArrayList<>();

    /**
     * Ajout d'une instance qui pourra observer les changements de la Partie
     * @param observer : instance qui doit observer la Partie
     */
    public void addObserver(ObserverGame observer)
    {
        m_observers.add(observer);
    } //Appeler ces trois méthodes suivantes au début de MainWindow avec "this" comme param

    public void addBoardObserver(ObserverBoard observer)
    {
        m_board.addObserver(observer);
    }

    public void addPlayersObserver(ObserverPlayer observer)
    {
        m_board.addPlayersObserver(observer);
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
        notifyTile();
    }

    public void placeTile(Direction dir, int index)
    {
        if(index%2 == 0)
        {
            return;
        }

        m_extraTile = m_board.placeTile(m_extraTile, dir, index);
        notifyTile();
    }

    /**
     * Méthode à appeler pour que le joueur en cours essaie de se déplacer dans une direction donnée
     */
    public void movePlayer(Direction dir){
        int[] position = m_currentPlayer.getPosition();
        if(m_board.isPlayerMovePossible(position[0], position[1], dir))
        {
            m_currentPlayer.move(dir);
        }
    }

    /**
     * Méthode à appeler quand le joueur finit son tour de jeu
     */
    public void endTurn()
    {
        m_board.endTurn(m_currentPlayer);
        m_turn++;
        notifyTurn();
        m_currentPlayer = m_board.getNextPlayer(m_currentPlayer);
    }

    /**
     * À appeler quand on passe au tour suivant pour prévenir les observers
     */
    private void notifyTurn()
    {
        for(ObserverGame obs : m_observers)
        {
            obs.updateTurn(m_turn);
        }
    }

    /**
     * À appeler quand la tuile en rab est modifiée/remplacée pour prévenir les observers
     */
    private void notifyTile()
    {
        for(ObserverGame obs : m_observers)
        {
            obs.updateTile(m_extraTile);
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
