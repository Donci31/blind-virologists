package model.moveStrats;

import controller.Prototype;
import model.Virologist;
import model.fields.Field;

import java.util.List;
import java.util.Random;

/**
 * A virológus vitustáncos lépését megvalósító osztály.
 */
public class DanceMove implements Move {
    /**
     * Random irányba eltéríti a virológust.
     *
     * @param f - célmező (nem feltétlenül ide lép, random szomszédos mezőre fog lépni)
     * @param v - lépő virológus
     */
    public void move(Field f, Virologist v) {
        Field f1 = v.getField();
        f1.remove(v);
        Field f2 = f;
        List<Field> neighbours = f1.getNeighbors();
        if (Prototype.getDeterministic()) {
            if (!neighbours.isEmpty()) {
                f2 = neighbours.get(0);
            }
        } else {
            Random r = new Random();
            f2 = neighbours.get(r.nextInt(neighbours.size()));
            while (f2 == null) {
                f2 = neighbours.get(r.nextInt(neighbours.size()));
            }
        }
        f2.accept(v);
    }
}
