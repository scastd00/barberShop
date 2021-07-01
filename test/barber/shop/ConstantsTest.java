package barber.shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstantsTest {
	private Constants constants;

	@Before
	public void setUp() {
		constants = new Constants();
	}

	@Test
	public void testAll() {
		assertEquals(31, Constants.NUM_HAIRCUTS);
		HairCut[] hairCuts = constants.getHairCuts();
		assertEquals("P0", hairCuts[10].getType());
	}
}
