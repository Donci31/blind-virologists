package model.agents;

import model.absorbStrats.ProtVaccineAbsorb;
import controller.SteppableController;
import model.Virologist;

/**
 * A más ágensek ellen védelmet nyújtó ágenst megvalósító oszály.
 * Felelős a hatás aktiválásáért, a hatásidő nyilvántartásáért, és az idő leteltével a hatás megszüntetéséért.
 */
public class ProtVaccine extends Agent {
    private ProtVaccineAbsorb strat;

    /**
     * Konstruktor, ami beállítja a virusTimer kezdőidejét
     */
    public ProtVaccine() {
        virusTimer = 2;
    }

    /**
     * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson és az ágens időzítője elkezd visszaszámolni.
     * A virológusra a hatás leteltéig nem lesz hatással semmilyen másik ágens.
     *
     * @param v - virológus, akire az ágens rá lesz kenve
     */
    public void smear(Virologist v) {
        this.strat = new ProtVaccineAbsorb();
        v.addAbsorbStrat(this.strat);
        super.smear(v);
    }

    /**
     * Körönként meghívódik, ha az ágens már fel van kenve, 1-el csökkenti a virusTimer értékét.
     * Ha lejárt az idő, akkor kiveszi a rá jellemző absorb stratégiát a virológusáról (model.absorbStrats.ProtVaccineAbsorb).
     */
    public void step() {
        virusTimer--;
        if (virusTimer == 0) {
            this.getSmearedVirologist().removeAbsorbStrat(this.strat);
            SteppableController.kidob(this);
        }
    }
}
