package barber.shop;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import barber.shop.exceptions.BarberException;
import barber.shop.windowsystem.SwingWindow;

public class MainBarberShop {

	private static final Logger logger = LogManager.getLogger(MainBarberShop.class);

	public static void main(String[] args) {
		SwingWindow sw = new SwingWindow();
		sw.initialize();
	}

}
