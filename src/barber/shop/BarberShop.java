package barber.shop;

import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;

import barber.shop.exceptions.BarberException;

/**
 * Class that makes all the transactions of a barber shop
 */
public class BarberShop {

	private static final Logger logger = LogManager.getLogger(BarberShop.class);

	/**
	 * Hash for all the customers. Size = 24.
	 */
	private HashMap<Integer, ArrayList<Customer>> customersTimeHashMap;

	/**
	 * Class constructor for 24 hours
	 */
	public BarberShop() {
		this.customersTimeHashMap = new HashMap<>(Constants.MAX_HOUR + 1);
		for (int i = 0; i < Constants.MAX_HOUR + 1; i++) {
			this.customersTimeHashMap.put(i, new ArrayList<Customer>(1));
			for (int j = 0; j < 4; j++) {
				this.customersTimeHashMap.get(i).add(j, null);
			}
		}

	}

	/**
	 * Makes a reservation for a new valid customer.
	 *
	 * @param customer the customer to make the reservation.
	 * @throws BarberException if the customer values are incorrect.
	 */
	public void addReservation(Customer customer) throws BarberException {
		if (this.isPossibleToMakeTransaction(customer)) {
			int position = this.hashPosition(customer);

			// Insertion of the new customer in the position (Hash, ArrayList) = (Hour, Minute)
			this.customersTimeHashMap.get(position).add(this.listPosition(customer), customer);
		} else {
			throw new BarberException("Invalid values. Try again");
		}
	}

	/**
	 * Removes an existing reservation of the specified customer.
	 *
	 * @param customer the customer to remove the reservation.
	 * @throws BarberException if the customer values are incorrect.
	 */
	public void cancelReservation(Customer customer) throws BarberException {
		if (this.isPossibleToMakeTransaction(customer)) {
//			this.customersTimeHashMap.get(this.hashPosition(customer)).remove(this.listPosition(customer));
		} else {
			throw new BarberException("Invalid values. Try again");
		}
	}

	/**
	 * Modifies an existing reservation.
	 *
	 * @param customer the customer to modify the reservation.
	 * @throws BarberException if the customer values are incorrect.
	 */
	public void modifyReservation(Customer customer) throws BarberException {
		if (this.isPossibleToMakeTransaction(customer)) {

		} else {
			throw new BarberException("Invalid values. Try again");
		}

	}

	/**
	 * Calculates the money payment
	 *
	 * @param paid  money paid
	 * @param toPay sum of all cuts offered
	 * @return the cashback if the customer paid more money
	 * @throws BarberException if the customer owes some money
	 */
	public float exchange(float paid, float toPay) throws BarberException {
		float exchange = paid - toPay;

		if (exchange < 0) {
			throw new BarberException("The customer owe some money: " + exchange * (-1));
		}

		return exchange;
	}

	/**
	 * Returns the position of the customer in the Hash
	 *
	 * @param customer Customer to get the hour
	 * @return the hour where the customer must be in the Hash
	 */
	private int hashPosition(Customer customer) {
		return customer.getHour() % 24;
	}

	/**
	 * Returns the position of the customer in the ArrayList
	 *
	 * @param customer Customer to get the minute
	 * @return the minute where the customer must be in the ArrayList
	 */
	private int listPosition(Customer customer) {
		int time = customer.getMinute();

		if (0 <= time && time < 15) {
			return 0;
		} else if (15 <= time && time < 30) {
			return 1;
		} else if (30 <= time && time < 45) {
			return 2;
		} else {
			return 3;
		}
	}

	/**
	 * Checks that the customer values are right.
	 *
	 * @param customer Customer to make a transaction.
	 * @return true if is a valid customer, false otherwise.
	 */
	public boolean isPossibleToMakeTransaction(Customer customer) {
		boolean isPossible = true;

		if (customer == null) {
			throw new IllegalArgumentException("Bad customer parameter");
		}

		if ((customer.getName() == null || customer.getName().length() == 0)
			|| (customer.getHour() < Constants.MIN_HOUR || customer.getHour() > Constants.MAX_HOUR)
			|| (customer.getMinute() < Constants.MIN_MINUTE || customer.getMinute() > Constants.MAX_MINUTE)
			|| (customer.getPlace() == null || customer.getPlace().length() == 0)) {

			isPossible = false;
		}

		return isPossible;
	}

	public void printHash() {
		for (int i = 0; i < this.customersTimeHashMap.size(); i++) {
			ArrayList<Customer> list = this.customersTimeHashMap.get(i);
			for (Customer cust :
				list) {
				if (cust != null) {
					System.out.println(cust.toString());
				}
			}
		}
	}

}
