package barber.shop;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UI {
	private static final Logger logger = LogManager.getLogger(UI.class);
	private BarberShop barberShop;
	private Constants haircuts;
	private Byte hour;
	private Byte minute;
	private String name;
	private String place;

	/**
	 * Class constructor
	 *
	 * @param bShop a barber shop
	 */
	public UI(BarberShop bShop) {
		this.barberShop = bShop;
		this.haircuts = new Constants();
		this.hour = 0;
		this.minute = 0;
		this.name = null;
		this.place = null;
	}

	/**
	 * Initializes the program
	 *
	 * @throws BarberException if the option is incorrect
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
				option = Byte.valueOf(input);
			} catch (NumberFormatException e) {
				throw new BarberException(input + " is not a number");
			}

			switch (option) {
				case 1:
					addUIReservation();
					break;
				case 2:
					cancelUIReservation();
					break;
				case 3:
					modifyUIReservation();
					break;
				case 4:
					moneyUITransactions();
					break;
				case 0:
					System.exit(0);
					break;
				default:
					throw new BarberException(input + " is not a valid number");
			}
		} while (option != 0);
	}

	public void addUIReservation() {
		this.transactions();
		try {
			this.barberShop.addReservation(this.name, this.hour, this.minute, this.place);
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	public void cancelUIReservation() {
		this.transactions();
		try {
			this.barberShop.cancelReservation(this.name, this.hour, this.minute, this.place);
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	public void modifyUIReservation() {
		this.transactions();
		try {
			this.barberShop.modifyReservation(this.name, this.hour, this.minute, this.place);
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	public void moneyUITransactions() throws BarberException {
		float sum = 0;
		byte option;
		logger.trace("Introduce the service you offered {}", "\n");
		do {
			logger.trace("Select an option of haircut: {}", "\n");
			showHaircuts();
			String input = Keyboard.readLine().trim();

			try {
				option = Byte.valueOf(input);
			} catch (NumberFormatException e) {
				throw new BarberException(input + " is not a number");
			}

			try {
				sum += haircutCost(option);
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
	 * @throws BarberException A BarberException will be thrown if the input is not
	 *                         a number.
	 */
	public byte inputHour() throws BarberException {
		String input = Keyboard.readLine().trim();
		logger.debug("Input hour: {}", input); // It is shown in the log file

		try {
			return Byte.valueOf(input);
		} catch (NumberFormatException e) {
			throw new BarberException(input + " is not a number.");
		}
	}

	/**
	 * Asks the minute of the reservation we want to apply changes.
	 *
	 * @return (byte) The number of the minute.
	 * @throws BarberException A BarberException will be thrown if the input is not
	 *                         a number.
	 */
	public byte inputMinute() throws BarberException {
		String input = Keyboard.readLine().trim();
		logger.debug("Input minute: {}", input); // It is shown in the log file

		try {
			return Byte.valueOf(input);
		} catch (NumberFormatException e) {
			throw new BarberException(input + " is not a number.");
		}
	}

	/**
	 * Shows the list of the haircuts
	 */
	private void showHaircuts() {
		StringBuilder output = new StringBuilder("0 - Finish\n");
		for (int i = 0; i < Constants.NUM_HAIRCUTS; i++) {
			output.append((i + 1) + " - " + HairCut.HairCutsEnum.values()[i].toString() + "\n");
		}
		logger.trace(output);
	}

	/**
	 * Returns the price of the chosen haircut
	 *
	 * @param option haircut to chose
	 * @return the price of the haircut
	 * @throws BarberException if option is less than 0 or greater than number of
	 *                         haircuts available
	 */
	private float haircutCost(byte option) throws BarberException {
		if (option < 1 || option > Constants.NUM_HAIRCUTS) {
			throw new BarberException("Incorrect value");
		} else {
			return this.haircuts.getHaircutsHashMap().get(option).getPrice();
		}
	}

	/**
	 * Modifies the auxiliary class attributes to make the transactions.
	 */
	private void transactions() {
		try {
			logger.trace("Introduce an hour to modify the reservation: ");
			this.hour = inputHour();

			logger.trace("Introduce a minute to modify the reservation: ");
			this.minute = inputMinute();

			logger.trace("Introduce the name of the customer to modify the reservation: ");
			this.name = Keyboard.readLine().trim();

			logger.trace("Introduce the place where you want to modify the reservation: ");
			this.place = Keyboard.readLine().trim();
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}
}
