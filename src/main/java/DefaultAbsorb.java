/**
 * Ez az osztály valósítja meg a virológusok alapértelmezett viselkedését, ha rájuk kennek egy ágenst.
 * Ilyenkor az absorb függvénye kezeli az eseményt.
 */
public class DefaultAbsorb implements Absorb {
	/**
	 * Kifejti a paraméterként kapott ágens hatását az ezt meghívó virológusra.
	 * @param a - ágens
	 */
	public void absorb(Agent a) {
		// HIBA! DefaultAbsorb nem tudja, melyik virológuson kéne meghívnia a smeart
		// Talán ha a strategy-kben eltárolnánk azt, hogy melyik virológusra vannak hatással egy referenciában még a strategy létrehozásakor (konstruktorban), akkor ez megoldaná a problémánkat
		//a.smear(victim);
	}
}
