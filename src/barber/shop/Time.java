package barber.shop;

import barber.shop.exceptions.BarberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that represents the time organization of the BarberShop
 */
public class Time {

	private static final Logger logger = LogManager.getLogger(Time.class);
	private int hour;
	private int minute;

	/**
	 * Class constructor.
	 *
	 * @param hour   Hour to set.
	 * @param minute Minute to set.
	 * @throws BarberException if values of hour or minute are incorrect.
	 */
	public Time(int hour, int minute) throws BarberException {
		StringBuilder error = new StringBuilder();

		if (hour < Constants.MIN_HOUR || hour > Constants.MAX_HOUR) {
			error.append("Error: Incorrect hour value");
		}

		if (minute < Constants.MIN_MINUTE || minute > Constants.MAX_MINUTE) {
			error.append("Error: Incorrect minute value");
		}

		if (error.length() > 0) {
			throw new BarberException(error.toString());
		} else {
			this.hour = hour;
			this.minute = minute;
		}
	}

	/**
	 * Empty class constructor. Sets the hour and the minute to 0.
	 */
	public Time() {
		this.hour = 0;
		this.minute = 0;
	}

	/**
	 * Hour getter.
	 *
	 * @return Returns the hour of the time.
	 */
	public int getHour() {
		return this.hour;
	}

	/**
	 * Hour setter.
	 *
	 * @param hour Hour to set.
	 * @throws BarberException if hour value is incorrect.
	 */
	public void setHour(int hour) throws BarberException {
		if (hour < Constants.MIN_HOUR || hour > Constants.MAX_HOUR) {
			throw new BarberException("Error: Incorrect hour value");
		} else {
			this.hour = hour;
		}
	}

	/**
	 * Minute getter.
	 *
	 * @return Returns the minute of the time.
	 */
	public int getMinute() {
		return this.minute;
	}

	/**
	 * Minute setter.
	 *
	 * @param minute Minute to set.
	 * @throws BarberException if minute value is incorrect.
	 */
	public void setMinute(int minute) throws BarberException {
		if (minute < Constants.MIN_HOUR || minute > Constants.MAX_HOUR) {
			throw new BarberException("Error: Incorrect minute value");
		} else {
			this.minute = minute;
		}
	}

	/**
	 * Returns the String of the time.
	 *
	 * @return The String of the time in the format hh:mm.
	 */
	@Override
	public String toString() {
		return String.format("%d:%d", this.hour, this.minute);
	}

}
