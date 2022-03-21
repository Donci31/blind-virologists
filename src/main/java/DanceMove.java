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
		Field f1 = v.getField();
		f1.remove(v);
		// Itt kéne egy random szomszédos mező választás
		//random.accept(v);
	}
}
