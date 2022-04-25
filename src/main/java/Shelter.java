/**
 * A laboratórium típusú mezőkért felelős osztály.
 * Ezeken a mezőkön védőfelszerelés található, amiket az ide érkező virológusok összeszedhetnek.
 */
public class Shelter extends Field {
	private Gear gear;

	/**
	 * Konstruktor, ami meghívja az ős konstrukorát
	 */
	public Shelter(){
		super();
	}

	/**
	 * Konstruktor, ami meghívja az ős konstruktorát a megadott név beállítására.
	 * @param name megadott név
	 */
	public Shelter(String name){
		super(name);
	}

	/**
	 * A paraméterként kapott virológus felveszi a mezőn található védőfelszerelést.
	 * @param v - mezővel interaktáló virológus
	 */
	public void interactWithField(Virologist v) {
		v.pickUpGear(gear);
		gear = null;
	}

	/**
	 * Visszaadja a mező gearjét
	 */
	public Gear getGear() {
		return gear;
	}

	/**
	 * Beállítja a paraméterül kapott védőfelszerelést az óvóhelyen megszerezhető védőfelszerelésnek.
	 * @param g - a mezőn elhelyezendő védőfelszerelés
	 */
	public void addGear(Gear g) {
		gear = g;
	}
}
