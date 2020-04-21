
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.utils.PairSet;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Testaa Kruskalin algoritmia hyödyntävän Kruskal-generointiluokan toimintaa.
 * Tässä käytetään apuna tyhjää sokkeloa m.
 */
public class KruskalTest {
    
    Maze m;
    Kruskal k;
    
    public KruskalTest() {
    }
    
    @Before
    public void setUp() {
        m = new Maze(21);
        k = new Kruskal();
    }
    
    @Test
    public void sameMazeRecognizesIfInSameSubsection() {
        int[][] parents = new int[][]{new int[]{0, 0},new int[]{0, 0},new int[]{0, 0},new int[]{0, 0}};
        k.setParent(parents);
        k.setGrid(new int[4]);
        int[] rep1 = k.representative(new int[]{0, 0});
        int[] rep2 = k.representative(new int[]{1, 0});
        assertTrue(rep1[0] == rep2[0] && rep1[1] == rep2[1]);
    }
    
    @Test
    public void sameMazeReturnsFalseNotIfInSameSubsection() {
        int[][] parents = new int[][]{new int[]{0, 0}, new int[]{0, 1},new int[]{0, 0},new int[]{0, 0}};
        k.setParent(parents);
        k.setGrid(new int[4]);
        int[] rep1 = k.representative(new int[]{0, 0});
        int[] rep2 = k.representative(new int[]{0, 1});
        assertTrue(!(rep1[0] == rep2[0] && rep1[1] == rep2[1]));
    }
    
    @Test
    public void originallyEachIsItsOwnParent() {
        k.generate(m);
        k.originalParents();
        boolean works = true;
        for (int i = 0; i < m.getSize(); i++) {
            for (int j = 0; j < m.getSize(); j++) {
                if (!(i == k.getParent()[i * m.getSize() + j][0] && j == k.getParent()[i * m.getSize() + j][1])) {
                    works = false;
                }
            }
        }
        assertTrue(works);
    }
    
    @Test
    public void originallyAllSizesAreOne() {
        k.setSize(m.getLayout());
        k.setGrid(m.getLayout());
        k.originalSizes();
        boolean works = true;
        for (int i = 0; i < k.getSize().length; i++) {
            if (k.getSize()[i] != 1) {
                works = false;
                break;
            }
        }
        assertTrue(works);
    }
    
    @Test
    public void afterUnitePairsHaveSameRepresentatives() {
        int[][] emptyParents = new int[m.getSize() * m.getSize()][2];
        k.setParent(emptyParents);
        k.setSize(m.getLayout());
        int[] emptyMaze = new int[m.getSize() * m.getSize()];
        k.setGrid(emptyMaze);
        k.originalParents();
        k.originalSizes();
        int[] p1 = new int[]{0, 1};
        int[] p2 = new int[]{0, 3};
        k.unite(p1, p2);
        assertTrue(k.representative(p1)[0] == k.representative(p2)[0] && k.representative(p1)[1] == k.representative(p2)[1]);
    }
    
    @Test
    public void smallerSubNetJoinsBiggerSubnet() {
        int[][] emptyParents = new int[m.getSize() * m.getSize()][2];
        k.setParent(emptyParents);
        k.setSize(m.getLayout());
        int[] emptyMaze = new int[m.getSize() * m.getSize()];
        k.setGrid(emptyMaze);
        k.originalParents();
        emptyMaze[0]++;
        k.setSize(emptyMaze);
        int[] p1 = new int[]{0, 0};
        int[] p2 = new int[]{0, 2};
        k.unite(p1, p2);
        assertTrue(k.representative(p2)[0] == p1[0] && k.representative(p2)[1] == p1[1]);
    }
    
    @Test
    public void originalEdgesContainsAllPossibleEdges() {
        boolean works = true;
        Maze m2 = new Maze(5);
        k.generate(m2);
        int[] maze = new int[]{
            1,1,1,1,1,
            1,0,1,0,1,
            1,1,1,1,1,
            1,0,1,0,1,
            1,1,1,1,1
        };
        k.setGrid(maze);
        k.setEdges(new PairSet());
        k.originalEdges();
        if (k.getEdges().contains(new int[]{1, 1, 1, 3}) < 0) {
            works = false;
        }
        if (k.getEdges().contains(new int[]{1, 1, 3, 1}) < 0) {
            works = false;
        }
        if (k.getEdges().contains(new int[]{1, 3, 3, 3}) < 0) {
            works = false;
        }
        if (k.getEdges().contains(new int[]{3, 1, 3, 3}) < 0) {
            works = false;
        }
        assertTrue(works);
    }
    
    @Test
    public void startingPointIsNotAWall() {
        m.setLayout(k.generate(m));
        assertTrue(m.getFromCoordinates(1, 1) == 0);
    }
    
    @Test
    public void generatesOuterWalls() {
        boolean wall = true;
        int n = m.getSize();
        m.setLayout(k.generate(m));
        
        for (int i = 0; i < n; i++) {
            if (m.getLayout()[i * n + 0] != 1) {
                wall = false;
            }
            if (m.getLayout()[0 * n + i] != 1) {
                wall = false;
            }
            if (m.getLayout()[i * n + n - 1] != 1) {
                wall = false;
            }
            if (m.getLayout()[(n - 1) * n + i] != 1) {
                wall = false;
            }
        }
        
        assertTrue(wall);
    }
    
    @Test
    public void generatedMazeIsSolvable() {
        m.setLayout(k.generate(m));
        WallFollower wf = new WallFollower();
        m.setLayout(wf.solve(m));
        assertTrue(m.getFromCoordinates(m.getSize() - 2, m.getSize() - 2) == 2);
    }
}
