package Model;

public abstract class Tuile {

private boolean m_nord;
private boolean m_sud;
private boolean m_east;
private boolean m_ouest;

    private boolean[] directions;

    public Tuile() {

        directions = new boolean[Direction.values().length];
        directions[Direction.NORD.ordinal()] = nord;
        directions[Direction.SUD.ordinal()] = sud;
        directions[Direction.EST.ordinal()] = est;
        directions[Direction.OUEST.ordinal()] = ouest;
    }



}
