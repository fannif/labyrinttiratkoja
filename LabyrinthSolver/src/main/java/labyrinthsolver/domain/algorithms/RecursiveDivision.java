
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.utils.MersenneTwister;

/**
 * Generoi sokkeloita rekursiivisella jakoalgoritmilla.
 * Orientation on suunta, jossa sokkelo jaetaan. Tässä 0
 * on vaakasuora ja 1 on pystysuora.
 * width on käsiteltävän sokkelon leveys ja height korkeus.
 */
public class RecursiveDivision {
    
    private int orientation = 0;
    private long time = 0;
    private int width;
    private int height;
    private int grid[];
    private int n;
    private MersenneTwister random = new MersenneTwister(System.currentTimeMillis());
    
    /**
     * Konstruktorimetodi.
     */
    public RecursiveDivision() {
    }
    
    /**
     * Metodi generoi uuden sokkelon.
     * @param maze Pohja, josta saadaan haluttu koko.
     * @return Generoitu sokkelopohja.
     */
    public int[] generate(Maze maze) {
        long startTime = System.nanoTime();
        n = maze.getSize();
        maze.initialize(n);
        width = n;
        height = n;
        grid = maze.getLayout();
        
        divide(width, height, new int[]{0, 0});
        long endTime = System.nanoTime();
        time = endTime - startTime;
        
        return grid;
    }
    
    /**
     * Valitsee, jaetaanko sokkelu seuraavaksi vaaka- vai pystysuunnassa.
     * @param height Senhetkisen käsiteltävän osan korkeus
     * @param width Käsiteltävän sokkelonosan leveys
     */
    public void newOrientation(int height, int width) {
        if (height > width) {
            orientation = 0;
        } else {
            orientation = 1;
        }
    }
    
    /**
     * Jakaa käsiteltävän sokkelon pienempään käsiteltävään alueeseen,
     * ja lisää seinän jakokohtaan, ja seinään reiän.
     * @param width Käsiteltävän sokkelonosan leveys.
     * @param height Käsiteltävän sokkelonosan korkeus.
     * @param offset Pari, joka kuvaa, kuinka kaukaa nollarivistä
     * ja -sarakkeesta käsiteltävä alue alkaa.
     */
    public void divide(int width, int height, int[] offset) {
        if (width < 4 || height < 4) {
            return;
        }
        newOrientation(height, width);
        int wall = 1;
        
        if (orientation == 0) {
            while (wall % 2 != 0 || wall == 0) {
                wall = random.nextInt(height - 1);
            }
            int path = 0;
            while (path % 2 == 0 || path == 0) {
                path = random.nextInt(width - 1);
            }
            for (int i = 0; i < width; i++) {
                if (i == path) {
                    continue;
                }
                grid[(wall + offset[1]) * n + i + offset[0]] = 1;
            }
        } else {
            while (wall % 2 != 0 || wall == 0) {
                wall = random.nextInt(width - 1);
            }
            int path = 0;
            while (path % 2 == 0 || wall == 0) {
                path = random.nextInt(height - 1);
            }
            for (int i = 0; i < height; i++) {
                if (i == path) {
                    continue;
                }
                grid[(i + offset[1]) * n + wall + offset[0]] = 1;
            }
        }
        
        if (orientation == 0) {
            divide(width, wall, offset);
            divide(width, height - wall, new int[]{offset[0], offset[1] + wall});
        } else {
            divide(wall, height, offset);
            divide(width - wall, height, new int[]{offset[0] + wall, offset[1]});
        }
    }
    
    /**
     * Palauttaa seuraavan jaon suuntaa vastaavan arvon
     * @return Yksi jos seuraava jako pystysuora, nolla jos seuraava jako vaakasuora
     */
    public int getOrientation() {
        return this.orientation;
    }
    
    /**
     * Palauttaa generoimiseen viimeksi kuluneen ajan.
     * @return Viimeksi genrointiin kulunut aika nanosekunteina.
     */
    public long getTime() {
        return time;
    }
}
