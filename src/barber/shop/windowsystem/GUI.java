package barber.shop.windowsystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import barber.shop.*;
import barber.shop.exceptions.BarberException;

public class GUI {

	private static final Logger logger = LogManager.getLogger(GUI.class);
	private BarberShop barberShop;
	private Constants haircuts;

	/**
	 * Class constructor
	 *
	 * @param bShop a barber shop
	 */
	public GUI(BarberShop bShop) {
		this.barberShop = bShop;
		this.haircuts = new Constants();
	}

	/**
	 * Adds a reservation for the specified customer
	 */
	private void addReservation(String name, int hour, int minute, String place) {
		try {
			this.barberShop.addReservation(new Customer(name, hour, minute, place));
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * Cancels a reservation of the specified customer
	 */
	private void cancelReservation(String name, int hour, int minute, String place) {
		try {
			this.barberShop.cancelReservation(new Customer(name, hour, minute, place));
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * Modifies a reservation for the specified customer
	 */
	private void modifyReservation(String name, int hour, int minute, String place) {
		try {
			this.barberShop.modifyReservation(new Customer(name, hour, minute, place));
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

	private void moneyTransactions() throws BarberException {
		float sum = 0;
		byte option;
		logger.trace("Introduce the service you offered {}", "\n");
		do {
			logger.trace("Select an option of haircut: {}", "\n");
			showHaircuts();
			String input = Keyboard.readLine().trim();

			try {
				option = Byte.parseByte(input);
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
	 * @throws BarberException A BarberException will be thrown if the input is
	 *                         not a number.
	 */
	public byte inputHour() throws BarberException {
		String input = Keyboard.readLine().trim();
		logger.debug("Input hour: {}", input); // It is shown in the log file

		try {
			return Byte.parseByte(input);
		} catch (NumberFormatException e) {
			logger.debug("{} is not a number.", input);
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
	public byte inputMinute() throws BarberException {
		String input = Keyboard.readLine().trim();
		logger.debug("Input minute: {}", input); // It is shown in the log file

		try {
			return Byte.parseByte(input);
		} catch (NumberFormatException e) {
			logger.debug("{} is not a number.", input);
			throw new BarberException(input + " is not a number.");
		}
	}

	/**
	 * Shows the list of the haircuts
	 */
	private void showHaircuts() {
		StringBuilder output = new StringBuilder("0 - Finish\n");
		for (int i = 0; i < Constants.NUM_HAIRCUTS; i++) {
			output.append(i + 1).append(" - ").append(HairCut.HairCutsEnum.values()[i].toString()).append("\n");
		}
		logger.trace(output);
	}

	/**
	 * Returns the price of the chosen haircut
	 *
	 * @param option haircut to chose
	 * @return the price of the haircut
	 * @throws BarberException if option is less than 0 or greater than number
	 *                         of haircuts available
	 */
	private float haircutCost(byte option) throws BarberException {
		if (option < 0 || option > Constants.NUM_HAIRCUTS) {
			throw new BarberException("Incorrect value");
		} else {
			return this.haircuts.getHaircutsHashMap().get(option).getPrice();
		}
	}

	public void actionPerformed(String action, String fullName, String hour, String minute, String place) {
		try {
			int h = Integer.parseInt(hour);
			int m = Integer.parseInt((minute));
			System.out.println(place);
			System.out.println(action);

			switch (action) {
				case "Add Reservation":
					this.addReservation(fullName, h, m, place);
					break;

				case "Cancel Reservation":
					this.cancelReservation(fullName, h, m, place);
					break;

				case "Modify Reservation":
					this.modifyReservation(fullName, h, m, place);
					break;

				case "Exit":
					System.exit(0);
					break;

				default:
					break;
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid time");
			logger.warn("Invalid time");
		}
	}
}
