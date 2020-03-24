
package labyrinthsolver.domain;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Testiluokka joka testaa RecursiveDivision-luokan toimintaa.
 * Tässä hyödynnetään Wall Follower -algoritmia, jotta voidaan varmistaa
 * labyrinttien ratkottavuus. Apuna on myös sokkelo m, joka on
 * generoinnin pohjana.
 */
public class RecursiveDivisionTest {
    
    private RecursiveDivision rd;
    private WallFollower wf;
    private Maze m;
    
    public RecursiveDivisionTest() {
    }
    
    @Before 
    public void setUp() {
        m = new Maze(15);
        rd = new RecursiveDivision();
        wf = new WallFollower();
    }
    
    @Test
    public void orientationIsVerticalIfHeghtIsEqualOrSmallerThanWidth() {
        rd.newOrientation(1, 1);
        assertTrue(rd.getOrientation() == 1);
    }
    
    @Test
    public void orientationIsHorizontalIfHeghtIsGreaterWidth() {
        rd.newOrientation(2, 1);
        assertTrue(rd.getOrientation() == 0);
    }
    
    @Test
    public void generateSetsOuterWalls() {
        boolean wall = true;
        int n = m.getSize();
        m.setLayout(rd.generate(m));
        
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
        m.setLayout(rd.generate(m));
        assertTrue(m.getLayout()[1][1] == 0);
    }
    
    @Test
    public void goalPointIsNotAWall() {
        int n = m.getSize();
        m.setLayout(rd.generate(m));
        assertTrue(m.getLayout()[n - 2][n - 2] == 0);
    }
    
    @Test
    public void generatedMazeCanBeSolved() {
        rd.generate(m);
        m.setLayout(wf.solve(m));
        assertTrue(m.getLayout()[13][13] == 2);
    }
}
