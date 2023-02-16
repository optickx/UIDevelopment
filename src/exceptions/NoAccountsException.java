package exceptions;

/**
 *
 * @author 2dam
 */
public class NoAccountsException extends Exception {

    /**
     * Creates a new instance of <code>NegativeNumberException</code> without detail
     * message.
     */
    public NoAccountsException() {
    }

    /**
     * Constructs an instance of <code>NegativeNumberException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoAccountsException(String msg) {
        super("Este customer no tiene ninguna cuenta");
    }
}
