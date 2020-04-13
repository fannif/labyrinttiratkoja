
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.algorithms.ShortestPaths;
import labyrinthsolver.domain.algorithms.Sidewinder;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa ShortestPaths-luokan toimintaa.
 * Käyttää apuna sokkeloa m, joka  on generoitu Sidewinderilla.
 */
public class ShortestPathsTest {
    
    Maze m;
    ShortestPaths sp;
    Sidewinder sidew;
    
    public ShortestPathsTest() {
    }
    
    @Before
    public void setUp() {
        m = new Maze(21);
        sidew = new Sidewinder();
        sidew.generate(m);
        sp = new ShortestPaths();
    }
    
    @Test
    public void BFSFindsShortestRoute() {
        m = new Maze(5);
        m.setLayout(new int[][] {
        {1, 1, 1, 1, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1}
        });
        
        sp.solve(m);
        assertTrue(sp.getDistanceTo(3, 3) == 4);
    }
    
    @Test
    public void pickyBFSMarksGoalWithTwo() {
        m.setLayout(sp.solve(m));
        assertTrue(m.getLayout()[19][19] == 2);
    }
    
    @Test
    public void pickyBFSGetsBackToStart() {
        m.setLayout(sp.solve(m));
        assertTrue(m.getLayout()[1][1] == 2);
    }
    
    @Test
    public void timeIsZeroBeforeSolving() {
        assertTrue(sp.getTime() == 0);
    }
    
    @Test
    public void solutionLengthIsPlausible() {
        sp.solve(m);
        int n = m.getSize();
        int length = sp.solutionLength();
        assertTrue(0 <= length && length < n * n);
    }
    
}
