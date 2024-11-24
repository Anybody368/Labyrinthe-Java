package model;

import java.util.Random;

public enum Direction {

    NORD, EST, SUD, OUEST   ;

    public Direction getOpposite()
    {
        return switch (this)
        {
            case NORD -> SUD;
            case EST -> OUEST;
            case SUD -> NORD;
            case OUEST -> EST;
        };
    }

    public Direction getNext()
    {
        return switch (this)
        {
            case NORD -> EST;
            case EST -> SUD;
            case SUD -> OUEST;
            case OUEST -> NORD;
        };
    }

    public static Direction getRandom()
    {
        Random rnd = new Random();
        return switch (rnd.nextInt(4))
        {
            case 0 -> NORD;
            case 1 -> EST;
            case 2 -> SUD;
            case 3 -> OUEST;
            default -> null;
        };
    }
}
