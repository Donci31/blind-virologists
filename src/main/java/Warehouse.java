/**
 *
 */
public class Warehouse extends Field implements Steppable {
	private int nProduced;
	private int aProduced;

	/**
	 * Körönként egyszer hívódik meg, a raktárban található anyagok száma random értékkel növekszik.
	 */
	public void step() {
	}

	/**
	 * A paraméterként kapott virológus felveszi a raktárból az összes anyagot, amit még magával tud vinni.
	 * @param v - mezővel interaktáló virológus
	 */
	public void interactWithField(Virologist v) {
	}
}
