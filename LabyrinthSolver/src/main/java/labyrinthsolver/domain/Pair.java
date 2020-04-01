
package labyrinthsolver.domain;

/**
 * Kahden arvon muodostama yksikkö, jota voidaan käyttää
 * koordinaattien säilömiseen
 */
public class Pair implements TwoValues{
    
    private int x;
    private int y;
    
    /**
     * Kostruktori asettaa parin kaksi arvoa
     * @param x
     * @param y 
     */
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Palauttaa parin ensimmäisen arvon
     * @return 
     */
    @Override
    public Integer getX() {
        return x;
    }
    
    /**
     * Palauttaa parin toisen arvon
     * @return 
     */
    public Integer getY() {
        return y;
    }

    /**
     * Metodi hashausta varten HashSetille
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.x;
        hash = 83 * hash + this.y;
        return hash;
    }
    
    /**
     * Metodi vertailua varten
     * @param obj Vertailtava objekti.
     * @return True jos objektit ovat samat. Muuten false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    
     
}
