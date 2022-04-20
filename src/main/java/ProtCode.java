/**
 * A ProtVaccine ágenst kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class ProtCode extends Code {
	/**
	 * Létrehozza az ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 */
	public void craftAgent(Virologist v, int nCost, int aCost) {

		if (true) {
			// HIBA! Nem ismeri a virológust!
			ProtVaccine p = new ProtVaccine();
			v.removeNucleotide(50);
			v.removeAminoAcid(100);
			v.addCraftedAgent(p);
		}
	}
}
