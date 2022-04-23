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
		Field f1 = v.getField();
		if(f1.getNeighbors().contains(f)){		//Leellenőrzi szomszédos-e
			f1.remove(v);
			f.accept(v);
		} 	// Ez a szekvenciában f2 nevű
	}
}
