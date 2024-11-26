package model.tuiles;

import model.Direction;
import model.Player;

public class TileBase extends Tile {

    private Player m_player;

    public TileBase(Direction[] dirs, Player player) {

        super(dirs);
        m_player = player;
    }

    @Override
    public void action(Player player) {
        player.backHome(m_player.getName());
    }

    @Override
    public String toString() {
        return "Tuile de " + m_player.getName() + ", " + super.toString();
    }
}
