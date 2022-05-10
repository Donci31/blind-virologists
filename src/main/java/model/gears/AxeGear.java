package model.gears;

import model.hitStrats.AxeHit;
import model.Virologist;

/**
 * Ez az osztály azt a védőfelszerelést valósítja meg, amivel a viselőre kent ágensek visszadobhatóak a kenőre.
 */
public class AxeGear extends Gear {
    private AxeHit strat = new AxeHit(this);
    private boolean used;

    /**
     * Visszaadja, hogy használt-e a balta
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * beállítja a balta használtságát
     *
     * @param b - használtság
     */
    public void setUsed(boolean b) {
        used = b;
    }

    /**
     * Megváltoztatja a paraméterként kapott virológus viselkedési stratégiáját a rákent ágensekkel szemben.
     * Az új stratégia a model.absorbStrats.GloveAbsorb lesz.
     *
     * @param v - virológus, akire hatni fog
     */
    public void giveStrat(Virologist v) {
        v.addHitStrat(strat);
    }

    /**
     * Kiveszi a paraméterként kapott virológus ágensekkel szemben védekező stratégiáji közül az általa beletett model.absorbStrats.GloveAbsorb-ot.
     *
     * @param v - virológus, akiről eltávolítja a hatását
     */
    public void removeStrat(Virologist v) {
        v.removeHitStrat(strat);
    }

    /**
     * Beállítja a used attribútumot igazra, azaz a balta használva volt.
     */
    public void useAxe() {
        if (!used) {
            used = true;
        }
    }
}
