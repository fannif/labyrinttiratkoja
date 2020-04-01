
package labyrinthsolver.domain;

/**
 * Luokka generoi labyrintteja Kruskalin algoritmilla.
 * Tässä käytetään kaikki seinät sisältävää sokkeloa, josta seiniä sitten otetaan pois
 * niin, että saadaan ratkaistava labyrintti.
 * Luokka hyödyntää puurakennetta. 
 * Taulukko parent kuvaa labyrintin solmujen vahempia puussa.
 * Taulukko grid on sokkelopohja, joka on aluksi tyhjä.
 * Taulukko size kuvaa kullekin solmulle sen verkkopalan kokoa, johon solmu kuuluu.
 * Algorimti nimittäin perustuu pienempien verkkojen yhdistämiseen.
 * Joukko edges sisältää kaikki välit, joista voisi poistaa seinän.
 */
public class Kruskal {
    
    private Pair[][] parent;
    private int[][] grid;
    private int [][] size;
    private long time = 0;
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
    public int[][] generate(Maze maze) {
        long startTime = System.nanoTime();
        int n = maze.getSize();
        maze.initialize();
        maze.initializeWithWalls();
        grid = maze.getLayout();
        parent = new Pair[n][n];
        size = new int[n][n];
        edges = new PairSet();
        originalParents();
        originalSizes();
        originalEdges();
        Edge edge;
        
        while (edges.getEndIndex() > 0) {
            edge = (Edge) edges.randomPair();
            edges.remove(edge);
            if (unite(edge.getX(), edge.getY())) {
                if (edge.getX().getX() > edge.getY().getX()) {
                    grid[edge.getX().getX() - 1][edge.getX().getY()] = 0;
                } else if (edge.getX().getX() < edge.getY().getX()) {
                    grid[edge.getX().getX() + 1][edge.getX().getY()] = 0;
                } else if (edge.getX().getY() > edge.getY().getY()) {
                    grid[edge.getX().getX()][edge.getX().getY() - 1] = 0;
                } else if (edge.getX().getY() < edge.getY().getY()) {
                    grid[edge.getX().getX()][edge.getX().getY() + 1] = 0;
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
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                parent[i][j] = new Pair(i, j);
            }
        }
    }
    
    /**
     * Täyttää kollekin läbyrintin solmulle sen aliverkon koon, 
     * jossa se alkutilanteessa on. 
     * Alussa kussakin aliverkossa on vain solmu itse.
     */
    public void originalSizes() {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                size[i][j] = 1;
            }
        }
    }
    
    /**
     * Lisää labyrintin polkusolmujen väliset mahdollisesti poistettavat 
     * seinät kaikki yhteen joukkoon.
     */
    public void originalEdges() {
        int n = grid.length;
        for (int i = 1; i < n - 1; i += 2) {
            for (int j = 1; j < n - 1; j += 2) {
                if (grid[i][j] == 1) {
                    continue;
                }
                if (i > 2) {
                    edges.add(new Edge(new Pair(i, j), new Pair(i - 2, j)));
                }
                if (i < n - 2) {
                    edges.add(new Edge(new Pair(i, j), new Pair(i + 2, j)));
                }
                if (j > 2) {
                    edges.add(new Edge(new Pair(i, j), new Pair(i, j - 2)));
                }
                if (j < n - 2) {
                    edges.add(new Edge(new Pair(i, j), new Pair(i, j + 2)));
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
    public Pair representative(Pair current) {
        int i = current.getX();
        int j = current.getY();
        while (!current.equals(parent[i][j])) {
            current = parent[i][j];
            i = current.getX();
            j = current.getY();
        }
        return current;
    }
    
    /**
     * Tarkistaa, kuuluvatko annetut solmut samaan aliverkkoon.
     * @param first Ensimmäinen ttutkittava solmu.
     * @param second Toinen tutkittava solmu.
     * @return True, jos ovat samassa alijoukossa, ja false jos eivät ole.
     */
    public boolean sameMaze(Pair first, Pair second) {
        return representative(first).equals(representative(second));
    }
    
    /**
     * Yhdistää kaksi solmua ja niiden aliverkot toisiinsa, jos eivät vielä
     * olleet samassa aliverkossa.
     * @param first Kaaren toisessa päässä oleva ensimmäinen yhdistettävä solmu.
     * @param second Saman kaaren toisessa päässä oleva toinen yhdistettävä solmu.
     * @return True, jos olivat eri aliverkoissa ja yhdistettiin, ja false jos olivat jo samassa, eikä tarvinnut yhdistää.
     */
    public boolean unite(Pair first, Pair second) {
        first = representative(first);
        second = representative(second);
        if (first.equals(second)) {
            return false;
        }
        if (size[first.getX()][first.getY()] <= size[second.getX()][second.getY()]) {
            parent[first.getX()][first.getY()] = second;
            size[second.getX()][second.getY()] += size[first.getX()][first.getY()];
            return true;
        } else {
            parent[second.getX()][second.getY()] = first;
            size[first.getX()][first.getY()] += size[second.getX()][second.getY()];
            return true;
        }
    }
    
    /**
     * Palauttaa tämänhetkisen sokkelopohjan
     * @return Tämänhetkinen sokkelopohja
     */
    public int[][] getGrid() {
        return grid;
    }
    
    /**
     * Palauttaa tämänhetkisen kokotaulukon
     * @return Taulukko, jossa on eri alijoukkojen kokoja
     */
    public int[][] getSize() {
        return size;
    }
    
    /**
     * Palauttaa generate-metodin ajamiseen viimeksi kuluneen ajan nanosekunteina
     * @return Labyrintin generoimiseen viimeksi mennyt aika nanosekunteina
     */
    public long getTime() {
        return time;
    }
    
    /**
     * Palauttaa listan solmujen vanhemmista.
     * Testausta varten.
     * @return  Lista solmujen vanhemmista.
     */
    public Pair[][] getParent() {
        return parent;
    }
    
    /**
     * Asettaa listan solmujen vanhemmista.
     * Testausta varten.
     * @param parent Uusi lista solmujen vanhemmista.
     */
    public void setParent(Pair[][] parent) {
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
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
    
    /**
     * Asettaa eri aliverkoille uudet koot.
     * Testausta varten.
     * @param size Uusi koko taulukko.
     */
    public void setSize(int[][] size) {
        this.size = size;
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
