
package labyrinthsolver.domain;

import java.util.Arrays;

public class Maze {
    
    private int size;
    private int[][] layout;
    
    /**
     * Konstruktori
     * @param s On haluttu sokkelopohjan koko
     */
    public Maze(int s) {
        size = s;
        layout = new int[size][size];
        initialize();
    }
    
    /**
     * Alustaa tyhjän sokkelon, jossa on vain reunoilla seinät
     */
    public void initialize() {
        for (int i = 0; i < size; i++) {
            Arrays.fill(layout[i], 0);
        }
        for (int i = 0; i < size; i++) {
            layout[i][0] = 1;
            layout[0][i] = 1;
            layout[size - 1][i] = 1;
            layout[i][size - 1] = 1;
        }
    } 
    
    /**
     * Lisää sokkeloon kaikkiin väleihin seinät.
     */
    public void initializeWithWalls() {
        for (int i = 0; i < size; i++) {
            if (i % 2 == 1) {
                for (int j = 0; j < size - 1; j = j + 2) {
                    layout[i][j] = 1;
                }
            } else {
                Arrays.fill(layout[i], 1);
            }
        }
        
        for (int i = 0; i < size; i++) {
            layout[i][size - 1] = 1;
        }
    }
    
    /**
     * Palauttaa sokkelon rivin/sarakkeen pituuden
     * @return 
     */
    public int getSize() {
        return size;
    }

    /**
     * Palauttaa sokkelun pohjana toimivan taulukon
     * @return 
     */
    public int[][] getLayout() {
        return layout;
    }

    /**
     * Asettaa uuden pohjan sokkelolle
     * @param layout Haluttu sokkelopohja
     */
    public void setLayout(int[][] layout) {
        this.layout = layout;
    }
    
    /**
     * Palautta tietyssä kohdassa taulukossa olevan arvon
     * @param i Haluttu sarake
     * @param j Haluttu rivi
     * @return  Haluttu arvo
     */
    public int getFromCoordinates(int i, int j) {
        if (i >= size || j >= size || i < 0 || j < 0) {
            return -1;
        }
        return layout[i][j];
    }
    
    /**
     * Asettaa sokkelon haluttuun kohtaan halutun arvon
     * @param i Haluttu sarake
     * @param j Haluttu rivi
     * @param value Asetettava arvo
     */
    public void setToCoordinates(int i, int j, int value) {
        if (i >= size || j >= size || i < 0 || j < 0) {
            return;
        }
        layout[i][j] = value;
    }
    
}
