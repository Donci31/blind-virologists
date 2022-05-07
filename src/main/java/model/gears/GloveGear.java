package model.gears;

import model.absorbStrats.GloveAbsorb;
import model.Virologist;

/**
 * Ez az osztály azt a védőfelszerelést valósítja meg, amivel a viselőre kent ágensek visszadobhatóak a kenőre.
 */
public class GloveGear extends Gear {
	private GloveAbsorb strat = new GloveAbsorb(this);
	private Virologist virologist;
	private int timesUsed;

	/**
	 * Visszaadja a kesztyű használati számát
	 */
	public int getTimesUsed() {
		return timesUsed;
	}

	/**
	 * beállítja a használatok számát
	 * @param t - a szám
	 */
	public void setTimesUsed(int t){
		timesUsed = t;
	}
	/**
	 * Megváltoztatja a paraméterként kapott virológus viselkedési stratégiáját a rákent ágensekkel szemben.
	 * Az új stratégia a model.absorbStrats.GloveAbsorb lesz.
	 * @param v - virológus, akire hatni fog
	 */
	public void giveStrat(Virologist v) {
		v.addAbsorbStrat(strat);
		virologist = v;
	}

	/**
	 * Kiveszi a paraméterként kapott virológus ágensekkel szemben védekező stratégiáji közül az általa beletett model.absorbStrats.GloveAbsorb-ot.
	 * @param v - virológus, akiről eltávolítja a hatását
	 */
	public void removeStrat(Virologist v) {
		v.removeAbsorbStrat(strat);
	}

	/**
	 * Használja a kesztyűt, azaz a timesUsed nő egy értékkel.
	 * Ha az a növelést követően 3 lenne, akkor a kesztyű elromlik, eldobjuk.
	 */
	public void useGlove(){
		timesUsed++;
		if(timesUsed == 3){
			virologist.loseGear(this);
		}
	}
}
