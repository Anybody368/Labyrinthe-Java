package model.tuiles;

import model.Direction;
import model.Player;

import java.io.File;

public class TileBase extends Tile {

    private final Player m_player;

    public TileBase(Shape shape, Direction dir, Player player) {

        super(shape, dir);
        m_player = player;
    }

    @Override
    public String getPathExtra() {
        String sep = File.separator;
        return "img"+sep+"ImgPion"+sep+m_player.getName()+".png";
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
