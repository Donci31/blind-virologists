/**
 * A genetikai kódokat megvalósító absztrakt osztály, az összes genetikai kódot megvalósító osztály ebből származik le.
 * A kódok laboratóriumokból gyűjthetőek össze. Felelősek a kódolt ágens létrehozásáért, illetve a létrehozás költségének nyilvántartásáért.
 */
public abstract class Code {
	protected int nCost;
	protected int aCost;

	/**
	 * Létrehozza a kódolt ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 */
	public abstract void craftAgent(Virologist v, int nCost, int aCost);
}
