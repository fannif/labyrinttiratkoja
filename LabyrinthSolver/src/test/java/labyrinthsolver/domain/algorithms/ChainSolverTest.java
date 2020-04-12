
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.algorithms.Sidewinder;
import labyrinthsolver.domain.algorithms.ChainSolver;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Testiluokka testaa ChainSolverin toimintaa.
 * Apuna käytetään sokkeloa m sekä Sidewinderia, jolla voidaan
 * generoida sokkelolle pohja, jonka ratkaisemista testataan.
 */
public class ChainSolverTest {
    Maze m;
    ChainSolver cs;
    Sidewinder sidew;
    
    public ChainSolverTest() {
    }
    
    @Before
    public void setUp() {
        m = new Maze(21);
        sidew = new Sidewinder();
        sidew.generate(m);
        cs = new ChainSolver();
    }
    
    @Test
    public void  pathStraightTrhoughColorsDiagonalPathWallsWith4AndPathWith3() {
        int[][] maze = new int[][]{
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,1,0,1},
                {1,0,0,0,1},
                {1,1,1,1,1}
        };
        
        cs.setGrid(maze);
        
        int n = 5;
        boolean works = true;
        
        cs.pathStraightThrough();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (i == j || i == j + 1) {
                    if (maze[i][j] == 0 && cs.getGrid()[i][j] != 3) {
                        works = false;
                        break;
                    }
                    if (maze[i][j] == 1 && cs.getGrid()[i][j] != 4) {
                        works = false;
                        break;
                    }
                }
            }
        }
        assertTrue(works);
    }
    
    @Test
    public void pathStraightThroughColorsDiagonallyOnly() {
        int[][] maze = new int[][]{
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,1,0,1},
                {1,0,0,0,1},
                {1,1,1,1,1}
        };
        
        cs.setGrid(maze);
        
        int n = 5;
        boolean works = true;
        
        cs.pathStraightThrough();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (i != j && i != j + 1) {
                    if (cs.getGrid()[i][j] == 3 || cs.getGrid()[i][j] == 4) {
                        works = false;
                    }
                }
            }
        }
        assertTrue(works);
    }
    
    @Test
    public void inTheEndThereAreNoFours() {
        m.setLayout(cs.solve(m));
        int n = m.getSize();
        boolean works = true;
        
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (m.getLayout()[i][j] == 4) {
                    works = false;
                }
            }
        }
        assertTrue(works);
    }
    
    @Test
    public void farEndCornerWallsAreNotTouched() {
        m.setLayout(cs.solve(m));
        assertTrue(m.getFromCoordinates(0, 0) == 1 && m.getFromCoordinates(m.getSize() - 1, m.getSize() - 1) == 1);
    }
    
    @Test
    public void startIsPartOfPath() {
        m.setLayout(cs.solve(m));
        assertTrue(m.getFromCoordinates(1, 1) == 2);
    }
    
    @Test
    public void solveFindsGoal() {
        m.setLayout(cs.solve(m));
        assertTrue(m.getFromCoordinates(m.getSize() - 2, m.getSize() - 2) == 2);
    }
}
