package barber.shop;

import barber.shop.exceptions.BarberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * Class that makes all the transactions of a barber shop.
 */
public class BarberShop {

	private static final Logger logger = LogManager.getLogger(BarberShop.class);

	/**
	 * Hash for all the customers. Size = 24.
	 */
	private final HashMap<Integer, Customer[]> timetable;

	/**
	 * Class constructor for the specified hours in Constants class.
	 */
	public BarberShop() {
		this.timetable = new HashMap<>(Constants.MAX_HOUR + 1);
		for (int i = 0; i < Constants.MAX_HOUR + 1; i++) {
			this.timetable.put(i, new Customer[4]);
			for (int j = 0; j < 4; j++) {
				this.timetable.get(i)[j] = null;
			}
		}
	}

	/**
	 * Returns the customer that has a reservation at the selected hour.
	 *
	 * @param time the time to select the customer.
	 * @return the customer selected.
	 * @throws BarberException if there is no customer in the selected position.
	 */
	public Customer getCustomerByTime(Time time) throws BarberException {
		int[] aux = time.hashArrayPositions();
		if (this.timetable.get(aux[0])[aux[1]] == null) {
			throw new BarberException("No customer has a reservation in that hour");
		}

		return this.timetable.get(aux[0])[aux[1]];
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

		int hashPos = this.hashPosition(customer);
		int listPos = this.arrayPosition(customer);

		// Checks if other customer already holds the same position as the new customer.
		if (this.timetable.get(hashPos)[listPos] != null) {
			throw new BarberException("Other customer has a reservation in that hour");
		}

		this.timetable.get(hashPos)[listPos] = customer;
	}

	/**
	 * Removes an existing reservation of the specified customer.
	 *
	 * @param customer the customer to remove the reservation.
	 */
	public void cancelReservation(Customer customer) {
		this.timetable.get(this.hashPosition(customer))[this.arrayPosition(customer)] = null;
	}

	/**
	 * Modifies an existing reservation.
	 *
	 * @param oldCustomer the customer to modify the reservation.
	 * @param newCustomer the new customer to add.
	 * @throws BarberException if the customer values are incorrect.
	 */
	public void modifyReservation(Customer oldCustomer, Customer newCustomer) throws BarberException {
		this.cancelReservation(oldCustomer);
		this.addReservation(newCustomer);
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
	private int hashPosition(Customer customer) {
		return customer.getTime().hashArrayPositions()[0];
	}

	/**
	 * Returns the position of the customer in the ArrayList.
	 *
	 * @param customer Customer to get the minute.
	 * @return the minute where the customer must be in the ArrayList.
	 */
	private int arrayPosition(Customer customer) {
		return customer.getTime().hashArrayPositions()[1];
	}

	/**
	 * Checks if the customer given is contained in the table.
	 *
	 * @param customer the customer to check.
	 * @return <code>true</code> if customer is in the table, <code>false</code> otherwise
	 */
	private boolean contains(Customer customer) {
		for (int i = 0; i < this.timetable.size(); i++) {
			Customer[] array = this.timetable.get(i);
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
	public void printHash() {
		for (int i = 0; i < this.timetable.size(); i++) {
			Customer[] array = this.timetable.get(i);
			for (Customer c : array) {
				if (c != null) {
					logger.info(c);
				}
			}
		}
		logger.trace("");
	}

}
