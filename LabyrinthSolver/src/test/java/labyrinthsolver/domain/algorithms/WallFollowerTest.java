
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
    public void turnLeftReducesDirectionNumberByOne() {
        wf.setDirection(2);
        wf.turnLeft();
        assertTrue(wf.getDirection() == 1);
    }
    
    @Test
    public void turnLeftSetsDirectionToThreeIfTurningFromZero() {
        wf.setDirection(0);
        wf.turnLeft();
        assertTrue(wf.getDirection() == 3);
    }
    
    @Test
    public void turnRightIncreasesDirectionNumberByOne() {
        wf.setDirection(0);
        wf.turnRight();
        assertTrue(wf.getDirection() == 1);
    }
    
    @Test
    public void turnRightSetsDirectionNumberFromThreeToZero() {
        wf.setDirection(3);
        wf.turnRight();
        assertTrue(wf.getDirection() == 0);
    }
    
    @Test
    public void goingUpPrimarilyTurnsLeft() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(2);
        wf.setY(2);
        wf.goingUp();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 2);
    }
    
    @Test
    public void goingDownPrimarilyTurnsLeft() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingDown();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 1);
    }
    
    @Test
    public void goingLeftPrimarilyTurnsLeft() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingLeft();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 2);
    }
    
    @Test
    public void goingRightPrimarilyTurnsLeft() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(2);
        wf.setY(2);
        wf.goingRight();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 1);
    }
    
    @Test
    public void goingUpSecondarilyKeepsGoingStraight() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(2);
        wf.setY(1);
        wf.goingUp();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 1);
    }
    
    @Test
    public void goingDownSecondarilyKeepsGoingStraight() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(1);
        wf.setY(2);
        wf.goingDown();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 2);
    }
    
    @Test
    public void goingLeftSecondarilyKeepsGoingStraight() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(2);
        wf.setY(2);
        wf.goingLeft();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 2);
    }
    
    @Test
    public void goingRightSecondarilyKeepsGoingStraight() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingRight();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 1);
    }
    
    @Test
    public void goingUpTurnsAroundIfOtherDirectionsNotPossible() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingUp();
        
        assertTrue(wf.getX() == 2 && wf.getY() == 1);
    }
    
    @Test
    public void goingDownTurnsAroundIfOtherDirectionsNotPossible() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(2);
        wf.setY(1);
        wf.goingDown();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 1);
    }
    
    @Test
    public void goingLeftTurnsAroundIfOtherDirectionsNotPossible() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(1);
        wf.setY(1);
        wf.goingLeft();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 2);
    }
    
    @Test
    public void goingRightTurnsAroundIfOtherDirectionsNotPossible() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        
        wf.setGrid(maze);
        
        wf.setX(2);
        wf.setY(1);
        wf.goingRight();
        
        assertTrue(wf.getX() == 1 && wf.getY() == 1);
    }
    
    @Test
    public void solveHasBeenAtTheStartPoint() {
        m.setLayout(wf.solve(m));
        assertTrue(m.getLayout()[1][1] == 2);
    }
    
    @Test
    public void solveMakesItToTheGoalPoint() {
        m.setLayout(wf.solve(m));
        int n = m.getLayout().length;
        assertTrue(m.getLayout()[n - 2][n - 2] == 2);
    }
    
    @Test
    public void timeIsZeroBeforeSolving() {
        assertTrue(wf.getTime() == 0);
    }
    
}
