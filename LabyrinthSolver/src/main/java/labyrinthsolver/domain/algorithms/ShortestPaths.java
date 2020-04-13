
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.utils.PairQueue;

/**
 * Ratkaisee sokkelon kaikki lyhimmät reitit.
 * Grid kuvaa sokkelopohjaa ja distance kuvaa
 * ruutujen etäisyyksiä lähtöpisteestä.
 */
public class ShortestPaths {
    
    private int[][] grid;
    private int[][] distance;
    private long time = 0;
    
    /**
     * Konstruktorimetodi.
     */
    public ShortestPaths() {
    }
    
    /**
     * Ratkaisee sokkelon hyödyntäen yhdistelmää kahdesta
     * erilaisesta leveyshausta.
     * @param maze Ratkaistava sokkelo
     * @return Ratkaistu sokkelopohja
     */
    public int[][] solve(Maze maze) {
        long startTime = System.nanoTime();
        int n = maze.getSize();
        grid = new int[n][n];
        distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = maze.getLayout()[i][j];
            }
        }
        bFS();
        pickyBFS(n - 2, n - 2);
        long endTime = System.nanoTime();
        time = endTime - startTime;
        
        return grid;
    }
    
    /**
     * Normaali leveyshaku. Etsii matkat lähtöpisteestä kaikkiin
     * pisteisiin.
     */
    public void bFS() {
        PairQueue queue = new PairQueue(5000);
        queue.enqueue(new int[]{1, 1});
        grid[1][1] = 3;
        distance[1][1] = 0;
        int[] currentPair = new int[]{1, 1};
        while (!queue.empty()) {
            currentPair = queue.dequeue();
            int x = currentPair[0];
            int y = currentPair[1];
            if (grid[y + 1][x] == 0) {
                queue.enqueue(new int[]{x, y + 1});
                grid[y + 1][x] = 3;
                distance[y + 1][x] = distance[y][x] + 1;
            } 
            if (grid[y - 1][x] == 0) {
                queue.enqueue(new int[]{x, y - 1});
                grid[y - 1][x] = 3;
                distance[y - 1][x] = distance[y][x] + 1;
            } 
            if (grid[y][x + 1] == 0) {
                queue.enqueue(new int[]{x + 1, y});
                grid[y][x + 1] = 3;
                distance[y][x + 1] = distance[y][x] + 1;
            } 
            if (grid[y][x - 1] == 0) {
                queue.enqueue(new int[]{x - 1, y});
                grid[y][x - 1] = 3;
                distance[y][x - 1] = distance[y][x] + 1;
            } 
        }
    }
    
    /**
     * Muunneltu leveyshaku. Etsii maalipisteestä lähtien
     * kaikki sellaiset reitit, joilla etäisyys aina lyhenee
     * alkua kohden mennessä. Ne merkataan kakkosilla.
     * @param startX Maalipisteen x-koordinaatti
     * @param startY Maalipisteen y-koordinaatti
     */
    public void pickyBFS(int startX, int startY) {
        PairQueue queue = new PairQueue(5000);
        queue.enqueue(new int[]{startX, startY});
        grid[startX][startY] = 2;
        int[] currentPair = new int[]{startX, startY};
        while (!queue.empty()) {
            currentPair = queue.dequeue();    
            int x = currentPair[0];
            int y = currentPair[1];
            int currentDist = distance[y][x];
            if (distance[y + 1][x] == currentDist - 1 && grid[y + 1][x] != 1) {
                queue.enqueue(new int[]{x, y + 1});
                grid[y + 1][x] = 2;
            } 
            if (distance[y - 1][x] == currentDist - 1 && grid[y - 1][x] != 1) {
                queue.enqueue(new int[]{x, y - 1});
                grid[y - 1][x] = 2;
            } 
            if (distance[y][x + 1] == currentDist - 1 && grid[y][x + 1] != 1) {
                queue.enqueue(new int[]{x + 1, y});
                grid[y][x + 1] = 2;
            } 
            if (distance[y][x - 1] == currentDist - 1 && grid[y][x - 1] != 1) {
                queue.enqueue(new int[]{x - 1, y});
                grid[y][x - 1] = 2;
            } 
        }
    }
    
    /**
     * Palautta distance-taulukon arvon kohdasta (y, x).
     * Testausta varten.
     * @param x Palautettavan arvon sarake
     * @param y Palautettavan arvon rivi
     * @return Etäisyys, joka on merkattu annetuille koordinaateille
     */
    public int getDistanceTo(int x, int y) {
        return distance[y][x];
    }

    /**
     * Palauttaa ajan, joka meni viimeksi solve-metodin ajamiseen.
     * @return Aika, joka meni solve-metodiin nanosekunteina.
     */
    public long getTime() {
        return time;
    }
    
    /**
     * Palauttaa lopullisen merkatun polun pituuden.
     * @return Lopullisen merkatun polun pituus.
     */
    public int solutionLength() {
        int n = grid.length;
        int path = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    path++;
                }
            }
        }
        return path;
    }
    
}
