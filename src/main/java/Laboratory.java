/**
 * A laboratórium típusú mezőkért felelős osztály.
 * Ezekről a mezőkről az ideérkező virológusok genetikai kódokat olvashatnak le és tanulhatnak meg, ha interaktálnak a mezővel.
 */
public class Laboratory extends Field {
	private Code code;
	private boolean infected;

	/**
	 * A paraméterül kapott virológus megtanulja a laboratóriumban található genetikai kódot.
	 * @param v - mezővel interaktáló virológus
	 */
	public void interactWithField(Virologist v) {
		Skeleton.log("-> interactWithField(v: Virologist)");
		v.learnCode(code);
		Skeleton.log("<- interactWithField(v: Virologist)");
	}

	/**
	 * Beállítja a paraméterül kapott genetikai kódot a laboratóriumban megtanulható genetikai kódnak.
	 * @param c - a mezőre elhelyezendő kód
	 */
	public void placeCode(Code c) {
		code = c;
	}
}
