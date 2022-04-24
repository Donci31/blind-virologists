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
	 * @throws IllegalArgumentException - ha túlköltekezne a virológus
	 */
	public void craftAgent(Virologist v, int nCost, int aCost) throws IllegalArgumentException {

		if (v.getAminoAcid() >= aCost && v.getNucleotide() >= nCost) {
			DanceVirus d = new DanceVirus();
			v.removeNucleotide(nCost);
			v.removeAminoAcid(aCost);
			v.addCraftedAgent(d);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
