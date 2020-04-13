
package labyrinthsolver.domain.utils;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PairSetTest {
    
    private PairSet set;
    
    public void PairSetTest() {
    }
    
    @Before
    public void setUp() {
        set = new PairSet();
    }
    
    @Test
    public void addAddsPairToSet() {
        int[] pair = new int[]{0, 0};
        set.add(pair);
        assertTrue(set.contains(pair) >= 0);
    }
    
    @Test
    public void containsReturnsCorrectIndex() {
        int[] pair = new int[]{0, 0};
        int[] pair2 = new int[]{1, 0};
        set.add(pair);
        set.add(pair2);
        assertTrue(set.contains(pair2) == 1);
    }
    
    @Test
    public void containsReturnsMinusOneIfDoesNotContainPair() {
        assertTrue(set.contains(new int[]{0, 0}) == -1);
    }
    
    @Test
    public void pairIfNotAddedIfIsAlreadyInSet() {
        int[] pair = new int[]{0, 0};
        set.add(pair);
        set.add(new int[]{0, 0});
        assertTrue(set.getEndIndex() == 1);
    }
    
    @Test
    public void removeRemovesPairFromSet() {
        int[] pair = new int[]{0, 0};
        int[] pair2 = new int[]{1, 0};
        set.add(pair);
        set.add(pair2);
        set.remove(pair);
        assertTrue(set.contains(pair) < 0);
    }
    
    @Test
    public void whenSetGetsFullSizeIsDoubled() {
        for (int i = 0; i < 12; i++) {
            set.add(new int[]{0, i});
        }
        assertTrue(set.getSize() == 20);
    }
    
    @Test
    public void whenAmountGetsSmallSizeIsCutToHalf() {
        for (int i = 0; i < 12; i++) {
            set.add(new int[]{0, i});
        }
        for (int i = 0; i < 10; i++) {
            set.remove(new int[]{0, i});
        }
        assertTrue(set.getSize() == 10);
    }
    
    @Test
    public void sizeIsNotDecreasedIfSizeIsNotOverTen() {
        for (int i = 0; i < 5; i++) {
            set.add(new int[]{0, i});
        }
        for (int i = 0; i < 4; i++) {
            set.remove(new int[]{0, i});
        }
        assertTrue(set.getSize() == 10);
    }
    
    @Test
    public void afterClearSizeIsTen() {
        for (int i = 0; i < 12; i++) {
            set.add(new int[]{0, i});
        }
        set.clear();
        assertTrue(set.getSize() == 10);
    }
    
    @Test
    public void afterClearThereAreNoPairs() {
        for (int i = 0; i < 12; i++) {
            set.add(new int[]{0, i});
        }
        set.clear();
        assertTrue(set.getEndIndex() == 0);
    }
    
    @Test
    public void randomReturnsPairFromSet() {
        for (int i = 0; i < 12; i++) {
            set.add(new int[]{0, i});
        }
        int[] random = set.randomPair();
        assertTrue(set.contains(random) >= 0);
    }
    
    @Test
    public void randomReturnsNullIfEmpty() {
        assertTrue(set.randomPair() == null);
    }
    
    @Test
    public void cannotRemovePairThatIsNotInSet() {
        set.remove(new int[]{0, 0});
        assertTrue(set.getEndIndex() == 0);
    }
}
