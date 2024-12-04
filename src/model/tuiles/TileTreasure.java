package model.tuiles;

import model.Direction;
import model.Player;
import model.Treasure;

public class TileTreasure extends Tile {

    private final Treasure m_treasure;

    public TileTreasure(Shape shape, Direction dir, Treasure treasure) {

        super(shape, dir);
        m_treasure = treasure;
    }

    @Override
    public void action(Player player) {
        player.validateObjective(m_treasure);
    }

    @Override
    public String toString() {
        return "Trésor : " + m_treasure.getName() + ", " + super.toString();
    }
}
