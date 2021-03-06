package model.agents;

import model.Virologist;

/**
 * A felejtést okozó vírus kezeléséért felelős osztály.
 * a ezt a vírust kenik egy virológusra, az elfelejti az összes addig megtanult genetikai kódot.
 * Ennek a vírusnak a hatása végzetes és azonnali, az érintett virológus egyból felejt, így a vírusnak nincs hatásideje, és a step függvénye nem csinál semmit.
 */
public class AmniVirus extends Agent {
    /**
     * A paraméterként kapott virológusra kenődik az ágens, az ágensnek megfelelő hatás aktiválódik a virológuson
     * és az ágens időzítője elkezd visszaszámolni. Az áldozatul esett virológus azonnal elfelejti az összes megtanult genetikai kódot.
     */
    public AmniVirus() {
        virusTimer = 3;
    }

    public void smear(Virologist v) {
        v.forgetCodes();
        super.smear(v);
    }

    /**
     * Felülírja az ős step függvényét, nem csinál semmit.
     */
    public void step() {
    }
}
