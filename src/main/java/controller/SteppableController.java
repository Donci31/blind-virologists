package controller;

import model.Steppable;
import model.Virologist;
import model.agents.Agent;

import java.util.ArrayList;

/**
 * A model.Steppable objektumokat kezelő Controller osztály.
 */
public abstract class SteppableController {
    private static ArrayList<Steppable> steppables = new ArrayList<>();
    private static ArrayList<Agent> appliedAgents = new ArrayList<>();
    private static ArrayList<Agent> kidobott = new ArrayList<>();

    public static void kidob(Agent a) {
        SteppableController.kidobott.add(a);
    }

    /**
     * Ez a metódus minden model.Steppable interface-t megvalósító objektumra meghívja a step() metódust.
     */
    public static void step() {
        for (Steppable steppable : steppables) {
            steppable.step();
        }
        for (Agent a : appliedAgents) {
            a.step();
        }
        for (Agent a : kidobott) {
            appliedAgents.remove(a);
        }
    }

    /**
     * A model.Steppable objektumok listájához hozzáad egy új model.Steppable objektumot.
     *
     * @param s - hozzáadandó léptethető objektum
     */
    public static void addSteppable(Steppable s) {
        steppables.add(s);
    }

    /**
     * A model.Steppable objektumok listájából kivesz egy korábban hozzáadott model.Steppable objektumot.
     *
     * @param s - kivevendő léptethető objektum
     */
    public static void removeSteppable(Steppable s) {
        steppables.remove(s);
    }

    /**
     * Az aktív ágensek listájához hozzáad egy frissen aktivált ágenst.
     *
     * @param a - új aktív ágens
     */
    public static void addAppliedAgent(Agent a) {
        appliedAgents.add(a);
    }

    /**
     * Az aktív ágensek listájából kivesz egy éppen lejárt ágenst.
     *
     * @param a - lejárt ágens
     */
    public static void removeAppliedAgent(Agent a) {
        appliedAgents.remove(a);
    }

    /**
     * Az aktív ágensek listájának gettere a kiíratáshoz.
     *
     * @return aktív ágensek listája
     */
    public static ArrayList<Agent> getAppliedAgents() {
        return appliedAgents;
    }

    /**
     * A megadott virológus aktív ágenseit törli a listából. Akkor hívódik, ha a virológus meghalt.
     *
     * @param v eltűnő virológus
     */
    public static void removeVirologistsAgents(Virologist v) {
        for (int i = 0; i < appliedAgents.size(); ) {
            if (appliedAgents.get(i).getSmearedVirologist() == v) {
                appliedAgents.remove(i);
            } else {
                i++;
            }
        }
    }

    /**
     * Kiüríti a model.Steppable-k listáit egy futás végén.
     */
    public static void clearSteppables() {
        steppables.clear();
        appliedAgents.clear();
    }
}
