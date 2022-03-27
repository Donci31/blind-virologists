import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

/**
 * Központi szkeleton osztály, amely a use-case-ek lefuttatásáért és a konzolos megjelenítésért felelős.
 */
public class Skeleton {

    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        Map<String, SkeletonHelper> testMap = new HashMap<>();

        testMap.put("move", Skeleton::DefaultMove);
        testMap.put("dancemove", Skeleton::DanceMove);
        testMap.put("craftamnivirus", Skeleton::CraftAmniVirus);
        testMap.put("craftdancevirus", Skeleton::CraftDanceVirus);
        testMap.put("craftprotvaccine", Skeleton::CraftProtVaccine);
        testMap.put("craftstunvirus", Skeleton::CraftStunVirus);
        testMap.put("startgame", Skeleton::StartGame);
        testMap.put("pickupglove", Skeleton::PickUpGlove);
        testMap.put("pickuprobe", Skeleton::PickUpRobe);
        testMap.put("pickupsack", Skeleton::PickUpSack);
        testMap.put("getresources", Skeleton::GetResources);
        testMap.put("learncode", Skeleton::LearnCode);
        testMap.put("updatewarehouse", Skeleton::UpdateWarehouse);
        testMap.put("updatestunvirus", Skeleton::UpdateStunVirus);
        testMap.put("updateprotvaccine", Skeleton::UpdateProtVaccine);
        testMap.put("updateamnivirus", Skeleton::UpdateAmniVirus);
        testMap.put("updatedancevirus", Skeleton::UpdateDanceVirus);
        testMap.put("updatevirologist", Skeleton::UpdateVirologist);
        testMap.put("smearprotvaccine", Skeleton::SmearProtVaccine);
        testMap.put("smearstunvirus", Skeleton::SmearStunVirus);
        testMap.put("smeardancevirus", Skeleton::SmearDanceVirus);
        testMap.put("smearamnivirus", Skeleton::SmearAmniVirus);

        while(true) {
            System.out.println("\n\nChoose from the possible use-cases:");
            for (String s : testMap.keySet())
                System.out.println(s);

            System.out.print("\nSelect use-case to run: ");
            String testCase = input.nextLine();
            testMap.get(testCase).test();
        }
    }

    /**
     * A virológus arréb lép egy mezővel
     */
    public static void DefaultMove() {
        log("-> test.DefaultMove()");
        //initialization
        var v = new Virologist();
        var d = new DefaultMove();
        var f1 = new Field();
        var f2 = new Field();
        v.setMoveStrat(d);
        f1.accept(v);
        v.setField(f1);
        f1.setNeighbor(0, f2);
        f2.setNeighbor(0, f1);

        //action
        v.move(f2);
        log("<- test.DefaultMove()");
    }

    /**
     * A virológus arréb lép véletlenszerűen egy mezővel
     */
    public static void DanceMove() {
        log("-> test.DanceMove()");
        //initialization
        var v = new Virologist();
        var d = new DanceMove();
        var f1 = new Field();
        var random = new Field();
        v.setMoveStrat(d);
        f1.accept(v);
        v.setField(f1);
        f1.setNeighbor(0, random);
        random.setNeighbor(0, f1);

        //action
        v.move(random);
        log("<- test.DanceMove()");
    }

    /**
     * A virológus craftol egy AmniVirus-t
     */
    public static void CraftAmniVirus() {
        log("-> test.CraftAmniVirus()");
        //initialization
        var v = new Virologist();
        var c = new AmniCode();
        v.learnCode(c);

        //action
        v.craftAgent(c);
        log("<- test.CraftAmniVirus()");
    }

    /**
     * A virológus craftol egy DanceVirus-t
     */
    public static void CraftDanceVirus() {
        log("-> test.CraftDanceVirus()");
        //initialization
        var v = new Virologist();
        var c = new DanceCode();
        v.learnCode(c);

        //action
        v.craftAgent(c);
        log("<- test.CraftDanceVirus()");
    }

    /**
     * A virológus craftol egy ProtVaccine-t
     */
    public static void CraftProtVaccine() {
        log("-> test.CraftProtVaccine()");
        //initialization
        var v = new Virologist();
        var c = new ProtCode();
        v.learnCode(c);

        //action
        v.craftAgent(c);
        log("<- test.CraftProtVaccine()");
    }

    /**
     * A virológus craftol egy StunVirus-t
     */
    public static void CraftStunVirus() {
        log("-> test.CraftStunVirus()");
        //initialization
        var v = new Virologist();
        var c = new StunCode();
        v.learnCode(c);

        //action
        v.craftAgent(c);
        log("<- test.CraftStunVirus()");
    }

    /**
     * A virológus felvesz egy védőkesztyűt és az absorb strategy-je GloveAbsorb lesz
     */
    public static void PickUpGlove(){
        log("-> test.PickUpGlove()");
        //initialization
        var glove = new GloveGear();
        var shelter = new Shelter();
        var virologist = new Virologist(shelter);
        shelter.addGear(glove);

        //action
        virologist.touch();

        log("<- test.PickUpGlove()");
    }

    /**
     * A virológus felvesz egy védőkesztyűt és az absorb strategy-je RobeAbsorb lesz
     */
    public static void PickUpRobe(){
        log("-> test.PickUpRobe()");
        //initialization
        var robe = new RobeGear();
        var shelter = new Shelter();
        shelter.addGear(robe);
        var virologist = new Virologist(shelter);

        //action
        virologist.touch();
        log("<- test.PickUpRobe()");
    }

    /**
     * A virológus felvesz egy zsákot és megnövekszik az anyagszállítóképessége
     */
    public static void PickUpSack(){
        log("-> test.PickUpSack()");
        //initialization
        var sack = new SackGear();
        var shelter = new Shelter();
        var virologist = new Virologist(shelter);
        shelter.addGear(sack);

        //action
        virologist.touch();

        log("<- test.PickUpSack()");
    }

    public static void GetResources(){
        log("-> test.GetResources()");
        //init
        var warehouse = new Warehouse();
        var virologist = new Virologist(warehouse);

        //action
        virologist.touch();
        log("<- test.GetResources()");
    }

    public static void LearnCode(){
        log("-> test.LearnCode()");
        //init
        var laboratory = new Laboratory();
        var code = new StunCode();
        var virologist = new Virologist(laboratory);
        laboratory.placeCode(code);

        //action
        virologist.touch();
        log("<- test.LearnCode()");
    }

    public static void UpdateWarehouse() {
        log("-> test.UpdateWarehouse()");
        //init
        var warehouse = new Warehouse();

        //action
        warehouse.step();
        log("<- test.UpdateWarehouse()");
    }

    /**
     * A kör végén a StunVirus működése
     */
    public static void UpdateStunVirus() {
        log("-> test.UpdateStunVirus()");
        //init
        var v = new Virologist();
        var stunVirus = new StunVirus();
        stunVirus.smear(v);
        //action
        stunVirus.step();
        log("<- test.UpdateStunVirus()");
    }

    /**
     * A kör végén a Virologist működése
     */
    public static void UpdateVirologist() {
        log("-> test.UpdateVirologist()");
        //init
        var v = new Virologist();
        //action
        v.step();
        log("<- test.UpdateVirologist()");
    }

    /**
     * A kör végén a DanceVirus működése
     */
    public static void UpdateDanceVirus() {
        log("-> test.UpdateDanceVirus()");
        //init
        var v = new Virologist();
        var danceVirus = new DanceVirus();
        danceVirus.smear(v);
        //action
        danceVirus.step();
        log("<- test.UpdateDanceVirus()");
    }

    /**
     * A kör végén az AmniVirus működése
     */
    public static void UpdateAmniVirus() {
        log("-> test.UpdateAmniVirus()");
        //init
        var amniVirus = new AmniVirus();
        //action
        amniVirus.step();
        log("<- test.UpdateAmniVirus()");
    }
    
    /**
     * A kör végén a ProtVaccine működése
     */
    public static void UpdateProtVaccine() {
        log("-> test.UpdateProtVaccine()");
        //init
        var v = new Virologist();
        var protVaccine = new ProtVaccine();
        protVaccine.smear(v); // <<create>> ProtVaccineAbsorb
        //action
        protVaccine.step();
        log("<- test.UpdateProtVaccine()");
    }


    /**
     * Virológus StunVírust ken egy másik virológusra, aki ezt az összes absorbal kezelheti
     *
     */
    public static void SmearStunVirus(){
        log("->test.SmearStunVirus");
        var fromV= new Virologist();
        var smearedVirologist=new Virologist();
        var s =new StunVirus();
        fromV.addCraftedAgent(s);
        var g= new GloveAbsorb();
        var p = new ProtVaccineAbsorb();
        var r= new RobeAbsorb();
        smearedVirologist.addAbsorbStrat(g);
        smearedVirologist.addAbsorbStrat(p);
        smearedVirologist.addAbsorbStrat(r);
        fromV.smearAgent(s,smearedVirologist);
        log("<-test.SmearStunVirus");

    }

    /**
     * Virológus DanceVírust ken egy másik virológusra, aki ezt az összes absorbal kezelheti
     *
     */
    public static void SmearDanceVirus(){
        log("->test.SmearDanceVirus");
        var fromV= new Virologist();
        var smearedVirologist=new Virologist();
        var d =new DanceVirus();
        fromV.addCraftedAgent(d);
        var g= new GloveAbsorb();
        var p = new ProtVaccineAbsorb();
        var r= new RobeAbsorb();
        smearedVirologist.addAbsorbStrat(g);
        smearedVirologist.addAbsorbStrat(p);
        smearedVirologist.addAbsorbStrat(r);
        fromV.smearAgent(d,smearedVirologist);
        log("<-test.SmearDanceVirus");

    }

    /**
     * Virológus AmniVírust ken egy másik virológusra, aki ezt az összes absorbal kezelheti
     *
     */
    public static void SmearAmniVirus(){
        log("->test.SmearAmniVirus");
        var fromV= new Virologist();
        var smearedVirologist=new Virologist();
        var a = new AmniVirus();
        fromV.addCraftedAgent(a);
        var g = new GloveAbsorb();
        var p = new ProtVaccineAbsorb();
        var r = new RobeAbsorb();
        smearedVirologist.addAbsorbStrat(g);
        smearedVirologist.addAbsorbStrat(p);
        smearedVirologist.addAbsorbStrat(r);
        fromV.smearAgent(a,smearedVirologist);
        log("<-test.SmearAmniVirus");

    }

    /**
     * Virológus ProtVaccine-t ken magára, ezt akármilyen absorbal kezelheti
     *
     */
    public static void SmearProtVaccine(){
        log("->test.SmearProtVaccine");
        var fromV = new Virologist();
        var p = new ProtVaccine();
        fromV.addCraftedAgent(p);
        var g = new GloveAbsorb();
        var pAbs = new ProtVaccineAbsorb();
        var r = new RobeAbsorb();
        fromV.addAbsorbStrat(g);
        fromV.addAbsorbStrat(pAbs);
        fromV.addAbsorbStrat(r);
        fromV.smearAgent(p,fromV);
        log("<-test.SmearProtVaccine");

    }

    /**
     * Játék indítása, pálya inicializálódás
     *
     */
    public static void StartGame(){
        Skeleton.log("->test.StartGame");
        var g= new Game();
        g.startGame();
        Skeleton.log("<-test.StartGame");
    }


    /**
     * Eldöntendő kérdés kiírása és válasz bekérése. Érvénytelen válasz esetén ismét felteszi a kérdést.
     * @param question A kérdés
     * @return `true` ha a válasz igen, `false` ha nem
     */

    public static boolean askYesOrNo(String question) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        int depth = stacktrace.length - 4;
        System.out.print("\t".repeat(depth) + "~~ " + question + " (Y/N): ");
        String answer = input.nextLine().toLowerCase();

        if (answer.equals("y") || answer.equals("yes")) {
            return true;
        } else if (answer.equals("n") || answer.equals("no")) {
            return false;
        } else {
            return askYesOrNo(question);
        }
    }

    /**
     * Függvényhívások és visszatérések kiírásához használt függvény
     * @param s Kiírt szöveg
     */
    public static void log(String s){
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        int depth = stacktrace.length - 4;
        System.out.println("\t".repeat(depth) + s /*+ "@" +stacktrace[3].getClassName()*/);
    }

}
