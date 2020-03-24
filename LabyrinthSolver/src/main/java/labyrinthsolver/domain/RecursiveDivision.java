
package labyrinthsolver.domain;

import java.util.Random;

public class RecursiveDivision {
    
    private int orientation = 0;
    private int width;
    private int height;
    private int grid[][];
    private Random random = new Random();
    
    public RecursiveDivision() {
    }
    
    public int[][] generate(Maze maze) {
        int n = maze.getSize();
        maze.initialize();
        width = n;
        height = n;
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = maze.getLayout()[i][j];
            }
        }
        divide(width, height, new Pair(0, 0));
        return grid;
    }
    
    public void newOrientation(int height, int width) {
        if (height > width) {
            orientation = 0;
        } else {
            orientation = 1;
        }
    }
    
    public void divide(int width, int height, Pair offset) {
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
                grid[wall + offset.getY()][i + offset.getX()] = 1;
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
                grid[i + offset.getY()][wall + offset.getX()] = 1;
            }
        }
        
        if (orientation == 0) {
            divide(width, wall, offset);
            divide(width, height - wall, new Pair(offset.getX(), offset.getY() + wall));
        } else {
            divide(wall, height, offset);
            divide(width - wall, height, new Pair(offset.getX() + wall, offset.getY()));
        }
    }
    
}
