package barber.shop;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Class that represents the haircuts
 */
public class HairCut {

	public enum HairCutsEnum {
		P1, P2, P3, P4, P5, P6, P7, P8, P9, P0, Q, W, E, R, T, Y, U, I, O, P, A, S, D, F, G, H, J, K, L, M
	}

	private static final Logger logger = LogManager.getLogger(HairCut.class);

	/**
	 * Price of the haircut
	 */
	private float price;

	/**
	 * Type of haircut
	 */
	private String hairCut;

	/**
	 * Class constructor
	 *
	 * @param num number corresponding to the haircut
	 */
	public HairCut(int num) {
		this.price = hairCutToPrice(numberToHaircut(num));
		this.hairCut = numberToHaircut(num).toString();
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
	public String getHairCut() {
		return this.hairCut;
	}

	/**
	 * Changes a num into the corresponding value of the haircut enumeration
	 *
	 * @param num haircut
	 * @return value of the haircut enumeration
	 */
	private HairCutsEnum numberToHaircut(int num) {
		return HairCutsEnum.values()[num];
	}

	/**
	 * Changes a value of the haircut enumeration into the price of it
	 *
	 * @param haircut
	 * @return price of the haircut
	 */
	private float hairCutToPrice(HairCutsEnum haircut) {
		float num = 0;

		switch (haircut) {
			case P1:
				num = 8.00f;
				break;

			case P2:
				num = 10.50f;
				break;

			case P3:
				num = 6.50f;
				break;

			default:
				break;
		}
		return num;
	}
}
