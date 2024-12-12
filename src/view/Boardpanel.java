package view;

import controller.MainController;
import helpers.ImageHelper;
import model.Direction;
import model.Game;
import model.observers.BoardObserver;
import model.observers.GameObserver;
import model.tuiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpers.ImageHelper.rotateClockwise;

public class Boardpanel extends JPanel implements BoardObserver, GameObserver {
    private final ArrayList<JButton> buttons = new ArrayList<>();
    private final ArrayList<ImagePanel> imgPanels = new ArrayList<>();

    public Boardpanel(Game game, MainController ctrl) {

        game.addBoardObserver(this);
        game.addObserver(this);

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
                    imgPanels.get(index + i * 7).setImage(ImageHelper.getTileImage(tiles[i]));
                }
            } else {
                for (int i = 0; i < 7; i++) {
                    imgPanels.get(index * 7 + i).setImage(ImageHelper.getTileImage(tiles[i]));
                }
            }

            // à remettre à la fin quand tout marche
            //activateButtons(false);
        });
    }

    /**
     * @param turn : numéro de tour
     */
    @Override
    public void updateTurn(int turn) {
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
     * @param bool
     */
    @Override
    public void updateCanPlayerMove(boolean bool) {
        for(JButton button : buttons)
        {
            button.setEnabled(!bool);
        }
    }
}
