package model.agents;

import model.Prototype;
import model.Steppable;
import model.SteppableController;
import model.Virologist;

/**
 * A különböző ágenseket összefogó absztrakt ősosztály.
 * Felelős az ágensek és hatásuk körönkénti frissítéséért, az ágens hatásának virológusokon való aktiválásáért,
 * és ha az ágens élettartama lejárt, az ágens hatásának megszüntetéséért.
 */
public abstract class Agent implements Steppable {
    protected int virusTimer;
    protected Virologist smearedVirologist;
    protected Virologist crafterVirologist = null;
    private static int id_counter = 1;
    protected String id;

    /**
     * Konstruktor, ami beállítja az id-t és hozzáadja a prototípus objektumokat dekódoló map-jébe.
     */
    public Agent() {
        id = "a" + id_counter++;
        Prototype.agents.put(id, this);
    }

    /**
     * Az id alapraméretezett állapotba állító függvénye.
     */
    public static void resetID() {
        id_counter = 1;
    }

    /**
     * Az id gettere.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Az id settere
     *
     * @param id az id új értéke
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * A virusTimer settere.
     *
     * @param t virusTimer új értéke
     */
    public void setVirusTimer(int t) {
        virusTimer = t;
    }

    /**
     * Visszaadja a vírus hátralevő idejét
     */
    public int getVirusTimer() {
        return virusTimer;
    }

    /**
     * A smearedVirologist settere
     */
    public void setSmearedVirologist(Virologist v) {
        smearedVirologist = v;
    }

    /**
     * A smearedVirologist gettere
     */
    public Virologist getSmearedVirologist() {
        return smearedVirologist;
    }

    /**
     * A crafterVirologist gettere
     */
    public Virologist getCrafterVirologist() {
        return crafterVirologist;
    }

    public void setCrafterVirologist(Virologist v) {
        crafterVirologist = v;
    }

    /**
     * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson
     * és az ágens időzítője elkezd visszaszámolni.
     *
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
