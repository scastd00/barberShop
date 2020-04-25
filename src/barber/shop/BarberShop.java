package barber.shop;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * BarberShop
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
		byte posAdd = this.map(hour, minute);
		this.customersTimetable[posAdd] = new Customer(name, hour, minute, place);
	}

	public void cancelReservation(String name, byte hour, byte minute, String place) throws BarberException {
		byte posCancel = this.map(hour, minute);
		this.customersTimetable[posCancel] = null;
	}

	public void modifyReservation(String name, byte hour, byte minute, String place) throws BarberException {
		byte posMod = this.map(hour, minute);
		this.customersTimetable[posMod] = new Customer(name, hour, minute, place);
	}

	public float exchange(float paid, float toPay) throws BarberException {
		float exchange = paid - toPay;

		if (exchange < 0) {
			throw new BarberException("The customer owe some money");
		}

		return exchange;
	}

	private byte map(byte hour, byte minute) {
		return 0;
	}


}
