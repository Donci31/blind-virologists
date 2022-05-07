package model.gears;

import model.Virologist;

/**
 * Ez az osztály azt a védőfelszerelést valósítja meg, ami a virológusok anyaggyűjtő képességét növeli meg.
 */
public class SackGear extends Gear {
	private final int limitUpgrade = 100;

	/**
	 * Növeli a paraméterként kapott virológus anyaggyűjtő képességét.
	 * @param v - virológus, akire hatni fog
	 */
	public void giveStrat(Virologist v) {
		v.setResourceLimit(v.getResourceLimit() + limitUpgrade);
	}

	/**
	 * Csökkenti a paraméterként kapott virológus anyaggyűjtő képességét.
	 * @param v - virológus, akiről eltávolítja a hatását
	 */
	public void removeStrat(Virologist v) {
		v.setResourceLimit(v.getResourceLimit() - limitUpgrade);
	}
}
