
package labyrinthsolver.domain;

public class ShortestPaths {
    
    private int[][] grid;
    private int[][] distance;
    
    public ShortestPaths() {
    }
    
    public int[][] solve(Maze maze) {
        int n = maze.getSize();
        grid = new int[n][n];
        distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = maze.getLayout()[i][j];
            }
        }
        BFS();
        pickyBFS(n - 2, n - 2);
        
        return grid;
    }
    
    public void BFS() {
        PairQueue queue = new PairQueue(5000);
        queue.enqueue(new Pair(1, 1));
        grid[1][1] = 3;
        distance[1][1] = 0;
        Pair current = new Pair(1, 1);
        while (!queue.empty()) {
            current = queue.dequeue();
            int x = current.getX();
            int y = current.getY();
            if (grid[y + 1][x] == 0) {
                queue.enqueue(new Pair(x, y + 1));
                grid[y + 1][x] = 3;
                distance[y + 1][x] = distance[y][x] + 1;
            } 
            if (grid[y - 1][x] == 0) {
                queue.enqueue(new Pair(x, y - 1));
                grid[y - 1][x] = 3;
                distance[y - 1][x] = distance[y][x] + 1;
            } 
            if (grid[y][x + 1] == 0) {
                queue.enqueue(new Pair(x + 1, y));
                grid[y][x + 1] = 3;
                distance[y][x + 1] = distance[y][x] + 1;
            } 
            if (grid[y][x - 1] == 0) {
                queue.enqueue(new Pair(x - 1, y));
                grid[y][x - 1] = 3;
                distance[y][x - 1] = distance[y][x] + 1;
            } 
        }
    }
    
    public void pickyBFS(int startX, int startY) {
        PairQueue queue = new PairQueue(5000);
        queue.enqueue(new Pair(startX, startY));
        grid[startX][startY] = 2;
        Pair current = new Pair(startX, startY);
        while (!queue.empty()) {
            current = queue.dequeue();    
            int x = current.getX();
            int y = current.getY();
            int currentDist = distance[y][x];
            if (distance[y + 1][x] == currentDist - 1 && grid[y + 1][x] != 1) {
                queue.enqueue(new Pair(x, y + 1));
                grid[y + 1][x] = 2;
            } 
            if (distance[y - 1][x] == currentDist - 1 && grid[y - 1][x] != 1) {
                queue.enqueue(new Pair(x, y - 1));
                grid[y - 1][x] = 2;
            } 
            if (distance[y][x + 1] == currentDist - 1 && grid[y][x + 1] != 1) {
                queue.enqueue(new Pair(x + 1, y));
                grid[y][x + 1] = 2;
            } 
            if (distance[y][x - 1] == currentDist - 1 && grid[y][x - 1] != 1) {
                queue.enqueue(new Pair(x - 1, y));
                grid[y][x - 1] = 2;
            } 
        }
    }
    
}
