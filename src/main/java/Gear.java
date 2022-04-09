/**
 * A védőfelszereléseket megvalósító absztrakt osztály, minden védőfelszerelés ennek az osztálynak lesz a leszármazottja.
 */
public abstract class Gear {
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
