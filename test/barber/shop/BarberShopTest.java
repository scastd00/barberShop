package barber.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import barber.shop.exceptions.BarberException;

public class BarberShopTest {

	private BarberShop bs;
	private Customer cust;

	@Before
	public void setUp() throws Exception {
		bs = new BarberShop();
		cust = new Customer("Sam",2,30,"Astorga");
	}

	@Test
	public void testAddReservation() throws BarberException {
		bs.addReservation(cust);
	}

}
