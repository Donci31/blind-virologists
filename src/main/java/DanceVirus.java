/**
 * A felejtést okozó vírus kezeléséért felelős osztály.
 * Ha ezt a vírust kenik egy virológusra, az vitustáncba kezd, és a vírus hatásának elmúlásáig kontrollálhatatlanul fog mozogni.
 */
public class DanceVirus extends Agent {
	private DanceMove strat;

	/**
	 * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson és az ágens időzítője elkezd visszaszámolni.
	 * A virológus mozgási viselkedése default move-ról dance move-ra állítódik át, aminek hatására kontrollálatlanul fog mozogni, amíg az ágens hatása le nem jár.
	 * @param v - virológus, akire az ágens rá lesz kenve
	 */
	public void smear(Virologist v) {
		Skeleton.log("-> smear(v: Virologist)");
		DanceMove dm = new DanceMove();
		v.setMoveStrat(dm);
		super.smear(v);
		Skeleton.log("<- smear(v: Virologist)");
	}

	/**
	 * Felülírja az ős step függvényét, csökkenti a virusTimer értékét, és ha lejárt az ideje, akkor visszaállítja a virológus alapraméretezett mozgását.
	 */
	public void step() {
		Skeleton.log("-> step()");
		var expired = Skeleton.askYesOrNo("VirusTimer expired?");
		if (expired) {
			DefaultMove def = new DefaultMove(); // <<create>> DefaultMove
			smearedVirologist.setMoveStrat(def);
		}
		Skeleton.log("<- step()");
	}
}
