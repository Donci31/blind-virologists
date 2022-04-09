/**
 * A védőköpeny nevű védőfelszerelést megvalósító osztály.
 * Az ilyen köpenyt viselő virológusok ellen az esetek 82.3%-ában hatástalanok az ágensek.
 * Az új stratégia a RobeAbsorb lesz.
 */
public class RobeGear extends Gear {
	private RobeAbsorb strat = new RobeAbsorb();

	/**
	 * Megváltoztatja a paraméterként kapott virológus viselkedési stratégiáját a rákent ágensekkel szemben.
	 * @param v - virológus, akire hatni fog
	 */
	public void giveStrat(Virologist v) {
		Skeleton.log("-> giveStat(v: Virologist)");
		v.addAbsorbStrat(strat);
		Skeleton.log("<- giveStat(v: Virologist)");
	}

	/**
	 * Kiveszi a paraméterként kapott virológus ágensekkel szemben védekező stratégiái közül az általa betett RobeAbsorb-ot.
	 * @param v - virológus, akiről eltávolítja a hatását
	 */
	public void removeStrat(Virologist v) {
		Skeleton.log("-> removeStat(v: Virologist)");
		v.removeAbsorbStrat(strat);
		Skeleton.log("<- removeStat(v: Virologist)");
	}
}
