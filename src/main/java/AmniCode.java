import java.util.Scanner;

/**
 * Az AmniVirus vírust kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class AmniCode extends Code {
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
			Skeleton.log("-> AmniVirus()");
			AmniVirus a = new AmniVirus();
			Skeleton.log("<- AmniVirus()");
			v.removeNucleotide(50);
			v.removeAminoAcid(100);
			v.addCraftedAgent(a);
		}
		Skeleton.log("<- craftAgent(v: Virologist, nCost: int, aCost: int)");
	}
}
