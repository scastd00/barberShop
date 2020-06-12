package barber.shop;

import barber.shop.exceptions.BarberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a customer.
 */
public class Customer {

	private String name;
	private Time time;
	private String place;
	private String code;
	private static final Logger logger = LogManager.getLogger(Customer.class);

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
	public Customer(@NotNull String name, byte hour, byte minute, String place) throws BarberException {
		StringBuilder error = new StringBuilder();

		if (name.length() == 0) {
			error.append("ERROR: you must introduce a valid name\n");
		}

		if (hour < Constants.MIN_HOUR || hour > Constants.MAX_HOUR) {
			error.append("ERROR: hour value must be between 0 and 23\n");
		}

		if (minute < Constants.MIN_MINUTE || minute > Constants.MAX_MINUTE) {
			error.append("ERROR: minute value must be between 0 and 59\n");
		}

		if (place.length() == 0) {
			error.append("You must introduce a valid place\n");
		}

		if (error.length() > 0) {
			throw new BarberException(error.toString());

		} else {
			this.name = name;
			this.time = new Time(hour, minute);
			this.place = place;
			this.code = this.codeGenerator();
		}

	}

	/**
	 * Empty constructor of the class
	 */
	public Customer() {
		this.name = null;
		this.time = new Time();
		this.place = null;
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
	public void setName(@NotNull String name) throws BarberException {
		if (name.length() == 0) {
			throw new BarberException("ERROR: you must introduce a valid name");
		} else {
			this.name = name;
		}
	}

	/**
	 * Hour getter.
	 *
	 * @return Hour of the reservation.
	 */
	public byte getHour() {
		return this.time.getHour();
	}

	/**
	 * Hour setter.
	 *
	 * @param hour The new hour to set.
	 * @throws BarberException if the introduced hour isn't valid.
	 */
	public void setHour(byte hour) throws BarberException {
		if (hour < Constants.MIN_HOUR || hour > Constants.MAX_HOUR) {
			throw new BarberException("ERROR: hour value must be between 0 and 23");
		} else {
			this.time.setHour(hour);
		}
	}

	/**
	 * Minute getter.
	 *
	 * @return Minute of the reservation (between 0 and 59, both included).
	 */
	public byte getMinute() {
		return this.time.getMinute();
	}

	/**
	 * Minute setter.
	 *
	 * @param minute The new minute to set.
	 * @throws BarberException A BarberException will be thrown if the
	 *                         introduced minute isn't valid.
	 */
	public void setMinute(byte minute) throws BarberException {
		if (minute < Constants.MIN_MINUTE || minute > Constants.MAX_MINUTE) {
			throw new BarberException("ERROR: minute value must be between 0 and 59");
		} else {
			this.time.setMinute(minute);
		}
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
	 * @throws BarberException A BarberException will be thrown if the
	 *                         introduced place isn't valid.
	 */
	public void setPlace(String place) throws BarberException {
		if (place.length() == 0) {
			throw new BarberException("ERROR: You must introduce a valid place");
		} else {
			this.place = place;
		}
	}

	/**
	 * Code getter.
	 *
	 * @return The code of each person depending the time of the reservation.
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Generates the code for each customer depending the time of the
	 * reservation.
	 *
	 * @return The code of this person.
	 */
	private String codeGenerator() {
		StringBuilder genCode = new StringBuilder(4);

		if (this.getHour() < 10) {
			genCode.append("0").append(this.getHour());
		} else {
			genCode.append(this.getHour());
		}

		if (this.getMinute() < 10) {
			genCode.append("0").append(this.getMinute());
		} else {
			genCode.append(this.getMinute());
		}

		return genCode.toString();
	}
}
