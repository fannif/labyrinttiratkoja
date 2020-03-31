
package labyrinthsolver.domain;

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
    
    public ChainSolver() {
        
    }
    
    public int[][] solve(Maze maze) {
        long startTime = System.currentTimeMillis();
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
     //   int[] secondPath = new int[3];
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
//                    firstPath = findWayAround(i, j, -1);
//                    secondPath = findWayAround(i, j, 1);
//                    if (firstPath[3] < secondPath[3] && firstPath[2] == 1) {
//                        i = firstPath[0];
//                        j = firstPath[1];
//                    } else if (secondPath[2] == 1) {
//                        i = secondPath[0];
//                        j = secondPath[1];
//                    } else {
//                        i = firstPath[0];
//                        j = firstPath[1];
//                    }
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
                } else {
                    grid[i + 1][j] = 0;
                    path[i + 1][j] = true;
                    i++;
                }
                System.out.println(i + ", " + j);
//                for (int x = 0; x < n; x++) {
//                    for (int y = 0; y < n; y++) {
//                        System.out.print(grid[y][x]);
//                    }
//                    System.out.println("");
//                }
            }
        }
        
        
        
        wallsToNormal();
        paintPath();
        
        for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        System.out.print(grid[y][x]);
                    }
                    System.out.println("");
                }
        
        return grid;
    }
    
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
    
    public void paintPath() {
        for (int i = 1; i < n - 2; i++) {
            for (int j = 1; j < n - 2; j++) {
                if (path[i][j]) {
                    grid[i][j] = 2;
                }
            }
        }
    }
    
    public int[] findWayAround(int y, int x) {
        success = 1;
        travelled = new int[n][n];
//        travelled[y][x] = 2;
        goUp(y, x, 0);
        int firstX = endX;
        int firstY = endY;
        int length = chainLength;
        int firstSuccess = success;
        travelled = new int[n][n];
        success = 1;
        goDown(y, x, 0);
        
        if (length < chainLength || firstSuccess == 1) {
            return new int[]{firstY, firstX, firstSuccess, length};
        } else if (success == 1) {
            return new int[]{endY, endX, success, chainLength};
        } else {
            return  new int[]{firstY, firstX, firstSuccess, length};
        }
//        while (true) {
//            if (grid[y + direction][x] == 3) {
//                y = y + direction;
//                grid[y][x] = 0;
//                path[y][x] = true;
//                length++;
//                break;
//            } else if (grid[y][x + direction] == 3) {
//                x = x + direction;
//                grid[y][x] = 0;
//                path[y][x] = true;
//                length++;
//                break;
//            } else if (grid[y][x - direction] == 3) {
//                x = x - direction;
//                grid[y][x] = 0;
//                path[y][x] = true;
//                length++;
//                break;
//            } else if (grid[y - direction][x] == 3) {
//                y = y - direction;
//                grid[y][x] = 0;
//                path[y][x] = true;
//                length++;
//                break;
//            } else if (grid[y + direction][x] == 0 && travelled[y + direction][x] < 1) {
//                y = y + direction;               
//                path[y][x] = true;
//                length++;
//                travelled[y][x]++;
//            } else if (grid[y][x + direction] == 0 && travelled[y][x + direction] < 1) {
//                x = x + direction;
//                path[y][x] = true;
//                length++;
//                travelled[y][x]++;
//            }  else if (grid[y][x - direction] == 0 && travelled[y][x - direction] < 1) {
//                x = x - direction;
//                path[y][x] = true;
//                length++;
//                travelled[y][x]++;
//            } else if (grid[y - direction][x] == 0 && travelled[y - direction][x] < 1) {
//                y = y - direction;
//                path[y][x] = true;
//                length++;
//                travelled[y][x]++;
//            } else if (grid[y + direction][x] == 0 && travelled[y + direction][x] < 2) {
//                y = y + direction;               
//                path[y][x] = false;
//                length--;
//                travelled[y][x]++;
//            } else if (grid[y][x + direction] == 0 && travelled[y][x + direction] < 2) {
//                x = x + direction;
//                path[y][x] = false;
//                length--;
//                travelled[y][x]++;
//            }  else if (grid[y][x - direction] == 0 && travelled[y][x - direction] < 2) {
//                x = x - direction;
//                path[y][x] = false;
//                length--;
//                travelled[y][x]++;
//            } else if (grid[y - direction][x] == 0 && travelled[y - direction][x] < 2) {
//                y = y - direction;
//                path[y][x] = false;
//                length--;
//                travelled[y][x]++;
//            } else {
//                // Ei löydy reittiä, koska ympärillä vain seiniä
//                // tai käytyjä kohtia.
//                success = 0;
//                break;
//            }
//        }
//        return new int[]{endY, endX, success, chainLength};
    }
    
    public void goUp(int y, int x, int length) {
        if (y < 1 || x < 1 || x > n - 2 || y > n - 2) {
            return;
        }
        if (grid[y][x] == 1 || grid[y][x] == 4 || travelled[y][x] == 1) {
            success = 0;
            return;
        }
        path[y][x] = true;
        travelled[y][x]++;
        if (grid[y][x] == 3) {
            endX = x;
            endY = y;
            chainLength = length;
            grid[y][x] = 0;
            return;
        }
        goUp(y + 1, x, length + 1);
        goUp(y, x + 1, length + 1);
        goUp(y, x - 1, length + 1);
        goUp(y - 1, x, length + 1);
    }
    
    public void goDown(int y, int x, int length) {
        if (y < 1 || x < 1 || x > n - 2 || y > n - 2) {
            return;
        }
        if (grid[y][x] == 1 || grid[y][x] == 4 || travelled[y][x] > 0) {
            return;
        }
        path[y][x] = true;
        travelled[y][x]++;
        if (grid[y][x] == 3) {
            endX = x;
            endY = y;
            chainLength = length;
            grid[y][x] = 0;
            return;
        }
        goDown(y - 1, x, length + 1);
        goDown(y, x - 1, length + 1);
        goDown(y, x + 1, length + 1);
        goDown(y + 1, x, length + 1);
    }
    
}
