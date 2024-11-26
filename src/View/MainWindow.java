package View;

import model.Direction;
import model.observers.ObserverPlateau;
import model.tuiles.Tuile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observer;

public class MainWindow extends JFrame implements ObserverPlateau {

    public  MainWindow() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Labyrinthe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600); // Taille ajustée

            // Panel principal
            JPanel mainPanel = new JPanel(new BorderLayout());

            // Panel pour le layout global (9x9 incluant les boutons)
            JPanel borderedPanel = new JPanel(new GridLayout(9, 9));

            // Création du plateau avec boutons autour
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (i == 0 && j % 2 == 0 && j > 0 && j < 8) {
                        // Boutons en haut (↓) sur les colonnes paires
                        borderedPanel.add(new JButton("↓"));
                    } else if (i == 8 && j % 2 == 0 && j > 0 && j < 8) {
                        // Boutons en bas (↑) sur les colonnes paires
                        borderedPanel.add(new JButton("↑"));
                    } else if (j == 0 && i % 2 == 0 && i > 0 && i < 8) {
                        // Boutons à gauche (→) sur les lignes paires
                        borderedPanel.add(new JButton("→"));
                    } else if (j == 8 && i % 2 == 0 && i > 0 && i < 8) {
                        // Boutons à droite (←) sur les lignes paires
                        borderedPanel.add(new JButton("←"));
                    } else if (i > 0 && i < 8 && j > 0 && j < 8) {
                        // Plateau central 7x7
                        JButton tile = new JButton();
                        tile.setPreferredSize(new Dimension(50, 50));
                        tile.setBackground(Color.LIGHT_GRAY);
                        tile.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                        borderedPanel.add(tile);
                    } else {
                        // Espaces vides dans les coins
                        borderedPanel.add(new JLabel());
                    }
                }
            }

            // Ajout du layout global au panneau principal
            mainPanel.add(borderedPanel, BorderLayout.CENTER);
            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }

    @Override
    public void updateDisposition(Direction dir, int index, Tuile[] tuiles) {

    }

    @Override
    public void updateRecupTresor(int x, int y) {

    }
}
