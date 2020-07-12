package barber.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import barber.shop.exceptions.BarberException;

public class BarberShopTest {

	private BarberShop bs;
	private Customer cust;
	private String[] varString = {null, "", "Something"};
	private int[] varInt = {-1, 30, 61};

	@Before
	public void setUp() throws Exception {
		bs = new BarberShop();
		cust = new Customer("Sam", 2, 30, "Astorga");
	}

	@Test
	public void test() {

	}

}
