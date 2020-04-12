
package labyrinthsolver.domain.utils;

public class MersenneTwister {
    
    private int seed;
    private int[] values;
    private int next;
    
    public MersenneTwister (long seed) {
        this.seed = (int) (seed % 100000);
        next = 0;
        values = new int[624];
        values[0] = this.seed;
        for (int i = 1; i < 624; i++) {
            values[i] = 1812433253 * (values[i-1] ^ (values[i-1] >> 30)) + i;
        }
    }
    
    public int nextInt(int limit) {
        if (next >= values.length) {
            newValues();
        }
        int value = values[next++];
        // Vaihdetaan vielä bittien paikkoja
        value = value ^ (value >> 11);
        value = value ^ ((value << 7) & 0x9d2c5680);
        value = value ^ ((value << 15) & 0xefc60000);
        value = value ^ (value >> 18);
       
        return value % limit;
    }
    
    public void newValues() {
        int length = values.length;
        int firstGroup = 227;
        int secondGroup = length - 227;
        int bits = 0;
        // Laskee uuden bittiesityksen esnimmäiselle puolikkaalle state-taulun kahden perättäisen arvon avulla
        for (int i = 0; i < firstGroup; i++) {
            // Muodostetaan bittiesityksiä
            bits = (values[i] & 0x80000000) | (values[i + 1] & 0x7fffffff);
            values[i] = (values[i + secondGroup] ^ (bits >>> 1)) ^ ((bits & 1) * 0x9908b0df);
        }
        // Tehdään vastaava muille lopuille taulukossa, mutta ei viimeiselle
        for (int i = firstGroup; i < length - 1; i++) {
            bits = (values[i] & 0x80000000) | (values[i + 1] & 0x7fffffff);
            values[i] = (values[i - firstGroup] ^ (bits >> 1)) ^ ((bits & 1) * 0x9908b0df);
        }
        bits = (values[length - 1] & 0x80000000) | (values[0] & 0x7fffffff);
        values[length - 1] = (values[secondGroup - 1] ^ (bits >> 1)) ^ ((bits & 1) * 0x9908b0df);
        next = 0;
    }
}
