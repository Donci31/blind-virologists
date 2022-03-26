/**
 * Ez az osztály valósítja meg a virológusok viselkedését, ha rájuk kennek egy ágenst, de a virológus éppen védő köpenyt visel.
 * Ilyenkor az osztály absorb függvénye kezeli ezt az eseményt.
 */
public class RobeAbsorb implements Absorb {
	/**
	 * A függvény sorsol egy random számot 0 és 1 között.
	 * Ha a szám kisebb mint 82.3, az ágens nem lesz hatással a virológusra, egyébként viszont a teljes hatás érvényesül.
	 * @param a - felkent ágens
	 */
	public void absorb(Agent a) {
		if(Math.random()>0.823){
			a.smear(a.getSmearedVirologist());
		}
	}
}
