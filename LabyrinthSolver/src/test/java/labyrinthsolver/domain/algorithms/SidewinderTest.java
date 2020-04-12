
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.algorithms.WallFollower;
import labyrinthsolver.domain.algorithms.Sidewinder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa Sidewinder-generointilgoritmin toteuttavaa luokkaa.
 * Käyttää apuna sokkeloa m.
 */
public class SidewinderTest {
    
    Maze m;
    Sidewinder sw;
    
    public SidewinderTest() {
    }
    
    @Before
    public void setUp() {
        m = new Maze(21);
        sw = new Sidewinder();
    }
    
    @Test
    public void generateSetsOuterWalls() {
        boolean wall = true;
        int n = m.getSize();
        m.setLayout(sw.generate(m));
        
        for (int i = 0; i < n; i++) {
            if (m.getLayout()[i][0] != 1) {
                wall = false;
            }
            if (m.getLayout()[0][i] != 1) {
                wall = false;
            }
            if (m.getLayout()[i][n - 1] != 1) {
                wall = false;
            }
            if (m.getLayout()[n - 1][i] != 1) {
                wall = false;
            }
        }
        
        assertTrue(wall);
    }
    
    @Test
    public void startingPointIsNotAWall() {
        m.setLayout(sw.generate(m));
        assertTrue(m.getLayout()[1][1] == 0);
    }
    
    @Test
    public void goalPointIsNotAWall() {
        int n = m.getSize();
        m.setLayout(sw.generate(m));
        assertTrue(m.getLayout()[n - 2][n - 2] == 0);
    }
    
    @Test
    public void generatedMazeCanBeSolved() {
        int n = m.getSize();
        m.setLayout(sw.generate(m));
        WallFollower wf = new WallFollower();
        
        m.setLayout(wf.solve(m));
        
        assertTrue(m.getLayout()[n - 2][n - 2] == 2);
    }
    
    @Test
    public void timeIsZeroBeforeGenerating() {
        assertTrue(sw.getTime() == 0);
    }
}
