package barber.shop;

import barber.shop.exceptions.BarberException;
import barber.shop.windowsystem.SwingWindow;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MainBarberShop {

	private static final Logger logger = LogManager.getLogger(MainBarberShop.class);

//	public static void main(String[] args) {
//		BarberShop bs = new BarberShop();
//		TextUI ui = new TextUI(bs);
//		try {
//			ui.init();
//		} catch (BarberException e) {
//			logger.warn("Unexpected. {}", e.getMessage());
//		}
//	}

	public static void main(String[] args) {
		BarberShop bs = new BarberShop();
		TextUI ui = new TextUI(bs);

		try {
			bs.addReservation(new Customer("Sam1", 12, 28, "Ast"));
			bs.addReservation(new Customer("Sam3", 12, 30, "Ast"));
			bs.addReservation(new Customer("Sam2", 12, 16, "Ast"));
			bs.addReservation(new Customer("Sam2", 12, 31, "Ast"));
			bs.printHash();
		} catch (BarberException e) {
			logger.fatal("Exception");
		}
	}
}
