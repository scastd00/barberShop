package barber.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BarberShopTest {

	private BarberShop bs;
	private Customer customer1;
	private Customer customer2;

	@Before
	public void setUp() throws Exception {
		bs = new BarberShop();
		customer1 = new Customer("Sam", 2, 35, "Astorga");
		customer2 = new Customer("Samuel", 12, 40, "San Justo");
	}

	@Test
	public void testAddReservation() throws BarberException {
		this.bs.addReservation(customer1);
		assertEquals(customer1, this.bs.getTimetable()[2][2]);

		this.bs.addReservation(customer2);
		assertEquals(customer2, this.bs.getTimetable()[12][2]);
	}

	@Test(expected = BarberException.class)
	public void testAddReservationOccupied() throws BarberException {
		this.bs.addReservation(customer1);
		addOccupied();
		customer2.setTime(new Time(2, 35));
		this.bs.addReservation(customer2);
	}

	private void addOccupied() {
		assertEquals(customer1, this.bs.getTimetable()[2][2]);
	}

	@Test(expected = BarberException.class)
	public void testAddReservationContains() throws BarberException {
		testAddReservationCheckOk();
		this.bs.addReservation(customer1);
	}

	@Test
	public void testAddReservationCheckOk() throws BarberException {
		this.bs.addReservation(customer1);
		assertEquals(customer1, this.bs.getTimetable()[2][2]);
	}

	@Test
	public void testCancelReservation() throws BarberException {
		this.bs.addReservation(customer1);
		assertEquals(customer1, this.bs.getTimetable()[2][2]);
		this.bs.cancelReservation(customer1);
		assertNull(this.bs.getTimetable()[2][2]);

		this.bs.addReservation(customer2);
		assertEquals(customer2, this.bs.getTimetable()[12][2]);
		this.bs.cancelReservation(customer2);
		assertNull(this.bs.getTimetable()[12][2]);
	}

	@Test(expected = BarberException.class)
	public void testCancelReservationNoCustomerInPosition() throws BarberException {
		this.bs.cancelReservation(new Customer("Manuel", 10, 10, "Astorga"));
	}

	@Test
	public void testModifyReservation() throws BarberException {
		this.bs.addReservation(customer1);
		assertEquals(customer1, this.bs.getTimetable()[2][2]);

		this.bs.modifyReservation(customer1, customer2);

		assertEquals(customer2, this.bs.getTimetable()[12][2]);
		assertNull(this.bs.getTimetable()[2][2]);
	}

	@Test
	public void testGetCustomerByTime() throws BarberException {
	    this.bs.addReservation(customer1);
	    this.bs.addReservation(customer2);

	    assertEquals(customer1, this.bs.getCustomerByTime(customer1.getTime()));
	    assertEquals(customer2, this.bs.getCustomerByTime(customer2.getTime()));
	}

	@Test(expected = BarberException.class)
	public void testGetCustomerByTimeNoCustomer() throws BarberException {
	    this.bs.getCustomerByTime(customer1.getTime());
	}

	@Test
	public void testExchange() throws BarberException {
	    assertEquals(3f, this.bs.exchange(6, 3), 0f);
	}

	@Test(expected = BarberException.class)
	public void testExchangeNegative() throws BarberException {
	    this.bs.exchange(2, 5);
	}

	@Test
	public void testToString() throws BarberException {
	    this.bs.addReservation(customer1);
	    this.bs.addReservation(customer2);

	    assertEquals("Sam  02:35  Astorga\nSamuel  12:40  San Justo\n", this.bs.toString());
	}
}
