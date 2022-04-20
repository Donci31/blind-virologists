/**
 * Ez az osztály azt a védőfelszerelést valósítja meg, amivel a viselőre kent ágensek visszadobhatóak a kenőre.
 */
public class AxeGear extends Gear {
    private AxeHit strat = new AxeHit();
    private boolean used;

    /**
     * Megváltoztatja a paraméterként kapott virológus viselkedési stratégiáját a rákent ágensekkel szemben.
     * Az új stratégia a GloveAbsorb lesz.
     * @param v - virológus, akire hatni fog
     */
    public void giveStrat(Virologist v) {
        v.addHitStrat(strat);
    }

    /**
     * Kiveszi a paraméterként kapott virológus ágensekkel szemben védekező stratégiáji közül az általa beletett GloveAbsorb-ot.
     * @param v - virológus, akiről eltávolítja a hatását
     */
    public void removeStrat(Virologist v) {
        v.removeHitStrat(strat);
    }
    /**
     * Használja a kesztyűt, azaz a timesUsed nő egy értékkel.
     * Ha az a növelést követően 3 lenne, akkor a kesztyű elromlik, eldobjuk.
     */
    public void useAxe(){
        if(!used){
            //TODO
            used = true;
        }
    }
}
