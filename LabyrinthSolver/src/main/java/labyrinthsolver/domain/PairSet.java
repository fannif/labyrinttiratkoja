
package labyrinthsolver.domain;

import java.util.Random;

/**
 * Joukko, johon voi tallettaa pareja.
 * Kukin pari ilmenee joukossa korkeintaan kerran
 */
public class PairSet {
    
    private TwoValues[] set;
    private int endIndex;
    private int size;
    
    /**
     * Konstruktori luo joukon.
     * Samalla alustetaan taulukko, johon alkiot talletetaan.
     * size on joukon senhetkinen maksimikoko ja endIndex kuvaa
     * indeksiä, johon seuraava alkio tulisi.
     */
    public PairSet() {
        set = new TwoValues[10];
        size = 10;
        endIndex = 0;
    }
    
    /**
     * Lisää joukkoon parin. Jos se on jo siellä, niin ei lisätä.
     * Jos ollaan maksimikoossa, niin kasvatetaan joukkoa.
     * @param pair Lisättävä pari
     */
    public void add(TwoValues pair) {
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
    public void remove(TwoValues pair) {
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
    
    /**
     * Tarkistaa, kuuluuko annettu alkio joukkoon
     * @param pair Tarkistettava alkio
     * @return Alkion indeksi, jos se kuuluu joukkoon, ja
     * -1 jos se ei kuulu.
     */
    public int contains(TwoValues pair) {
        for (int i = 0; i < endIndex; i++) {
            if (set[i].equals(pair)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Palauttaa satunnaisesti valitun alkion joukosta.
     * @return Satunnainen joukon alkio.
     */
    public TwoValues randomPair() {
        Random random = new Random();
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
        TwoValues[] newSet = new TwoValues[size * 2];
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
        TwoValues[] newSet = new TwoValues[size / 2];
        for (int i = 0; i < endIndex; i++) {
            newSet[i] = set[i];
        }
        set = newSet;
        size = newSet.length;
    }
    
    /**
     * Tyhjentää joukon, ja asettaa sen maksimikooksi 10.
     */
    public void clear() {
        set = new TwoValues[10];
        size = 10;
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
