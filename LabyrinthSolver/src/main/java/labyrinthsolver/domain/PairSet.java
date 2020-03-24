
package labyrinthsolver.domain;

import java.util.Random;

public class PairSet {
    
    private Pair[] set;
    private int endIndex;
    private int size;
    
    public PairSet() {
        set = new Pair[10];
        size = 10;
        endIndex = 0;
    }
    
    public void add(Pair pair) {
        int exists = contains(pair);
        if (exists >= 0) {
            return;
        }
        if (endIndex == size) {
            increaseSize();
        }
        set[endIndex] = pair;
        endIndex++;
    }
    
    public void remove(Pair pair) {
        int exists = contains(pair);
        if (exists < 0) {
            return;
        }
        set[exists] = null;
        for (int i = exists; i < endIndex - 1; i++) {
            set[i] = set[i + 1];
        }
        endIndex--;
        if (size >= 20 && endIndex < size / 4) {
            decreaseSize();
        }
    }
    
    public int contains(Pair pair) {
        for (int i = 0; i < endIndex; i++) {
            if (set[i] == pair) {
                return i;
            }
        }
        return -1;
    }
    
    public Pair randomPair() {
        Random random = new Random();
        if (endIndex == 0) {
            return null;
        }
        int next = random.nextInt(endIndex);
        return set[next];
    }
    
    private void increaseSize() {
        Pair[] newSet = new Pair[size * 2];
        for (int i = 0; i < endIndex; i++) {
            newSet[i] = set[i];
        }
        set = newSet;
        size = newSet.length;
    }
    
    private void decreaseSize() {
        if (endIndex > size / 4) {
            return;
        }
        Pair[] newSet = new Pair[size / 2];
        for (int i = 0; i < endIndex; i++) {
            newSet[i] = set[i];
        }
        set = newSet;
        size = newSet.length;
    }
    
    public void clear() {
        set = new Pair[10];
        size = 10;
        endIndex = 0;
    }
    
}
