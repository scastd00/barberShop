package barber.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that represents the User Interface (Console) of the application.
 */
public class TextUI {

	/**
	 * Logger of the class.
	 */
	private static final Logger logger = LogManager.getLogger(TextUI.class);

	/**
	 * Constant used to log an error when the user does not introduce a number.
	 */
	private static final String NAN = "{} is not a number";

	/**
	 * The Barber Shop used in the program.
	 */
	private final BarberShop barberShop;

	/**
	 * All the haircuts available.
	 */
	private final Constants haircuts;

	/**
	 * Class constructor.
	 *
	 * @param bShop a barber shop.
	 */
	public TextUI(BarberShop bShop) {
		this.barberShop = bShop;
		this.haircuts = new Constants();
	}

	/**
	 * Initializes the program.
	 *
	 * @throws BarberException if the option is incorrect.
	 * @deprecated since program has GUI.
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
					logger.warn(NAN, input);
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
							logger.warn("Invalid option, try again");
					}
				}
			} catch (NumberFormatException e) {
				logger.warn(NAN, input);
			} catch (BarberException e) {
				logger.debug(e.getMessage());
			}
			logger.trace(this.barberShop);
		} while (true);
	}

	/**
	 * Adds a reservation for the specified customer.
	 */
	private void addReservation() {
		try {
			this.barberShop.addReservation(this.customerModification(Constants.MODIFY_ALL));
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * Cancels a reservation of the specified customer.
	 */
	private void cancelReservation() {
		try {
			this.barberShop.cancelReservation(this.customerModification(Constants.MODIFY_TIME));
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * Modifies a reservation for the specified customer.
	 */
	private void modifyReservation() {
		try {
			logger.trace("Introduce the customer you want to modify\n");
			Customer oldC = this.customerModification(Constants.MODIFY_TIME);

			logger.trace("Introduce the new customer\n");
			Customer newC = this.customerModification(Constants.MODIFY_ALL);

			this.barberShop.modifyReservation(oldC, newC);
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * Manages money transactions.
	 *
	 * @throws BarberException if the entered value is incorrect.
	 */
	private void moneyTransactions() throws BarberException {
		float sum = 0.0f;
		byte option = 1;
		logger.trace("Introduce the service you offered {}", "\n");

		do {
			logger.trace("Select an option of haircut: {}", "\n");
			this.showHaircuts();
			String input = Keyboard.readLine().trim();

			try {
				option = Byte.parseByte(input);
				sum += haircutCost(option);
			} catch (NumberFormatException e) {
				logger.warn(NAN, input);
			} catch (BarberException e) {
				logger.warn(e.getMessage());
			}

		} while (option != 0);

		logger.trace("Total to pay {}", sum);
	}

	/**
	 * Asks the hour of the reservation we want to apply changes.
	 *
	 * @return The number of the hour.
	 *
	 * @throws BarberException if the input is not a number.
	 */
	public int inputHour() throws BarberException {
		String input = Keyboard.readLine().trim();
		logger.debug("Input hour: {}", input);

		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new BarberException(input + " is not a number");
		}
	}

	/**
	 * Asks the minute of the reservation we want to apply changes.
	 *
	 * @return The number of the minute.
	 *
	 * @throws BarberException if the input is not a number.
	 */
	public int inputMinute() throws BarberException {
		String input = Keyboard.readLine().trim();
		logger.debug("Input minute: {}", input);

		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new BarberException(input + " is not a number");
		}
	}

	/**
	 * Shows the list of the haircuts.
	 */
	private void showHaircuts() {
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < Constants.NUM_HAIRCUTS; i++) {
			output.append(i).append(" - ").append(HairCuts.values()[i].toString()).append("\n");
		}

		logger.trace(output);
	}

	/**
	 * Returns the price of the chosen haircut.
	 *
	 * @param option haircut chosen.
	 *
	 * @return haircut price.
	 *
	 * @throws BarberException if option is less than 0 or greater than number of haircuts available.
	 */
	private float haircutCost(int option) throws BarberException {
		if (option < 0 || option > Constants.NUM_HAIRCUTS) {
			throw new BarberException(" Incorrect value");
		} else {
			return this.haircuts.getHairCuts()[option].getPrice();
		}
	}

	/**
	 * Creates a customer with the specified parameters.
	 *
	 * @param modify 1 - Time, 2 - All.
	 *
	 * @return a new Customer with the new properties introduced by the user.
	 *
	 * @throws BarberException if there is no customer in the selected position. Exception treated in {@link #addReservation()},
	 *                         {@link #cancelReservation()} and {@link #modifyReservation()} methods.
	 */
	private Customer customerModification(int modify) throws BarberException {
		Customer customer = new Customer();

		logger.trace("Introduce a hour: ");
		int hour = inputHour();

		logger.trace("Introduce a minute: ");
		int minute = inputMinute();
		Time time = new Time(hour, minute);
		customer.setTime(time);

		if (modify == 1) {
			Customer aux = this.barberShop.getCustomerByTime(time);
			customer.setName(aux.getName());
			customer.setPlace(aux.getPlace());

		} else if (modify == 2) {
			logger.trace("Introduce the name of the customer: ");
			customer.setName(Keyboard.readLine().trim());

			logger.trace("Introduce the place where the customer want to reserve: ");
			customer.setPlace(Keyboard.readLine().trim());
		}

		return customer;
	}
}
