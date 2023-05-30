package voyages;

public class Tuple<E> implements java.io.Serializable {
    
    private E first;
    private E second;
    
    public Tuple(E first, E second) {
        this.first = first;
        this.second = second;
    }

    public Tuple() {
        this(null, null);
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

    public void setFirst(E first) {
        this.first = first;
    }

    public void setSecond(E second) {
        this.second = second;
    }

    /** Returns the element of the tuple associated with the given element.
     * @param e the "key" element.
     * @return the element of the tuple associated with the given element or null if the element is not in the tuple.
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

    public void put(E e1, E e2) {
        setFirst(e1);
        setSecond(e2);
    }

    public static <E> boolean equals(E e1, E e2) {
        return e1.equals(e2);
    }
}