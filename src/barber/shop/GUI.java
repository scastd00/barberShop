package barber.shop;

import barber.shop.exceptions.BarberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GUI {
	private static final Logger logger = LogManager.getLogger(GUI.class);
	private final BarberShop barberShop;

	/**
	 * Class constructor.
	 *
	 * @param bShop a barber shop.
	 */
	public GUI(BarberShop bShop) {
		this.barberShop = bShop;
	}

	/**
	 * Adds a reservation for the specified customer.
	 *
	 * @param customer the customer to make the reservation.
	 * @throws BarberException
	 */
	private void addReservation(Customer customer) throws BarberException {
		this.barberShop.addReservation(customer);
	}

	/**
	 * Cancels a reservation of the specified customer.
	 *
	 * @param customer the customer to remove the reservation.
	 * @throws BarberException
	 */
	private void cancelReservation(Customer customer) throws BarberException {
		this.barberShop.cancelReservation(customer);
	}

	/**
	 * Modifies a reservation for the specified customer.
	 *
	 * @param oldC the customer to modify the reservation.
	 * @param newC the new customer.
	 * @throws BarberException
	 */
	private void modifyReservation(Customer oldC, Customer newC) throws BarberException {
		this.barberShop.modifyReservation(oldC, newC);
	}

	public void actionPerformed(String action, String name, String hour, String minute, String place) {
		try {
			int h = Integer.parseInt(hour);
			int m = Integer.parseInt(minute);
			Customer customer = new Customer(name, h, m, place);

			switch (action) {
				case "Add Reservation":
					addReservation(customer);
					break;

				case "Cancel Reservation":
					cancelReservation(customer);
					break;

				default:
					logger.warn("Invalid option, try again.");
			}
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		} catch (NumberFormatException e) {
			logger.warn(Constants.ERROR + " wrong hour or minute");
		}
		this.barberShop.printHash();
	}

	public void actionModifyCustomer(String[] names, String[] hours, String[] minutes, String[] places) {
		try {
			Customer customer0 = new Customer(names[0], Integer.parseInt(hours[0]), Integer.parseInt(minutes[0]), places[0]);
			Customer customer1 = new Customer(names[1], Integer.parseInt(hours[1]), Integer.parseInt(minutes[1]), places[1]);

			modifyReservation(customer0, customer1);
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		} catch (NumberFormatException e) {
			logger.warn(Constants.ERROR + " wrong hour or minute");
		}
		this.barberShop.printHash();
	}
}
