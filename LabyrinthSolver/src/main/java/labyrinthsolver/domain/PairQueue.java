
package labyrinthsolver.domain;


public class PairQueue {
    
    private Pair[] queue;
    private int endIndex = -1;
    private int startIndex = 0;
    private int size;
    private int count = 0;
    
    public PairQueue(int size) {
        queue = new Pair[size];
        this.size = size;
    }
    
    public void enqueue(Pair pair) {
        if (full()) {
            return;
        }
        endIndex = (endIndex + 1) % size;
        count++;
        queue[endIndex] = pair;
    }
    
    public Pair dequeue() {
        if (empty()) {
            return new Pair(-1, -1);
        }
        int oldStart = startIndex;
        startIndex = (startIndex + 1) % size;
        count--;
        return queue[oldStart];
    }
    
    public boolean full() {
        return size == count;
    }
    
    public boolean empty() {
        return count == 0;
    }
}
