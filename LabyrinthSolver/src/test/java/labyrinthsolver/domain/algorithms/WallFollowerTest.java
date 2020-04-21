
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa WallFollower-algoritmin toteuttavaa luokkaa.
 * Hyödyntää sokkeloa, joka on luotu Sidewinderilla.
 */
public class WallFollowerTest {
    
    Maze m;
    Maze m2;
    WallFollower wf;
    WallFollower wf2;
    Sidewinder sidew;
    
    public WallFollowerTest() {
    }
    
    @Before
    public void setUp() {
        m = new Maze(21);
        m2 = new Maze(4);
        sidew = new Sidewinder();
        sidew.generate(m);
        wf = new WallFollower();
        wf2 = new WallFollower();
        wf2.solve(m2);
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
        
        wf2.setGrid(maze);
        
        wf2.setX(2);
        wf2.setY(2);
        wf2.goingUp();
        
        assertTrue(wf2.getX() == 1 && wf2.getY() == 2);
    }
    
    @Test
    public void goingDownPrimarilyTurnsLeft() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(1);
        wf2.setY(1);
        wf2.goingDown();
        
        assertTrue(wf2.getX() == 2 && wf2.getY() == 1);
    }
    
    @Test
    public void goingLeftPrimarilyTurnsLeft() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(1);
        wf2.setY(1);
        wf2.goingLeft();
        
        assertTrue(wf2.getX() == 1 && wf2.getY() == 2);
    }
    
    @Test
    public void goingRightPrimarilyTurnsLeft() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(2);
        wf2.setY(2);
        wf2.goingRight();
        
        assertTrue(wf2.getX() == 2 && wf2.getY() == 1);
    }
    
    @Test
    public void goingUpSecondarilyKeepsGoingStraight() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(2);
        wf2.setY(1);
        wf2.goingUp();
        
        assertTrue(wf2.getX() == 1 && wf2.getY() == 1);
    }
    
    @Test
    public void goingDownSecondarilyKeepsGoingStraight() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(1);
        wf2.setY(2);
        wf2.goingDown();
        
        assertTrue(wf2.getX() == 2 && wf2.getY() == 2);
    }
    
    @Test
    public void goingLeftSecondarilyKeepsGoingStraight() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(2);
        wf2.setY(2);
        wf2.goingLeft();
        
        assertTrue(wf2.getX() == 1 && wf2.getY() == 2);
    }
    
    @Test
    public void goingRightSecondarilyKeepsGoingStraight() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(1);
        wf2.setY(1);
        wf2.goingRight();
        
        assertTrue(wf2.getX() == 2 && wf2.getY() == 1);
    }
    
    @Test
    public void goingUpTurnsAroundIfOtherDirectionsNotPossible() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(1);
        wf2.setY(1);
        wf2.goingUp();
        
        assertTrue(wf2.getX() == 2 && wf2.getY() == 1);
    }
    
    @Test
    public void goingDownTurnsAroundIfOtherDirectionsNotPossible() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(2);
        wf2.setY(1);
        wf2.goingDown();
        
        assertTrue(wf2.getX() == 1 && wf2.getY() == 1);
    }
    
    @Test
    public void goingLeftTurnsAroundIfOtherDirectionsNotPossible() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(1);
        wf2.setY(1);
        wf2.goingLeft();
        
        assertTrue(wf2.getX() == 1 && wf2.getY() == 2);
    }
    
    @Test
    public void goingRightTurnsAroundIfOtherDirectionsNotPossible() {
        int[][] maze = new int[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        
        wf2.setGrid(maze);
        
        wf2.setX(2);
        wf2.setY(1);
        wf2.goingRight();
        
        assertTrue(wf2.getX() == 1 && wf2.getY() == 1);
    }
    
    @Test
    public void solveHasBeenAtTheStartPoint() {
        m.setLayout(wf.solve(m));
        assertTrue(m.getFromCoordinates(1, 1) == 2);
    }
    
    @Test
    public void solveMakesItToTheGoalPoint() {
        m.setLayout(wf.solve(m));
        int n = m.getSize();
        assertTrue(m.getFromCoordinates(n - 2, n - 2) == 2);
    }
    
    @Test
    public void timeIsZeroBeforeSolving() {
        assertTrue(wf.getTime() == 0);
    }
    
    @Test
    public void solutionLengthIsPlausible() {
        wf.solve(m);
        int n = m.getSize();
        int length = wf.solutionLength();
        assertTrue(0 <= length && length < n * n);
    }
    
}
