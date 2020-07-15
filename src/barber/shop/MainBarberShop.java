package barber.shop;

import barber.shop.exceptions.BarberException;
import barber.shop.windowsystem.BarberFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainBarberShop {

	private static final Logger logger = LogManager.getLogger(MainBarberShop.class);

	public static void main(String[] args) {
		try {
			BarberShop bs = new BarberShop();
			TextUI ui = new TextUI(bs);
			ui.init();
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}

	}
}
