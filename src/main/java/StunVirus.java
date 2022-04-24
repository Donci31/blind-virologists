/**
 * A bénulást okozó vírus kezeléséért felelős osztály.
 * Ha ezt a vírust kenik egy virológusra, az lebénul, és a hatás elmúlásáig nem tud semmilyen akciót végrehajtani.
 * A bénulás ideje alatt más virológusok elvehetnek tőle védőfelszereléseket.
 */
public class StunVirus extends Agent {

	StunVirus(){
		virusTimer=2;

	}

	/**
	 * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson és az ágens időzítője elkezd visszaszámolni.
	 * A virológus stunned attribútuma igazra állítódik át.
	 * @param v - virológus, akire az ágens rá lesz kenve
	 */
	public void smear(Virologist v) {
		v.setStunned(true);
		super.smear(v);
	}

	/**
	 * Körönként meghívódik, ha az ágens már fel van kenve, 1-el csökkenti a virusTimer értékét.
	 * Ha lejárt az idő, akkor kiveszi a rá jellemző absorb stratégiát a virológusáról (StunAbsorb).
	 */
	public void step() {
		virusTimer--;
		if (virusTimer == 0) {
			smearedVirologist.setStunned(false);
			SteppableController.removeAppliedAgent(this);
		}
	}
}
