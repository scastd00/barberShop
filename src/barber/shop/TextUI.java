package barber.shop;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import barber.shop.exceptions.BarberException;

/**
 * Class that represents the User Interface of the app.
 */
public class TextUI {

	private static final Logger logger = LogManager.getLogger(TextUI.class);
	private final BarberShop barberShop;
	private final Constants haircuts;
	// private WindowInterface windowInterface;
	// private WindowPanel panel;

	/**
	 * Class constructor.
	 *
	 * @param bShop a barber shop.
	 */
	public TextUI(BarberShop bShop) {
		this.barberShop = bShop;
		this.haircuts = new Constants();
		// this.windowInterface = new WindowInterface();
		// this.panel = new WindowPanel();
	}

	/**
	 * Initializes the program.
	 *
	 * @throws BarberException if the option is incorrect.
	 */
	public void init() throws BarberException {
		byte option;

		do {
			logger.trace("Select an option{}", "\n");
			logger.trace("0 - Exit");
			logger.trace("1 - Add reservation");
			logger.trace("2 - Cancel reservation");
			logger.trace("3 - Modify reservation");
			logger.trace("4 - Money transactions");

			String input = Keyboard.readLine().trim();

			try {
				if (input.length() == 0) {
					logger.warn("{} is not a number", input);
				} else {
					option = Byte.parseByte(input);

					switch (option) {
						case 1:
							addReservation();
							break;

						case 2:
							cancelReservation();
							break;

						case 3:
							modifyReservation();
							break;

						case 4:
							moneyTransactions();
							break;

						case 0:
							System.exit(0);
							break;

						default:
							logger.warn("Invalid option, try again.");
					}
				}
			} catch (NumberFormatException e) {
				throw new BarberException(input + " is not a number");
			}
			this.barberShop.printHash();
		} while (true);
	}

	/**
	 * Adds a reservation for the specified customer.
	 */
	private void addReservation() {
		try {
			this.barberShop.addReservation(this.customerModification());
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * Cancels a reservation of the specified customer.
	 */
	private void cancelReservation() {
		try {
			this.barberShop.cancelReservation(this.customerModification());
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * Modifies a reservation for the specified customer.
	 */
	private void modifyReservation() {
		try {
			logger.info("Introduce the customer you want to modify\n\n");
			Customer oldC = this.customerModification();
			logger.info("Introduce the customer new customer\n\n");
			Customer newC = this.customerModification();

			this.barberShop.modifyReservation(oldC, newC);
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	private void moneyTransactions() throws BarberException {
		float sum = 0.0f;
		byte option = 0;
		logger.trace("Introduce the service you offered {}", "\n");
		do {
			logger.trace("Select an option of haircut: {}", "\n");
			this.showHaircuts();
			String input = Keyboard.readLine().trim();

			try {
				option = Byte.parseByte(input);
				sum += haircutCost(option);
			} catch (NumberFormatException e) {
				throw new BarberException(input + " is not a number");
			} catch (BarberException e) {
				logger.warn(e.getMessage());
			}

		} while (option != 0);
		logger.trace("Total to pay {}", sum);
	}

	/**
	 * Asks the hour of the reservation we want to apply changes.
	 *
	 * @return (byte) The number of the hour.
	 * @throws BarberException A BarberException will be thrown if the input is
	 *                         not a number.
	 */
	public int inputHour() throws BarberException {
		String input = Keyboard.readLine().trim();
		logger.debug("Input hour: {}", input); // It is shown in the log file

		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new BarberException(input + " is not a number.");
		}
	}

	/**
	 * Asks the minute of the reservation we want to apply changes.
	 *
	 * @return (byte) The number of the minute.
	 * @throws BarberException A BarberException will be thrown if the input is
	 *                         not a number.
	 */
	public int inputMinute() throws BarberException {
		String input = Keyboard.readLine().trim();
		logger.debug("Input minute: {}", input); // It is shown in the log file

		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new BarberException(input + " is not a number.");
		}
	}

	/**
	 * Shows the list of the haircuts.
	 */
	private void showHaircuts() {
		StringBuilder output = new StringBuilder("0 - Finish\n");
		for (int i = 0; i < Constants.NUM_HAIRCUTS; i++) {
			output.append(i + 1).append(" - ").append(HairCut.HairCutsEnum.values()[i].toString()).append("\n");
		}
		logger.trace(output);
	}

	/**
	 * Returns the price of the chosen haircut.
	 *
	 * @param option haircut to chose.
	 * @return the price of the haircut.
	 * @throws BarberException if option is less than 0 or greater than number
	 *                         of haircuts available.
	 */
	private float haircutCost(int option) throws BarberException {
		if (option < 0 || option > Constants.NUM_HAIRCUTS) {
			throw new BarberException("Incorrect value");
		} else {
			return this.haircuts.getHaircutsHashMap().get(option).getPrice();
		}
	}

	/**
	 * Creates a customer with the specified parameters.
	 * @return a new Customer.
	 */
	private Customer customerModification() {
		Customer customer = new Customer();
		try {
			logger.trace("Introduce a hour: ");
			customer.setHour(inputHour());

			logger.trace("Introduce a minute: ");
			customer.setMinute(inputMinute());

			logger.trace("Introduce the name of the customer: ");
			customer.setName(Keyboard.readLine().trim());

			logger.trace("Introduce the place where the customer want to reserve: ");
			customer.setPlace(Keyboard.readLine().trim());
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
		return customer;
	}

}
