/**
 * Ez az osztály valósítja meg a virológusok viselkedését, ha rájuk kennek egy ágenst, de a virológuson védőoltást alkalmaztak.
 * Ilyenkor az osztály absorb függvénye kezeli ezt az eseményt.
 */
public class ProtVaccineAbsorb implements Absorb {
	/**
	 * A rákent ágensnek nincs hatása a virológusra.
	 * @param a - felkent ágens
	 */
	public void absorb(Agent a) {
		//üres mert nem csinál semmit, esetleg kitörölheti majd a smearedVirologist a vírusból
	}
}
