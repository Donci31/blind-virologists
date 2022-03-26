/**
 * Központi szkeleton osztály, amely a use-case-ek lefuttatásáért és a konzolos megjelenítésért felelős.
 */
public class Skeleton {
    public static void main(String[] args) {
        PickUpGlove();
        PickUpRobe();
        PickUpSack();
    }

    /**
     * A virológus arréb lép egy mezővel
     */
    public static void Move() {
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
    }

    /**
     * A virológus arréb lép véletlenszerűen egy mezővel
     */
    public static void DanceMove() {
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
        virologist.move(shelter);

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
        virologist.move(shelter);

        //action
        virologist.touch();
        log("<- test.PickUpRobe()");
    }

    /**
     * A virológus felvesz egy zsákot és megnövekszik az anyagszállítóképessége
     */
    public static void PickUpSack(){
        log("<- test.PickUpSack()");
        //initialization
        var sack = new SackGear();
        var shelter = new Shelter();
        var virologist = new Virologist(shelter);
        shelter.addGear(sack);
        virologist.move(shelter);

        //action
        virologist.touch();

        log("<- test.PickUpSack()");
    }

    public static void LearnCode(){

    }

    public static void GatherResources(){

    }
    /**
     * függvényhívások és visszatérések kiírásához használt függvény
     */
    public static void log(String s){
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        int depth = stacktrace.length;
        System.out.println("\t".repeat(Math.max(0, depth - 4)) + s);
    }
    // stb.
}
