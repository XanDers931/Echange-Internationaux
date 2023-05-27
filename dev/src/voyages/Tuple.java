package voyages;

public class Tuple<E> implements java.io.Serializable {
    
    private E first;
    private E second;
    
    public Tuple(E first, E second) {
        this.first = first;
        this.second = second;
    }
    
    /** Returns the first element of the tuple.
     * @return the first element of the tuple.
     */
    public E getFirst() {
        return first;
    }
    
    /** Returns the second element of the tuple.
     * @return the second element of the tuple.
     */
    public E getSecond() {
        return second;
    }

    /** Returns the element of the tuple associated with the given element.
     * @param e the "key" element.
     * @return the element of the tuple associated with the given element.
     */
    public E get(E e) {
        if (e.equals(first)) {
            return second;
        } else if (e.equals(second)) {
            return first;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}