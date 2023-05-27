package voyages;

import java.io.Serializable;
import java.time.LocalDate;
import java.io.IOException;

public class History implements Serializable {
    
    private Tuple<Teenager> teenagers;
    private LocalDate year;
    private static final long serialVersionUID = 1L;

    public History(Tuple<Teenager> teenagers, LocalDate year) {
        this.teenagers = teenagers;
        this.year = year;
    }

    public History(Teenager first, Teenager second, LocalDate year) {
        this(new Tuple<Teenager>(first, second), year);
    }

    public LocalDate getYear() {
        return year;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(teenagers);
        out.writeObject(year);
    }

    private void readObject(java.io.ObjectInputStream ois) throws IOException, ClassNotFoundException {
        teenagers = (Tuple<Teenager>) ois.readObject();
        year = (LocalDate) ois.readObject();
    }

    @Override
    public String toString() {
        return "History [teenagers=" + teenagers.toString() + ", year=" + getYear() + "]";
    }
}