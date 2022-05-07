package model.gears;

import model.Prototype;
import model.Virologist;

/**
 * A védőfelszereléseket megvalósító absztrakt osztály, minden védőfelszerelés ennek az osztálynak lesz a leszármazottja.
 */
public abstract class Gear {
	private static int id_counter = 1;
	protected String id;

	/**
	 * Konstruktor.
	 */
	public Gear() {
		id = "g" + id_counter++;
		Prototype.gears.put(id, this);
	}

	/**
	 * Az id alapraméretezett állapotba állító függvénye.
	 */
	public static void resetID() {id_counter = 1; }

	/**
	 * Az id gettere.
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Az id settere
	 * @param id a beállítandó id
	 */
	public void setID(String id){ this.id = id;}

	/**
	 * Megváltoztatja virológus valamilyen, a védőfelszerelésre jellemző tulajdonságát.
	 * @param v - virológus, akire hatni fog
	 */
	public abstract void giveStrat(Virologist v);

	/**
	 * Visszaállítja a paraméterként kapott virológus ágensekkel szemben védekező stratégiáját.
	 * @param v - virológus, akiről eltávolítja a hatását
	 */
	public abstract void removeStrat(Virologist v);
}
