/**
 * Ez az osztály azt a védőfelszerelést valósítja meg, amivel a viselőre kent ágensek visszadobhatóak a kenőre.
 */
public class GloveGear extends Gear {
	private GloveAbsorb strat = new GloveAbsorb();
	//TODO: implement tracking of usage
	private int timesUsed;

	/**
	 * Megváltoztatja a paraméterként kapott virológus viselkedési stratégiáját a rákent ágensekkel szemben.
	 * Az új stratégia a GloveAbsorb lesz.
	 * @param v - virológus, akire hatni fog
	 */
	public void giveStrat(Virologist v) {
		Skeleton.log("-> giveStat(v: Virologist)");
		v.addAbsorbStrat(strat);
		Skeleton.log("<- giveStat(v: Virologist)");
	}

	/**
	 * Kiveszi a paraméterként kapott virológus ágensekkel szemben védekező stratégiáji közül az általa beletett GloveAbsorb-ot.
	 * @param v - virológus, akiről eltávolítja a hatását
	 */
	public void removeStrat(Virologist v) {
		Skeleton.log("-> removeStat(v: Virologist)");
		v.removeAbsorbStrat(strat);
		Skeleton.log("<- removeStat(v: Virologist)");
	}
	/**
	 * Használja a kesztyűt, azaz a timesUsed nő egy értékkel.
	 * Ha az a növelést követően 3 lenne, akkor a kesztyű elromlik, eldobjuk.
	 */
	public void useGlove(){
		timesUsed++;
		//TODO
	}
}
