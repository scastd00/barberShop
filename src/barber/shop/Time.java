package barber.shop;

public class Time {

	private byte hour;
	private byte minute;

	public Time(byte hour, byte minute) throws BarberException {
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

	public Time() {
		this.hour = 0;
		this.minute = 0;
	}

	public byte getHour() {
		return this.hour;
	}

	public void setHour(byte hour) throws BarberException {
		if (hour < Constants.MIN_HOUR || hour > Constants.MAX_HOUR) {
			throw new BarberException("Error: Incorrect hour value");
		} else {
			this.hour = hour;
		}
	}

	public byte getMinute() {
		return this.minute;
	}

	public void setMinute(byte minute) throws BarberException {
		if (minute < Constants.MIN_HOUR || minute > Constants.MAX_HOUR) {
			throw new BarberException("Error: Incorrect minute value");
		} else {
			this.minute = minute;
		}
	}

	@Override
	public String toString() {
		return String.format("%d:%d", this.hour, this.minute);
	}

}
