package barber.shop;

import barber.shop.exceptions.BarberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that makes all the transactions of a barber shop.
 */
public class BarberShop {

	private static final Logger logger = LogManager.getLogger(BarberShop.class);

	/**
	 * Hash for all the customers. Size = 24.
	 */
	private final HashMap<Integer, ArrayList<Customer>> customersTimeHashMap;

	/**
	 * Class constructor for the specified hours in Constants class.
	 */
	public BarberShop() {
		this.customersTimeHashMap = new HashMap<>(Constants.MAX_HOUR + 1);
		for (int i = 0; i < Constants.MAX_HOUR + 1; i++) {
			this.customersTimeHashMap.put(i, new ArrayList<>(1));
			for (int j = 0; j < 4; j++) {
				this.customersTimeHashMap.get(i).add(j, null);
			}
		}
	}

	/**
	 * Getter of the hashMap attribute.
	 *
	 * @return the hashMap of the customers.
	 */
	public Map<Integer, ArrayList<Customer>> getCustomersTimeHashMap() {
		return this.customersTimeHashMap;
	}

	/**
	 * Returns the customer that has a reservation at the selected hour.
	 *
	 * @param time the hour to select the customer.
	 * @return the customer selected.
	 * @throws BarberException if there is no customer in the selected position.
	 */
	public Customer getCustomerByTime(Time time) throws BarberException {
		int[] aux = time.hashListPositions();
		if (this.customersTimeHashMap.get(aux[0]).get(aux[1]) == null) {
			throw new BarberException("No customer has a reservation in that hour");
		}

		return this.customersTimeHashMap.get(aux[0]).get(aux[1]);
	}

	/**
	 * Makes a reservation for a new valid customer.
	 * Inserts the new customer in the position (Hash, ArrayList) = (Hour, Minute).
	 *
	 * @param customer the customer to make the reservation.
	 * @throws BarberException if the new customer values are incorrect or other customer holds the same position.
	 */
	public void addReservation(Customer customer) throws BarberException {
		if (!this.isPossibleToMakeTransaction(customer)) {
			throw new BarberException("Invalid values. Try again");
		}

		if (this.contains(customer)) {
			throw new BarberException("This customer already has a reservation");
		}

		int hashPos = this.hashPosition(customer);
		int listPos = this.listPosition(customer);

		// Checks if the introduced customer already exists in the specified position.
		if (this.customersTimeHashMap.get(hashPos).get(listPos) != null) {
			throw new BarberException("Other customer has a reservation in that hour");
		}

		this.customersTimeHashMap.get(hashPos).add(listPos, customer);
	}

	/**
	 * Removes an existing reservation of the specified customer.
	 *
	 * @param customer the customer to remove the reservation.
	 * @throws BarberException if the customer values are incorrect.
	 */
	public void cancelReservation(Customer customer) throws BarberException {
		if (!this.isPossibleToMakeTransaction(customer)) {
			throw new BarberException("Invalid values. Try again");
		}

		if (!this.contains(customer)) {
			throw new BarberException("This customer does not have a reservation");
		}

		if (this.customersTimeHashMap.get(this.hashPosition(customer)).get(this.listPosition(customer)).equals(customer)) {
			this.customersTimeHashMap.get(this.hashPosition(customer)).remove(this.listPosition(customer));
		}
	}

	/**
	 * Modifies an existing reservation.
	 *
	 * @param oldCustomer the customer to modify the reservation.
	 * @param newCustomer the new customer.
	 * @throws BarberException if the customer values are incorrect.
	 */
	public void modifyReservation(Customer oldCustomer, Customer newCustomer) throws BarberException {
		if (!this.isPossibleToMakeTransaction(oldCustomer) || !this.isPossibleToMakeTransaction(newCustomer)) {
			throw new BarberException("Invalid values. Try again");
		}

		if (!this.contains(oldCustomer)) {
			throw new BarberException(oldCustomer.getName() + " customer does not have a reservation");
		}

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
		return customer.getTime().hashListPositions()[0];
	}

	/**
	 * Returns the position of the customer in the ArrayList.
	 *
	 * @param customer Customer to get the minute.
	 * @return the minute where the customer must be in the ArrayList.
	 */
	private int listPosition(Customer customer) {
		return customer.getTime().hashListPositions()[1];
	}

	/**
	 * Checks that the customer values are right.
	 *
	 * @param customer Customer to make a transaction.
	 * @return <code>true</code> if is a valid customer, <code>false</code> otherwise.
	 */
	public boolean isPossibleToMakeTransaction(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("Bad customer parameter");
		}

		return (customer.getName() != null && customer.getName().length() != 0)
			&& (customer.getTime().getHour() >= Constants.MIN_HOUR && customer.getTime().getHour() <= Constants.MAX_HOUR)
			&& (customer.getTime().getMinute() >= Constants.MIN_MINUTE && customer.getTime().getMinute() <= Constants.MAX_MINUTE)
			&& (customer.getPlace() != null && customer.getPlace().length() != 0);
	}

	/**
	 * Checks if the customer given is contained in the table.
	 *
	 * @param customer the customer to check.
	 * @return <code>true</code> if customer is in the table, <code>false</code> otherwise
	 */
	private boolean contains(Customer customer) {
		for (int i = 0; i < this.customersTimeHashMap.size(); i++) {
			ArrayList<Customer> list = this.customersTimeHashMap.get(i);
			for (Customer c : list) {
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
		for (int i = 0; i < this.customersTimeHashMap.size(); i++) {
			ArrayList<Customer> list = this.customersTimeHashMap.get(i);
			for (Customer c : list) {
				if (c != null) {
					logger.info(c);
				}
			}
		}
		logger.info("");
	}

}
