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
}
