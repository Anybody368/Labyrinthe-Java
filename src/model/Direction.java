package model;

import java.util.Random;

public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    public Direction getOpposite()
    {
        return switch (this)
        {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
        };
    }

    public Direction getNext()
    {
        return switch (this)
        {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    public static Direction getRandom()
    {
        Random rnd = new Random();
        return switch (rnd.nextInt(4))
        {
            case 0 -> NORTH;
            case 1 -> EAST;
            case 2 -> SOUTH;
            case 3 -> WEST;
            default -> null;
        };
    }
}
