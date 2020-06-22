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
	public void testIPTMT() {
	    assertTrue(bs.isPossibleToMakeTransaction(cust));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTNullName() throws BarberException {
	    bs.isPossibleToMakeTransaction(new Customer(null, 10, 0, "Ast"));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTEmptyName() throws BarberException {
		bs.isPossibleToMakeTransaction(new Customer("", 10, 0, "Ast"));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTHourLess() throws BarberException {
		bs.isPossibleToMakeTransaction(new Customer("A", -1, 0, "Ast"));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTHourGreater() throws BarberException {
		bs.isPossibleToMakeTransaction(new Customer("A", 24, 0, "Ast"));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTMinuteLess() throws BarberException {
		bs.isPossibleToMakeTransaction(new Customer("A", 0, -1, "Ast"));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTMinuteGreater() throws BarberException {
		bs.isPossibleToMakeTransaction(new Customer("A", 0, 60, "Ast"));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTNullPlace() throws BarberException {
		bs.isPossibleToMakeTransaction(new Customer("A", 10, 0, null));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTEmptyPlace() throws BarberException {
		bs.isPossibleToMakeTransaction(new Customer("A", 10, 0, ""));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTNullNamePlace() throws BarberException {
	    bs.isPossibleToMakeTransaction(new Customer(null, 0, 0, null));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTNullLessOkNull() throws BarberException {
	    bs.isPossibleToMakeTransaction(new Customer(null, -1, 0, null));
	}

	@Test(expected = BarberException.class)
	public void testIPTMTNullGreaterOkNull() throws BarberException {
		bs.isPossibleToMakeTransaction(new Customer(null, 24, 0, null));
	}

	@Test(expected = BarberException.class)
	public void testIMTMTAllWrong() throws BarberException {
	    bs.isPossibleToMakeTransaction(new Customer(null, -1, -1, null));
	}

}
