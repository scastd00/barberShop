package barber.shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class that serializes the objects to recover the last state of the customers' timetable.
 */
public final class Serialization implements Serialize {

	private File file;
	private static final InputStream in = System.in;
	private static final PrintStream out = System.out;
	private Scanner scanner;

	public Serialization() throws BarberException {
		try {
			this.file = new File("./timetable.txt");
			this.scanner = new Scanner(this.file).useDelimiter(" ");
		} catch (FileNotFoundException e) {
			throw new BarberException("File not found");
		} finally {
			this.scanner.close();
		}
	}

	@Override
	public void serializeCustomer() {

	}

	@Override
	public void serializeTime() {

	}

	@Override
	public void serializePlace() {

	}

	@Override
	public void terminate() {
		this.scanner.close();
	}
}
