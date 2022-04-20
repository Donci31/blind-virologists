/**
 * A laboratórium típusú mezőkért felelős osztály.
 * Ezeken a mezőkön védőfelszerelés található, amiket az ide érkező virológusok összeszedhetnek.
 */
public class Shelter extends Field {
	private Gear gear;

	/**
	 * A paraméterként kapott virológus felveszi a mezőn található védőfelszerelést.
	 * @param v - mezővel interaktáló virológus
	 */
	public void interactWithField(Virologist v) {
		v.pickUpGear(gear);
	}

	/**
	 * Beállítja a paraméterül kapott védőfelszerelést az óvóhelyen megszerezhető védőfelszerelésnek.
	 * @param g - a mezőn elhelyezendő védőfelszerelés
	 */
	public void addGear(Gear g) {
		gear = g;
	}
}
