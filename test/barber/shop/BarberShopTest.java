package barber.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import barber.shop.exceptions.BarberException;

public class BarberShopTest {

	private BarberShop bs;
	private Customer cust1;
	private Customer cust2;

	@Before
	public void setUp() throws Exception {
		bs = new BarberShop();
		cust1 = new Customer("Sam", 2, 35, "Astorga");
		cust2 = new Customer("Samuel", 12, 40, "San Justo");

	}

	@Test
	public void testAddReservation() throws BarberException {
		this.bs.addReservation(cust1);
		assertEquals(this.bs.getTimetable().get(2)[2], cust1);

		this.bs.addReservation(cust2);
		assertEquals(this.bs.getTimetable().get(12)[2], cust2);
	}

	@Test(expected = BarberException.class)
	public void testAddReservationOccupied() throws BarberException {
		this.bs.addReservation(cust1);
		assertEquals(this.bs.getTimetable().get(2)[2], cust1);
		cust2.setTime(new Time(2, 35));
		this.bs.addReservation(cust2);
	}

	@Test(expected = BarberException.class)
	public void testAddReservationContains() throws BarberException {
		this.bs.addReservation(cust1);
		assertEquals(this.bs.getTimetable().get(2)[2], cust1);
		this.bs.addReservation(cust1);
	}

	@Test
	public void testCancelReservation() throws BarberException {
		this.bs.addReservation(cust1);
		assertEquals(this.bs.getTimetable().get(2)[2], cust1);
		this.bs.cancelReservation(cust1);
		assertNull(this.bs.getTimetable().get(2)[2]);

		this.bs.addReservation(cust2);
		assertEquals(this.bs.getTimetable().get(12)[2], cust2);
		this.bs.cancelReservation(cust2);
		assertNull(this.bs.getTimetable().get(12)[2]);
	}

	@Test
	public void testModifyReservation() throws BarberException {
		this.bs.addReservation(cust1);
		assertEquals(this.bs.getTimetable().get(2)[2], cust1);

		this.bs.modifyReservation(cust1, cust2);

		assertEquals(this.bs.getTimetable().get(12)[2], cust2);
		assertNull(this.bs.getTimetable().get(2)[2]);
	}

	@Test
	public void testGetCustomerByTime() throws BarberException {
	    this.bs.addReservation(cust1);
	    this.bs.addReservation(cust2);

	    assertEquals(this.bs.getCustomerByTime(cust1.getTime()), cust1);
	    assertEquals(this.bs.getCustomerByTime(cust2.getTime()), cust2);
	}

	@Test(expected = BarberException.class)
	public void testGetCustomerByTimeNoCustomer() throws BarberException {
	    this.bs.getCustomerByTime(cust1.getTime());
	}

	@Test
	public void testExchange() throws BarberException {
	    assertEquals(3, this.bs.exchange(6, 3),0);
	}

	@Test(expected = BarberException.class)
	public void testExchangeNegative() throws BarberException {
	    this.bs.exchange(2, 5);
	}

	@Test
	public void testToString() throws BarberException {
	    this.bs.addReservation(cust1);
	    this.bs.addReservation(cust2);

	    assertEquals(this.bs.toString(), "Sam  02:35  Astorga\nSamuel  12:40  San Justo\n");
	}
}
