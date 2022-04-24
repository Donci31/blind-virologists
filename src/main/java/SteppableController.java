import java.util.ArrayList;

/**
 * A Steppable objektumokat kezelő Controller osztály.
 */
public abstract class SteppableController {
    private static ArrayList<Steppable> steppables = new ArrayList<>();
    private static ArrayList<Agent> appliedAgents = new ArrayList<>();

    /**
     * Ez a metódus minden Steppable interface-t megvalósító objektumra meghívja a step() metódust.
     */
    public static void step() {
        for (Steppable steppable : steppables) {
            steppable.step();
        }
        for (Agent a : appliedAgents) {
            a.step();
        }
    }

    /**
     * A Steppable objektumok listájához hozzáad egy új Steppable objektumot.
     * @param s - hozzáadandó léptethető objektum
     */
    public static void addSteppable(Steppable s) {
        steppables.add(s);
    }

    /**
     * A Steppable objektumok listájából kivesz egy korábban hozzáadott Steppable objektumot.
     * @param s - kivevendő léptethető objektum
     */
    public static void removeSteppable(Steppable s) {
        steppables.remove(s);
    }

    /**
     * Az aktív ágensek listájához hozzáad egy frissen aktivált ágenst.
     * @param a - új aktív ágens
     */
    public static void addAppliedAgent(Agent a) {
        appliedAgents.add(a);
    }

    /**
     * Az aktív ágensek listájából kivesz egy éppen lejárt ágenst.
     * @param a - lejárt ágens
     */
    public static void removeAppliedAgent(Agent a) {
        appliedAgents.remove(a);
    }

    /**
     * Az aktív ágensek listájának gettere a kiíratáshoz.
     * @return aktív ágensek listája
     */
    public static ArrayList<Agent> getAppliedAgents() {
        return appliedAgents;
    }

    /**
     * A megadott virológus aktív ágenseit törli a listából. Akkor hívódik, ha a virológus meghalt.
     * @param v eltűnő virológus
     */
    public static void removeVirologistsAgents(Virologist v) {
        for (int i = 0; i < appliedAgents.size(); ) {
            if (appliedAgents.get(i).smearedVirologist == v) {
                appliedAgents.remove(i);
            } else {
                i++;
            }
        }
    }
}
