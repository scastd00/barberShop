package barber.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;

/**
 * Class that makes all the transactions of a barber shop.
 */
public class BarberShop {

	private static final Logger logger = LogManager.getLogger(BarberShop.class);

	/**
	 * Array for all the customers. Default size = 24 * 4.
	 */
	private final Customer[][] timetable;

	/**
	 * Class constructor for the specified hours in Constants class.
	 */
	@Contract(pure = true)
	public BarberShop() {
		this.timetable = new Customer[Constants.MAX_HOUR + 1][Constants.ARRAY_SIZE];
	}

	public Customer[][] getTimetable() {
		return this.timetable;
	}

	/**
	 * Makes a reservation for a new valid customer.
	 * Inserts the new customer in the position (Hour array, Minute array) = (Hour, Minute).
	 *
	 * @param customer the customer to make the reservation.
	 *
	 * @throws BarberException if the values of the new customer are incorrect or other customer holds the specified position.
	 */
	public void addReservation(Customer customer) throws BarberException {
		if (this.contains(customer)) {
			throw new BarberException(customer.getName() + " already has a reservation");
		}

		int hourPosition = this.hourPosition(customer);
		int minutePosition = this.minutePosition(customer);

		// Checks if other customer holds the same position as the new customer.
		if (this.timetable[hourPosition][minutePosition] != null) {
			throw new BarberException("Other customer has a reservation in that hour");
		}

		this.timetable[hourPosition][minutePosition] = customer;
		logger.debug("Added: {}", customer);
	}

	/**
	 * Removes an existing reservation of the specified customer.
	 *
	 * @param customer the customer to remove the reservation.
	 */
	public void cancelReservation(Customer customer) throws BarberException {
		int hourPosition = this.hourPosition(customer);
		int minutePosition = this.minutePosition(customer);

		if (this.timetable[hourPosition][minutePosition] == null) {
			throw new BarberException("No customer has a reservation in that hour. Can't remove an empty slot");
		} else {
			this.timetable[hourPosition][minutePosition] = null;
			logger.debug("Cancelled: {} {}", customer.getName(), customer.getTime());
		}
	}

	/**
	 * Modifies an existing reservation.
	 *
	 * @param oldCustomer the customer to modify the reservation.
	 * @param newCustomer the new customer to add.
	 *
	 * @throws BarberException if the customers' values are incorrect.
	 */
	public void modifyReservation(Customer oldCustomer, Customer newCustomer) throws BarberException {
		this.cancelReservation(oldCustomer);
		this.addReservation(newCustomer);
		logger.debug("Modification done");
	}

	/**
	 * Returns the customer that has a reservation at the selected hour.
	 *
	 * @param sinkTime the sinkTime to select the customer.
	 *
	 * @return the customer selected with the specified sinkTime.
	 *
	 * @throws BarberException if there is no customer in the selected position.
	 */
	public Customer getCustomerByTime(Time sinkTime) throws BarberException {
		int[] positions = sinkTime.hourMinutePositions();

		if (this.timetable[positions[0]][positions[1]] == null) {
			throw new BarberException("No customer has a reservation in that hour");
		}

		return this.timetable[positions[0]][positions[1]];
	}

	/**
	 * Calculates the money payment.
	 *
	 * @param paid  money paid.
	 * @param toPay sum of all cuts offered.
	 *
	 * @return the cashback if the customer paid more money.
	 *
	 * @throws BarberException if the customer owes some money.
	 */
	public float exchange(float paid, float toPay) throws BarberException {
		if ((paid - toPay) < 0) {
			throw new BarberException("The customer owe some money: " + (paid - toPay) * (-1));
		}

		return paid - toPay;
	}

	/**
	 * Returns the position of the customer in the Hour Array.
	 *
	 * @param customer Customer to get the hour.
	 *
	 * @return the hour where the customer must be in the Hour Array.
	 */
	private int hourPosition(Customer customer) {
		return customer.getTime().hourMinutePositions()[0];
	}

	/**
	 * Returns the position of the customer in the Minute Array.
	 *
	 * @param customer Customer to get the minute.
	 *
	 * @return the minute where the customer must be in the Minute Array.
	 */
	private int minutePosition(Customer customer) {
		return customer.getTime().hourMinutePositions()[1];
	}

	/**
	 * Checks if the customer given is contained in the table.
	 *
	 * @param customer the customer to check.
	 *
	 * @return <code>true</code> if customer is in the table, <code>false</code> otherwise
	 */
	@Contract(pure = true)
	private boolean contains(Customer customer) {
		for (Customer[] array : this.timetable) {
			for (Customer c : array) {
				if (c != null && c.equals(customer)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Prints all the customers in the table.
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (Customer[] array : this.timetable) {
			for (Customer c : array) {
				if (c != null) {
					output.append(c).append('\n');
				}
			}
		}

		return output.toString();
	}
}
