package barber.shop;

/**
 * Class that represents the haircuts
 */
public class HairCut {

	/**
	 * Price of the haircut
	 */
	private final float price;

	/**
	 * Type of haircut
	 */
	private final String hairCutName;

	/**
	 * Class constructor
	 *
	 * @param num number corresponding to the haircut
	 */
	public HairCut(int num) {
		this.price = hairCutToPrice(numberToHaircut(num));
		this.hairCutName = numberToHaircut(num).toString();
	}

	/**
	 * Price getter
	 *
	 * @return returns the price of the haircut
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * Haircut getter
	 *
	 * @return returns the name of the haircut
	 */
	public String getHairCutName() {
		return this.hairCutName;
	}

	/**
	 * Changes a num into the corresponding value of the haircut enumeration
	 *
	 * @param num haircut
	 * @return value of the haircut enumeration
	 */
	private HairCuts numberToHaircut(int num) {
		return HairCuts.values()[num];
	}

	/**
	 * Changes a value of the haircut enumeration into the price of it
	 *
	 * @param haircut selected haircut
	 * @return price of the haircut
	 */
	private float hairCutToPrice(HairCuts haircut) {
		switch (haircut) {
			case P1:
				return 8.00f;

			case P2:
				return 10.50f;

			case P3:
				return 6.50f;

			default:
				return 0;
		}
	}
}
