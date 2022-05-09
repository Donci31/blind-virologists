package model;

import model.agents.Agent;
import model.codes.Code;
import model.fields.Field;
import model.fields.Laboratory;
import model.fields.Shelter;
import model.fields.Warehouse;
import model.gears.Gear;

import java.io.File;
import java.util.*;
import java.util.Map;

/**
 * Az osztály, amely a prototípus viselkedést megvalósítja.
 * Kezeli a beérkező user inputot, a parancsokat értelmezi, hatásukat érvényre juttatja.
 */
public class Prototype {
    private static boolean deterministic = false;
    // A prototípus számon tartja a különböző fajta objektumpéldányok azonosítóit, és hozzárendeli a valódi objektumok referenciáihoz
    public static Map<String, Virologist> virologists = new HashMap<>();
    public static Map<String, Field> fields = new HashMap<>();
    public static Map<String, Agent> agents = new HashMap<>();
    public static Map<String, Code> codes = new HashMap<>();
    public static Map<String, Gear> gears = new HashMap<>();

    /**
     * A detereministic attribútum gettere.
     * @return deterministic
     */
    public static boolean getDeterministic(){return deterministic;}

    /**
     * Kitöröl minden egyes korábbi objektumot az azonsoítókat tartalmazó map-ekből
     */
    protected static void clearMaps() {
        virologists.clear();
        fields.clear();
        agents.clear();
        codes.clear();
        gears.clear();
    }

    /**
     * Az összes id-t az alapraméretezett állapotba állítja
     */
    protected static void resetAllID() {
        Field.resetID();
        Agent.resetID();
        Code.resetID();
        Gear.resetID();
        Virologist.resetID();
    }

    /**
     * A load parancs hatását megvalósító metódus.
     * Betölti az első argumentumban megadott fájlból a játék állapotát.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void load(String[] args) {
        var ir = new InputReader();
        ArrayList<Field> fieldsLoaded = ir.readFields("./src/test/resources/" + args[1]);
        for (Virologist virologist : virologists.values()) {
            virologist.signalEndTurn();
        }
    }

    /**
     * A save parancs hatását megvalósító metódus.
     * Kiírja az aktuális állapotot az első argumentumban megadott nevű fájlba.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void save(String[] args) {
        InputWriter.printFields(args[1], new ArrayList<>(fields.values()));
    }

    /**
     * A move parancs hatását megvalósító metódus.
     * Az első argumentumban kapott virológust a második argumentumban megadott azonosítójú mezőre lépteti.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void move(String[] args) {
        if (!virologists.containsKey(args[1]) || !fields.containsKey(args[2])) {
            System.out.println("Error in command 'move'! Usage: move <virologist_id> <field_id>");
            return;
        }

        Virologist v = virologists.get(args[1]);
        if (v.isStunned()) return;

        Field dest = fields.get(args[2]);

        // Lehet, hogy a szomszédtesztet inkább a move()-on belül kellene elintézni
        if (!v.getField().isNeighbor(dest)) {
            throw new IllegalArgumentException("Error! " + args[1] + " can't reach " + args[2] + "!");
        } else {
            v.move(dest);
        }
    }

    /**
     * A smear parancs hatását megvalósító metódus.
     * Az első argumentumban kapott virológust a második argumentumban megadott ágensét
     * a harmadik argumentumban megadott virológusra keni.
     * Opcionálisan az áldozat virológus a negyedik argumentumban jelezheti, hogy melyik védőeszközével védekezik
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void smear(String[] args) {
        if (!virologists.containsKey(args[1]) || !agents.containsKey(args[2]) || !virologists.containsKey(args[3])) {
            System.out.println("Error in command 'smear'! Usage: smear <virologist_id> <agent_id> <virologist_id> [<gear_id/agent_id>]");
            return;
        }

        Virologist attacker = virologists.get(args[1]);
        if (attacker.isStunned()) return;

        Agent agent = agents.get(args[2]);
        Virologist victim = virologists.get(args[3]);

        if (attacker.getField() != victim.getField()) {
            throw new IllegalArgumentException("Error! " + attacker.getName() + " can't reach " + victim.getName() + "!");
        }

        if (args.length > 4) {
            // Ha van opcionális 4. paraméter, át kell adni a védekezés típusát is
            attacker.smearAgent(agent, victim);
            try {
                victim.chooseAbsorbStrat(Integer.parseInt(args[4]));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Error! " + victim.getName() + " doesn't have this many defenses!");
            }
        } else {
            // Ha nincs, nem kell átadni a védekezés típusát
            attacker.smearAgent(agent, victim);
        }
    }

    /**
     * A craft_agent parancs hatását megvalósító metódus.
     * Az első argumentumban kapott virológus létrehozza a második argumentumban megadott genetikai kódból az ahhoz tartozó ágens egy példányát.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void craftAgent(String[] args) {
        if (!virologists.containsKey(args[1]) || !codes.containsKey(args[2])) {
            System.out.println("Error in command 'craft_agent'! Usage: craft_agent <virologist_id> <code_id>");
            return;
        }

        Virologist v = virologists.get(args[1]);
        if (v.isStunned()) return;
        Code code = codes.get(args[2]);
        if (!v.getIdList().contains(code.getId())) {
            throw new IllegalArgumentException("Error! " + v.getName() + " can't craft " + code.getId() + "!");
        }

        try {
            v.craftAgent(code);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error! " + v.getName() + " lacks resources!");
        }
    }

    /**
     * A learn_code parancs hatását megvalósító metódus.
     * Az első argumentumban kapott virológus megtanulja a második argumentumban megadott genetikai kódot a laboratóriumban.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void learnCode(String[] args) {
        if (!virologists.containsKey(args[1])) {
            System.out.println("Error in command 'learn_code'! Usage: learn_code <virologist_id>");
            return;
        }

        Virologist v = virologists.get(args[1]);
        if (v.isStunned()) return;

        // Ha nem laboratóriumon történt a metódushívás, akkor nem történik semmi sem
        if (v.getField() instanceof Laboratory) {
            v.touch();
        }
    }

    /**
     * A get_gear parancs hatását megvalósító metódus.
     * Az első argumentumban kapott virológus felveszi a mezőjén levő felszerelést.
     * A második argumentum opcionális. Felvételkor a második argumentumban megjelölt felszerelést eldobja a virológus.
     * Hogyha az új felszerelés felvevésével 3-nál több felszerelése lenne a virológusnak, akkor az először felvett felszerelését automatikusan eldobja.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void getGear(String[] args) {
        if (!virologists.containsKey(args[1])) {
            System.out.println("Error in command 'get_gear'! Usage: get_gear <virologist_id> [gear_id]");
            return;
        }

        Virologist v = virologists.get(args[1]);
        if (v.isStunned()) return;

        // Ha nem óvóhelyen történt a metódushívás, akkor nem történik semmi sem
        if (v.getField() instanceof Shelter) {
            // Ha van opcionális 2. argumentum, azaz eldob a virológus egy felszerelést
            if (args.length > 2 && gears.containsKey(args[2])) {
                Gear gearToBeThrown = gears.get(args[2]);
                v.loseGear(gearToBeThrown);
            }
            v.touch();
        }
    }

    /**
     * A loot parancs hatását megvalósító metódus.
     * Az első argumentumban kapott virológus ellopja a második argumentumban jelzett virológustól a harmadik argumentumban megadott felszerelését.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void loot(String[] args) {
        if (!virologists.containsKey(args[1]) || !virologists.containsKey(args[2]) || !gears.containsKey(args[3])) {
            System.out.println("Error in command 'loot'! Usage: loot <virologist_id> <virologist_id> <gear_id>");
            return;
        }

        Virologist attacker = virologists.get(args[1]);
        if (attacker.isStunned()) return;

        Virologist victim = virologists.get(args[2]);
        Gear gear = gears.get(args[3]);
        if (attacker.getField() == victim.getField()) {
            attacker.loot(victim, gear);
        }
    }

    /**
     * A hit parancs hatását megvalósító metódus.
     * Az első argumentumban kapott virológus megüti a második argumentumban jelzett virológust (természetesen csak baltával lesz ennek bármi hatása).
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void hit(String[] args) {
        if (!virologists.containsKey(args[1]) || !virologists.containsKey(args[2])) {
            System.out.println("Error in command 'hit'! Usage: hit <virologist_id> <virologist_id>");
            return;
        }

        Virologist attacker = virologists.get(args[1]);
        if (attacker.isStunned()) return;

        Virologist victim = virologists.get(args[2]);
        if (attacker.getField() != victim.getField()) {
            throw new IllegalArgumentException("Error! " + attacker.getName() + " can't reach " + victim.getName() + "!");
        }
        attacker.hit(victim);
    }

    /**
     * A gather_resources parancs hatását megvalósító metódus.
     * Az első argumentumban kapott virológus begyűjti a raktár mezőjéről a rajta található nyersanyagokat.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void gatherResources(String[] args) {
        if (!virologists.containsKey(args[1])) {
            System.out.println("Error in command 'gather_resources'! Usage: gather_resources <virologist_id>");
            return;
        }

        Virologist v = virologists.get(args[1]);
        if (v.isStunned()) return;

        // Ha nem raktárban történt a metódushívás, akkor nem történik semmi sem
        if (v.getField() instanceof Warehouse) {
            v.touch();
        }
    }

    /**
     * A set_deterministic parancs hatását megvalósító metódus.
     * Beállítja a determinisztikus futás igazságértékét az első argumentumban megadott Y/N (true/false) értékre.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void setDeterministic(String[] args) {
        if (args[1].toLowerCase(Locale.ROOT).equals("y") || args[1].toLowerCase(Locale.ROOT).equals("yes")) {
            deterministic = true;
        } else if (args[1].toLowerCase(Locale.ROOT).equals("n") || args[1].toLowerCase(Locale.ROOT).equals("no")) {
            deterministic = false;
        } else {
            System.out.println("Error in command 'set_deterministic'! Usage: set_deterministic (Y/N)");
        }
    }

    /**
     * A step parancs hatását megvalósító metódus.
     * Lépteti a játékot egy körrel.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void step(String[] args) {
        SteppableController.step();
    }

    /**
     * A prototípus futásának kezelését megvalósító main().
     * @param testid teszt futtatása
     */
    public static void runTest(int testid) {
        Map<String, Command> commands = new HashMap<>();
        commands.put("load", Prototype::load);
        commands.put("save", Prototype::save);
        commands.put("move", Prototype::move);
        commands.put("smear", Prototype::smear);
        commands.put("craft_agent", Prototype::craftAgent);
        commands.put("learn_code", Prototype::learnCode);
        commands.put("get_gear", Prototype::getGear);
        commands.put("loot", Prototype::loot);
        commands.put("hit", Prototype::hit);
        commands.put("gather_resources", Prototype::gatherResources);
        commands.put("set_deterministic", Prototype::setDeterministic);
        commands.put("step", Prototype::step);

        clearMaps();
        resetAllID();
        SteppableController.clearSteppables();

        File inputFile = new File("./src/test/resources/test" + testid + "/in.txt");

        try (Scanner input = new Scanner(inputFile)) {
            while (input.hasNextLine()) {
                String[] command = input.nextLine().split(" ");
                commands.get(command[0]).execute(command);
            }
        } catch (Exception e) {
            System.out.println("\nTest" + testid + " was unsuccessful!\n\n");
            e.printStackTrace();
        }
    }
}
