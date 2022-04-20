/**
 * A medvetáncot okozó vírus kezeléséért felelős osztály.
 * Ha ezt a vírust kenik egy virológusra, az medventáncba kezd, amely élete során már nem múlik el.
 * Amíg medvetáncot jár, addig minden alapanyagot elpusztít a raktárakon, amelyekre rálép,
 * és minden vele egy mezőn álló virológust megfertőz a medvevírussal.
 */
public class BearVirus extends Agent {
    private BearMove strat = new BearMove();
    /**
     * A paraméterként kapott virológusra kenődik az ágens,
     * az ágensnek megfelelő hatás aktiválódik a virológuson,
     * azaz BearMove-ra állítódik a mozgási viselkedése.
     * @param v - virológus, aki megfertőződik a vírussal
     */
    public void smear(Virologist v) {
        smearedVirologist = v;
        v.setMoveStrat(strat);
        super.smear(v);
    }

    /**
     * Felülírja az ős step függvényét, nem csinál semmit sem
     */
    public void step() {
        //do nothing
    }
}
