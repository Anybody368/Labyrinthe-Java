package view;

import controller.MainController;
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
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Marges internes

            // Texte "Joueur"
            JLabel playerLabel = new JLabel("Joueur");
            playerLabel.setFont(new Font("Arial", Font.BOLD, 20));
            playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer le texte
            rightPanel.add(playerLabel);

            rightPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espace vertical


        // Création du panneau d'image
            ImagePanel spareTilePanel = new ImagePanel();
            spareTilePanel.setPreferredSize(new Dimension(100, 100));
            spareTilePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // Bordure

        // Chargement de l'image

            BufferedImage tileImage = null; // Remplacez par votre chemin
            try {
                tileImage = ImageIO.read(new File("C:\\Users\\natha\\Labyrinthe\\a31-labyrinthe\\img\\ExempleTuiles\\tuile_angle.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            spareTilePanel.setImage(tileImage);
            rightPanel.add(spareTilePanel);
            rightPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espace vertical


            // Pavé directionnel (T inversé)
            JPanel directionPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Bouton "Haut"
            JButton upButton = new JButton("↑");
            gbc.gridx = 1;
            gbc.gridy = 0;
            directionPanel.add(upButton, gbc);
            upButton.addActionListener(actionEvent -> {
                ctrl.movePlayer(NORTH);

            });

            // Bouton "Gauche"
            JButton leftButton = new JButton("←");
            gbc.gridx = 0;
            gbc.gridy = 1;
            directionPanel.add(leftButton, gbc);
            leftButton.addActionListener(actionEvent -> {
                ctrl.movePlayer(WEST);

            });

            // Bouton "Droite"
            JButton rightButton = new JButton("→");
            gbc.gridx = 2;
            gbc.gridy = 1;
            directionPanel.add(rightButton, gbc);
            rightButton.addActionListener(actionEvent -> {
                ctrl.movePlayer(EAST);

            });

            // Bouton "Bas"
            JButton downButton = new JButton("↓");
            gbc.gridx = 1;
            gbc.gridy = 2;
            directionPanel.add(downButton, gbc);
            downButton.addActionListener(actionEvent -> {
                ctrl.movePlayer(SOUTH);

            });

            rightPanel.add(directionPanel); // Ajouter le pavé directionnel

            rightPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espace vertical

            // Bouton "Rotate"
            JButton rotateButton = new JButton("⟳ Rotate");
            rotateButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer
            rightPanel.add(rotateButton);
            rotateButton.addActionListener(actionEvent -> {
                ctrl.rotateTile();
                BufferedImage img = spareTilePanel.image;
                spareTilePanel.setImage(rotateClockwise(img));
            });

            rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace vertical

            // Bouton "Trésors"
            JButton treasureButton = new JButton("🏆 Trésors");
            treasureButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer
            rightPanel.add(treasureButton);
            treasureButton.addActionListener(actionEvent -> {

            });

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
