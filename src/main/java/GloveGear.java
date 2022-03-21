/**
 * Ez az osztály azt a védőfelszerelést valósítja meg, amivel a viselőre kent ágensek visszadobhatóak a kenőre.
 */
public class GloveGear extends Gear {
	private GloveAbsorb strat;

	/**
	 * Megváltoztatja a paraméterként kapott virológus viselkedési stratégiáját a rákent ágensekkel szemben.
	 * Az új stratégia a GloveAbsorb lesz.
	 * @param v - virológus, akire hatni fog
	 */
	public void giveStat(Virologist v) {
	}

	/**
	 * Kiveszi a paraméterként kapott virológus ágensekkel szemben védekező stratégiáji közül az általa beletett GloveAbsorb-ot.
	 * @param v - virológus, akiről eltávolítja a hatását
	 */
	public void removeStat(Virologist v) {
	}
}
