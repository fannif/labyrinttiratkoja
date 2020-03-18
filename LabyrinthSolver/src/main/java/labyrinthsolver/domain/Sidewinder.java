
package labyrinthsolver.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Luokka, joka luo labyrintteja Sidewinder-algoritmilla
 */
public class Sidewinder {

    /**
     * Konstruktori
     */
    public Sidewinder() {
    }
    
    /**
     * Sidewinder-algoritmi. Generoi labyrintin
     * @param maze Labyrintti, jota ratkaistaan
     * @return Palauta luotu taulukkopohja, joka
     *  voidaan sitten asettaa labyrinttiin
     */
    public int[][] generate(Maze maze) {
        
        int n = maze.getSize();
        maze.initialize();
        maze.initializeWithWalls();

        int[][] grid = maze.getLayout();
        
        Random random = new Random();
        
        for (int x = 1; x < n - 1; x++) {
            grid[x][1] = 0;
        }
        
        for (int y = 3; y < n; y = y + 2) {
            HashSet<Pair> run = new HashSet<>();
            
            for (int x = 2; x < n; x = x + 2) {
                run.add(new Pair(x, y));
                
                if (x < n - 1 && (random.nextInt(2) + 1) % 2 == 0) {
                    grid[x][y] = 0;
                } else {
                    int next = random.nextInt(run.size());
                    Iterator<Pair> iter = run.iterator();
                    for (int k = 0; k < next; k++) {
                        iter.next();
                    }
                    int newX = iter.next().getX();
                    grid[newX - 1][y - 1] = 0;
                    run.clear();
                }
            }
        }
        
        return grid;
    }
    
}
