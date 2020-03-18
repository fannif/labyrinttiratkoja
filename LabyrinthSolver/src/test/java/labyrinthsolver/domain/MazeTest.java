
package labyrinthsolver.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Luokka testaa Maze-luokan toimintaa.
 * 
 */
public class MazeTest {
    
    Maze m;
    
    public MazeTest() {
    }
    
    @Before
    public void setUp() {
        m = new Maze(15);
    }
    
    @Test
    public void konstruktoriLuoOikeanKorkuisen() {
        assertTrue(m.getLayout().length == 15);
    }
    
    @Test
    public void konstruktoriLuoOikeanLevyisen() {
        assertTrue(m.getLayout()[0].length == 15);
    }
    
    @Test
    public void getSizePalauttaaOikeanArvon() {
        assertTrue(m.getSize() == 15);
    }
    
    @Test
    public void initializeTekeeReunoistaSeinat() {
        boolean ykkosia = true;
        
        for (int i = 0; i < m.getSize(); i++) {
            if (m.getLayout()[i][0] != 1) {
                ykkosia = false;
            }
            if (m.getLayout()[0][i] != 1) {
                ykkosia = false;
            }
            if (m.getLayout()[m.getSize() - 1][i] != 1) {
                ykkosia = false;
            }
            if (m.getLayout()[i][m.getSize() - 1] != 1) {
                ykkosia = false;
            }
        }
        
        assertTrue(ykkosia);
    }
    
    @Test
    public void initializeWithWallsLuoSeinaRuudukon() {
        boolean ruudukko = true;
        
        m.initializeWithWalls();
        
        for (int i = 1; i < m.getSize() - 1; i++) {
            for (int j = 1; j < m.getSize() - 1; j++) {
                if (j % 2 == 0 || i % 2 == 0) {
                    if (m.getLayout()[i][j] != 1) {
                        ruudukko = false;
                    }
                } else {
                    if (m.getLayout()[i][j] != 0) {
                        ruudukko = false;
                    }
                }
            }
        }
        
        assertTrue(ruudukko);
    }
    
    @Test
    public void getFromCoordinatesAntaaOikeanArvon() {
        assertTrue(m.getFromCoordinates(0, 0) == 1 && m.getFromCoordinates(0, 0) == m.getLayout()[0][0]);
    }
    
    @Test
    public void liianIsollaXKoordinaatillaPalautetaanMiinusYksi() {
        assertTrue(m.getFromCoordinates(100, 1) == -1);
    }
    
    @Test
    public void liianIsollaYKoordinaatillaPalautetaanMiinusYksi() {
        assertTrue(m.getFromCoordinates(0, 100) == -1);
    }
    
    @Test
    public void liianPienellaYKoordinaatillaPalautetaanMiinusYksi() {
        assertTrue(m.getFromCoordinates(0, -1) == -1);
    }
    
    @Test
    public void liianPienellaXKoordinaatillaPalautetaanMiinusYksi() {
        assertTrue(m.getFromCoordinates(-5, 0) == -1);
    }
    
    @Test
    public void setToCoordinatesAsettaaOikeanArvonOikeaanKohtaan() {
        m.setToCoordinates(5, 6, 7);
        assertTrue(m.getLayout()[5][6] == 7);
    }
    
    @Test
    public void eiKaaduHuonoihinAsetettavanKoordinaatteihin() {
        m.setToCoordinates(-5, 6, 7);
        m.setToCoordinates(5, -6, 7);
        m.setToCoordinates(55, 6, 7);
        m.setToCoordinates(5, 66, 7);
    }
    
    @Test
    public void setLayoutAsettaaOikeanPohjan() {
        int[][] uusi = new int[][]{
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4}
        };
        
        m.setLayout(uusi);
        
        boolean oikein = true;
        
        for (int i = 0; i < m.getSize(); i++) {
            for (int j = 0; j < m.getSize(); j++) {
                if (m.getLayout()[i][j] != uusi[i][j]) {
                    oikein = false;
                }
            }
        }
        
        assertTrue(oikein);
    }
    
    @Test
    public void setLayoutVaihtaaKoonOikeaksi() {
        int[][] uusi = new int[][]{
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4}
        };
        
        m.setLayout(uusi);
        
        assertTrue(m.getSize() == 5);
    }
    
    @Test
    public void voidaanAsettaaPohjaksiVainNKertaaNTaulukoita() {

        int[][] uusi = new int[][]{
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
        };
        
        m.setLayout(uusi);
        
        assertTrue(m.getLayout().length == m.getLayout()[0].length);
    }
}
