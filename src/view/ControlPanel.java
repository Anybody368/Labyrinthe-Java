package view;

import controller.MainController;
import helpers.ImageHelper;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static helpers.ImageHelper.rotateClockwise;
import static model.Direction.*;

public class ControlPanel extends JPanel {

    public ControlPanel(MainController ctrl, Game game) {
        // Configuration du panneau principal
        this.setLayout(new BorderLayout());

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
        BufferedImage tileImage = ImageHelper.getTileImage(game.getExtraTile());
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
        upButton.addActionListener(actionEvent -> ctrl.movePlayer(NORTH));

        // Bouton "Gauche"
        JButton leftButton = new JButton("←");
        gbc.gridx = 0;
        gbc.gridy = 1;
        directionPanel.add(leftButton, gbc);
        leftButton.addActionListener(actionEvent -> ctrl.movePlayer(WEST));

        // Bouton "Droite"
        JButton rightButton = new JButton("→");
        gbc.gridx = 2;
        gbc.gridy = 1;
        directionPanel.add(rightButton, gbc);
        rightButton.addActionListener(actionEvent -> ctrl.movePlayer(EAST));

        // Bouton "Bas"
        JButton downButton = new JButton("↓");
        gbc.gridx = 1;
        gbc.gridy = 2;
        directionPanel.add(downButton, gbc);
        downButton.addActionListener(actionEvent -> ctrl.movePlayer(SOUTH));

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

        // Ajout de `rightPanel` au panneau principal
        this.add(rightPanel, BorderLayout.CENTER);
    }
}
