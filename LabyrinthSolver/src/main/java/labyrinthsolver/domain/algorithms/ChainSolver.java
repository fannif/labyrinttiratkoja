
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;

/**
 * Luokka toteuttaa labyrintin ratkaisun ketjualgoritmilla.
 * Siinä hyödynnetään rekursiota, joten sitä varten muistissa pidettävät
 * arvot y:lle, x:lle, pituudelle ja onnistumiselle ja kuljetulle matkalle
 * on asetettu luokan muuttujiksi endY, endX, chainLength, success ja travelled.
 */
public class ChainSolver {
    
    private int[][] grid;
    private boolean[][] path;
    private long time;
    private int n;
    private int chainLength;
    private int endY;
    private int endX;
    private int success;
    private int[][] travelled;
    
    /**
     * Konstruktorimetodi.
     */
    public ChainSolver() {
    }
    
    /**
     * Ratkaisee labyrintin käyttämällä ketjualgoritmia.
     * Ensin sokkelopohja kopioidaan talteen, sitten siihen piirretään
     * suora reitti sokkelon läpi, ja sitten lähdetään kulkemaan reittiä, kiertämään reitille osuneita seiniä.
     * Lopuksi merkataan lopullinen polku ja palautetaan sokkelon seinät ennalleen.
     * @param maze Ratkaistava sokkelo.
     * @return Ratkaistu sokkelopohja.
     */
    public int[][] solve(Maze maze) {
        long startTime = System.nanoTime();
        n = maze.getSize();
        grid = new int[n][n];
        path = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = maze.getLayout()[i][j];
            }
        }
        pathStraightThrough();
        int[] newPath = new int[3];
        int i = 1;
        int j = 1;
        path[i][j] = true;
        grid[i][j] = 0;
        while (i < n - 2 || j < n - 2) {
            if (i > j) {
                if (grid[i][j + 1] == 4 || grid[i][j + 1] == 1) {
                    newPath = findWayAround(i, j);
                    i = newPath[0];
                    j = newPath[1];
                    grid[i][j] = 0;
                } else {
                    grid[i][j + 1] = 0;
                    path[i][j + 1] = true;
                    j++;
                }
            }
            if (i == n - 2 && j == n - 2) {
                break;
            }
            if (i == j) {
                if (grid[i + 1][j] == 4 || grid[i + 1][j] == 1) {
                    newPath = findWayAround(i, j);
                    i = newPath[0];
                    j = newPath[1];
                    grid[i][j] = 0;
                } else {
                    grid[i + 1][j] = 0;
                    path[i + 1][j] = true;
                    i++;
                }
            }
        }
        wallsToNormal();
        paintPath();
        long endTime = System.nanoTime();
        time = endTime - startTime;
        
        return grid;
    }
    
    /**
     * Piirtää sokkelopohjaan polun alusta loppuun.
     * Polulla seinät merkitään nelosella ja kulkuväylä kolmosella.
     */
    public void pathStraightThrough() {
        n = grid.length;
        for (int i = 1; i < n - 2; i++) {
            if (grid[i][i] == 1) {
                grid[i][i] = 4;
            } else {
                grid[i][i] = 3;
            }
            if (grid[i + 1][i] == 1) {
                grid[i + 1][i] = 4;
            } else {
                grid[i + 1][i] = 3;
            }
        }
        grid[n - 2][n - 2] = 3;
    }
    
    /**
     * Palauttaa seinät normaaleiksi seiniksi.
     * Siis ne seinät, jotka oli merkattu nelosella, vaihdetaan ykkösiksi.
     */
    public void wallsToNormal() {
        n = grid.length;
        for (int i = 1; i < n - 2; i++) {
            if (grid[i][i] == 4) {
                grid[i][i] = 1;
            }
            if (grid[i + 1][i] == 4) {
                grid[i + 1][i] = 1;
            }
        }
    }
    
    /**
     * Merkkaa path-muuttujan perusteella algoritmin löytämän polun
     * sokkelopohjaan.
     */
    public void paintPath() {
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (path[i][j]) {
                    grid[i][j] = 2;
                }
            }
        }
    }
    
    /**
     * Etsii seinään törmätessä reitin seinän ympäri takaisin kolmosella merkatulle kulkuväylälle.
     * @param y Rivi, jolta kiertäminen aloitetaan.
     * @param x Sarake, josta kiertäminen aloitetaan.
     * @return Palauta nelikko, jossa on annettu lyhyemmän kiertoreitin 
     * koordinaatit, onnistumistieto ja reitin pituus.
     */
    public int[] findWayAround(int y, int x) {
        success = 0;
        travelled = new int[n][n];
        chainLength = 0;
        goUp(y, x, 0);
        int firstX = endX;
        int firstY = endY;
        int length = chainLength;
        int firstSuccess = success;
        travelled = new int[n][n];
        success = 0;
        chainLength = 0;
        goDown(y, x, 0);
        
        if (length < chainLength && firstSuccess == 1) {
            return new int[]{firstY, firstX, firstSuccess, length};
        } else if (success == 1) {
            return new int[]{endY, endX, success, chainLength};
        } else {
            return  new int[]{firstY, firstX, firstSuccess, length};
        }
    }
    
    /**
     * Etsii reittiä takaisin merkatulle polulle pyrkien pystyessään menemään ylös ja oikealle.
     * @param y Tämänhetkinen rivi.
     * @param x Tämänhetkinen sarake.
     * @param length Senhetkisen löydetyn reitin pituus.
     */
    public void goUp(int y, int x, int length) {
        if (success == 1) {
            return;
        }
        if (y < 1 || x < 1 || x > n - 2 || y > n - 2) {
            return;
        }
        if (grid[y][x] == 1 || grid[y][x] == 4 || travelled[y][x] > 0) {
            return;
        }      
        travelled[y][x]++;
        if (grid[y][x] == 3) {
            endX = x;
            endY = y;
            chainLength = length;
            success = 1;
            path[y][x] = true;
            return;
        }
        goUp(y - 1, x, length + 1);
        goUp(y, x + 1, length + 1);
        goUp(y, x - 1, length + 1);
        goUp(y + 1, x, length + 1);
        if (success == 1) {
            path[y][x] = true;
        }
    }
    
    /**
     * Etsii reittiä takaisin merkatulle polulle pyrkien pystyessään menemään alas ja vasemalle.
     * @param y Tämänhetkinen rivi.
     * @param x Tämänhetkinen sarake.
     * @param length Tällä hetkellä löydetyn reitin pituus.
     */
    public void goDown(int y, int x, int length) {
        if (success == 1) {
            return;
        }
        if (grid[y][x] == 1 || grid[y][x] == 4 || travelled[y][x] > 0) {
            return;
        }
        travelled[y][x]++;
        if (grid[y][x] == 3) {
            endX = x;
            endY = y;
            chainLength = length;
            success = 1;
            path[y][x] = true;
            return;
        }
        goDown(y + 1, x, length + 1);
        goDown(y, x - 1, length + 1);
        goDown(y, x + 1, length + 1);
        goDown(y - 1, x, length + 1);
        if (success == 1) {
            path[y][x] = true;
        }
    }
    
    /**
     * Palauttaa ajan, joka viimeksi on kulunut labyrintin ratkaisemiseen.
     * @return Ketjualgoritmin suoritukseen viimeksi kulunut aika nanosekunteina.
     */
    public long getTime() {
        return time;
    }
    
    /**
     * Palauttaa kopion tämänhetkisestä sokkelopohjasta.
     * @return Sokkelopohjan kopio.
     */
    public int[][] getGrid() {
        int[][] grid = new int[this.grid.length][this.grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = this.grid[i][j];
            }
        }
        return grid;
    }
    
    /**
     * Asettaa uuden sokkelopohjan tarkasteltavaksi.
     * Uusi pohja on annetun taulukon kopio.
     * @param grid Uusi sokkelopohja, joka kopioidaan.
     */
    public void setGrid(int[][] grid) {
        this.grid = new int[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }
    
    
    
}
