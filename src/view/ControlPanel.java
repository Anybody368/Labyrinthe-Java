package view;

import controller.MainController;
import helpers.ImageHelper;
import model.Game;
import model.observers.GameObserver;
import model.tuiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static model.Direction.*;

public class ControlPanel extends JPanel implements GameObserver {
    private final ImagePanel extraTile;
    private final ArrayList<JButton> allButtons = new ArrayList<>();

    public ControlPanel(MainController ctrl, Game game) {
        game.addObserver(this);

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
        extraTile = spareTilePanel;
        spareTilePanel.setPreferredSize(new Dimension(100, 100));
        spareTilePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // Bordure

        // Chargement de l'image
        BufferedImage tileImage = ImageHelper.getTileImage(game.getExtraTile());
        spareTilePanel.setImage(tileImage);
        rightPanel.add(spareTilePanel);

        rightPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espace vertical

        // Bouton "Rotate"
        JButton rotateButton = new JButton("⟳ Rotation");
        rotateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        allButtons.add(rotateButton);// Centrer
        rightPanel.add(rotateButton);
        rotateButton.addActionListener(actionEvent -> ctrl.rotateTile());

        // Pavé directionnel (T inversé)
        JPanel directionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Bouton "Haut"
        JButton upButton = new JButton("↑");
        upButton.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        allButtons.add(upButton);
        directionPanel.add(upButton, gbc);
        upButton.addActionListener(actionEvent -> ctrl.movePlayer(NORTH));

        // Bouton "Gauche"
        JButton leftButton = new JButton("←");
        leftButton.setEnabled(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        allButtons.add(leftButton);
        directionPanel.add(leftButton, gbc);
        leftButton.addActionListener(actionEvent -> ctrl.movePlayer(WEST));

        // Bouton "Droite"
        JButton rightButton = new JButton("→");
        rightButton.setEnabled(false);
        gbc.gridx = 2;
        gbc.gridy = 1;
        allButtons.add(rightButton);
        directionPanel.add(rightButton, gbc);
        rightButton.addActionListener(actionEvent -> ctrl.movePlayer(EAST));

        // Bouton "Bas"
        JButton downButton = new JButton("↓");
        downButton.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        allButtons.add(downButton);
        directionPanel.add(downButton, gbc);
        downButton.addActionListener(actionEvent -> ctrl.movePlayer(SOUTH));

        rightPanel.add(directionPanel); // Ajouter le pavé directionnel
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espace vertical

        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace vertical

        // Bouton "Fin de tour"
        JButton endTurnButton = new JButton("Fin du tour");
        endTurnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endTurnButton.addActionListener(actionEvent -> ctrl.endTurn());
        allButtons.add(endTurnButton);
        rightPanel.add(endTurnButton);

        // Ajout de `rightPanel` au panneau principal
        this.add(rightPanel, BorderLayout.CENTER);
    }

    /**
     * @param turn : numéro de tour
     */
    @Override
    public void updateTurn(int turn) {

    }

    /**
     * @param tile : Nouvelle tuile en rab
     */
    @Override
    public void updateTile(Tile tile) {
        SwingUtilities.invokeLater(() -> extraTile.setImage(ImageHelper.getTileImage(tile)));
    }

    /**
     * @param bool
     */
    @Override
    public void updateCanPlayerMove(boolean bool) {
        for(JButton button : allButtons)
        {
            button.setEnabled(bool);
        }
    }
}
