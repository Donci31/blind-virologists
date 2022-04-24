/**
 * A laboratórium típusú mezőkért felelős osztály.
 * Ezekről a mezőkről az ideérkező virológusok genetikai kódokat olvashatnak le és tanulhatnak meg, ha interaktálnak a mezővel.
 */
public class Laboratory extends Field {
	private Code code;
	private boolean infected;


	public Laboratory(){
		super();
		infected = Math.random() > 0.8;
	}

	public Laboratory(String name){
		super(name);
		infected = Math.random() > 0.8;
	}

	public void setInfected(boolean infected) {
		this.infected = infected;
	}
	/**
	 * Visszaadja, hogy fertőzőtt-e a mező
	 */
	public boolean isInfected() {
		return infected;
	}
	/**
	 * A paraméterül kapott virológus megtanulja a laboratóriumban található genetikai kódot.
	 * @param v - mezővel interaktáló virológus
	 */
	public void interactWithField(Virologist v) {
		v.learnCode(code);
	}

	/**
	 * Beállítja a paraméterül kapott genetikai kódot a laboratóriumban megtanulható genetikai kódnak.
	 * @param c - a mezőre elhelyezendő kód
	 */
	public void placeCode(Code c) {
		code = c;
	}

	/**
	 * Felvesz egy virológust a mezőre. Ha a labor medvevírussal fertőzött, a virológus megfertőződik
	 * @param v - lépő virológus
	 */
	public void accept(Virologist v){
		virologists.add(v);
		if(infected){
			v.absorb(new BearVirus());
		}
	}
}
