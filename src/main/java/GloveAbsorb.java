/**
 * Ez az osztály valósítja meg a virológusok viselkedését, ha rájuk kennek egy ágenst, de a virológus éppen védőkesztyűt visel.
 * Ilyenkor az osztály absorb függvénye kezeli ezt az eseményt.
 */
public class GloveAbsorb implements Absorb {
	/**
	 * Az ágens hatását az ágenst dobó virológusra fejti ki.
	 * Az így érintett virológust az ágens egyik attribútumából ismeri.
	 * @param a - felkent ágens
	 */
	public void absorb(Agent a) {
		Skeleton.log("->absorb(a)");
		a.setSmearedVirologist(a.getCrafterVirologist());
		a.smear(a.getSmearedVirologist());
		Skeleton.log("<-absorb(a)");
	}
}
