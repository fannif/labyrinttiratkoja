
package labyrinthsolver.domain.utils;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Testiluokka, joka testaa satunnaislukujen generoimiseen
 * toteutettua luokkaa MersenneTwister.
 */
public class MersenneTwisterTest {
    
    private MersenneTwister mt;
    
    @Before
    public void setUp() {
        mt = new MersenneTwister(System.currentTimeMillis());
    }
    
   @Test
    public void numberIsBetweenGivenValueAndZero() {
        int value = mt.nextInt(10);
        assertTrue(0 <= value && value < 10);
    }
    
    @Test
    public void negativeNumberReturnsAValue() {
        int value = mt.nextInt(-10);
        assertTrue(-10 < value && value < 10);
    }
    
    @Test
    public void nextIntWillReturnZeroIfGivenZero() {
        int value = mt.nextInt(0);
        assertTrue(value == 0);
    }
    
    @Test
    public void numberAlwaysStaysBetweenGivenValueAndZero() {
        boolean works = true;
        int value = 0;
        for (int i = 0; i < 700; i++) {
            value = mt.nextInt(10);
            if (!(0 <= value && value < 10)) {
                works = false;
                break;
            }
        }
        assertTrue(works);
    }
}
