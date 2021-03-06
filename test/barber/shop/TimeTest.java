package barber.shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
	public void testHourMinutePositions() throws BarberException {
		time1.setHour(17);
		time1.setMinute(10);
		int[] pos = time1.hourMinutePositions();

		assertEquals(17, pos[0]);
		assertEquals(0, pos[1]);

		time1.setMinute(20);
		pos = time1.hourMinutePositions();
		assertEquals(17, pos[0]);
		assertEquals(1, pos[1]);

		time1.setMinute(40);
		pos = time1.hourMinutePositions();
		assertEquals(17, pos[0]);
		assertEquals(2, pos[1]);

		time1.setMinute(50);
		pos = time1.hourMinutePositions();
		assertEquals(17, pos[0]);
		assertEquals(3, pos[1]);
	}

	@Test
	public void testToString() throws BarberException {
		time.setHour(16);
		time.setMinute(27);
		assertEquals("16:27", time.toString());
	}

	@Test
	public void testEquals() throws BarberException {
		assertTrue(time.equals(time));
		assertTrue(time.equals(time1));
		time.setMinute(12);
		assertFalse(time.equals(time1));
		time.setHour(2);
		assertFalse(time.equals(time1));
		assertFalse(time.equals(new Customer()));

	}

	@Test
	public void testHashCode() throws BarberException {
		assertEquals(0, time.hashCode());
		time.setHour(12);
		time.setMinute(39);
		assertEquals(51, time.hashCode());
	}
}
