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
		a.smear(a.getSmearedVirologist());
	}
}
