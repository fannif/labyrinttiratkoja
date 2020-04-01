
package labyrinthsolver.domain;

public interface TwoValues<T> {
    
        /**
     * Palauttaa parin ensimmäisen arvon
     * @return 
     */
    public T getX();
    
    /**
     * Palauttaa parin toisen arvon
     * @return 
     */
    public T getY();
    
    public boolean equals(Object obj);
    
}
