import java.io.*;
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
     * A load parancs hatását megvalósító metódus.
     * Betölti az első argumentumban megadott fájlból a játék állapotát.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void load(String[] args) {
        try {
            FileInputStream f = new FileInputStream(args[1]);

            //TODO

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A save parancs hatását megvalósító metódus.
     * Kiírja az aktuális állapotot az első argumentumban megadott nevű fájlba.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void save(String[] args) {
        try {
            FileOutputStream f = new FileOutputStream(args[1]);

            //TODO

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Field dest = fields.get(args[2]);

        // Lehet, hogy a szomszédtesztet inkább a move()-on belül kellene elintézni
        if (!v.getField().isNeighbor(dest)) {
            System.out.println("Error! " + args[1] + " can't reach " + args[2] + "!");
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
        Agent agent = agents.get(args[2]);
        Virologist victim = virologists.get(args[3]);

        if (args[4].length() > 1 && (gears.containsKey(args[4]) || agents.containsKey(args[4]))) {
            // Ha van opcionális 4. paraméter, át kell adni a védekezés típusát is
            //attacker.smearAgent(agent, victim); // TODO új paraméter a függvénybe, try-catch kell még ide
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
        Code code = codes.get(args[2]);
        v.craftAgent(code);
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
        // Ha nem óvóhelyen történt a metódushívás, akkor nem történik semmi sem
        if (v.getField() instanceof Shelter) {
            // Ha van opcionális 2. argumentum, azaz eldob a virológus egy felszerelést
            if (args[2].length() > 1 && gears.containsKey(args[2])) {
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
        Virologist victim = virologists.get(args[2]);
        Gear gear = gears.get(args[3]);
        // TODO vajon itt érdemes a "szomszédosságot" leellenőrízni vagy bent a loot() metódusban?
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
        Virologist victim = virologists.get(args[2]);
        // TODO vajon itt érdemes a "szomszédosságot" leellenőrízni vagy bent a hit() metódusban?
        if (attacker.getField() == victim.getField()) {
            attacker.hit(victim);
        }
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
        if (args[0].toLowerCase(Locale.ROOT).equals("y") || args[0].toLowerCase(Locale.ROOT).equals("yes")) {
            deterministic = true;
        } else if (args[0].toLowerCase(Locale.ROOT).equals("n") || args[0].toLowerCase(Locale.ROOT).equals("no")) {
            deterministic = false;
        } else {
            System.out.println("Error in command 'set_deterministic'! Usage: set_deterministic (Y/N)");
        }

        /*Test1
        Field f1=new Field();
        Field f2=new Field();
        Virologist v1=new Virologist();
        f1.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        */

        /*Test 2
        Shelter f1=new Shelter();
        Shelter f2=new Shelter();
        Virologist v1=new Virologist();
        f1.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        */

        /*Test 3
        Warehouse f1=new Warehouse;
        Warehouse f2=new Warehouse;
        Virologist v1=new Virologist();
        f1.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        */

        /*TEst 4
        Laboratory f1=new Laboratory();
        Laboratory f2=new Laboratory();
        f1.setInfected(false);
        f2.setInfected(false);
        Virologist v1=new Virologist();
        f1.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        */

        /*TEst 5
        Shelter f1=new shelter();
        RobeGear g1=new RobeGear();
        Virologist v1=new Virologist();
        f1.addGear(g1);
        f1.accept(v1);
         ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        */


        /*Test 6
        Shelter f1=new shelter();
        AxeGear g1=new AxeGear();
        SackGear g2=new SackGear();
        Virologist v1=new Virologist();
        f1.addGear(g1);
        f1.addGear(g2);
        f1.accept(v1);
        v1.pickUpGear(g2);
         ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        */

        /*Test 7
        Field f1=new Field();
        Laboratory f2=new Laboratory();
        f2.setInfected(true);
        Virologist v1=new Virologist();
        f1.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        */


        /*Test8
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        AxeGear g1=new AxeGear();
        g1.useAxe();
        v1.pickUpGear(g1);
        */

        /*Test9
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        AxeGear g1=new AxeGear();
        f1
        v1.addGear(g1);
        */

        /*Test 10
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        RobeGear g1=new RobeGear();
        v2.addGear(g1);
        f1.accept(v1);
        f1.accept(v2);
        StunVirus a1=new StunVirus();
        a1.setSmearedVirologist(v2);
        a1.smear(v1);
        */

        //Test 11

    }

    /**
     * A step parancs hatását megvalósító metódus.
     * Lépteti a játékot egy körrel.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void step(String[] args) {
        SteppableController.step();
    }

    public static void main(String[] args) {
        Field f1 = new Warehouse();
        Field f2 = new Shelter();
        Field f3 = new Laboratory();
        f1.accept(new Virologist());
        f1.accept(new Virologist());
        f3.accept(new Virologist());
        f1.setNeighbor(0, f2);
        f2.setNeighbor(0, f1);
        f3.setNeighbor(0, f1);
        f3.setNeighbor(1, f2);
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        InputWriter.printFields(fields);

        /*var ir = new InputReader();
        ArrayList<Field> fieldsLoaded = ir.readFields("./src/main/resources/file.yml");
        for(Field f : fieldsLoaded){
            System.out.println("Field: " + f.getName() + "\n");
            for(Virologist v: f.getVirologists()){
                System.out.println("Virologist: " + v.getName() + "\n");
            }
        }*/
    }
}
