package view;

import helpers.ImageHelper;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Boardpanel extends JPanel {

    public Boardpanel(Game game) {
        // Création du panneau bordé
        JPanel borderedPanel = new JPanel(new GridLayout(9, 9)); // Plateau avec boutons autour

        // Construction du plateau et des boutons autour
        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                if (i == 0 && j % 2 == 0 && j > 0 && j < 8) {

                    borderedPanel.add(new JButton("↓")); // Boutons du haut

                } else if (i == 8 && j % 2 == 0 && j > 0 && j < 8) {
                    JButton bouton = new JButton("↑");
                    borderedPanel.add(bouton); // Boutons du bas

                } else if (j == 0 && i % 2 == 0 && i > 0 && i < 8) {
                    borderedPanel.add(new JButton("→")); // Boutons à gauche

                } else if (j == 8 && i % 2 == 0 && i > 0 && i < 8) {
                    borderedPanel.add(new JButton("←")); // Boutons à droite

                } else if (i > 0 && i < 8 && j > 0 && j < 8) {
                    ImagePanel imgPanel = new ImagePanel();
                    BufferedImage tileImage = ImageHelper.getTileImage(game.getBoardTile(i-1,j-1));
                    //tile.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                    imgPanel.setImage(tileImage);
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
}
