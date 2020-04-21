
package labyrinthsolver.domain.utils;

import labyrinthsolver.domain.algorithms.ChainSolver;
import labyrinthsolver.domain.algorithms.Kruskal;
import labyrinthsolver.domain.algorithms.RecursiveDivision;
import labyrinthsolver.domain.algorithms.ShortestPaths;
import labyrinthsolver.domain.algorithms.Sidewinder;
import labyrinthsolver.domain.algorithms.WallFollower;

/**
 * Luokka, jolla voidaan tehdä algoritmeille suorituskykytestausta.
 */
public class Tester {
    
    private Maze maze;
    private Sidewinder sidew;
    private ChainSolver chains;
    private Kruskal kruskal;
    private RecursiveDivision recursived;
    private ShortestPaths shortestp;
    private WallFollower wallf;
    private int[] testSizes;
    
    
    /**
     * Kostruktori.
     */
    public Tester() {
        maze = new Maze(9);
        sidew = new Sidewinder();
        chains = new ChainSolver();
        kruskal = new Kruskal();
        recursived = new RecursiveDivision();
        shortestp = new ShortestPaths();
        wallf = new WallFollower();
        testSizes = new int[]{9, 51, 99, 501, 999};
    }
    
    /**
     * Ajaa suorituskykytestaukset kaikille kuudelle algoritmille.
     */
    public void runAll() {
        System.out.println("Testing Sidewinder...");
        testSidewinder();
        System.out.println("\nTesting Recursive division...");
        testRecursiveDivision();
        System.out.println("\nTesting Wall follower...");
        testWallFollower();
        System.out.println("\nTesting Shortest paths...");
        testShortestPaths();
        System.out.println("\nTesting Chain solver...");
        testChainSolver();
        System.out.println("\nTesting Kruskal...");
        testKruskal();
        System.out.println("\nDone.");
        
    }
    
    /**
     * Suorituskykytestaus Sidewinderille.
     * Testaa keskimääräisen ajan, joka menee eri kokoisten sokkeloiden generointiin.
     */
    public void testSidewinder() {
        long sum = 0;
        for (int i = 0; i < testSizes.length; i++) {
            sum = 0;
            for (int j = 0; j < 100; j++)  {
                maze = new Maze(testSizes[i]);
                sidew.generate(maze);
                sum += sidew.getTime();
            }
            long average = sum / 100;
            System.out.println("Maze size: " + testSizes[i] + "-by-" + testSizes[i] + ", Average time to generate: " + average + " ns");
        }  
    }
    
    /**
     * Suorituskykytestaus WallFollowerille.
     * Testaa keskimääräiset ajat, jotka menevät eri kokoisten sokkeloiden ratkaisemiseen.
     */
    public void testWallFollower() {
        long sum = 0;
        for (int i = 0; i < testSizes.length; i++) {
            sum = 0;
            maze = new Maze(testSizes[i]);
            for (int j = 0; j < 100; j++)  {
                recursived.generate(maze);
                wallf.solve(maze);
                sum += wallf.getTime();
            }
            long average = sum / 100;
            System.out.println("Maze size: " + testSizes[i] + "-by-" + testSizes[i] + ", Average time to solve: " + average + " ns");
        }
    }
    
    /**
     * Suorituskykytestaus ShortestPaths-algoritmille.
     * Testaa keskimääräiset ajat, jotka menevät eri kokoisten sokkeloiden ratkaisemiseen.
     */
    public void testShortestPaths() {
        long sum = 0;
        for (int i = 0; i < testSizes.length; i++) {
            sum = 0;
            maze = new Maze(testSizes[i]);
            for (int j = 0; j < 100; j++)  {
                recursived.generate(maze);
                shortestp.solve(maze);
                sum += shortestp.getTime();
            }
            long average = sum / 100;
            System.out.println("Maze size: " + testSizes[i] + "-by-" + testSizes[i] + ", Average time to solve: " + average + " ns");
        }
    }
    
    /**
     * Suorituskykytestaus Kruskalille.
     * Testaa keskimääräiset ajat, jotka menevät eri kokoisten sokkeloiden generointiin.
     */
    public void testKruskal() {
        long sum = 0;
        for (int i = 0; i < testSizes.length; i++) {
            sum = 0;
            for (int j = 0; j < 100; j++)  {
                maze = new Maze(testSizes[i]);
                kruskal.generate(maze);
                sum += kruskal.getTime();
            }
            long average = sum / 100;
            System.out.println("Maze size: " + testSizes[i] + "-by-" + testSizes[i] + ", Average time to generate: " + average + " ns");
        }
    }
    
    /**
     * Suorituskykytestaus ChainSolverille.
     * Testaa keskimääräiset ajat, jotka menevät eri kokoisten sokkeloiden ratkaisemiseen.
     */
    public void testChainSolver() {
        long sum = 0;
        for (int i = 0; i < testSizes.length; i++) {
            sum = 0;
            maze = new Maze(testSizes[i]);
            for (int j = 0; j < 100; j++)  {
                recursived.generate(maze);
                chains.solve(maze);
                sum += chains.getTime();
            }
            long average = sum / 100;
            System.out.println("Maze size: " + testSizes[i] + "-by-" + testSizes[i] + ", Average time to solve: " + average + " ns");
        }
    }
    
    /**
     * Suorituskykytestaus RecursiveDivision-algoritmille.
     * Testaa keskimääräiset ajat, jotka menevät eri kokoisten sokkeloiden generointiin.
     */
    public void testRecursiveDivision() {
        long sum = 0;
        for (int i = 0; i < testSizes.length; i++) {
            sum = 0;
            for (int j = 0; j < 100; j++)  {
                maze = new Maze(testSizes[i]);
                recursived.generate(maze);
                sum += recursived.getTime();
            }
            long average = sum / 100;
            System.out.println("Maze size: " + testSizes[i] + "-by-" + testSizes[i] + ", Average time to generate: " + average + " ns");
        }
    }
    
}
