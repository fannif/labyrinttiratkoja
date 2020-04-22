
package labyrinthsolver.domain.algorithms;

import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.utils.PairSet;

/**
 * Luokka generoi labyrintteja Kruskalin algoritmilla.
 * Tässä käytetään kaikki seinät sisältävää sokkeloa, josta seiniä sitten otetaan pois
 * niin, että saadaan ratkaistava labyrintti.
 * Luokka hyödyntää puurakennetta. 
 * Taulukko parent kuvaa labyrintin solmujen vahempia puussa. Tämä on toteutettu kaksiulotteisena taulukkona,
 * jossa toinen ulottuvuus lasketaan kunkin solmun koordinaateista, ja toinen kuvaa solmuje xy-pareja.
 * Taulukko grid on sokkelopohja, joka on aluksi tyhjä.
 * Taulukko size kuvaa kullekin solmulle sen verkkopalan kokoa, johon solmu kuuluu.
 * Algorimti nimittäin perustuu pienempien verkkojen yhdistämiseen.
 * Joukko edges sisältää kaikki välit, joista voisi poistaa seinän.
 */
public class Kruskal {
    
    private int[][] parent;
    private int[] grid;
    private int [] size;
    private long time = 0;
    private int n;
    PairSet edges;
    
    /**
     * Kostruktorimetodi.
     */
    public Kruskal() {
    }
    
    /**
     * Metodi generoi sokkelopohjan Kruskalin algoritmilla.
     * Siinä aloitetaan tilanteesta, jossa sokkelossa on kaikki seinät, 
     * ja sitten niitä avataan yksitellen.
     * @param maze Pohja, johon generoidaan labyrintti.
     * @return Generoitu labyrinttipohja.
     */
    public int[] generate(Maze maze) {
        long startTime = System.nanoTime();
        n = maze.getSize();
        maze.initializeWithWalls();
        grid = maze.getLayout();
        parent = new int[n * n][2];
        size = new int[n * n];
        edges = new PairSet(n * n);
        originalParents();
        originalSizes();
        originalEdges();
        int[] edge;
        
        while (edges.getEndIndex() > 0) {
            edge = edges.randomPair();
            edges.remove(edge);
            if (unite(new int[]{edge[0], edge[1]}, new int[]{edge[2], edge[3]})) {
                if (edge[0] > edge[2]) {
                    grid[(edge[0] - 1) * n + edge[1]] = 0;
                } else if (edge[0] < edge[2]) {
                    grid[(edge[0] + 1) * n + edge[1]] = 0;
                } else if (edge[1] > edge[3]) {
                    grid[edge[0] * n + edge[1] - 1] = 0;
                } else if (edge[1] < edge[3]) {
                    grid[edge[0] * n + edge[1] + 1] = 0;
                }
            }
        }
        long endTime = System.nanoTime();
        time = endTime - startTime;
        return grid;
    }
    
    /**
     * Täyttää kullekin labyrintin solmulle alkutilanteessa niiden vanhemman, 
     * jos labyrintti ajatellaan puurakenteena.
     */
    public void originalParents() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                parent[i * n + j] = new int[]{i, j};
            }
        }
    }
    
    /**
     * Täyttää kullekin labyrintin solmulle sen aliverkon koon, 
     * jossa se alkutilanteessa on. 
     * Alussa kussakin aliverkossa on vain solmu itse.
     */
    public void originalSizes() {
        for (int i = 0; i < size.length; i++) {
            size[i] = 1;
        }
    }
    
    /**
     * Lisää labyrintin polkusolmujen väliset mahdollisesti poistettavat 
     * seinät kaikki yhteen joukkoon.
     */
    public void originalEdges() {
        for (int i = 1; i < n - 1; i += 2) {
            for (int j = 1; j < n - 1; j += 2) {
                if (grid[i * n + j] == 1) {
                    continue;
                }
                if (i < n - 2) {
                    edges.add(new int[]{i, j, i + 2, j});
                }
                if (j < n - 2) {
                    edges.add(new int[]{i, j, i, j + 2});
                }
            }
        }
    }
    
    /**
     * Laskee labyrintin solmulle, kuka edustaa aliverkkoa, johon
     * se kuuluu. Kaikilla saman aliverkon alkioilla on sama edustaja.
     * @param current Tällähetkellä käsiteltävänä oleva solmu, jolle etsitään edustajaa.
     * @return Alunperin annetu solmun edustaja.
     */
    public int[] representative(int[] current) {
        int i = current[0];
        int j = current[1];
        while (!(i == parent[i * n + j][0] && j == parent[i * n + j][1])) {
            current = parent[i * n + j];
            i = current[0];
            j = current[1];
        }
        return current;
    }
    
    /**
     * Yhdistää kaksi solmua ja niiden aliverkot toisiinsa, jos eivät vielä
     * olleet samassa aliverkossa.
     * @param first Kaaren toisessa päässä oleva ensimmäinen yhdistettävä solmu.
     * @param second Saman kaaren toisessa päässä oleva toinen yhdistettävä solmu.
     * @return True, jos olivat eri aliverkoissa ja yhdistettiin, ja false jos olivat jo samassa, eikä tarvinnut yhdistää.
     */
    public boolean unite(int[] first, int[] second) {
        first = representative(first);
        second = representative(second);
        if (first[0] == second[0] && first[1] == second[1]) {
            return false;
        }
        if (size[first[0] * n + first[1]] <= size[second[0] * n + second[1]]) {
            parent[first[0] * n + first[1]] = second;
            size[second[0] * n + second[1]] += size[first[0] * n + first[1]];
            return true;
        } else {
            parent[second[0] * n + second[1]] = first;
            size[first[0] * n + first[1]] += size[second[0] * n + second[1]];
            return true;
        }
    }
    
    /**
     * Palauttaa tämänhetkisen sokkelopohjan
     * @return Tämänhetkinen sokkelopohja
     */
    public int[] getGrid() {
        int[] grid = new int[this.grid.length];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = this.grid[i];
        }
        return grid;
    }
    
    /**
     * Palauttaa tämänhetkisen kokotaulukon
     * @return Taulukko, jossa on eri alijoukkojen kokoja
     */
    public int[] getSize() {
        int[] size = new int[this.size.length];
        for (int i = 0; i < size.length; i++) {
            size[i] = this.size[i];
        }
        return size;
    }
    
    /**
     * Palauttaa generate-metodin ajamiseen viimeksi kuluneen ajan nnosekunteina
     * @return Labyrintin generoimiseen viimeksi mennyt aika nanosekunteina
     */
    public long getTime() {
        return time;
    }
    
    /**
     * Palauttaa taulukon solmujen vanhemmista.
     * Testausta varten.
     * @return  Taulukko solmujen vanhemmista.
     */
    public int[][] getParent() {
        return parent;
    }
    
    /**
     * Asettaa listan solmujen vanhemmista.
     * Testausta varten.
     * @param parent Uusi lista solmujen vanhemmista.
     */
    public void setParent(int[][] parent) {
        this.parent = parent;
    }
    
    /**
     * Palauttaa kaaret, jotka kuvaavaat seiniä, jotka voidaan poistaa.
     * Testausta varten.
     * @return Joukko mahdollisista poistettavista kaarista.
     */
    public PairSet getEdges() {
        return edges;
    }
    
    /**
     * Asettaa uuden labyrinttipohjan käsiteltäväksi.
     * Testausta varten.
     * @param grid Uusi labyrinttipohja.
     */
    public void setGrid(int[] grid) {
        this.grid = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            this.grid[i] = grid[i];
        }
    }
    
    /**
     * Asettaa eri aliverkoille uudet koot.
     * Testausta varten.
     * @param size Uusi koko taulukko.
     */
    public void setSize(int[] size) {
        this.size = new int[size.length];
        for (int i = 0; i < size.length; i++) {
            this.size[i] = size[i];
        }
    }
    
    /**
     * Asettaa uuden vielä mahdollisesti poistettavien seinien joukon.
     * Testaamista varten.
     * @param edges Uusi joukko poistettavia solmujen välisiä seiniä.
     */
    public void setEdges(PairSet edges) {
        this.edges = edges;
    }
    
}
