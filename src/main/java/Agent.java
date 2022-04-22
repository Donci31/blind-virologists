/**
 * A különböző ágenseket összefogó absztrakt ősosztály.
 * Felelős az ágensek és hatásuk körönkénti frissítéséért, az ágens hatásának virológusokon való aktiválásáért,
 * és ha az ágens élettartama lejárt, az ágens hatásának megszüntetéséért.
 */
public abstract class Agent implements Steppable {
	protected int virusTimer;
	protected Virologist smearedVirologist;
	protected Virologist crafterVirologist;


	/**
	 * A smearedVirologist settere
	 */
	public void setSmearedVirologist(Virologist v){smearedVirologist=v;}

	/**
	 * A smearedVirologist gettere
	 */
	public Virologist getSmearedVirologist(){return smearedVirologist;}

	/**
	 * A crafterVirologist gettere
	 */
	public Virologist getCrafterVirologist(){return crafterVirologist;}

	/**
	 * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson
	 * és az ágens időzítője elkezd visszaszámolni.
	 * @param v - virológus, akire az ágens rá lesz kenve
	 */
	public void smear(Virologist v) {
		setSmearedVirologist(v);
		// TODO: Add to steppables
	}

	/**
	 * Körönként meghívódik, ha az ágens már fel van kenve, 1-el csökkenti a virusTimer értékét.
	 */
	public abstract void step();
}
