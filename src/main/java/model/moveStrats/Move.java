package model.moveStrats;

import model.Virologist;
import model.fields.Field;

/**
 * Interfész, ami segít megvalósítani a Strategy elvet.
 * A virológus mozgását befolyásolja.
 */
public interface Move {
	/**
	 * Virológus arrébb lép egy mezővel.
	 * @param f - kiválasztott mező
	 * @param v - lépő virológus
	 */
	void move(Field f, Virologist v);
}
