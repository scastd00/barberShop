package barber.shop;

/**
 * Constants used in the program.
 */
public class Constants {

	public static final byte MAX_HOUR = 23;
	public static final byte MIN_HOUR = 0;
	public static final byte MAX_MINUTE = 59;
	public static final byte MIN_MINUTE = 0;
	public static final int NUM_HAIRCUTS = HairCuts.values().length;
	public static final byte ARRAY_SIZE = 4;
	public static final int MODIFY_TIME = 1;
	public static final int MODIFY_ALL = 2;

	/**
	 * Array where the haircuts are stored to access them when making transactions.
	 */
	private final HairCut[] hairCuts;

	/**
	 * Class constructor for the array of haircuts.
	 */
	public Constants() {
		this.hairCuts = new HairCut[Constants.NUM_HAIRCUTS];
		this.fillArray();
	}

	public HairCut[] getHairCuts() {
		return this.hairCuts;
	}

	/**
	 * Fills the haircuts array.
	 */
	private void fillArray() {
		for (int i = 0; i < Constants.NUM_HAIRCUTS; i++) {
			this.hairCuts[i] = new HairCut(i);
		}
	}
}
