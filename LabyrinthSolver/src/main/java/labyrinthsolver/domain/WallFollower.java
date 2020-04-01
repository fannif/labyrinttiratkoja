
package labyrinthsolver.domain;

/**
* Luokka ratkaisee sokkelon Wall follower -algoritmilla.
* Tässä simuloidaan ihmist, joka ratkaisisi sokkeloa
* niin, että pitäisi aina kättä kiinni vasemmalla
* olevassa seinässä.
* 
* Muuttuja direction kuvaa senhetkistä etenemissuuntaa.
* Tässä 0: oikealle, 1: alas, 2: vasemmalle ja 3: ylös.
* 
* Grid on taulukko, jossa on labyrinttipohja.
*/
public class WallFollower {
    
    private int direction = 0;
    private int[][] grid;
    private int x;
    private int y;
    private long time = 0;
    
    /**
     * Konstruktorimetodi
     */
    public WallFollower() {
    }
    
    /**
     * Metodi suorittaa wall follower -algorimin sokkelon
     * ratkaisemiseksi.
     * Ratkaisee labyrintin ja pitää kirjaa
     * käydystä retistä.
     * Merkataan aina käyty paikka 2:lla.
     * 
     * @param maze Ratkaistava sokkelo.
     * @return Ratkaistun sokkelon sokkelopohja
     */
    public int[][] solve(Maze maze) {
        long startTime = System.nanoTime();
        int n = maze.getSize();
        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = maze.getLayout()[i][j];
            }
        }
        
        x = 1;
        y = 1;
        grid[x][y] = 2;
        direction = 0;
        
        while (true) {
            if (x == n - 2 && y == n - 2) {
                break;
            }
            if (direction == 0) {
                goingRight();
            } else if (direction == 1) {
                goingDown();
            } else if (direction == 2) {
                goingLeft();
            } else if (direction == 3) {
                goingUp();
            } else {
                //Jotain on mennyt pieleen
                break;
            }
        }
        long endTime = System.nanoTime();
        time = endTime - startTime;
        return grid;
    }
    
    /**
     * Muuta suunta niin, että käännytään oikealle
     */
    public void turnRight() {
        direction = (direction + 1) % 4;
    }
    
    /**
     * Muuta suunta niin, että käännytään vasemmalle
     */
    public void turnLeft() {
        if (direction == 0) {
            direction = 3;
        } else {
            direction--;
        }
    }
    
    /**
     * Edetään seuraavaan ruutuun oikealle mennessä.
     * Jos mahdollista, niin vasemmalle.
     * Muuten jos mahdollista, niin eteenpäin.
     * Muuten jos mahdollista, niin oikealle.
     * Muuten taaksepäin.
     * Näin "pidetään kiinni" aina vasemmanpuoleisesta seinästä.
     */
    public void goingRight() {
        if (grid[x][y - 1] != 1) {
            y = y - 1;
            grid[x][y] = 2;
            turnLeft();
        } else if (grid[x + 1][y] != 1) {
            x = x + 1;
            grid[x][y] = 2;
        } else if (grid[x][y + 1] != 1) {
            y = y + 1;
            grid[x][y] = 2;
            turnRight();
        } else if (grid[x - 1][y] != 1) {
            x = x - 1;
            turnLeft();
            turnLeft();
        }
    }
    
    /**
     * Edetään seuraavaan ruutuun alas mennessä.
     * Jos mahdollista, niin vasemmalle.
     * Muuten jos mahdollista, niin eteenpäin.
     * Muuten jos mahdollista, niin oikealle.
     * Muuten taaksepäin.
     * Näin "pidetään kiinni" aina vasemmanpuoleisesta seinästä.
     */
    public void goingDown() {
        if (grid[x + 1][y] != 1) {
            x = x + 1;
            grid[x][y] = 2;
            turnLeft();
        } else if (grid[x][y + 1] != 1) {
            y = y + 1;
            grid[x][y] = 2;
        } else if (grid[x - 1][y] != 1) {
            x = x - 1;
            grid[x][y] = 2;
            turnRight();
        } else if (grid[x][y - 1] != 1) {
            y = y - 1;
            turnLeft();
            turnLeft();
        }
    }
    
    /**
     * Edetään seuraavaan ruutuun vasemmalle mennessä.
     * Jos mahdollista, niin vasemmalle.
     * Muuten jos mahdollista, niin eteenpäin.
     * Muuten jos mahdollista, niin oikealle.
     * Muuten taaksepäin.
     * Näin "pidetään kiinni" aina vasemmanpuoleisesta seinästä.
     */
    public void goingLeft() {
        if (grid[x][y + 1] != 1) {
            y = y + 1;
            grid[x][y] = 2;
            turnLeft();
        } else if (grid[x - 1][y] != 1) {
            x = x - 1;
            grid[x][y] = 2;
        } else if (grid[x][y - 1] != 1) {
            y = y - 1;
            grid[x][y] = 2;
            turnRight();
        } else if (grid[x + 1][y] != 1) {
            x = x + 1;
            turnLeft();
            turnLeft();
        }
    }
    
    /**
     * Edetään seuraavaan ruutuun ylös mennessä.
     * Jos mahdollista, niin vasemmalle.
     * Muuten jos mahdollista, niin eteenpäin.
     * Muuten jos mahdollista, niin oikealle.
     * Muuten taaksepäin.
     * Näin "pidetään kiinni" aina vasemmanpuoleisesta seinästä.
     */
    public void goingUp() {
        if (grid[x - 1][y] != 1) {
            x = x - 1;
            grid[x][y] = 2;
            turnLeft();
        } else if (grid[x][y - 1] != 1) {
            y = y - 1;
            grid[x][y] = 2;
        } else if (grid[x + 1][y] != 1) {
            x = x + 1;
            grid[x][y] = 2;
            turnRight();
        } else if (grid[x][y + 1] != 1) {
            y = y + 1;
            turnLeft();
            turnLeft();
        }
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid.clone();
    }
    
    /**
     * Palauttaa ajan, joka meni viimeksi solve-metodin ajamiseen.
     * @return Aika, joka meni solve-metodiin nanosekunteina.
     */
    public long getTime() {
        return time;
    }
}
