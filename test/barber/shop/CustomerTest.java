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

	@Test(expected = BarberException.class)
	public void testConstructorNullName() throws BarberException {
		Customer c = new Customer(null, 0, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorEmptyName() throws BarberException {
		Customer c = new Customer("", 0, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorWrongHourLess() throws BarberException {
		Customer c = new Customer("A", -1, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorWrongHourGreater() throws BarberException {
		Customer c = new Customer("A", 24, 0, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorWrongMinuteLess() throws BarberException {
		Customer c = new Customer("A", 0, -1, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorWrongMinuteGreater() throws BarberException {
		Customer c = new Customer("A", 0, 60, "Astorga");
	}

	@Test(expected = BarberException.class)
	public void testConstructorNullPlace() throws BarberException {
		Customer c = new Customer("A", 0, 0, null);
	}

	@Test(expected = BarberException.class)
	public void testConstructorEmptyPlace() throws BarberException {
		Customer c = new Customer("A", 0, 0, "");
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
		customer.setHour(3);
		customer.setMinute(0);
		assertEquals(3, customer.getHour());
		assertEquals(0, customer.getMinute());
	}

	@Test(expected = BarberException.class)
	public void testSetHourLess() throws BarberException {
		customer.setHour(-1);
	}

	@Test(expected = BarberException.class)
	public void testSetHourGreater() throws BarberException {
		customer.setHour(24);
	}

	@Test(expected = BarberException.class)
	public void testSetMinuteLess() throws BarberException {
		customer.setMinute(-1);
	}

	@Test(expected = BarberException.class)
	public void testSetMinuteGreater() throws BarberException {
		customer.setMinute(60);
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


}
