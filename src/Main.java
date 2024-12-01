import model.Direction;
import model.Game;

public class Main {

    public static void main(String[] args) {

    Game jeu = new Game();
    jeu.startGame();
    System.out.println(jeu);
    jeu.placeTile(Direction.EAST, 1);
    jeu.movePlayer(Direction.SOUTH);
    System.out.println(jeu);
    }
}
