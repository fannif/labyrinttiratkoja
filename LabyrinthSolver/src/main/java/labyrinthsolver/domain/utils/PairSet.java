
package labyrinthsolver.domain.utils;

/**
 * Joukko, johon voi tallettaa pareja.
 * Kukin pari ilmenee joukossa korkeintaan kerran
 */
public class PairSet {
    
    private int[][] set;
    private int endIndex;
    private int size;
    
    /**
     * Konstruktori luo joukon.
     * Samalla alustetaan taulukko, johon alkiot talletetaan.
     * size on joukon senhetkinen maksimikoko ja endIndex kuvaa
     * indeksiä, johon seuraava alkio tulisi.
     */
    public PairSet() {
        this(16);
    }
    
    /**
     * Kostruktoriversio, jossa voidaan itse antaa haluttu aloituskoko.
     * @param size Joukon haluttu aloituskoko.
     */
    public PairSet(int size) {
        set = new int[size][4];
        this.size = size;
        endIndex = 0;        
    }
    
    /**
     * Lisää joukkoon parin. Jos se on jo siellä, niin ei lisätä.
     * Jos ollaan maksimikoossa, niin kasvatetaan joukkoa.
     * @param pair Lisättävä pari
     */
    public void add(int[] pair) {
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
    
    /**
     * Poistaa alkion joukosta.
     * Jos alkioiden määrä putoaa alle neljännekseen
     * maksimikoosta ja joukko on tarpeeksi iso,
     * niin pienennetään joukkoa.
     * @param pair Poistettava alkio
     */
    public void remove(int[] pair) {
        int exists = contains(pair);
        if (exists < 0) {
            return;
        }
        set[exists] = set[endIndex - 1];
        endIndex--;
        if (size >= 32 && endIndex < size / 4) {
            decreaseSize();
        }
    }
    
    /**
     * Tarkistaa, kuuluuko annettu alkio joukkoon
     * @param pair Tarkistettava alkio
     * @return Alkion indeksi, jos se kuuluu joukkoon, ja
     * -1 jos se ei kuulu.
     */
    public int contains(int[] pair) {
        boolean exists = true;
        for (int i = 0; i < endIndex; i++) {
            for (int j = 0; j < pair.length; j++) {
                if (set[i][j] != pair[j]) {
                    exists = false;
                    if (pair.length == 4) {
                        if (set[i][0] == pair[2] && set[i][1] == pair[3]) {
                            exists = true;
                        }
                    }
                }
                
            }
            if (exists) {
                return i;
            }
            exists = true;
        }
        return -1;
    }
    
    /**
     * Palauttaa satunnaisesti valitun alkion joukosta.
     * @return Satunnainen joukon alkio.
     */
    public int[] randomPair() {
        MersenneTwister random = new MersenneTwister(System.currentTimeMillis());
        if (endIndex == 0) {
            return null;
        }
        int next = random.nextInt(endIndex);
        return set[next];
    }
    
    /**
     * Kasvattaa joukon maksimikoon kaksinkertaiseksi.
     */
    private void increaseSize() {
        int[][] newSet = new int[size * 2][4];
        for (int i = 0; i < endIndex; i++) {
            newSet[i] = set[i];
        }
        set = newSet;
        size = newSet.length;
    }
    
    /**
     * Puolittaa joukon maksimikoon.
     */
    private void decreaseSize() {
        if (endIndex > size / 4) {
            return;
        }
        int[][] newSet = new int[size / 2][4];
        for (int i = 0; i < endIndex; i++) {
            newSet[i] = set[i];
        }
        set = newSet;
        size = newSet.length;
    }
    
    /**
     * Tyhjentää joukon, ja asettaa sen maksimikooksi 16.
     */
    public void clear() {
        set = new int[16][4];
        size = 16;
        endIndex = 0;
    }

    /**
     * Palauttaa endIndexin.
     * @return Seuraavan lisättävän alkion indeksi.
     */
    public int getEndIndex() {
        return endIndex;
    }
    
    /**
     * Palauttaa joukon senhetkisen maksimikoon.
     * @return Joukon tämänhetkinen maksimikoko.
     */
    public int getSize() {
        return size;
    }
    
}
