package model.tuiles;

import model.Direction;
import model.Player;

public class TileDefault extends Tile {

    public TileDefault(Direction[] dirs) {

        super(dirs);
    }

    @Override
    public void action(Player player) {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
