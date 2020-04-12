
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Pair;
import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.utils.PairSet;
import labyrinthsolver.domain.utils.Edge;
import labyrinthsolver.domain.algorithms.WallFollower;
import labyrinthsolver.domain.algorithms.Kruskal;
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
        Pair[][] parents = new Pair[][]{{new Pair(0, 0)},{new Pair(0, 0)}};
        k.setParent(parents);
        assertTrue(k.sameMaze(new Pair(0, 0), new Pair(1, 0)));
    }
    
    @Test
    public void sameMazeReturnsFalseNotIfInSameSubsection() {
        Pair[][] parents = new Pair[][]{{new Pair(0, 0)},{new Pair(1, 0)}};
        k.setParent(parents);
        assertTrue(!k.sameMaze(new Pair(0, 0), new Pair(1, 0)));
    }
    
    @Test
    public void originallyEachIsItsOwnParent() {
        Pair[][] emptyParents = new Pair[m.getSize()][m.getSize()];
        k.setParent(emptyParents);
        k.setGrid(m.getLayout());
        k.originalParents();
        boolean works = true;
        for (int i = 0; i < m.getSize(); i++) {
            for (int j = 0; j < m.getSize(); j++) {
                Pair pair = new Pair(i, j);
                if (!pair.equals(k.getParent()[i][j])) {
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
        for (int i = 0; i < m.getSize(); i++) {
            for (int j = 0; j < m.getSize(); j++) {
                if (1 != k.getSize()[i][j]) {
                    works = false;
                }
            }
        }
        assertTrue(works);
    }
    
    @Test
    public void afterUnitePairsHaveSameRepresentatives() {
        Pair[][] emptyParents = new Pair[m.getSize()][m.getSize()];
        k.setParent(emptyParents);
        k.setSize(m.getLayout());
        int[][] emptyMaze = new int[m.getSize()][m.getSize()];
        k.setGrid(emptyMaze);
        k.originalParents();
        k.originalSizes();
        Pair p1 = new Pair(0, 1);
        Pair p2 = new Pair (0, 3);
        k.unite(p1, p2);
        assertTrue(k.representative(p1).equals(k.representative(p2)));
    }
    
    @Test
    public void smallerSubNetJoinsBiggerSubnet() {
        Pair[][] emptyParents = new Pair[m.getSize()][m.getSize()];
        k.setParent(emptyParents);
        k.setSize(m.getLayout());
        int[][] emptyMaze = new int[m.getSize()][m.getSize()];
        k.setGrid(emptyMaze);
        k.originalParents();
        emptyMaze[0][0]++;
        k.setSize(emptyMaze);
        Pair p1 = new Pair(0, 0);
        Pair p2 = new Pair (0, 2);
        k.unite(p1, p2);
        assertTrue(k.representative(p2).equals(p1));
    }
    
    @Test
    public void originalEdgesContainsAllPossibleEdges() {
        boolean works = true;
        int[][] maze = new int[][]{
            {1,1,1,1,1},
            {1,0,1,0,1},
            {1,1,1,1,1},
            {1,0,1,0,1},
            {1,1,1,1,1}
        };
        k.setGrid(maze);
        k.setEdges(new PairSet());
        k.originalEdges();
        if (k.getEdges().contains(new Edge(new Pair(1, 1), new Pair(1, 3))) < 0) {
            works = false;
        }
        if (k.getEdges().contains(new Edge(new Pair(1, 1), new Pair(3, 1))) < 0) {
            works = false;
        }
        if (k.getEdges().contains(new Edge(new Pair(1, 3), new Pair(3, 3))) < 0) {
            works = false;
        }
        if (k.getEdges().contains(new Edge(new Pair(3, 1), new Pair(3, 3))) < 0) {
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
    public void generatedMazeIsSolvable() {
        m.setLayout(k.generate(m));
        WallFollower wf = new WallFollower();
        m.setLayout(wf.solve(m));
        assertTrue(m.getFromCoordinates(m.getSize() - 2, m.getSize() - 2) == 2);
    }
}
