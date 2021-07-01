package barber.shop;

import java.awt.*;

public class MainBarberShop {

	/*
	 * Main method of the program.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new SwingBarber().init());
	}
}
