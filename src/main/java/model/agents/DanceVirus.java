package model.agents;

import model.moveStrats.DanceMove;
import model.moveStrats.DefaultMove;
import model.SteppableController;
import model.Virologist;

/**
 * A felejtést okozó vírus kezeléséért felelős osztály.
 * Ha ezt a vírust kenik egy virológusra, az vitustáncba kezd, és a vírus hatásának elmúlásáig kontrollálhatatlanul fog mozogni.
 */
public class DanceVirus extends Agent {
	private DanceMove strat = new DanceMove();

	/**
	 * Konstruktor, ami beállítja a virusTimer-t
	 */
	public DanceVirus(){
		virusTimer=4;
	}

	/**
	 * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson és az ágens időzítője elkezd visszaszámolni.
	 * A virológus mozgási viselkedése default move-ról dance move-ra állítódik át, aminek hatására kontrollálatlanul fog mozogni, amíg az ágens hatása le nem jár.
	 * @param v - virológus, akire az ágens rá lesz kenve
	 */
	public void smear(Virologist v) {
		v.setMoveStrat(strat);
		super.smear(v);
	}

	/**
	 * Felülírja az ős step függvényét, csökkenti a virusTimer értékét, és ha lejárt az ideje, akkor visszaállítja a virológus alapraméretezett mozgását.
	 */
	public void step() {
		virusTimer--;
		if (virusTimer <= 0) {
			DefaultMove def = new DefaultMove(); // <<create>> model.moveStrats.DefaultMove
			smearedVirologist.setMoveStrat(def);
			SteppableController.kidob(this);
		}
	}
}
