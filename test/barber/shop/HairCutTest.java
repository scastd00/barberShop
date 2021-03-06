package barber.shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HairCutTest {
	private HairCut hairCut;
	private HairCut finishHC;

	@Before
	public void setUp() {
		hairCut = new HairCut(1);
		finishHC = new HairCut(0);
	}

	@Test
	public void testGetPrice() {
		assertEquals(8.00f, hairCut.getPrice(), 0);
		assertEquals(0f, finishHC.getPrice(), 0f);
	}

	@Test
	public void testGetHairCutName() {
		assertEquals("P1", hairCut.getType());
		assertEquals("FINISH", finishHC.getType());
	}

	@Test
	public void testHairCutToPrice() {
		HairCut test = new HairCut(2);
		assertEquals(10.50f, test.getPrice(), 0);
		test = new HairCut(3);
		assertEquals(6.50f, test.getPrice(), 0);
	}
}
