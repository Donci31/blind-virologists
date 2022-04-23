import java.util.ArrayList;

/**
 * A Steppable objektumokat kezelő Controller osztály.
 */
public abstract class SteppableController {
    private static ArrayList<Steppable> steppables = new ArrayList<>();

    /**
     * Ez a metódus minden Steppable interface-t megvalósító objektumra meghívja a step() metódust.
     */
    public static void step() {
        for (Steppable steppable : steppables) {
            steppable.step();
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
     * @param s - kivonandó léptethető objektum
     */
    public static void removeSteppable(Steppable s) {
        steppables.remove(s);
    }
}
