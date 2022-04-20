import java.util.ArrayList;

/**
 * A Steppable objektumokat kezelő Controller osztály.
 */
public class SteppableController {
    private ArrayList<Steppable> steppables = new ArrayList<>();

    /**
     * Ez a metódus minden Steppable interface-t megvalósító objektumra meghívja a step() metódust.
     */
    public void step() {
        for (Steppable steppable : steppables) {
            steppable.step();
        }
    }
}
