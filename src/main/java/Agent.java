/**
 * A különböző ágenseket összefogó absztrakt ősosztály.
 * Felelős az ágensek és hatásuk körönkénti frissítéséért, az ágens hatásának virológusokon való aktiválásáért,
 * és ha az ágens élettartama lejárt, az ágens hatásának megszüntetéséért.
 */
public abstract class Agent implements Steppable {
	protected int virusTimer;
	protected Virologist smearedVirologist;
	protected Virologist crafterVirologist=null;

	private static int id_counter = 1;
	protected String id;

	public Agent() {
		id = "a" + id_counter++;
		Prototype.agents.put(id, this);
	}

	public String getId() {
		return id;
	}
	public void setId(String id){this.id = id;}
	public void setVirusTimer(int t){virusTimer = t;}
	/**
	 * Visszaadja a vírus hátralevő idejét
	 */
	public int getVirusTimer() {
		return virusTimer;
	}

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

	public void setCrafterVirologist(Virologist v){crafterVirologist=v;}

	/**
	 * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson
	 * és az ágens időzítője elkezd visszaszámolni.
	 * @param v - virológus, akire az ágens rá lesz kenve
	 */
	public void smear(Virologist v) {
		setSmearedVirologist(v);
		SteppableController.addAppliedAgent(this); // Hozzáadja a léptethetők listájához az ágenst
	}

	/**
	 * Körönként meghívódik, ha az ágens már fel van kenve, 1-el csökkenti a virusTimer értékét.
	 */
	public abstract void step();
}
