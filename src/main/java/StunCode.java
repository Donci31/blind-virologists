/**
 * A StunVirus vírust kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class StunCode extends Code {

	/**
	 * Létrehozza az ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
	 * @param v - craftoló virológus
	 * @param nCost - szükséges nukleotid
	 * @param aCost - szükséges aminosav
	 */
	public void craftAgent(Virologist v, int nCost, int aCost) {
		Skeleton.log("-> craftAgent(v: Virologist, nCost: int, aCost: int)");
		var hasEnoughResource = Skeleton.askYesOrNo("~~ Does the virologist have enough resources? (Y/N): ");

		if (hasEnoughResource) {
			// HIBA! Nem ismeri a virológust!
			Skeleton.log("-> StunVirus()");
			StunVirus s = new StunVirus();
			Skeleton.log("<- StunVirus()");
			v.removeNucleotide(50);
			v.removeAminoAcid(100);
			v.addCraftedAgent(s);
		}
		Skeleton.log("<- craftAgent(v: Virologist, nCost: int, aCost: int)");
	}
}
