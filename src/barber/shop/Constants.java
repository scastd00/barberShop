package barber.shop;

import java.util.*;

/**
 * Constants used in the other classes of the program.
 */
public class Constants {

	/**
	 * HashMap where the haircuts are stored to access them
	 */
	private HashMap<Byte, HairCut> haircutsHashMap;

	/**
	 * Class constructor for the hash of haircuts
	 */
	public Constants() {
		this.haircutsHashMap = new HashMap<>();
		fillHashMap(this.haircutsHashMap);
	}

	public HashMap<Byte, HairCut> getHaircutsHashMap() {
		return this.haircutsHashMap;
	}

	/**
	 * Fills the haircuts HashMap with | Key: (Byte) number | Value: (HairCut)
	 * object |
	 *
	 * @param hash haircut HashMap
	 */
	private void fillHashMap(HashMap<Byte, HairCut> hash) {
		hash.put((byte) 0, new HairCut((short) 0));

		for (byte i = 1; i <= NUM_HAIRCUTS; i++) {
			hash.put(i, new HairCut((byte) (i - 1)));
		}
	}

	public static final byte MAX_CUSTOMERS_DAY = 48; // Client each 24h

	public static final byte MAX_HOUR = 23;

	public static final byte MIN_HOUR = 0;

	public static final byte MAX_MINUTE = 59;

	public static final byte MIN_MINUTE = 0;

	public static final byte NUM_HAIRCUTS = 30;
}
