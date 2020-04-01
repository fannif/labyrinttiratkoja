
package labyrinthsolver.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Luokka, joka luo labyrintteja Sidewinder-algoritmilla
 */
public class Sidewinder {
    
    private long time = 0;

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
        long startTime = System.nanoTime();
        int n = maze.getSize();
        maze.initialize();
        maze.initializeWithWalls();

        int[][] grid = maze.getLayout();
        
        Random random = new Random();
        
        for (int x = 1; x < n - 1; x++) {
            grid[x][1] = 0;
        }
        
        for (int y = 3; y < n; y = y + 2) {
            PairSet run = new PairSet();
            
            for (int x = 2; x < n; x = x + 2) {
                run.add(new Pair(x, y));
                
                if (x < n - 1 && (random.nextInt(2) + 1) % 2 == 0) {
                    grid[x][y] = 0;
                } else {
                    Pair newPair = (Pair) run.randomPair();
                    if (newPair == null) {
                        continue;
                    }
                    int newX = newPair.getX();
                    grid[newX - 1][y - 1] = 0;
                    run.clear();
                }
            }
        }
        long endTime = System.nanoTime();
        time = endTime - startTime;
        
        return grid;
    }
    
    /**
     * Palauttaa generoimiseen viimeksi kuluneen ajan.
     * @return Viimeksi genrointiin kulunut aika nanosekunteina.
     */
    public long getTime() {
        return time;
    }
    
}
