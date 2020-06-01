package barber.shop;

import java.util.Map;
import java.util.HashMap;

/**
 * Constants used in the program.
 */
public class Constants {

	public static final byte MAX_HOUR = 23;

	public static final byte MIN_HOUR = 0;

	public static final byte MAX_MINUTE = 59;

	public static final byte MIN_MINUTE = 0;

	public static final byte NUM_HAIRCUTS = 30;

	public static final byte ARRAY_LIST_SIZE = 4;

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

	public Map<Byte, HairCut> getHaircutsHashMap() {
		return this.haircutsHashMap;
	}

	/**
	 * Fills the haircuts HashMap with Key: (Byte) | Value: (HairCut)
	 *
	 * @param hash haircut HashMap
	 */
	private void fillHashMap(HashMap<Byte, HairCut> hash) {
		hash.put((byte) 0, new HairCut((short) 0));

		for (byte i = 1; i <= NUM_HAIRCUTS; i++) {
			hash.put(i, new HairCut((byte) (i - 1)));
		}
	}
}
