package voyages;

import java.io.Serializable;
import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;

public class History implements Serializable {
    
    private ArrayList<Tuple<Teenager>> teenagers;
    private LocalDate year;
    private static final long serialVersionUID = 1L;

    public History(ArrayList<Tuple<Teenager>> teenagers, LocalDate year) {
        this.teenagers = teenagers;
        this.year = year;
    }

    public History(LocalDate year) {
        this(new ArrayList<>(), year);
    }

    public LocalDate getYear() {
        return year;
    }

    /** Writes the object to the specified output stream.
     * @param out the output stream.
     * @throws IOException if an I/O error occurs while writing to the stream.
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(teenagers);
        out.writeObject(year);
    }

    /** Reads the object from the specified input stream.
     * @param ois the input stream.
     * @throws IOException if an I/O error occurs while reading from the stream.
     * @throws ClassNotFoundException if the class of the serialized object cannot be found.
     */
    private void readObject(java.io.ObjectInputStream ois) throws IOException, ClassNotFoundException {
        teenagers = (ArrayList<Tuple<Teenager>>) ois.readObject();
        year = (LocalDate) ois.readObject();
    }

    @Override
    public String toString() {
        return "History [teenagers=" + teenagers.toString() + ", year=" + getYear() + "]";
    }
}