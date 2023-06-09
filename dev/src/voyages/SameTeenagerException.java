package voyages;

public class SameTeenagerException extends Exception {
	/**
     * Constructs a {@code SameCountryException} with no detail message.
     */
    public SameTeenagerException() { super(); }

    /**
     * Constructs a {@code SameCountryException} with the specified detail message.
     * @param msg the detail message.
     */
    public SameTeenagerException(String msg) { super(msg); }
}