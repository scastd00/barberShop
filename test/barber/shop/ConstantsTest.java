package barber.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	    assertEquals("P0", hairCuts[10].getHairCutName());
	}
}
