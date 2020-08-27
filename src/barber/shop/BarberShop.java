package barber.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that makes all the transactions of a barber shop.
 */
public class BarberShop {

	private static final Logger logger = LogManager.getLogger(BarberShop.class);

	/**
	 * Hash for all the customers. Size = 24.
	 */
	private final Customer[][] timetable;

	/**
	 * Class constructor for the specified hours in Constants class.
	 */
	public BarberShop() {
		this.timetable = new Customer[Constants.MAX_HOUR + 1][4];
	}

	public Customer[][] getTimetable() {
		return this.timetable;
	}

	/**
	 * Makes a reservation for a new valid customer.
	 * Inserts the new customer in the position (Hash, Array) = (Hour, Minute).
	 *
	 * @param customer the customer to make the reservation.
	 * @throws BarberException if the new customer values are incorrect or other customer holds the specified position.
	 */
	public void addReservation(Customer customer) throws BarberException {
		if (this.contains(customer)) {
			throw new BarberException(customer.getName() + " already has a reservation");
		}

		int hashPos = this.hourPosition(customer);
		int listPos = this.minutePosition(customer);

		// Checks if other customer already holds the same position as the new customer.
		if (this.timetable[hashPos][listPos] != null) {
			throw new BarberException("Other customer has a reservation in that hour");
		}

		this.timetable[hashPos][listPos] = customer;
		logger.debug("Added: {}", customer);
	}

	/**
	 * Removes an existing reservation of the specified customer.
	 *
	 * @param customer the customer to remove the reservation.
	 */
	public void cancelReservation(Customer customer) throws BarberException {
		if (this.timetable[this.hourPosition(customer)][this.minutePosition(customer)] == null){
			throw new BarberException("No customer has a reservation in that hour. Can't remove an empty slot");
		} else {
			this.timetable[this.hourPosition(customer)][this.minutePosition(customer)] = null;
			logger.debug("Cancelled: {} {}", customer.getName(), customer.getTime());
		}
	}

	/**
	 * Modifies an existing reservation.
	 *
	 * @param oldCustomer the customer to modify the reservation.
	 * @param newCustomer the new customer to add.
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
	 * @param time the time to select the customer.
	 * @return the customer selected.
	 * @throws BarberException if there is no customer in the selected position.
	 */
	public Customer getCustomerByTime(Time time) throws BarberException {
		int[] aux = time.hourMinutePositions();
		if (this.timetable[aux[0]][aux[1]] == null) {
			throw new BarberException("No customer has a reservation in that hour");
		}

		return this.timetable[aux[0]][aux[1]];
	}

	/**
	 * Calculates the money payment.
	 *
	 * @param paid  money paid.
	 * @param toPay sum of all cuts offered.
	 * @return the cashback if the customer paid more money.
	 * @throws BarberException if the customer owes some money.
	 */
	public float exchange(float paid, float toPay) throws BarberException {
		float exchange = paid - toPay;

		if (exchange < 0) {
			throw new BarberException("The customer owe some money: " + exchange * (-1));
		}

		return exchange;
	}

	/**
	 * Returns the position of the customer in the Hash.
	 *
	 * @param customer Customer to get the hour.
	 * @return the hour where the customer must be in the Hash.
	 */
	private int hourPosition(Customer customer) {
		return customer.getTime().hourMinutePositions()[0];
	}

	/**
	 * Returns the position of the customer in the ArrayList.
	 *
	 * @param customer Customer to get the minute.
	 * @return the minute where the customer must be in the ArrayList.
	 */
	private int minutePosition(Customer customer) {
		return customer.getTime().hourMinutePositions()[1];
	}

	/**
	 * Checks if the customer given is contained in the table.
	 *
	 * @param customer the customer to check.
	 * @return <code>true</code> if customer is in the table, <code>false</code> otherwise
	 */
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
					output.append(c.toString()).append('\n');
				}
			}
		}
		return output.toString();
	}

}
