import View.MainWindow;
import model.Direction;
import model.Game;

public class Main {

    public static void main(String[] args) {

    Game game = new Game();
    game.startGame();
    System.out.println(game);
    game.placeTile(Direction.EST, 1);
    game.movePlayer(Direction.SOUTH);
    System.out.println(game);
    new MainWindow();
    }
}
