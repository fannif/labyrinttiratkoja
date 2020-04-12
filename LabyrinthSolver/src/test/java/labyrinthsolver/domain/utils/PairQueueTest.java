
package labyrinthsolver.domain.utils;

import labyrinthsolver.domain.utils.Pair;
import labyrinthsolver.domain.utils.PairQueue;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PairQueueTest {
    
    PairQueue q;
    
    public void PairQueueTest() {   
    }
    
    @Before
    public void setUp() {
        q = new PairQueue(10);
    }
    
    @Test
    public void queueIsCreatedWithCorrectSize() {
        assertTrue(q.getSize() == 10);
    }
    
    @Test
    public void addingPairIncreasesCount() {
        q.enqueue(new Pair(0, 0));
        assertTrue(q.getCount() == 1);
    }
    
    @Test
    public void dequeueDecreasesCount() {
        Pair pair = new Pair(0, 0);
        q.enqueue(pair);
        q.dequeue();
        assertTrue(q.getCount() == 0);
    }
    
    @Test
    public void EmptyReturnsTrueIfEmpty() {
        assertTrue(q.empty());
    }
    
    @Test
    public void cannotDequeIfEmpty() {
        q.dequeue();
        assertTrue(q.getCount() == 0);
    }
    
    @Test
    public void fullReturnsTrueIfFull() {
        for (int i = 0; i < 10; i++) {
            q.enqueue(new Pair(0, i));
        }
        assertTrue(q.full());
    }
    
    @Test
    public void countCannotExceedSize() {
        for (int i = 0; i < 12; i++) {
            q.enqueue(new Pair(0, i));
        }
        assertTrue(q.getCount() == 10);
    }
    
    @Test
    public void enqueueIncreasesEndIndex() {
        int old = q.getEndIndex();
        q.enqueue(new Pair(0, 0));
        assertTrue(q.getEndIndex() == old + 1);
    }
    
    @Test
    public void dequeueIncreasesStartIndex() {
        int old = q.getStartIndex();
        q.enqueue(new Pair(0, 0));
        q.dequeue();
        assertTrue(q.getStartIndex() == old + 1);
    }
}
