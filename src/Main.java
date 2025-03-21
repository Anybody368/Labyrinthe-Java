import controller.MainController;
import view.MainWindow;
import model.Game;

public class Main {

    public static void main(String[] args) {
        
    Game game = new Game();
    MainController controller = new MainController(game);
    game.startGame();
    System.out.println(game);
    new MainWindow(controller, game);
    }
}
