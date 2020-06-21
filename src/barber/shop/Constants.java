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

	public static final int NUM_HAIRCUTS = HairCuts.values().length;

	public static final byte ARRAY_LIST_SIZE = 4;

	public static final int MODIFY_TIME = 1;

	public static final int MODIFY_ALL = 2;

	/**
	 * HashMap where the haircuts are stored to access them
	 */
	private final HashMap<Integer, HairCut> haircutsHashMap;

	/**
	 * Class constructor for the hash of haircuts
	 */
	public Constants() {
		this.haircutsHashMap = new HashMap<>();
		this.fillHashMap();
	}

	public Map<Integer, HairCut> getHaircutsHashMap() {
		return this.haircutsHashMap;
	}

	/**
	 * Fills the haircuts HashMap with Key: (Byte) | Value: (HairCut)
	 */
	private void fillHashMap() {
		for (int i = 0; i < Constants.NUM_HAIRCUTS; i++) {
			this.haircutsHashMap.put(i, new HairCut(i));
		}
	}
}
