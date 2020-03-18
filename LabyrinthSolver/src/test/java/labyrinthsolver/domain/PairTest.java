
package labyrinthsolver.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *
 */
public class PairTest {
    
    public PairTest() {
    }
    
    @Test
    public void samePairIsRecognizedAsEqual() {
        Pair pair1 = new Pair(1, 2);
        Pair pair2 = new Pair(1, 2);
        
        assertTrue(pair1.equals(pair2));
    }
    
}
