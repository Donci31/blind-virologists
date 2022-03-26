/**
 * Központi szkeleton osztály, amely a use-case-ek lefuttatásáért és a konzolos megjelenítésért felelős.
 */
public class Skeleton {
    public static void main(String[] args) {
        DefaultMove();
        DanceMove();
        PickUpGlove();
        PickUpRobe();
        PickUpSack();
        GetResources();
        LearnCode();
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








    /**
     * függvényhívások és visszatérések kiírásához használt függvény
     */
    public static void log(String s){
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        int depth = stacktrace.length - 4;
        System.out.println("\t".repeat(depth) + s /*+ "@" +stacktrace[3].getClassName()*/);
    }
    // stb.
}
