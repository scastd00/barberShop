package barber.shop;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MainBarberShop {

	private static final Logger logger = LogManager.getLogger(MainBarberShop.class);

	public static void main(String[] args) {
		BarberShop bShop = new BarberShop();
		try {
			UI ui = new UI(bShop);
			ui.init();
		} catch (BarberException e) {
			logger.warn(e.getMessage());
		}
	}

}
