/**
 * A genetikai kódokat megvalósító absztrakt osztály, az összes genetikai kódot megvalósító osztály ebből származik le.
 * A kódok laboratóriumokból gyűjthetőek össze. Felelősek a kódolt ágens létrehozásáért, illetve a létrehozás költségének nyilvántartásáért.
 */
public abstract class Code {
	protected int nCost;
	protected int aCost;

	private static int id_counter = 1;
	protected String id;

	public Code() {
		id = "c" + id_counter++;
		Prototype.codes.put(id, this);
	}

	/**
	 * Az id alapraméretezett állapotba állító függvénye.
	 */
	public static void resetID() {id_counter = 1; }
	public void setID(String id){ this.id = id;}
	public String getId() {
		return id;
	}

	public int getnCost(){return nCost;}
	public int getaCost(){return aCost;}
	public void setnCost(int k){nCost=k;}
	public void setaCost(int k){aCost=k;}

	/**
	 * Létrehozza a kódolt ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 */
	public abstract void craftAgent(Virologist v, int nCost, int aCost);

	public boolean equals(Code c){ return this.id == c.id;}
}
