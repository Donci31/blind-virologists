/**
 * Központi szkeleton osztály, amely a use-case-ek lefuttatásáért és a konzolos megjelenítésért felelős.
 */
public class Skeleton {
    public static void main(String[] args) {

    }

    public static void Move() {

    }

    public static void DanceMove() {

    }

    /**
     * A virológus felvesz egy védőkesztyűt és az absorb strategy-je GloveAbsorb lesz
     */
    public static void PickUpGlove(){
        //initialization
        var virologist = new Virologist();
        var glove = new GloveGear();
        var shelter = new Shelter();
        shelter.addGear(glove);
        virologist.move(shelter);

        //action
        virologist.touch();

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
