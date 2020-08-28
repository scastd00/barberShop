package barber.shop;

public class MainBarberShop {

	public static void main(String[] args) {
//      Text User Interface

//		try {
//			BarberShop bs = new BarberShop();
//			TextUI ui = new TextUI(bs);
//			ui.init();
//		} catch (BarberException e) {
//			logger.warn(e.getMessage());
//		}

//		Graphic User Interface

		new SwingBarber().init();
	}
}
