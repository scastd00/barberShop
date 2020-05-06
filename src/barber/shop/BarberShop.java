package barber.shop;

import org.apache.logging.log4j.Logger;
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

	public BarberShop() {
		this.customersTimetable = new Customer[Constants.MAX_CUSTOMERS_DAY];
	}

	public void addReservation(String name, byte hour, byte minute, String place) throws BarberException {
		if (this.isPossibleToMakeTransaction(name, hour, minute, place)) {
			byte posAdd = this.map(hour, minute);
			this.customersTimetable[posAdd] = new Customer(name, hour, minute, place);
		} else {
			throw new BarberException("Error: Invalid values");
		}
	}

	public void cancelReservation(String name, byte hour, byte minute, String place) throws BarberException {
		if (this.isPossibleToMakeTransaction(name, hour, minute, place)) {
			byte posCancel = this.map(hour, minute);
			this.customersTimetable[posCancel] = null;
		} else {
			throw new BarberException("Error: Invalid values");
		}
	}

	public void modifyReservation(String name, byte hour, byte minute, String place) throws BarberException {
		if (this.isPossibleToMakeTransaction(name, hour, minute, place)) {
			byte posMod = this.map(hour, minute);
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

	private byte map(byte hour, byte minute) {
		if (hour == 0)
			return (byte) (minute % 4);
		else
			return (byte) (hour * 60 + minute % 4); // TODO: comprobar 
	}

	private boolean isPossibleToMakeTransaction(String name, byte hour, byte minute, String place) {
		boolean isPossible = true;

		if ((name.length() == 0) || (hour < Constants.MIN_HOUR || hour > Constants.MAX_HOUR)
				|| (minute < Constants.MIN_MINUTE || minute > Constants.MAX_MINUTE) || (place.length() == 0)) {

			isPossible = false;
		}

		return isPossible;
	}

}
