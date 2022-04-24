/**
 * A ProtVaccine ágenst kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class ProtCode extends Code {

	ProtCode(){
		nCost=100;
		aCost=70;

	}
	/**
	 * Létrehozza az ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 */
	public void craftAgent(Virologist v, int nCost, int aCost) {

		if (v.getAminoAcid() > aCost && v.getNucleotide() > nCost) {
			ProtVaccine p = new ProtVaccine();
			v.removeNucleotide(nCost);
			v.removeAminoAcid(aCost);
			v.addCraftedAgent(p);
		}
	}
}
