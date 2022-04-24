/**
 * A ProtVaccine ágenst kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class ProtCode extends Code {

	ProtCode(){
		nCost=100;
		aCost=100;
	}

	/**
	 * Létrehozza az ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 * @throws IllegalArgumentException - ha túlköltekezne a virológus
	 */
	public void craftAgent(Virologist v, int nCost, int aCost) throws IllegalArgumentException{

		if (v.getAminoAcid() >= aCost && v.getNucleotide() >= nCost) {
			ProtVaccine p = new ProtVaccine();
			v.removeNucleotide(nCost);
			v.removeAminoAcid(aCost);
			v.addCraftedAgent(p);
		} else {
			throw new IllegalArgumentException();
		}
	}
}
