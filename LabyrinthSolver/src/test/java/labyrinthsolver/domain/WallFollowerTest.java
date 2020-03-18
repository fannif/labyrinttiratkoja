
package labyrinthsolver.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa WallFollower-algoritmin toteuttavaa luokkaa.
 * Hyödyntää sokkeloa, joka on luotu Sidewinderilla.
 */
public class WallFollowerTest {
    
    Maze m;
    WallFollower wf;
    Sidewinder sidew;
    
    public WallFollowerTest() {
    }
    
    @Before
    public void setUp() {
        m = new Maze(21);
        sidew = new Sidewinder();
        sidew.generate(m);
        wf = new WallFollower();
    }
    
    @Test
    public void turnLeftVahentaaSuunnanNumeroaYhdella() {
        wf.setDirection(2);
        wf.turnLeft();
        assertTrue(wf.getDirection() == 1);
    }
    
    @Test
    public void turnLeftPalauttaaSuunnanKolmeenJosKaannytaanNollasta() {
        wf.setDirection(0);
        wf.turnLeft();
        assertTrue(wf.getDirection() == 3);
    }
    
    @Test
    public void turnRightKasvattaaSuunnanNumeroaYhdella() {
        wf.setDirection(0);
        wf.turnRight();
        assertTrue(wf.getDirection() == 1);
    }
    
    @Test
    public void turnRightSiirtyyKolmosestaNollaan() {
        wf.setDirection(3);
        wf.turnRight();
        assertTrue(wf.getDirection() == 0);
    }
    
    @Test
    public void goingUpKaantyyVasemmalleEnsisijaisesti() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(2);
        wf.setY(2);
        wf.goingUp();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 2);
    }
    
    @Test
    public void goingDownKaantyyVasemmalleEnsisijaisesti() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingDown();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 1);
    }
    
    @Test
    public void goingLeftKaantyyVasemmalleEnsisijaisesti() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingLeft();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 2);
    }
    
    @Test
    public void goingRightKaantyyVasemmalleEnsisijaisesti() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(2);
        wf.setY(2);
        wf.goingRight();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 1);
    }
    
    @Test
    public void goingUpMeneeToissijaisestiSuoraan() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(2);
        wf.setY(1);
        wf.goingUp();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 1);
    }
    
    @Test
    public void goingDownMeneeToissijaisestiSuoraan() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(1);
        wf.setY(2);
        wf.goingDown();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 2);
    }
    
    @Test
    public void goingLeftMeneeToissijaisestiSuoraan() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(2);
        wf.setY(2);
        wf.goingLeft();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 2);
    }
    
    @Test
    public void goingRightMeneeToissijaisestiSuoraan() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingRight();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 1);
    }
    
    @Test
    public void goingUpKaantyyYmpariJosMuutSuunnatEivatMahdollisia() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingUp();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 1);
    }
    
    @Test
    public void goingDownKaantyyYmpariJosMuutSuunnatEivatMahdollisia() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(2);
        wf.setY(1);
        wf.goingDown();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 1);
    }
    
    @Test
    public void goingLeftKaantyyYmpariJosMuutSuunnatEivatMahdollisia() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingLeft();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 2);
    }
    
    @Test
    public void goingRightKaantyyYmpariJosMuutSuunnatEivatMahdollisia() {
        int[][] sokkelo = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(sokkelo);
        
        wf.setX(2);
        wf.setY(1);
        wf.goingRight();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 1);
    }
    
    @Test
    public void solveOnOllutAlkusolmussa() {
        m.setLayout(wf.solve(m));
        assertTrue(m.getLayout()[1][1] == 2);
    }
    
    @Test
    public void solvePaaseeMaaliinAsti() {
        m.setLayout(wf.solve(m));
        int n = m.getLayout().length;
        assertTrue(m.getLayout()[n - 2][n - 2] == 2);
    }
    
}
