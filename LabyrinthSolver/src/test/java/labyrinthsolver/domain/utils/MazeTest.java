
package labyrinthsolver.domain.utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka testaa Maze-luokan toimintaa.
 * 
 */
public class MazeTest {
    
    Maze m;
    
    public MazeTest() {
    }
    
    @Before
    public void setUp() {
        m = new Maze(15);
    }
    
    @Test
    public void constructorCreatesCorrectSizeArray() {
        assertTrue(m.getLayout().length == 15 * 15);
    }
    
    @Test
    public void getSizeReturnsCorrectValue() {
        assertTrue(m.getSize() == 15);
    }
    
    @Test
    public void initializeCreatesOuterWalls() {
        boolean ones = true;
        
        for (int i = 0; i < m.getSize(); i++) {
            if (m.getFromCoordinates(i, 0) != 1) {
                ones = false;
            }
            if (m.getFromCoordinates(0, i) != 1) {
                ones = false;
            }
            if (m.getFromCoordinates(m.getSize() - 1, i) != 1) {
                ones = false;
            }
            if (m.getFromCoordinates(i, m.getSize() - 1) != 1) {
                ones = false;
            }
        }
        
        assertTrue(ones);
    }
    
    @Test
    public void initializeWithWallsCreatesGridOfWalls() {
        boolean grid = true;
        
        m.initializeWithWalls();
        
        for (int i = 1; i < m.getSize() - 1; i++) {
            for (int j = 1; j < m.getSize() - 1; j++) {
                if (j % 2 == 0 || i % 2 == 0) {
                    if (m.getFromCoordinates(i, j) != 1) {
                        grid = false;
                    }
                } else {
                    if (m.getFromCoordinates(i, j) != 0) {
                        grid = false;
                    }
                }
            }
        }
        
        assertTrue(grid);
    }
    
    @Test
    public void getFromCoordinatesReturnsCorrectValue() {
        assertTrue(m.getFromCoordinates(0, 0) == 1 && m.getFromCoordinates(0, 0) == m.getLayout()[0]);
    }
    
    @Test
    public void tooLargeXCoordinateReturnsMinusOne() {
        assertTrue(m.getFromCoordinates(100, 1) == -1);
    }
    
    @Test
    public void tooLargeYCoordinateReturnsMinusOne() {
        assertTrue(m.getFromCoordinates(0, 100) == -1);
    }
    
    @Test
    public void negativeYCoordinateReturnsMinusOne() {
        assertTrue(m.getFromCoordinates(0, -1) == -1);
    }
    
    @Test
    public void negativeXCoordinateReturnsMinusOne() {
        assertTrue(m.getFromCoordinates(-5, 0) == -1);
    }
    
    @Test
    public void setToCoordinatesSetsCorrectValueToCorrectCoordinates() {
        m.setToCoordinates(5, 6, 7);
        assertTrue(m.getFromCoordinates(5, 6) == 7);
    }
    
    @Test
    public void doesNotCrashWithBadCoordinates() {
        m.setToCoordinates(-5, 6, 7);
        m.setToCoordinates(5, -6, 7);
        m.setToCoordinates(55, 6, 7);
        m.setToCoordinates(5, 66, 7);
    }
    
    @Test
    public void setLayoutSetsCorrectLayout() {
        Maze m2 = new Maze(5);
        int[] newMaze = new int[]{
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4
        };
        
        m2.setLayout(newMaze);
        
        boolean correct = true;
        
        for (int i = 0; i < m2.getSize(); i++) {
            for (int j = 0; j < m2.getSize(); j++) {
                if (m2.getFromCoordinates(i, j) != newMaze[i * 5 + j]) {
                    correct = false;
                }
            }
        }
        
        assertTrue(correct);
    }
    
    @Test
    public void layoutsCanOnlyBeSetIfTheyMatchMazeSize() {

        int[] newMaze = new int[]{
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
            0, 1, 2, 3, 4,
        };
        
        m.setLayout(newMaze);
        
        assertTrue(m.getLayout().length == 15 * 15);
    }
}
