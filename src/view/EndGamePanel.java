package view;

import javax.swing.*;
import java.awt.*;
public class EndGamePanel extends JFrame {

    /*
    public static void main(String[] args) {
        // Exemple d'appel pour tester l'interface
        SwingUtilities.invokeLater(() -> afficherFinDePartie("Joueur 1"));
    }
    */

    public static void afficherFinDePartie(String winnerName) {
        // Créer une nouvelle JFrame pour la fin de partie
        JFrame endGamePanel = new JFrame("Fin de Partie");
        endGamePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endGamePanel.setSize(400, 200);
        endGamePanel.setLocationRelativeTo(null);

        // Conteneur principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Message indiquant le gagnant
        JLabel messageGagnant = new JLabel("Joueur \"" + winnerName + "\" a gagné !", JLabel.CENTER);
        messageGagnant.setFont(new Font("Arial", Font.BOLD, 16));

        // Bouton pour relancer la partie
        JButton boutonRelancer = new JButton("Relancer la partie");
        boutonRelancer.setFont(new Font("Arial", Font.PLAIN, 14));

        // Ajouter les composants au panneau principal
        mainPanel.add(messageGagnant, BorderLayout.CENTER);
        mainPanel.add(boutonRelancer, BorderLayout.SOUTH);

        // Ajouter le panneau principal à la fenêtre
        mainPanel.add(mainPanel);

        // Rendre la fenêtre visible
        endGamePanel.setVisible(true);
    }
}
