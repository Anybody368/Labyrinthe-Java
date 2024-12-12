package view;

import controller.MainController;
import helpers.ImageHelper;
import model.Direction;
import model.Game;
import model.Treasure;
import model.observers.BoardObserver;
import model.observers.GameObserver;
import model.observers.PlayerObserver;
import model.tuiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Boardpanel extends JPanel implements BoardObserver, GameObserver, PlayerObserver {
    private final ArrayList<JButton> buttons = new ArrayList<>();
    private final ArrayList<ImagePanel> imgPanels = new ArrayList<>();
    private final Game m_game;

    public Boardpanel(Game game, MainController ctrl) {

        m_game = game;
        m_game.addBoardObserver(this);
        m_game.addObserver(this);
        m_game.addPlayersObserver(this);

        // Création du panneau bordé
        JPanel borderedPanel = new JPanel(new GridLayout(9, 9)); // Plateau avec boutons autour

        // Construction du plateau et des boutons autour
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                int x = j-1; //Variables finales pour pouvoir être utilisé dans les actionListeners
                int y = i-1;

                if (i == 0 && j % 2 == 0 && j > 0 && j < 8) {
                    JButton bouton = new JButton("↓");
                    bouton.addActionListener(actionEvent -> {
                        ctrl.placeTile(Direction.NORTH, x);
                    });
                    buttons.add(bouton);
                    borderedPanel.add(bouton); // Boutons du haut

                } else if (i == 8 && j % 2 == 0 && j > 0 && j < 8) {
                    JButton bouton = new JButton("↑");
                    bouton.addActionListener(actionEvent -> {
                        ctrl.placeTile(Direction.SOUTH, x);
                    });
                    buttons.add(bouton);
                    borderedPanel.add(bouton); // Boutons du bas

                } else if (j == 0 && i % 2 == 0 && i > 0 && i < 8) {
                    JButton bouton = new JButton("→");
                    bouton.addActionListener(actionEvent -> {
                        ctrl.placeTile(Direction.WEST, y);
                    });
                    buttons.add(bouton);
                    borderedPanel.add(bouton); // Boutons de gauche
                } else if (j == 8 && i % 2 == 0 && i > 0 && i < 8) {
                    JButton bouton = new JButton("←");
                    bouton.addActionListener(actionEvent -> {
                        ctrl.placeTile(Direction.EAST, y);
                    });
                    buttons.add(bouton);
                    borderedPanel.add(bouton); // Boutons de droite
                } else if (i > 0 && i < 8 && j > 0 && j < 8) {
                    ImagePanel imgPanel = new ImagePanel();
                    BufferedImage tileImage = ImageHelper.getTileImage(game.getBoardTile(x, y));
                    imgPanel.setImage(tileImage);
                    imgPanels.add(imgPanel);
                    borderedPanel.add(imgPanel); // Cases du plateau

                } else {
                    borderedPanel.add(new JLabel()); // Espaces vides
                }
            }
        }

        // Ajout du panneau interne au panneau principal
        this.setLayout(new BorderLayout());
        this.add(borderedPanel, BorderLayout.CENTER);
    }

    private ImagePanel getTilePanel(int x, int y)
    {
        return imgPanels.get(x + 7*y);
    }

    /**
     * @param dir   : Direction du changement (EST/OUEST pour ligne, NORD/SUD pour colonne)
     * @param index : Index de la ligne/colonne
     * @param tiles : Tuiles qui composent la ligne/colonne
     */
    @Override
    public void updateTilesArrangement(Direction dir, int index, Tile[] tiles) {

        SwingUtilities.invokeLater(() -> {
            if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                for (int i = 0; i < 7; i++) {
                    getTilePanel(index, i).setImage(ImageHelper.getTileImage(tiles[i]));
                }
            } else {
                for (int i = 0; i < 7; i++) {
                    getTilePanel(i, index).setImage(ImageHelper.getTileImage(tiles[i]));
                }
            }
        });
    }

    /**
     * @param turn : numéro de tour
     * @param playerName
     */
    @Override
    public void updateTurn(int turn, String playerName) {
        //on s'en fout
    }

    /**
     * @param tile : Nouvelle tuile en rab
     */
    @Override
    public void updateTile(Tile tile) {
        //on s'en fout
    }

    /**
     * @param bool : Le joueur est-il dans sa phase de déplacement ?
     */
    @Override
    public void updateCanPlayerMove(boolean bool) {
        for(JButton button : buttons)
        {
            button.setEnabled(!bool);
        }
    }

    /**
     * @param name : Nom du joueur
     * @param x    : Colonne du joueur
     * @param y    : Ligne du joueur
     */
    @Override
    public void updatePosition(String name, int x, int y) {
        getTilePanel(x, y).setImage(ImageHelper.getTileImage(m_game.getBoardTile(x, y), name));
    }

    /**
     * @param name                : Nom du joueur
     * @param treasure            : Nouveau trésor cherché (null si plus aucun)
     * @param nbTreasureRemaining : Nombre de trésors restants
     */
    @Override
    public void updateTreasure(String name, Treasure treasure, int nbTreasureRemaining) {

    }

    /**
     * @param name : Nom du joueur
     */
    @Override
    public void updateVictory(String name) {

    }
}
