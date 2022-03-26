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
		Skeleton.log("-> interactWithField(v: Virologist)");
		v.pickUpGear(gear);
		gear.giveStat(v);
		Skeleton.log("<- interactWithField(v: Virologist)");
	}

	/**
	 * Beállítja a paraméterül kapott védőfelszerelést az óvóhelyen megszerezhető védőfelszerelésnek.
	 * @param g - a mezőn elhelyezendő védőfelszerelés
	 */
	public void addGear(Gear g) {
		Skeleton.log("-> addGear(g: Gear)");
		gear = g;
		Skeleton.log("<- addGear(g: Gear)");
	}
}
