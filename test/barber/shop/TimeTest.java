package barber.shop;

import static org.junit.Assert.*;

import barber.shop.exceptions.BarberException;
import org.junit.Before;
import org.junit.Test;

public class TimeTest {

	private Time time;
	private Time time1;

	@Before
	public void setUp() throws Exception {
		time = new Time();
		time1 = new Time(0, 0);
	}

	@Test(expected = BarberException.class)
	public void testConstructorNegativeHourValue() throws BarberException {
		time = new Time(-1, 0);
	}

	@Test(expected = BarberException.class)
	public void testConstructorNegativeMinuteValue() throws BarberException {
		time = new Time(0, -1);
	}

	@Test(expected = BarberException.class)
	public void testConstructorNegativeValues() throws BarberException {
		time = new Time(-1, -1);
	}

	@Test(expected = BarberException.class)
	public void testConstructorGreaterHourValue() throws BarberException {
		time = new Time(24, 0);
	}

	@Test(expected = BarberException.class)
	public void testConstructorGreaterMinuteValue() throws BarberException {
		time = new Time(0, 60);
	}

	@Test(expected = BarberException.class)
	public void testConstructorGreaterValues() throws BarberException {
		time = new Time(24, 60);
	}

	@Test
	public void testSetGetHour() throws BarberException {
		time.setHour(2);
		assertEquals(2, time.getHour());
		time.setHour(12);
		assertEquals(12, time.getHour());
	}

	@Test(expected = BarberException.class)
	public void testSetNegativeHour() throws BarberException {
		time1.setHour(-1);
	}

	@Test(expected = BarberException.class)
	public void testSetGreaterHour() throws BarberException {
		time1.setHour(24);
	}

	@Test(expected = BarberException.class)
	public void testSetNegativeMinute() throws BarberException {
		time1.setMinute(-1);
	}

	@Test(expected = BarberException.class)
	public void testSetGreaterMinute() throws BarberException {
		time1.setMinute(60);
	}

	@Test
	public void testSetGetMinute() throws BarberException {
		time.setMinute(2);
		assertEquals(2, time.getMinute());
		time.setMinute(12);
		assertEquals(12, time.getMinute());
	}

	@Test
	public void testToString() throws BarberException {
		time.setHour(16);
		time.setMinute(27);
		assertEquals("16:27", time.toString());
	}

	@Test
	public void testEquals() throws BarberException {
		assertTrue(time.equals(time1));
	    time.setMinute(12);
	    assertFalse(time.equals(time1));
	    assertFalse(time.equals(new Customer()));
	}
}
