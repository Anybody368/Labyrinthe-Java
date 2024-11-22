package model;

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
}
