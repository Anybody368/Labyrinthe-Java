package model.tuiles;

import model.Direction;
import model.Player;

public class TileBase extends Tile {

    private Player m_player;

    /*public TileBase(Direction[] dirs, Player player) {

        super(dirs);
        m_player = player;
    }*/

    public TileBase(Shape shape, Direction dir, Player player) {

        super(shape, dir);
        m_player = player;
    }

    @Override
    public void action(Player player) {
        if(player == m_player)
        {
            m_player.backHome();
        }
    }

    @Override
    public String toString() {
        return "Tuile de " + m_player.getName() + ", " + super.toString();
    }
}
