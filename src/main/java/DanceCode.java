/**
 * A DanceVirus vírust kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class DanceCode extends Code {
	/**
	 * Létrehozza az ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 */
	public void craftAgent(Virologist v, int nCost, int aCost) {

		if (true) { // Itt kell feltenni a felhasználónak egy értelmes kérdést, pl. "Does the virologist have enough resources?"
			// HIBA! Nem ismeri a virológust!
			DanceVirus d = new DanceVirus();
			v.removeNucleotide(50);
			v.removeAminoAcid(100);
			v.addCraftedAgent(d);
		}
	}
}
