
package labyrinthsolver.domain.utils;

/**
 * Jono, johon voidaan asettaa numeropareja.
 * Sille annetaan maksimikoko. Se toimii kehäpuskurityylillä,
 * joten pidetään kirjaa kunkinhetkisestä alku- ja loppukohdasta.
 * Count kertoo, montako alkiota jonossa milloinkin on.
 */
public class PairQueue {
    
    private Pair[] queue;
    private int endIndex = -1;
    private int startIndex = 0;
    private int size;
    private int count = 0;
    
    /**
     * Konstruktori luo jonon
     * @param size Kuvaa jonoon mahtuvien alkioiden maksimimäärää.
     */
    public PairQueue(int size) {
        queue = new Pair[size];
        this.size = size;
    }
    
    /**
     * Asettaa annetun alkion jonon perälle.
     * Samalla päivitetään jonon lopun paikka
     * @param pair On asetettava alkio
     */
    public void enqueue(Pair pair) {
        if (full()) {
            return;
        }
        endIndex = (endIndex + 1) % size;
        count++;
        queue[endIndex] = pair;
    }
    
    /**
     * Palauttaa jonon ensimmäisen alkion, ja poistaa sen jonosta
     * @return Jonon ensimmäinen alkio, joka myös poistetaan
     */
    public Pair dequeue() {
        if (empty()) {
            return new Pair(-1, -1);
        }
        int oldStart = startIndex;
        startIndex = (startIndex + 1) % size;
        count--;
        return queue[oldStart];
    }
    
    /**
     * Tarkistaa, onko jono täynnä
     * @return True jos jono on täysi
     */
    public boolean full() {
        return size == count;
    }
    
    /**
     * Tarkistaa, onko jono tyhjä
     * @return True jos jono on tyhjä
     */
    public boolean empty() {
        return count == 0;
    }
    
    /**
     * Palautta loppuindeksin.
     * @return Viimeisen alkion indeksi.
     */
    public int getEndIndex() {
        return endIndex;
    }
    
    /**
     * Palauttaa alkuindeksin.
     * @return Ensimmäisen alkion indeksi.
     */
    public int getStartIndex() {
        return startIndex;
    }
    
    /**
     * Palauttaa maksimikoon.
     * @return Jonon alkioiden maksimimäärä.
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Palauttaa jonon alkioiden määrän.
     * @return Jonossa olevien alkioiden määrä.
     */
    public int getCount() {
        return count;
    }
    
    
    
}
