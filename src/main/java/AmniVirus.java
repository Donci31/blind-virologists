/**
 * A felejtést okozó vírus kezeléséért felelős osztály.
 * a ezt a vírust kenik egy virológusra, az elfelejti az összes addig megtanult genetikai kódot.
 * Ennek a vírusnak a hatása végzetes és azonnali, az érintett virológus egyból felejt, így a vírusnak nincs hatásideje, és a step függvénye nem csinál semmit.
 */
public class AmniVirus extends Agent {
	/**
	 * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson
	 * és az ágens időzítője elkezd visszaszámolni. Az áldozatul esett virológus azonnal elfelejti az összes megtanult genetikai kódot.
	 * @param v - virológus, akire az ágens rá lesz kenve
	 */
	public void smear(Virologist v) {
		Skeleton.log("-> smear(v: Virologist)");
		v.forgetCodes();
		super.smear(v);
		Skeleton.log("<- smear(v: Virologist)");
	}

	/**
	 * Felülírja az ős step függvényét, nem csinál semmit.
	 */
	public void step() {
		Skeleton.log("-> step()");
		Skeleton.log("<- step()");
	}
}
