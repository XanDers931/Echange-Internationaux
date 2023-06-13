package voyages;

/** The {@code Tuple} class, it represents a couple of two of the same element.
 * @param <E> the type of the element to be stored.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.3, 06/13/23
 */
public class Tuple<E> implements java.io.Serializable {
    
    /** The first element of the {@code Tuple}. */
    private E first;

    /** The second element of the {@code Tuple}. */
    private E second;
    
    /** Constructs a new {@code Tuple} with the given elements.
     * @param first the first element of the {@code Tuple}.
     * @param second the second element of the {@code Tuple}.
     */
    public Tuple(E first, E second) {
        this.first = first;
        this.second = second;
    }

    /** Constructs a new {@code Tuple} with no elements. */
    public Tuple() {
        this(null, null);
    }
    
    /** Returns the first element of the {@code Tuple}.
     * @return the first element of the {@code Tuple}.
     */
    public E getFirst() {
        return first;
    }
    
    /** Returns the second element of the {@code Tuple}.
     * @return the second element of the {@code Tuple}.
     */
    public E getSecond() {
        return second;
    }

    /** Sets the first element of the {@code Tuple}.
     * @param first the new first element of the {@code Tuple}.
     */
    public void setFirst(E first) {
        this.first = first;
    }

    /** Sets the second element of the {@code Tuple}.
     * @param second the new second element of the {@code Tuple}.
     */
    public void setSecond(E second) {
        this.second = second;
    }

    /** Returns the element of the {@code Tuple} associated with the given element.
     * @param e the "key" element.
     * @return the element of the {@code Tuple} associated with the given element or null if the element is not in the {@code Tuple}.
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

    /** Puts the given elements in the {@code Tuple}.
     * @param e1 the first element of the {@code Tuple}.
     * @param e2 the second element of the {@code Tuple}.
     */
    public void put(E e1, E e2) {
        setFirst(e1);
        setSecond(e2);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }

    /** This method is used to determine if two {@code Tuple} are equals.
     * @param e1 the first {@code Tuple} to be tested.
     * @param e2 the second {@code Tuple} to be tested.
     * @return true if the {@code Tuple} are equals.
     */
	public static <E> boolean equals(E e1, E e2) {
        return e1.equals(e2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tuple<E> other = (Tuple<E>) obj;
        if (first == null) {
            if (other.first != null)
                return false;
        } else if (!first.equals(other.first))
            return false;
        if (second == null) {
            if (other.second != null)
                return false;
        } else if (!second.equals(other.second))
            return false;
        return true;
    }
}