/**
 * Központi szkeleton osztály, amely a use-case-ek lefuttatásáért és a konzolos megjelenítésért felelős.
 */
public class Skeleton {
    public static void main(String[] args) {
        Move();
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
        var d = new DefaultMove();
        var f1 = new Field();
        var random = new Field();
        v.setMoveStrat(d);
        f1.accept(v);
        f1.setNeighbor(0, random);
        random.setNeighbor(0, f1);

        //action
        v.move(random);
    }

    /**
     * A virológus felvesz egy védőkesztyűt és az absorb strategy-je GloveAbsorb lesz
     */
    public static void PickUpGlove(){
        System.out.println("-> PickUpGlove()");
        //initialization
        var virologist = new Virologist();
        var glove = new GloveGear();
        var shelter = new Shelter();
        shelter.addGear(glove);
        virologist.move(shelter);

        //action
        virologist.touch();

        System.out.println("<- PickUpGlove()");
    }

    /**
     * A virológus felvesz egy védőkesztyűt és az absorb strategy-je RobeAbsorb lesz
     */
    public static void PickUpRobe(){
        //initialization
        var virologist = new Virologist();
        var robe = new RobeGear();
        var shelter = new Shelter();
        shelter.addGear(robe);
        virologist.move(shelter);

        //action
        virologist.touch();
    }

    /**
     * A virológus felvesz egy zsákot és megnövekszik az anyagszállítóképessége
     */
    public static void PickUpSack(){
        //initialization
        var virologist = new Virologist();
        var sack = new SackGear();
        var shelter = new Shelter();
        shelter.addGear(sack);
        virologist.move(shelter);

        //action
        virologist.touch();
    }

    public static void LearnCode(){

    }

    public static void GatherResources(){

    }

    // stb.
}
