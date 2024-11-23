package helpers;

import model.Direction;
import model.Tresor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utilitaire {

    /**
     * Permet d'obtenir n directions distinctes (entre 1 et 4) de manière aléatoire
     * @param n : nombre de directions à sélectionner
     * @return un tableau contenant n directions distinctes
     */
    public static Direction[] getRandomDirections(int n)
    {
        ArrayList<Direction> allDirs = new ArrayList<>(List.of(Direction.values()));
        Collections.shuffle(allDirs);
        Direction[] dirs = new Direction[n];
        for (int i = 0; i < n; i++) {
            dirs[i] = allDirs.get(i);
        }
        return dirs;
    }

    /**
     * Permet d'obtenir la liste des trésors dans un ordre aléatoire sous forme d'ArrayList afin de pouvoir supprimer des éléments au fur et à mesure
     * @return ArrayList contenant tous les trésors shuffled
     */
    public static ArrayList<Tresor> getRandomTreasureList()
    {
        ArrayList<Tresor> tresors = new ArrayList<>(List.of(Tresor.values()));
        Collections.shuffle(tresors);
        return tresors;
    }
}
