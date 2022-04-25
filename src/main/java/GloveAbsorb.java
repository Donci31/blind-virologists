/**
 * Ez az osztály valósítja meg a virológusok viselkedését, ha rájuk kennek egy ágenst, de a virológus éppen védőkesztyűt visel.
 * Ilyenkor az osztály absorb függvénye kezeli ezt az eseményt.
 */
public class GloveAbsorb implements Absorb {
	GloveGear glove;

	/**
	 * Konstruktor, amely hozzáadja a viselkedéshez azt a kesztű példányt, amihez tartozik.
	 * @param g kesztyű
	 */
	public GloveAbsorb(GloveGear g) {
		this.glove = g;
	}

	/**
	 * Az ágens hatását az ágenst dobó virológusra fejti ki.
	 * Az így érintett virológust az ágens egyik attribútumából ismeri.
	 * @param a - felkent ágens
	 */
	public void absorb(Agent a) {
		if(a.getCrafterVirologist()!=null) {
			a.setSmearedVirologist(a.getCrafterVirologist());
		}
		a.smear(a.getSmearedVirologist());
		glove.useGlove();
	}
}
