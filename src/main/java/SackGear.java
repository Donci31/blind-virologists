/**
 * Ez az osztály azt a védőfelszerelést valósítja meg, ami a virológusok anyaggyűjtő képességét növeli meg.
 */
public class SackGear extends Gear {
	private int limitUpgrade = 100;

	/**
	 * Növeli a paraméterként kapott virológus anyaggyűjtő képességét.
	 * @param v - virológus, akire hatni fog
	 */
	public void giveStrat(Virologist v) {
		Skeleton.log("-> giveStat(v: Virologist)");
		v.setResourceLimit(v.getResourceLimit() + limitUpgrade);
		Skeleton.log("<- giveStat(v: Virologist)");
	}

	/**
	 * Csökkenti a paraméterként kapott virológus anyaggyűjtő képességét.
	 * @param v - virológus, akiről eltávolítja a hatását
	 */
	public void removeStrat(Virologist v) {
		Skeleton.log("-> removeStat(v: Virologist)");
		v.setResourceLimit(v.getResourceLimit() - limitUpgrade);
		Skeleton.log("<- removeStat(v: Virologist)");
	}
}
