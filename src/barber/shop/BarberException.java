package barber.shop;

/**
 * Class that represents Exceptions thrown during the execution of the program.
 */
public class BarberException extends Exception {

	private static final long serialVersionUID = 6763763780393690535L;

	/**
	 * @param message Message shown in the exception thrown.
	 */
	public BarberException(String message) {
		super(message);
	}
}
