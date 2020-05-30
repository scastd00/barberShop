package barber.shop;

import org.apache.logging.log4j.Logger;

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
	 * Array for all the customers of the day.
	 */
	private Customer[] customersTimetable;

	/**
	 * Hash for all the customers. Size: 24
	 */
	private HashMap<Byte, ArrayList<Customer>> customersTimeHashMap;

	/**
	 * Class constructor for MAX_CUSTOMERS_DAY
	 */
	public BarberShop() {
		this.customersTimetable = new Customer[6];
		this.customersTimeHashMap = new HashMap<>(24);
	}

	/**
	 * Class constructor for a custom size
	 *
	 * @param customers number of customers for the array
	 */
	public BarberShop(int customers) {
		this.customersTimetable = new Customer[customers];
		this.customersTimeHashMap = new HashMap<>(24);
	}

	public void addReservation(Customer customer) throws BarberException {
		if (this.isPossibleToMakeTransaction(customer.getName(), customer.getHour(), customer.getMinute(),
				customer.getPlace())) {
			byte position = this.getHashHour(customer);
			ArrayList<Customer> customersArray = this.customersTimeHashMap.get(position);

			if (customersArray == null) {
				this.customersTimeHashMap.put(position, new ArrayList<Customer>(6));
			}

			byte posList = this.getHashMinute(customer);
			this.customersTimeHashMap.get(position).add(posList, customer);
		} else {
			throw new BarberException("Error: Invalid values. Try again");
		}
	}

	public void cancelReservation(Customer customer) throws BarberException {
		if (this.isPossibleToMakeTransaction(customer.getName(), customer.getHour(), customer.getMinute(),
				customer.getPlace())) {
			this.customersTimeHashMap.get(this.getHashHour(customer)).remove(this.getHashMinute(customer));
		} else {
			throw new BarberException("Error: Invalid values. Try again");
		}
	}

	public void modifyReservation(Customer customer) throws BarberException {
		if (this.isPossibleToMakeTransaction(customer.getName(), customer.getHour(), customer.getMinute(),
				customer.getPlace())) {

		} else {
			throw new BarberException("Error: Invalid values. Try again");
		}

	}

	public float exchange(float paid, float toPay) throws BarberException {
		float exchange = paid - toPay;

		if (exchange < 0) {
			throw new BarberException("The customer owe some money: " + exchange * (-1));
		}

		return exchange;
	}

	private byte getHashHour(Customer customer) {
		return (byte) (customer.getHour() % 24);
	}

	private byte getHashMinute(Customer customer) {
		return (byte) (customer.getMinute() % 60);
	}

	public boolean isPossibleToMakeTransaction(String name, byte hour, byte minute, String place) {
		boolean isPossible = true;

		if ((name.length() == 0) || (hour < Constants.MIN_HOUR || hour > Constants.MAX_HOUR)
				|| (minute < Constants.MIN_MINUTE || minute > Constants.MAX_MINUTE) || (place.length() == 0)) {

			isPossible = false;
		}

		return isPossible;
	}

}
