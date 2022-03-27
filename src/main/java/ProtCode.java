import java.util.Scanner;

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
		Skeleton.log("-> craftAgent(v: Virologist, nCost: int, aCost: int)");
		Skeleton.logq("~~ Does the virologist have enough resources? (Y/N): ");
		Scanner input = new Scanner(System.in);

		String answer = input.nextLine();

		if (answer.charAt(0) == 'Y') { // Itt kell feltenni a felhasználónak egy értelmes kérdést, pl. "Does the virologist have enough resources?"
			// HIBA! Nem ismeri a virológust!
			Skeleton.log("-> ProtVaccine()");
			ProtVaccine p = new ProtVaccine();
			Skeleton.log("<- ProtVaccine()");
			v.removeNucleotide(50);
			v.removeAminoAcid(100);
			v.addCraftedAgent(p);
		}
		Skeleton.log("<- craftAgent(v: Virologist, nCost: int, aCost: int)");
	}
}
