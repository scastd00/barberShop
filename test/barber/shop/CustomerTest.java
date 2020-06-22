package barber.shop;

import static org.junit.Assert.*;

import barber.shop.exceptions.BarberException;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;
	private Customer customer1;

	@Before
	public void setUp() throws Exception {
		customer = new Customer();
		customer1 = new Customer("Sam", 1, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorNullName() throws BarberException {
		new Customer(null, 0, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorEmptyName() throws BarberException {
		new Customer("", 0, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorWrongHourLess() throws BarberException {
		new Customer("A", -1, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorWrongHourGreater() throws BarberException {
		new Customer("A", 24, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorWrongMinuteLess() throws BarberException {
		new Customer("A", 0, -1, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorWrongMinuteGreater() throws BarberException {
		new Customer("A", 0, 60, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorNullPlace() throws BarberException {
		new Customer("A", 0, 0, null);
	}

	@Test(expected = BarberException.class)
	public void testConstructorEmptyPlace() throws BarberException {
		new Customer("A", 0, 0, "");
	}

	@Test
	public void testSetGetName() throws BarberException {
		customer.setName("Sam");
		assertEquals("Sam", customer.getName());
	}

	@Test(expected = BarberException.class)
	public void testSetNullName() throws BarberException {
		customer.setName(null);
	}

	@Test(expected = BarberException.class)
	public void testSetEmptyName() throws BarberException {
		customer.setName("");
	}

	@Test
	public void testSetGetTime() throws BarberException {
	    customer.setTime(new Time(12, 30));
	    assertEquals(12, customer.getTime().getHour());
		assertEquals(30, customer.getTime().getMinute());
	}

	@Test(expected = BarberException.class)
	public void testSetHourLess() throws BarberException {
		customer.getTime().setHour(-1);
	}

	@Test(expected = BarberException.class)
	public void testSetHourGreater() throws BarberException {
		customer.getTime().setHour(24);
	}

	@Test(expected = BarberException.class)
	public void testSetMinuteLess() throws BarberException {
		customer.getTime().setMinute(-1);
	}

	@Test(expected = BarberException.class)
	public void testSetMinuteGreater() throws BarberException {
		customer.getTime().setMinute(60);
	}

	@Test
	public void testSetGetPlace() throws BarberException {
		customer.setPlace("Astorga");
		assertEquals("Astorga", customer.getPlace());
	}

	@Test(expected = BarberException.class)
	public void testSetPlaceNull() throws BarberException {
		customer.setPlace(null);
	}

	@Test(expected = BarberException.class)
	public void testSetPlaceEmpty() throws BarberException {
		customer.setPlace("");
	}

	@Test
	public void testToString() throws BarberException {
		assertEquals("Sam  01:00  Astorga", customer1.toString());
		customer1.getTime().setHour(16);
		customer1.getTime().setMinute(15);
		assertEquals("Sam  16:15  Astorga", customer1.toString());
	}

	@Test
	public void testEquals() throws BarberException {
	    assertFalse(customer.equals(customer1));
	    customer.setName("Sam");
	    customer.getTime().setHour(1);
	    customer.setPlace("Astorga");
	    assertTrue(customer.equals(customer1));
	    assertFalse(customer.equals(new BarberShop()));
	}

}
