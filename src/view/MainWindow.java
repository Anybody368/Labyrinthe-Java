package view;

import controller.MainController;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

    public  MainWindow(MainController ctrl, Game game) { // mettre un controleur et une game en parametre
        SwingUtilities.invokeLater(() -> {
            //La Frame parent
            JFrame frame = new JFrame("Labyrinthe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Largeur augmentée pour inclure l'interface utilisateur

            // Panel principal (layout à deux colonnes)
            JPanel mainPanel = new JPanel(new BorderLayout());

            // ==================== Partie Gauche : Plateau ====================

            Boardpanel boardpanel = new Boardpanel(game, ctrl);

            // ==================== Partie Droite : Interface utilisateur ====================

            ControlPanel rightPanel = new ControlPanel(ctrl,game);

            // ==================== Assemblage de l'interface ====================
            mainPanel.add(boardpanel,BorderLayout.CENTER); // Plateau à gauche
            mainPanel.add(rightPanel, BorderLayout.EAST); // Interface utilisateur à droite

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
