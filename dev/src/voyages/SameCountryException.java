package voyages;

public class SameCountryException extends Exception {
	/**
     * Constructs a {@code SameCountryException} with no detail message.
     */
    public SameCountryException() { super(); }

    /**
     * Constructs a {@code SameCountryException} with the specified detail message.
     * @param msg the detail message.
     */
    public SameCountryException(String msg) { super(msg); }
}
