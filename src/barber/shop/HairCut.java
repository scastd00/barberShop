package barber.shop;

import org.jetbrains.annotations.Contract;

/**
 * Class that represents all the available haircuts in the Barber Shop.
 */
public class HairCut {

	/**
	 * Price of the haircut.
	 */
	private final float price;

	/**
	 * Type of haircut.
	 */
	private final String type;

	/**
	 * Class constructor.
	 *
	 * @param num number corresponding to the haircut.
	 */
	public HairCut(int num) {
		this.price = hairCutToPrice(numberToHaircut(num));
		this.type = numberToHaircut(num).toString();
	}

	/**
	 * Price getter.
	 *
	 * @return the price of the haircut.
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * Haircut getter.
	 *
	 * @return the name of the haircut.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Changes a number into the corresponding value of the haircut enumeration.
	 *
	 * @param num haircut code.
	 * @return value of the haircut enumeration.
	 */
	@Contract(pure = true)
	private HairCuts numberToHaircut(int num) {
		return HairCuts.values()[num];
	}

	/**
	 * Changes a value of the haircut enumeration into its price.
	 *
	 * @param haircut the selected haircut.
	 * @return price of the selected haircut.
	 */
	@Contract(pure = true)
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
