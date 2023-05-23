package voyages;

/**Thrown when the value of country is not supporting criterion using the {@code CountryName}.
 * @see CountryName
 */
public class CountryValueException extends Exception {
    
    /**
     * Constructs a {@code CountryValueException} with no detail message.
     */
    public CountryValueException() { super(); }

    /**
     * Constructs a {@code CountryValueException} with the specified detail message.
     * @param msg the detail message.
     */
    public CountryValueException(String msg) { super(msg); }
}