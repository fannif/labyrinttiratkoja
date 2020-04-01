
package labyrinthsolver.domain;

import java.util.Objects;

public class Edge implements TwoValues{
    
    private Pair x;
    private Pair y;
    
    public Edge(Pair x, Pair y) {
        this.x = x;
        this.y = y;
    }
    
     /**
     * Palauttaa kaaren ensimmäisen parin
     * @return Ensimmäinen pari
     */
    @Override
    public Pair getX() {
        return x;
    }
    
    /**
     * Palauttaa kaaren toisen parin
     * @return Toinen pari
     */
    @Override
    public Pair getY() {
        return y;
    }

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
        final Edge other = (Edge) obj;
        if (!Objects.equals(this.x, other.x) && !Objects.equals(this.x, other.y)) {
            return false;
        }
        if (!Objects.equals(this.y, other.y) && !Objects.equals(this.y, other.x)) {
            return false;
        }
        if (Objects.equals(this.x, other.x) && Objects.equals(this.y, other.y)) {
            return true;
        }
        if (Objects.equals(this.x, other.y) && Objects.equals(this.y, other.x)) {
            return true;
        }
        return false;
    }
    
    
    
}
