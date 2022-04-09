/**
 * A más ágensek ellen védelmet nyújtó ágenst megvalósító oszály.
 * Felelős a hatás aktiválásáért, a hatásidő nyilvántartásáért, és az idő leteltével a hatás megszüntetéséért.
 */
public class ProtVaccine extends Agent implements Steppable{
	private ProtVaccineAbsorb strat;

	/**
	 * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson és az ágens időzítője elkezd visszaszámolni.
	 * A virológusra a hatás leteltéig nem lesz hatással semmilyen másik ágens.
	 * @param v - virológus, akire az ágens rá lesz kenve
	 */
	public void smear(Virologist v) {
		Skeleton.log("-> smear(v: Virologist)");
		this.strat = new ProtVaccineAbsorb();
		v.addAbsorbStrat(this.strat);
		super.smear(v);
		Skeleton.log("<- smear(v: Virologist)");
	}

	/**
	 * Körönként meghívódik, ha az ágens már fel van kenve, 1-el csökkenti a virusTimer értékét.
	 * Ha lejárt az idő, akkor kiveszi a rá jellemző absorb stratégiát a virológusáról (ProtVaccineAbsorb).
	 */
	public void step() {
		Skeleton.log("-> step()");
		var expired = Skeleton.askYesOrNo("VirusTimer expired?");
		if (expired) {
			this.getSmearedVirologist().removeAbsorbStrat(this.strat);
		}
		Skeleton.log("<- step()");
	}
}
