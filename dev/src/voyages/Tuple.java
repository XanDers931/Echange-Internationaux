package voyages;

public class Tuple<E> {
    
    private E first;
    private E second;
    
    public Tuple(E first, E second) {
        this.first = first;
        this.second = second;
    }
    
    public E getFirst() {
        return first;
    }
    
    public E getSecond() {
        return second;
    }

    public E get(E e) {
        if (e.equals(first)) {
            return second;
        } else if (e.equals(second)) {
            return first;
        }
        return null;
    }
    
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}