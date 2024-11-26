package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Treasure {

    CHEST("Coffre"),
    OWL("Chouette"),
    GHOST("Fantôme"),
    RING("Bague"),
    CANDLESTICK("Chandelier"),
    LIZARD("Lézard"),
    SWORD("Épée"),
    BAT("Chauve-souris"),
    RAT("Rat"),
    MAP("Carte"),
    PRINCESS("Princesse"),
    DRAGON("Dragon"),
    SKULL("Crâne"),
    KEYS("Clés"),
    GNOME("Gnôme"),
    CROWN("Couronne"),
    BEETLE("Scarabé"),
    BAG("Sac"),
    BOOK("Livre"),
    NECKLACE("Collier"),
    SPIDER("Araignée"),
    DIAMOND("Diamant"),
    ARMOR("Armure"),
    BUTTERFLY("Papillon");

    private String m_name;

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
