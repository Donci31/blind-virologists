package model.gears;

import model.absorbStrats.RobeAbsorb;
import model.Virologist;

/**
 * A védőköpeny nevű védőfelszerelést megvalósító osztály.
 * Az ilyen köpenyt viselő virológusok ellen az esetek 82.3%-ában hatástalanok az ágensek.
 * Az új stratégia a model.absorbStrats.RobeAbsorb lesz.
 */
public class RobeGear extends Gear {
    private RobeAbsorb strat = new RobeAbsorb();

    /**
     * Megváltoztatja a paraméterként kapott virológus viselkedési stratégiáját a rákent ágensekkel szemben.
     *
     * @param v - virológus, akire hatni fog
     */
    public void giveStrat(Virologist v) {
        v.addAbsorbStrat(strat);
    }

    /**
     * Kiveszi a paraméterként kapott virológus ágensekkel szemben védekező stratégiái közül az általa betett model.absorbStrats.RobeAbsorb-ot.
     *
     * @param v - virológus, akiről eltávolítja a hatását
     */
    public void removeStrat(Virologist v) {
        v.removeAbsorbStrat(strat);
    }
}
