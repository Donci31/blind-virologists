/**
 * A DanceVirus vírust kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class DanceCode extends Code {

	DanceCode(){
		nCost=50;
		aCost=50;
	}

	/**
	 * Létrehozza az ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 */
	public void craftAgent(Virologist v, int nCost, int aCost) {

		if (v.getAminoAcid() >= aCost && v.getNucleotide() >= nCost) {
			DanceVirus d = new DanceVirus();
			v.removeNucleotide(nCost);
			v.removeAminoAcid(aCost);
			v.addCraftedAgent(d);
		}
	}
}
