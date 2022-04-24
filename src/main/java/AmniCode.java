/**
 * Az AmniVirus vírust kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class AmniCode extends Code {
	AmniCode(){
		nCost=50;
		aCost=100;
	}

	/**
	 * Létrehozza az ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 * @throws IllegalArgumentException - ha túlköltekezne a virológus
	 */
	public void craftAgent(Virologist v, int nCost, int aCost) {

		if (v.getAminoAcid() >= aCost && v.getNucleotide() >= nCost) {
			AmniVirus a = new AmniVirus();
			v.removeNucleotide(nCost);
			v.removeAminoAcid(aCost);
			v.addCraftedAgent(a);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
