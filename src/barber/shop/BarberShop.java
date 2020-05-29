package barber.shop;

import org.apache.logging.log4j.Logger;

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
	private HashMap<Integer, Customer[]> customerTimeHashMap;

	/**
	 * Class constructor for MAX_CUSTOMERS_DAY
	 */
	public BarberShop() {
		this.customersTimetable = new Customer[6];
		this.customerTimeHashMap = new HashMap<>(24);
	}

	/**
	 * Class constructor for a custom size
	 *
	 * @param customers number of customers for the array
	 */
	public BarberShop(int customers) {
		this.customersTimetable = new Customer[customers];
	}

	public void addReservation(String name, byte hour, byte minute, String place) throws BarberException {
		if (this.isPossibleToMakeTransaction(name, hour, minute, place)) {
			byte posAdd = this.hashFunction(hour, minute);
			this.customersTimetable[posAdd] = new Customer(name, hour, minute, place);
			// Se puede quitar los parámetros y hacer set después
		} else {
			throw new BarberException("Error: Invalid values");
		}
	}

	public void cancelReservation(String name, byte hour, byte minute, String place) throws BarberException {
		if (this.isPossibleToMakeTransaction(name, hour, minute, place)) {
			byte posCancel = this.hashFunction(hour, minute);
			this.customersTimetable[posCancel] = null;
		} else {
			throw new BarberException("Error: Invalid values");
		}
	}

	public void modifyReservation(String name, byte hour, byte minute, String place) throws BarberException {
		if (this.isPossibleToMakeTransaction(name, hour, minute, place)) {
			byte posMod = this.hashFunction(hour, minute);
			this.customersTimetable[posMod] = new Customer(name, hour, minute, place);
		} else {
			throw new BarberException("Error: Invalid values");
		}

	}

	public float exchange(float paid, float toPay) throws BarberException {
		float exchange = paid - toPay;

		if (exchange < 0) {
			throw new BarberException("The customer owe some money: " + exchange * (-1));
		}

		return exchange;
	}

	private byte hashFunction(byte hour, byte minute) {
		return 0;
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
