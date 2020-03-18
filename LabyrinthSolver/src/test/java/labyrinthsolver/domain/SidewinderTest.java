
package labyrinthsolver.domain;

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
    public void generointiJattaaLaidatSeiniksi() {
        boolean onSeina = true;
        int n = m.getSize();
        m.setLayout(sw.generate(m));
        
        for (int i = 0; i < n; i++) {
            if (m.getLayout()[i][0] != 1) {
                onSeina = false;
            }
            if (m.getLayout()[0][i] != 1) {
                onSeina = false;
            }
            if (m.getLayout()[i][n - 1] != 1) {
                onSeina = false;
            }
            if (m.getLayout()[n - 1][i] != 1) {
                onSeina = false;
            }
        }
        
        assertTrue(onSeina);
    }
    
    @Test
    public void lahtoruutuEiOleSeina() {
        m.setLayout(sw.generate(m));
        assertTrue(m.getLayout()[1][1] == 0);
    }
    
    @Test
    public void maaliruutuEiOleSeina() {
        int n = m.getSize();
        m.setLayout(sw.generate(m));
        assertTrue(m.getLayout()[n - 2][n - 2] == 0);
    }
    
    @Test
    public void luotuLabyrinttiOnMahdollistaRatkaista() {
        int n = m.getSize();
        m.setLayout(sw.generate(m));
        WallFollower wf = new WallFollower();
        
        m.setLayout(wf.solve(m));
        
        assertTrue(m.getLayout()[n - 2][n - 2] == 2);
    }
}
