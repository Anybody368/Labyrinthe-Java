package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Treasure {

    CANNON("Cannon"),
    OWL("Chouette"),
    POTION("Potion"),
    RING("Bague"),
    CANDLESTICK("Chandelier"),
    LIZARD("Lézard"),
    DAGGER("Dagger"),
    BAT("Chauve-souris"),
    MOUSE("Mouse"),
    TREASURE("Treasure"),
    MERMAID("Mermaid"),
    HOLYGRAIL("Holygrail"),
    BOMB("Bomb"),
    KEYS("Clés"),
    COINS("Coins"),
    CROWN("Couronne"),
    BUG("Bug"),
    PONY("Pony"),
    BOOK("Livre"),
    KNIGHTHELMET("Knighthelmet"),
    CAT("Cat"),
    DIAMOND("Diamond"),
    ARMOR("Armor"),
    BUTTERFLY("Butterfly");

    private final String m_name;

    Treasure(String nom)
    {
        m_name = nom;
    }

    public String getName()
    {
        return m_name;
    }

    /**
     * Permet d'obtenir la liste des trésors dans un ordre aléatoire sous forme d'ArrayList afin de pouvoir supprimer des éléments au fur et à mesure
     * @return ArrayList contenant tous les trésors shuffled
     */
    public static ArrayList<Treasure> getRandomTreasureList()
    {
        ArrayList<Treasure> treasures = new ArrayList<>(List.of(values()));
        Collections.shuffle(treasures);
        return treasures;
    }
}
