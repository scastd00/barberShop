package barber.shop;

import static org.junit.Assert.*;

import barber.shop.exceptions.BarberException;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;

	@Before
	public void setUp() throws Exception {
		customer = new Customer();
	}

	@Test
	public void testSetGetName() throws BarberException {
		customer.setName("Sam");
		assertEquals("Sam", customer.getName());
	}

	@Test
	public void testSetGetTime() throws BarberException {
		customer.setHour((byte) 3);
		customer.setMinute((byte) 0);
		assertEquals(3, customer.getHour());
		assertEquals(0, customer.getMinute());
	}


}
