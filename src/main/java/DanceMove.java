import java.util.List;
import java.util.Random;

/**
 * A virológus vitustáncos lépését megvalósító osztály.
 */
public class DanceMove implements Move {
	/**
	 * Random irányba eltéríti a virológust.
	 * @param f - célmező (nem feltétlenül ide lép, random szomszédos mezőre fog lépni)
	 * @param v - lépő virológus
	 */
	public void move(Field f, Virologist v) {
		System.out.println("-> DanceMove");
		Field f1 = v.getField();
		f1.remove(v);

		List<Field> neighbours = f1.getNeighbors();
		Random r = new Random();

		Field f2 = neighbours.get(r.nextInt(neighbours.size()));
		f2.accept(v);

		System.out.println("<- DanceMove()");
	}
}
