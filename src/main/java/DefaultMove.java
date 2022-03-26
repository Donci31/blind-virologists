/**
 * A virológus normális lépését megvalósító osztály.
 */
public class DefaultMove implements Move {
	/**
	 * Kiválasztott mezőre lépteti a virológust.
	 * @param f - kiválasztott mező
	 * @param v - lépő virológus
	 */
	public void move(Field f, Virologist v) {
		Skeleton.log("-> move(f: Field, v: Virologist)");
		Field f1 = v.getField();
		f1.remove(v);
		f.accept(v); 	// Ez a szekvenciában f2 nevű
		Skeleton.log("<- move(f: Field, v: Virologist)");
	}
}
