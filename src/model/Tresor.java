package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Tresor {

    COFFRE("Coffre"),
    CHOUETTE("Chouette"),
    FANTOME("Fantôme"),
    BAGUE("Bague"),
    CHANDELIER("Chandelier"),
    LEZARD("Lézard"),
    EPEE("Épée"),
    CHAUVE_SOURIS("Chauve-souris"),
    RAT("Rat"),
    CARTE("Carte"),
    PRINCESSE("Princesse"),
    DRAGON("Dragon"),
    CRANE("Crâne"),
    CLES("Clés"),
    GNOME("Gnôme"),
    COURONNE("Couronne"),
    SCARABE("Scarabé"),
    SAC("Sac"),
    LIVRE("Livre"),
    COLLIER("Collier"),
    ARAIGNEE("Araignée"),
    DIAMANT("Diamant"),
    ARMURE("Armure"),
    PAPILLON("Papillon");

    private String m_nom;

    Tresor(String nom)
    {
        m_nom = nom;
    }

    public String getNom()
    {
        return m_nom;
    }

    /**
     * Permet d'obtenir la liste des trésors dans un ordre aléatoire sous forme d'ArrayList afin de pouvoir supprimer des éléments au fur et à mesure
     * @return ArrayList contenant tous les trésors shuffled
     */
    public static ArrayList<Tresor> getRandomTreasureList()
    {
        ArrayList<Tresor> tresors = new ArrayList<>(List.of(values()));
        Collections.shuffle(tresors);
        return tresors;
    }
}
