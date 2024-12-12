package model;

import model.observers.BoardObserver;
import model.observers.GameObserver;
import model.observers.PlayerObserver;
import model.tuiles.Tile;

import java.util.ArrayList;

public class Game {

    public static final int BOARD_SIZE = 7;
            
    private int m_turn;
    private Board m_board;
    private Player m_currentPlayer;
    private Tile m_extraTile;
    private final ArrayList<GameObserver> m_observers = new ArrayList<>();
    private boolean m_playerCanMove;

    /**
     * Ajout d'une instance qui pourra observer les changements de la Partie
     * @param observer : instance qui doit observer la Partie
     */
    public void addObserver(GameObserver observer)
    {
        m_observers.add(observer);
    }

    public void addBoardObserver(BoardObserver observer)
    {
        m_board.addObserver(observer);
    }

    public void addPlayersObserver(PlayerObserver observer)
    {
        m_board.addPlayersObserver(observer);
    }

    /**
     * Méthode à appeler pour initialiser et démarrer une partie
     */
    public void startGame() {
        m_turn = 0;
        Player[] players = createGamePlayers();

        m_board = new Board(players);
        m_extraTile = m_board.generateTilesAndBoard();
        m_currentPlayer = m_board.getNextPlayer(null);
        m_playerCanMove = false;
    }

    private Player[] createGamePlayers()
    {
        ArrayList<Treasure> treasures = Treasure.getRandomTreasureList();
        Player[] players = new Player[4];
        players[0] = new Player("bleu", 0, 0, new ArrayList<>(treasures.subList(0, 6)));
        players[1] = new Player("jaune", 0, BOARD_SIZE -1, new ArrayList<>(treasures.subList(6, 12)));
        players[2] = new Player("rouge", BOARD_SIZE -1, 0, new ArrayList<>(treasures.subList(12, 18)));
        players[3] = new Player("vert", BOARD_SIZE -1, BOARD_SIZE -1, new ArrayList<>(treasures.subList(18, 24)));
        return players;
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
        if(index%2 == 0 || m_playerCanMove)
        {
            return;
        }

        m_extraTile = m_board.placeTile(m_extraTile, dir, index);
        m_playerCanMove = true;
        notifyTile();
        notifyCanPlayerMove();
    }

    /**
     * Méthode à appeler pour que le joueur en cours essaie de se déplacer dans une direction donnée
     */
    public void movePlayer(Direction dir){
        if(!m_playerCanMove)
        {
            return;
        }

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
        m_playerCanMove = false;
        notifyCanPlayerMove();
        m_currentPlayer = m_board.getNextPlayer(m_currentPlayer);
    }

    /**
     * À appeler quand on passe au tour suivant pour prévenir les observers
     */
    private void notifyTurn()
    {
        for(GameObserver obs : m_observers)
        {
            obs.updateTurn(m_turn);
        }
    }

    /**
     * À appeler quand la tuile en rab est modifiée/remplacée pour prévenir les observers
     */
    private void notifyTile()
    {
        for(GameObserver obs : m_observers)
        {
            obs.updateTile(m_extraTile);
        }
    }

    public void notifyCanPlayerMove()
    {
        for(GameObserver obs : m_observers)
        {
            obs.updateCanPlayerMove(m_playerCanMove);
        }
    }

    public Tile getExtraTile()
    {
        return m_extraTile;
    }

    public Tile getBoardTile(int x, int y)
    {
        return m_board.getTile(x, y);
    }

    public Player[] getAllPlayers()
    {
        return m_board.getAllPlayers();
    }

    @Override
    public String toString() {

        return "Tour " + m_turn + "\n" +
                "Tour de " + m_currentPlayer.getName() + "\n" +
                "Tuile en rab : " + m_extraTile.toString() + "\n\n" +
                m_board.toString() + "\n";
    }
}
