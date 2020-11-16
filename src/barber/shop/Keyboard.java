package barber.shop;

import org.jetbrains.annotations.Contract;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class that reads lines introduced in the console.
 */
public final class Keyboard {

	@Contract(value = " -> fail", pure = true)
	private Keyboard() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Read a line from console.
	 *
	 * @return String written in the command.
	 *
	 * @throws BarberException A BarberException will be thrown if there would
	 *                         be any issue with the I/O system.
	 */
	public static String readLine() throws BarberException {
		String line;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			line = br.readLine();
		} catch (Exception e) {
			throw new BarberException("ERROR: Something went wrong with I/O. Please, reenter the input");
		}

		return line;
	}

}