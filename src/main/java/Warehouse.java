/**
 * A raktár típusú mezők megvalósításáért felelős osztály.
 * Ezeken a mezőkön nyersanyag található, amelyet az ide érkező virológusok összeszedhetnek.
 */
public class Warehouse extends Field implements Steppable {
	private int nProduced = 0;
	private int aProduced = 0;

	/**
	 * Körönként egyszer hívódik meg, a raktárban található anyagok száma random értékkel növekszik.
	 */
	public void step() {
		nProduced += (int)(Math.random()*10);
		aProduced += (int)(Math.random()*10);
	}

	/**
	 * A paraméterként kapott virológus felveszi a raktárból az összes anyagot, amit még magával tud vinni.
	 * @param v - mezővel interaktáló virológus
	 */
	public void interactWithField(Virologist v) {
		nProduced -= v.addNucleotide(nProduced);
		aProduced -= v.addAminoAcid(aProduced);
	}

	/**
	 * Elpusztítja az összes a mezőn található anyagot.
	 */
	public void destroyResources(){
		aProduced = 0;
		nProduced = 0;
	}
}
