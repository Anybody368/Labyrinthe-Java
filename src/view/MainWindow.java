package view;

import controller.MainController;
import helpers.ImageHelper;
import model.Direction;
import model.Treasure;
import model.observers.BoardObserver;
import model.observers.GameObserver;
import model.observers.PlayerObserver;
import model.tuiles.Tile;
import static model.Direction.*;
import model.Game;
import static helpers.ImageHelper.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame implements BoardObserver, PlayerObserver, GameObserver {

    public  MainWindow(MainController ctrl, Game game) { // mettre un controleur et une game en parametre
        SwingUtilities.invokeLater(() -> {
            // ajout des observers
            game.addObserver(this);
            game.addPlayersObserver(this);
            game.addBoardObserver(this);
            //La Frame parent
            JFrame frame = new JFrame("Labyrinthe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Largeur augmentée pour inclure l'interface utilisateur

            // Panel principal (layout à deux colonnes)
            JPanel mainPanel = new JPanel(new BorderLayout());

            // ==================== Partie Gauche : Plateau ====================

            Boardpanel boardpanel = new Boardpanel();

            // ==================== Partie Droite : Interface utilisateur ====================

            ControlPanel rightPanel = new ControlPanel(ctrl,game);

            // ==================== Assemblage de l'interface ====================
            mainPanel.add(boardpanel,BorderLayout.CENTER); // Plateau à gauche
            mainPanel.add(rightPanel, BorderLayout.EAST); // Interface utilisateur à droite

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }


    @Override
    public void updateTilesArrangement(Direction dir, int index, Tile[] tiles) {

    }

    @Override
    public void updateTurn(int turn) {

    }

    @Override
    public void updateTile(Tile tile) {

    }

    @Override
    public void updatePosition(String name, int x, int y) {

    }

    @Override
    public void updateTreasure(String name, Treasure treasure, int tRemaining) {

    }

    @Override
    public void updateVictory(String name) {

    }
}
