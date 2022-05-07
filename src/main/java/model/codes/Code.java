package model.codes;

import model.Prototype;
import model.Virologist;

/**
 * A genetikai kódokat megvalósító absztrakt osztály, az összes genetikai kódot megvalósító osztály ebből származik le.
 * A kódok laboratóriumokból gyűjthetőek össze. Felelősek a kódolt ágens létrehozásáért, illetve a létrehozás költségének nyilvántartásáért.
 */
public abstract class Code {
	public int nCost;
	public int aCost;
	private static int id_counter = 1;
	protected String id;

	/**
	 * Konstruktor, amely beállítja az id-t, és hozzáadja az objektumot a model.Prototype id dekódoló map-jéhez.
	 */
	public Code() {
		id = "c" + id_counter++;
		Prototype.codes.put(id, this);
	}

	/**
	 * Az id alapraméretezett állapotba állító függvénye.
	 */
	public static void resetID() {id_counter = 1; }

	/**
	 * Az id settere.
	 * @param id új id érték
	 */
	public void setID(String id){ this.id = id;}

	/**
	 * Az id gettere.
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Létrehozza a kódolt ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 */
	public abstract void craftAgent(Virologist v, int nCost, int aCost);
}
