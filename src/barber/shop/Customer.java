package barber.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that represents a customer.
 */
public class Customer {

	private static final Logger logger = LogManager.getLogger(Customer.class);
	private String name;
	private Time time;
	private String place;

	/**
	 * Class constructor of the customer.
	 *
	 * @param name   Name of the customer.
	 * @param hour   Hour when the customer want to go to the BarberShop.
	 * @param minute Minute when the customer want to go to the BarberShop.
	 * @param place  Place of the BarberShop.
	 * @throws BarberException A BarberException will be thrown if there's no
	 *                         name, hour, minute or place.
	 */
	public Customer(String name, int hour, int minute, String place) throws BarberException {
		StringBuilder error = new StringBuilder();

		if (name == null || name.trim().length() == 0) {
			error.append(Constants.ERROR + " you must introduce a valid name\n");
		}

		if (hour < Constants.MIN_HOUR || hour > Constants.MAX_HOUR) {
			error.append(Constants.ERROR + " hour value must be between 0 and 23\n");
		}

		if (minute < Constants.MIN_MINUTE || minute > Constants.MAX_MINUTE) {
			error.append(Constants.ERROR + " minute value must be between 0 and 59\n");
		}

		if (place == null || place.trim().length() == 0) {
			error.append(Constants.ERROR + " you must introduce a valid place\n");
		}

		if (error.length() > 0) {
			logger.debug(error);
			throw new BarberException(error.toString());
		} else {
			this.name = name;
			this.time = new Time(hour, minute);
			this.place = place;
		}

	}

	/**
	 * Empty constructor of the class. Empty name, 0:00, empty place.
	 */
	public Customer() {
		this.name = "";
		this.time = new Time();
		this.place = "";
	}

	/**
	 * Name getter.
	 *
	 * @return The name of the customer.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Name setter.
	 *
	 * @param name The new name to set.
	 * @throws BarberException A BarberException will be thrown if the
	 *                         introduced name is incorrect.
	 */
	public void setName(String name) throws BarberException {
		if (name == null || name.length() == 0) {
			throw new BarberException(Constants.ERROR + " You must introduce a valid name");
		} else {
			this.name = name;
		}
	}

	/**
	 * Time getter.
	 *
	 * @return the time of the customer's reservation
	 */
	public Time getTime() {
		return this.time;
	}

	/**
	 * Time setter.
	 *
	 * @param time the time of the reservation to set.
	 */
	public void setTime(Time time) throws BarberException {
		if (time == null) {
			throw new BarberException(Constants.ERROR + " Invalid time assignation");
		}

		this.time = time;
	}

	/**
	 * Place getter.
	 *
	 * @return Place where the customer want to go.
	 */
	public String getPlace() {
		return this.place;
	}

	/**
	 * Place setter.
	 *
	 * @param place Place where the customer want to go.
	 * @throws BarberException if the introduced place isn't valid.
	 */
	public void setPlace(String place) throws BarberException {
		if (place == null || place.length() == 0) {
			throw new BarberException(Constants.ERROR + " You must introduce a valid place");
		} else {
			this.place = place;
		}
	}

	/**
	 * Compares the time of this customer and o customer.
	 *
	 * @param o Other customer
	 * @return <code>true</code> if the name is equal for both customers, <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o instanceof Customer) {
			Customer customer = (Customer) o;
			return this.name.equalsIgnoreCase(customer.name);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() + this.place.hashCode() + this.time.hashCode();
	}

	/**
	 * String version of the customer.
	 *
	 * @return a String with the format: name  time  place.
	 */
	@Override
	public String toString() {
		return this.name + "  " + this.time.toString() + "  " + this.place;
	}
}
