package voyages;

import java.io.Serializable;
import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;

/** The {@code History} class, it represents the last affectations of an exchange.
 */
public class History implements Serializable {
    
    /**
     * An {@code ArrayList<Tuple<Teenager>>} of teenagers.
     */
    private ArrayList<Tuple<Teenager>> teenagers;

    /**
     * A {@code LocalDate} representing the year of the exchange.
     */
    private LocalDate year;

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * History constructor
     * @param teenagers an {@code ArrayList<Tuple<Teenager>>} of teenagers.
     * @param year a {@code LocalDate} representing the year of the exchange.
     */
    public History(ArrayList<Tuple<Teenager>> teenagers, LocalDate year) {
        this.teenagers = teenagers;
        this.year = year;
    }

    /**
     * Another history constructor
     * @param year a {@code LocalDate} representing the year of the exchange.
     */
    public History(LocalDate year) {
        this(new ArrayList<>(), year);
    }

    /**
     * The year getter.
     * @return the year.
     */
    public LocalDate getYear() {
        return year;
    }

    /** The teenagers getter.
	 * @return the teenagers.
	 */
	public ArrayList<Tuple<Teenager>> getTeenagers() {
		return teenagers;
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