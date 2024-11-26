package model;

import java.util.Random;

public enum Direction {

    NORTH, EST, SOUTH, WEST;

    public Direction getOpposite()
    {
        return switch (this)
        {
            case NORTH -> SOUTH;
            case EST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EST;
        };
    }

    public Direction getNext()
    {
        return switch (this)
        {
            case NORTH -> EST;
            case EST -> SOUTH;
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
            case 1 -> EST;
            case 2 -> SOUTH;
            case 3 -> WEST;
            default -> null;
        };
    }
}
