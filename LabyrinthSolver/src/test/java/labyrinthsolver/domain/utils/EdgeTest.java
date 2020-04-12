
package labyrinthsolver.domain.utils;

import labyrinthsolver.domain.utils.Pair;
import labyrinthsolver.domain.utils.Edge;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Testaa Edge-luokan toimintaa.
 */
public class EdgeTest {
    
    @Test
    public void equalsRecognizesIdenticalEdges() {
        Edge edge1 = new Edge(new Pair(0, 1), new Pair(1, 1));
        Edge edge2 = new Edge(new Pair(0, 1), new Pair(1, 1));
        assertTrue(edge1.equals(edge2));
    }
    
    @Test
    public void equalsEvenIfPairsAreInDifferentOrder() {
        Edge edge1 = new Edge(new Pair(0, 1), new Pair(1, 1));
        Edge edge2 = new Edge(new Pair(1, 1), new Pair(0, 1));
        assertTrue(edge1.equals(edge2));
    }
    
    @Test
    public void notEqualsIfDifferentPairs() {
        Edge edge1 = new Edge(new Pair(0, 1), new Pair(1, 1));
        Edge edge2 = new Edge(new Pair(1, 1), new Pair(1, 1));
        assertTrue(!edge1.equals(edge2));
    }
}
