package controller;

import model.Direction;
import model.Game;

public class MainController {
    private final Game m_game;

    public MainController(Game game)
    {
        m_game = game;
    }

    public void startGame()
    {
        m_game.startGame();
    }

    public void movePlayer(Direction dir)
    {
        m_game.movePlayer(dir);
    }

    public void placeTile(Direction dir, int index)
    {
        m_game.placeTile(dir, index);
    }

    public void endTurn()
    {
        m_game.endTurn();
    }

    public void rotateTile()
    {
        m_game.turnTile();
    }
}
