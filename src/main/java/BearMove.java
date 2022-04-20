import java.util.List;
import java.util.Random;

/**
 * A virológus medvetáncos viselkedését megvalósító osztály. A Strategy-elvet segít megvalósítani
 */
public class BearMove implements Move{
    /**
     * Random irányba eltéríti a virológust, majd a mezőn,
     * amire rálép elpusztítja az összes nyersanyagot (csak ha az egy raktár),
     * és megfertőz minden ott álló virológust.
     * @param f - célmező (nem feltétlenül ide lép, random szomszédos mezőre fog lépni)
     * @param v - lépő virológus
     */
    public void move(Field f, Virologist v){
        Field f1 = v.getField();
        f1.remove(v);

        List<Field> neighbours = f1.getNeighbors();
        Random r = new Random();

        Field f2 = neighbours.get(r.nextInt(neighbours.size()));
        f2.accept(v);
        f2.smearAllVirologists(v);
        f2.destroyResources();
    }
}