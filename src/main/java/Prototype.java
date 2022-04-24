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

    public static boolean getDeterministic(){return deterministic;}

    /**
     * A load parancs hatását megvalósító metódus.
     * Betölti az első argumentumban megadott fájlból a játék állapotát.
     * @param args - a parancs argumentumainak tömbje
     */
    protected static void load(String[] args) {
        var ir = new InputReader();
        //ArrayList<Field> fieldsLoaded = ir.readFields("./src/main/resources/file.yml");
        ArrayList<Field> fieldsLoaded = ir.readFields("./src/main/resources/" + args[1]);
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
        if (attacker.isStunned()) return;

        Agent agent = agents.get(args[2]);
        Virologist victim = virologists.get(args[3]);

        if (args[4].length() > 1) {
            // Ha van opcionális 4. paraméter, át kell adni a védekezés típusát is
            attacker.smearAgent(agent, victim);
            victim.chooseAbsorbStrat(Integer.parseInt(args[4]));
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
        if (attacker.isStunned()) return;

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
        if (attacker.isStunned()) return;

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
        if (args[0].toLowerCase(Locale.ROOT).equals("y") || args[0].toLowerCase(Locale.ROOT).equals("yes")) {
            deterministic = true;
        } else if (args[0].toLowerCase(Locale.ROOT).equals("n") || args[0].toLowerCase(Locale.ROOT).equals("no")) {
            deterministic = false;
        } else {
            System.out.println("Error in command 'set_deterministic'! Usage: set_deterministic (Y/N)");
        }
    }

        //TODO Ezeket most ideteszem, ha gondoljátok át lehet tenni majd máshova őket
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

        /*Test 1 exp
        Field f1=new Field();
         Field f2=new Field();
           Virologist v1=new Virologist();
        f2.accept(Virologist);
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

        /* Test 2 exp
        Shelter f1=new Shelter();
        Shelter f2=new Shelter();
        Virologist v1=new Virologist();
        f2.accept(Virologist);
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
        /* Test 3 exp
         Warehouse f1=new Warehouse;
        Warehouse f2=new Warehouse;
        Virologist v1=new Virologist();
        f2.accept(Virologist);
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

        /* Test 4 exp
        Laboratory f1=new Laboratory();
        Laboratory f2=new Laboratory();
        f1.setInfected(false);
        f2.setInfected(false);
        Virologist v1=new Virologist();
        f2.accept(Virologist);
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

          /* Test 5 exp
         Shelter f1=new shelter();
        RobeGear g1=new RobeGear();
        Virologist v1=new Virologist();
        f1.accept(v1);
        v1.addGear(g1);
         ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
         */

        /*Test 6
        Shelter f1=new shelter();
        AxeGear g1=new AxeGear();
        SackGear g2=new SackGear();
        Virologist v1=new Virologist();
        f1.addGear(g1);
        f1.accept(v1);
        v1.addGear(g2);
         ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        */

          /* Test 6 exp
         Shelter f1=new shelter();
        AxeGear g1=new AxeGear();
        Virologist v1=new Virologist();
        f1.accept(v1);
        v1.addGear(g1);
         ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);

          */

        /*Test 7
        Field f1=new Field();
        Laboratory f2=new Laboratory();
        f2.setInfected(true);
        Virologist v1=new Virologist();
        f1.accept(v1);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        */

        /* Test 7 exp
        Field f1=new Field();
        Laboratory f2=new Laboratory();
        f2.setInfected(true);
        Virologist v1=new Virologist();
        f2.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        BearVirus a1=new BearVirus();
        a1.smear(v1);
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

    /* Test 8 exp
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

        /* Test 9 exp
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        f1.accept(v1);
        AxeGear g1=new AxeGear();
        v1.addGear(g1);
        g1.useAxe();
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

        /* Test 10 exp
         Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        RobeGear g1=new RobeGear();
        v1.addGear(g1);
        f1.accept(v1);
        f1.accept(v2);
        StunVirus a1=new StunVirus();
        a1.setSmearedVirologist(v2);
        a1.smear(v1);

        */

        /*Test 11
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        RobeGear g1=new RobeGear();
        v1.addGear(g1);
        GloveGear g2=new GloveGear();
        SackGear g3 =new SackGear();
        AxeGear g4=new AxeGear();
        v1.addGear(g2);
        v1.addGear(g3);
        v2.addGear(g4);
        StunVirus a1=new StunVirus();
        a1.smear(v2);
        */

         /* Test 11 exp
         Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        RobeGear g1=new RobeGear();
        GloveGear g2=new GloveGear();
        SackGear g3 =new SackGear();
        AxeGear g4=new AxeGear();
        v1.addGear(g2);
        v1.addGear(g3);
        v1.addGear(g4);
        StunVirus a1=new StunVirus();
        a1.smear(v2);

          */

        /*Test 12
        Warehouse f1=new Warehouse();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        f1.accept(v1);
        f1.setaProduced(5000);
        f1.setnProduced(5000);
        */

        /* Test 12 exp
           Warehouse f1=new Warehouse();
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
    Virologist v1=new Virologist();
        f1.accept(v1);
        f1.setaProduced(4500);
        f1.setnProduced(4500);
      */

        /*Test 13
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        AmniVirus a1=new AmniVirus();
        v1.addCraftedAgent(a1);
        */

        /* Test 13 exp
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        AmniVirus a1=new AmniVirus();
        a1.smear(v2);
        */

        /*Test 14
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        StunVirus a1=new StunVirus();
        v1.addCraftedAgent(a1);
        GloveGear g1=new GloveGear();
        v2.addGear(g1);
        */

        /* Test 14 exp
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        StunVirus a1=new StunVirus();
        GloveGear g1=new GloveGear();
        v2.addGear(g1);
        g1.setTimesUsed(1);
        a1.smear(v1);


        */

        /*Test 15
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        DanceVirus a1=new DanceVirus();
        v1.addCraftedAgent(a1);
        ProtVaccine a2=new ProtVaccine();
        a2.smear(v2);
        */

        /* Test 15 exp
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        DanceVirus a1=new DanceVirus();
        ProtVaccine a2=new ProtVaccine();
        a2.smear(v2);


         */

        /*Test 16
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        ProtVaccine a1=new ProtVaccine();
        v1.addCraftedAgent(a1);
        RobeGear g1=new RobeGear();
        v2.addGear(g1);
        */

        /* Test 16 exp
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        ProtVaccine a1=new ProtVaccine();
        RobeGear g1=new RobeGear();
        v2.addGear(g1);
     */

        /*Test 17
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        f1.accept(v1);
        AmniCode c1=new AmniCode();
        v1.learnCode(c1);
        v1.addNucleotide(100);
        v1.addAminoAcid(100);
        */

     /* Test 17 exp
     Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        f1.accept(v1);
        AmniCode c1=new AmniCode();
        v1.learnCode(c1);
        AmniVirus a1=new AmniVirus();
        v1.addCraftedAgent(a1);
        v1.addNucleotide(50);
        v1.addAminoAcid(50);
     */

        /*Test 18
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        f1.accept(v1);
        ProtCode c1=new ProtCode();
        v1.learnCode(c1);
        v1.addNucleotide(50);
        v1.addAminoAcid(50);
        */

        /* Test 18 exp
        Error! v1 lacks resources!
        */

        /*Test 19
        Field f1 = new Field();
        Field f2 = new Field()
        Field f3 = new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        f1.setNeighbor(0, f2);
        f1.setNeighbor(1, f3);
        f2.setNeighbor(0, f1);
        f3.setNeighbor(0, f1);
        DanceVirus a1=new DanceVirus();
        Virologist v1=new Virologist();
        f2.accept(v1);
        a1.smear(v1);
        */

        /* Test 19 exp
        Field f1 = new Field();
        Field f2 = new Field()
        Field f3 = new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        f1.setNeighbor(0, f2);
        f1.setNeighbor(1, f3);
        f2.setNeighbor(0, f1);
        f3.setNeighbor(0, f1);
        DanceVirus a1=new DanceVirus();
        Virologist v1=new Virologist();
        f3.accept(v1);
        a1.smear(v1);
        */

        /*Test 20
        Field f1 = new Field();
        Field f2 = new Field()
        Warehouse f3 = new Warehouse();
        f3.setnProduced(500);
        f3.setaProduced(500);
        f1.setNeighbor(0, f2);
        f1.setNeighbor(1, f3);
        f2.setNeighbor(0, f1);
        f3.setNeighbor(0, f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        BearVirus a1=new BearVirus();
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f3.accept(v2);
        f1.accept(v1);
        a1.smear(v1);
        v1.setAminoAcid(100);
        v1.setNucleotide(100);
        v1.setAminoAcid(30);
        v1.setNucleotide(40);
        */
        //TODO NCountot és ACountot növelni
       /* Test 20 exp
        Field f1 = new Field();
        Field f2 = new Field()
         ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        Warehouse f3 = new Warehouse();
        f3.setnProduced(0);
        f3.setaProduced(0);
        f1.setNeighbor(0, f2);
        f1.setNeighbor(1, f3);
        f2.setNeighbor(0, f1);
        f3.setNeighbor(0, f1);
        BearVirus a1=new BearVirus();
        Virologist v1=new Virologist();
        f1.accept(v1);
        a1.smear(v1);
        BearVirus a2=new BearVirus();
        a2.smear(v2);
        v1.setAminoAcid(100);
        v1.setNucleotide(100);
     */

    /*Teszt 21
    Shelter f1=new shelter();
    SackGear g1=new RobeGear();
    Virologist v1=new Virologist();
        f1.addGear(g1);
        f1.accept(v1);
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
    */

    /*Teszt 21 exp
    Shelter f1=new shelter();
    SackGear g1=new RobeGear();
    Virologist v1=new Virologist();
    v1.addGear(g1);
    f1.accept(v1);
    ArrayList<Field> fields=new ArrayList<>();
    fields.add(f1);
    v1.setAminoAcid(100);
        v1.setNucleotide(100);
     */

    /*Teszt 22
        Shelter f1=new Shelter();
        Field f2=new Shelter();
        Virologist v1=new Virologist();
        f1.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        RobeGear g1=new RobeGear();
        f1.addGear();
        Stunvirus a1=new StunVirus();
        AmniCode c1=new AmniCode();
        v1.learnCode(c1;)
        a1.smear(v1);
        v1.setAminoAcid(100);
        v1.setNucleotide(100);
     */

    /*Teszt 22 exp
        Shelter f1=new Shelter();
        Field f2=new Shelter();
        Virologist v1=new Virologist();
        f2.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        RobeGear g1=new RobeGear();
        f1.addGear();
        Stunvirus a1=new StunVirus();
        AmniCode c1=new AmniCode();
        v1.learnCode(c1;)
        a1.smear(v1);
        v1.setAminoAcid(100);
        v1.setNucleotide(100);
     */

    /*Teszt 23
        Field f1 = new Field();
        Field f2 = new Field()
        Field f3 = new Field();
        f1.setNeighbor(0, f2);
        f1.setNeighbor(1, f3);
        f2.setNeighbor(0, f1);
        f3.setNeighbor(0, f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        DanceVirus a1=new DanceVirus();
        Virologist v1=new Virologist();
        f1.accept(v1);
        Amnicode c1=new AmniCode();
        v1.learnCode(c1);
        DanceVirus a1=new DanceVirus();
        a1.smear(v1);
        for(int i=0;i<a1.getVirusTimer()-1;i++){
            a1.step();

        }
        v1.setAminoAcid(100);
        v1.setNucleotide(100);


     */

    /*Teszt 23 exp
          Field f1 = new Field();
        Field f2 = new Field()
        Field f3 = new Field();
        f1.setNeighbor(0, f2);
        f1.setNeighbor(1, f3);
        f2.setNeighbor(0, f1);
        f3.setNeighbor(0, f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        DanceVirus a1=new DanceVirus();
        Virologist v1=new Virologist();
        f3.accpet(v1);
        Amnicode c1=new AmniCode();
        v1.learnCode(c1);
        DanceVirus a1=new DanceVirus();
        v1.setAminoAcid(100);
        v1.setNucleotide(100);
     */

    /*Teszt 24
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
        Amnicode c1=new AmniCode();
        v1.learnCode(c1);
        Protvaccine a1=new ProtVaccine();
        a1.smear(v1);
        for(int i=0;i<a1.getVirusTimer()-1;i++){
            a1.step();
        }
        v1.setAminoAcid(100);
        v1.setNucleotide(100);
     */

    /*Teszt 24 exp
        Field f1=new Field();
        Laboratory f2=new Laboratory();
        f2.setInfected(true);
        Virologist v1=new Virologist();
        f2.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        Amnicode c1=new AmniCode();
        v1.learnCode(c1);
        BearVirus a2=new BearVirus();
        a2.smear(v1);
        v1.setAminoAcid(100);
        v1.setNucleotide(100);
     */

    /*Teszt 25
        Field f1=new Field();
        Field f2=new Field();
        Virologist v1=new Virologist();
        f1.accept(Virologist);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        Amnicode c1=new AmniCode();
        v1.learnCode(c1);
        StunVirus a1=new StunVirus();
        a2.smear(v1);
        for(int i=0;i<a1.getVirusTimer()-1;i++){
            a1.step();
        }
        v1.setAminoAcid(100);
        v1.setNucleotide(100);
     */

    /*Teszt 25 exp

     Field f1=new Field();
        Field f2=new Field();
        Virologist v1=new Virologist();
        f2.accept(v1);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        Amnicode c1=new AmniCode();
        v1.learnCode(c1);
        v1.setAminoAcid(100);
        v1.setNucleotide(100);

     */

    /*Teszt 26
        Laboratory f1=new Laboratory();
        Virologist v1=new Virologist();
        f1.accept(v1);
         ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        AmniCode c1=new AmniCode;
        f1.placeCode(c1);
         v1.setAminoAcid(100);
        v1.setNucleotide(30);
     */

    /*Teszt 26 exp
        Laboratory f1=new Laboratory();
        Virologist v1=new Virologist();
        f1.accept(v1);
         ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        AmniCode c1=new AmniCode;
        f1.placeCode(c1);
         v1.setAminoAcid(100);
        v1.setNucleotide(30);
        v1.learnCode(c1);
     */

    /*Teszt 27
        Laboratory f1=new Laboratory();
        Laboratory f2=new Laboratory();
        Laboratory f3=new Laboratory();
        Laboratory f4=new Laboratory();
        f1.setNeighbor(0,f2);
        f1.setNeighbor(1,f3);
        f1.setNeighbor(2,f4);
        f2.setNeighbor(0,f1);
        f2.setNeighbor(1,f3);
        f2.setNeighbor(2,f4);
        f3.setNeighbor(0,f1);
        f3.setNeighbor(1,f2);
        f3.setNeighbor(2,f4);
        f4.setNeighbor(0,f1);
        f4.setNeighbor(1,f2);
        f4.setNeighbor(2,f3);
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
        fields.add(f4);
        Virologist v1=new Virologist();
        f1.placeCode(c1);
        f2.placeCode(c2);
        f3.placeCode(c3);
        f4.placeCode(c4);
        v1.learnCode(c1);
        v1.learnCode(c2);
        v1.learnCode(c3);
        F4.accept(v1);
        v1.setAminoAcid(200);
        v1.setNucleotide(100);
     */

    /*Teszt 27 exp
      The game has ended, v1 won!

    */

    /*Teszt 28
        Field f1=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f1.accept(v2);
        AmniCode c1=new AmniCode();
        DanceCode c2=new DanceCode();
        v2.learnCode(c1);
        v2.learnCode(c2);
        v1.setAminoAcid(30);
        v1.setNucleotide(20);
        v2.setAminoAcid(50);
        v2.setNucleotide(50);
        AmniVirus a1=new AmniVirus();
        DanceVirus a2=new DanceVirus();
        v2.addCraftedAgent(a1);
        v2.addCraftedAgent(a2);
        GloveGear g1=new GloveGear();
        v1.addGear(g1);
        g1.setTimesUsed(2);

     */

    /*Test 28 exp
	    Error! v1 doesn’t have g1!

     */

    /* Test 29
        Field f1=new Field();
        Field f2=new Field();
        ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        f1.setNeighbor(0,f2);
        f2.setNeighbor(0,f1);
        Virologist v1=new Virologist();
        Virologist v2=new Virologist();
        f1.accept(v1);
        f2.accept(v2);
        AmniVirus a1=new AmniVirus();
        v1.addCraftedAgent(a1);

     */

    /*Test 29 exp
        Error! v1 can’t reach v2!

     */



    //_________________________________________________________________________30-tól
public void valami() {

    /* Text 30
    Field f1 = new Field();
    Field f2 = new Field();
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
    f1.setNeighbor(0, f2);
    f2.setNeighbor(0, f1);
    AxeGear g1 = new AxeGear();
    Virologist v1 = new Virologist();
    Virologist v2 = new Virologist();
    v1.addGear(g1);
    f1.accept(v1);
    f2.accept(v2);
     */

    /* Test 30 exp
    Error! v1 can't reach v2!
     */


    /* Test 31
    Field f1 = new Field();
    Field f2 = new Field();
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
    Virologist v1 = new Virologist();
    f1.accept(v1);
     */

    /* Test 31 exp
    Error! v1 can’t reach f2!
     */


    /* Test 32
    Laboratory f1 = new Laboratory();
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
    Virologist v1 = new Virologist();
    Code c1 = new StunCode();
    f1.placeCode(c1);
     */

    /* Test 32 exp
    Error! v1 can’t craft c1!
     */


    /* Test 33
    Laboratory f1 = new Laboratory();
    Field f2 = new Field();
    Laboratory f3 = new Laboratory();
    f3.setInfected(true);
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
    Virologist v1 = new Virologist();
    Virologist v2 = new Virologist();
    f1.accept(v1);
    f2.accept(v2);
    f1.setNeighbor(0, f2);
    f1.setNeighbor(1, f3);
    f2.setNeighbor(0, f1);
    f2.setNeighbor(1, f3);
    f3.setNeighbor(0, f1);
    f3.setNeighbor(1, f2);
    AxeGear g1 = new AxeGear();
    RobeGear g2 = new RobeGear();
    v1.addGear(g1);
    v1.addGear(g2);
     */

    /* Test 33 exp
    Laboratory f1 = new Laboratory();
    Field f2 = new Field();
    Laboratory f3 = new Laboratory();
    f3.setInfected(true);
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
    Virologist v1 = new Virologist();
    f3.accept(v1);
    f1.setNeighbor(0, f2);
    f1.setNeighbor(1, f3);
    f2.setNeighbor(0, f1);
    f2.setNeighbor(1, f3);
    f3.setNeighbor(0, f1);
    f3.setNeighbor(1, f2);
    AxeGear g1 = new AxeGear();
    g1.setUsed(true);
    RobeGear g2 = new RobeGear();
    v1.addGear(g1);
    v1.addGear(g2);
     */


    /* Test 34
    Shelter f1 = new Shelter();
    Field f2 = new Field();
    Laboratory f3 = new Laboratory();
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
    Virologist v1 = new Virologist();
    Virologist v2 = new Virologist();
    v2.setAminoAcid(200);
    v2.setNucleotide(200);
    f1.accept(v1);
    f2.accept(v2);
    f1.setNeighbor(0, f2);
    f1.setNeighbor(1, f3);
    f2.setNeighbor(0, f1);
    f2.setNeighbor(1, f3);
    f3.setNeighbor(0, f1);
    f3.setNeighbor(1, f2);
    SackGear g1 = new SackGear();
    Code c1 = new StunCode();
    c1.setaCost(70);
    c1.setnCost(50);
    f3.placeCode(c1);
    v2.addGear(g1);
    */

    /* Test34 exp
    Shelter f1 = new Shelter();
    Field f2 = new Field();
    Laboratory f3 = new Laboratory();
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
    Virologist v1 = new Virologist();
    Virologist v2 = new Virologist();
    f3.accept(v1);
    f3.accept(v2);
    f1.setNeighbor(0, f2);
    f1.setNeighbor(1, f3);
    f2.setNeighbor(0, f1);
    f2.setNeighbor(1, f3);
    f3.setNeighbor(0, f1);
    f3.setNeighbor(1, f2);
    Code c1 = new StunCode();
    c1.setaCost(70);
    c1.setnCost(50);
    f3.placeCode(c1);
    v1.learnCode(c1);
    v2.learnCode(c1);
    SackGear g1 = new SackGear();
    v2.addGear(g1);
    Agent a1 = new StunVirus();
    a1.smear(v1);
     */


    /* Test 35
    Laboratory f1 = new Laboratory();
    f1.setInfected(true);
    Laboratory f2 = new Laboratory();
    Field f3 = new Field();
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
    Virologist v1 = new Virologist();
    Virologist v2 = new Virologist();
    f1.setNeighbor(0, f2);
    f1.setNeighbor(1, f3);
    f2.setNeighbor(0, f1);
    f2.setNeighbor(1, f3);
    f3.setNeighbor(0, f1);
    f3.setNeighbor(1, f2);
    f1.accept(v1);
    f2.accept(v2);
    BearVirus a1 = new BearVirus();
    a1.smear(v1);
    Code c1 = new AmniCode();
    c1.setnCost(20);
    c1.setaCost(30);
    f1.placeCode(c1);
    v1.learnCode(c1);
    AmniVirus a2 = new AmniVirus();
    v1.addCraftedAgent(a2);
    Code c2 = new ProtCode();
    c2.setnCost(50);
    c2.setaCost(60);
    AxeGear g1 = new AxeGear();
    f2.placeCode(c2);
    v2.learnCode(c2);
    v2.addGear(g1);
     */

    /* Test 35 exp
    Laboratory f1 = new Laboratory();
    f1.setInfected(true);
    Laboratory f2 = new Laboratory();
    Field f3 = new Field();
    ArrayList<Field> fields=new ArrayList<>();
        fields.add(f1);
        fields.add(f2);
        fields.add(f3);
    Virologist v2 = new Virologist();
    f1.setNeighbor(0, f2);
    f1.setNeighbor(1, f3);
    f2.setNeighbor(0, f1);
    f2.setNeighbor(1, f3);
    f3.setNeighbor(0, f1);
    f3.setNeighbor(1, f2);
    f2.accept(v2);
    Code c1 = new AmniCode();
    c1.setnCost(20);
    c1.setaCost(30);
    f1.placeCode(c1);
    Code c2 = new ProtCode();
    c2.setnCost(50);
    c2.setaCost(60);
    f2.placeCode(c2);
    AxeGear g1 = new AxeGear();
    g1.setUsed(true);
    v2.addGear(g1);
    StunVirus a1 = new StunVirus(); // Filler
    AmniVirus a2 = new AmniVirus();
    BearVirus a3 = new BearVirus();
    a3.smear(v2);
    a2.smear(v2);
     */


    /*
    Laboratory f1 = new Laboratory();
    Laboratory f2 = new Laboratory();
    Laboratory f3 = new Laboratory();
    Shelter f4 = new Shelter();
    Shelter f5 = new Shelter();
    Shelter f6 = new Shelter();
    Warehouse f7 = new Warehouse();
    Warehouse f8 = new Warehouse();
    Warehouse f9 = new Warehouse();
    ArrayList<Field> fields=new ArrayList<>();
    fields.add(f1);
    fields.add(f2);
    fields.add(f3);
    fields.add(f4);
    fields.add(f5);
    fields.add(f6);
    fields.add(f7);
    fields.add(f8);
    fields.add(f9);
    f1.setNeighbor(0, f2);
    f2.setNeighbor(0, f3);
    f3.setNeighbor(0, f4);
    f4.setNeighbor(0, f5);
    f5.setNeighbor(0, f6);
    f6.setNeighbor(0, f7);
    f7.setNeighbor(0, f8);
    f8.setNeighbor(0, f9);
    f9.setNeighbor(0, f1);
    Virologist v1 = new Virologist();
    Virologist v2 = new Virologist();
    Virologist v3 = new Virologist();
    f3.accept(v1);
    f4.accept(v2);
    f5.accept(v3);


    Laboratory f1 = new Laboratory();
    Laboratory f2 = new Laboratory();
    Laboratory f3 = new Laboratory();
    Shelter f4 = new Shelter();
    Shelter f5 = new Shelter();
    Shelter f6 = new Shelter();
    Warehouse f7 = new Warehouse();
    Warehouse f8 = new Warehouse();
    Warehouse f9 = new Warehouse();
    ArrayList<Field> fields=new ArrayList<>();
    fields.add(f1);
    fields.add(f2);
    fields.add(f3);
    fields.add(f4);
    fields.add(f5);
    fields.add(f6);
    fields.add(f7);
    fields.add(f8);
    fields.add(f9);
    f1.setNeighbor(0, f2);
    f2.setNeighbor(0, f3);
    f3.setNeighbor(0, f4);
    f4.setNeighbor(0, f5);
    f5.setNeighbor(0, f6);
    f6.setNeighbor(0, f7);
    f7.setNeighbor(0, f8);
    f8.setNeighbor(0, f9);
    f9.setNeighbor(0, f1);
    Virologist v1 = new Virologist();
    Virologist v2 = new Virologist();
    Virologist v3 = new Virologist();

     */
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
