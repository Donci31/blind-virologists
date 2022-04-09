import java.util.*;

/**
 * A játékosok által irányított karaktereket reprezentáló osztály.
 * Nyilvántartja a karakter aktuális pozícióját a pályán, a megtanult kódokat, az összegyűjtött védőfelszereléseket,
 * az elkészített ágenseket, a birtokolt nukleotidok és aminosavak számát, ezeknek az anyagoknak a maximális birtokolható mennyiségét,
 * valamint azt, hogy a játékos éppen le van-e bénulva.
 * Ez az osztály felelős a karakterek mozgásáért, és világgal valamint egymással való interakcióik kezeléséért.
 */
public class Virologist implements Steppable {
	private int nucleotide = 0;
	private int aminoAcid = 0;
	private int resourceLimit = 100;
	private boolean stunned = false;
	private ArrayList<Absorb> absorbStrats = new ArrayList<>();
	private ArrayList<Agent> craftedAgents = new ArrayList<>();
	private ArrayList<Gear> gears = new ArrayList<>();
	private Field field;
	private ArrayList<Code> learntCodes = new ArrayList<>();
	private Move moveStrat = new DefaultMove();
	private ArrayList<Hit> hitStrat = new ArrayList<>();


	public Virologist(){
		Skeleton.log("-> Virologist()");
		var defAbs=new DefaultAbsorb();
		addAbsorbStrat(defAbs);
		hitStrat.add(new DefaultHit());
		Skeleton.log("<- Virologist()");
	}
	public Virologist(Field f){
		field = f;
	}

	/**
	 * A resourceLimit attribútum gettere.
	 */
	public int getResourceLimit() {
		return resourceLimit;
	}

	/**
	 * A resourceLimit attribútum settere.
	 */
	public void setResourceLimit(int resourceLimit) {
		this.resourceLimit = resourceLimit;
	}

	/**
	 * A field attribútum gettere.
	 */
	public Field getField() {
		Skeleton.log("-> getField()");
		Skeleton.log("<- getField()");
		return field;
	}

	/**
	 * A field attribútum settere.
	 */
	public void setField(Field f) {
		field = f;
	}

	/**
	 * A moveStrat attribútum settere.
	 */
	public void setMoveStrat(Move moveStrat) {
		Skeleton.log("-> setMoveStrat(moveStrat: Move)");
		this.moveStrat = moveStrat;
		Skeleton.log("<- setMoveStrat(moveStrat: Move)");
	}


	/**
	 * A stun attribútum settere
	 */
	public void setStunned(Boolean b){
		Skeleton.log("-> setStunned()");
		stunned = b;
		Skeleton.log("<- setStunned()");
	}

	/**
	 * A virológus átlép a paraméterként adott mezőre.
	 * @param f - a mező
	 */
	public void move(Field f) {
		Skeleton.log("-> move()");
		moveStrat.move(f, this);
		Skeleton.log("<- move()");
	}

	/**
	 * A paraméterként kapott virológusra keni a paraméterként kapott ágenst.
	 * @param a - felkenendő ágens
	 * @param v - megcélzott virológus
	 */
	public void smearAgent(Agent a, Virologist v) {
		Skeleton.log("-> smearAgent(a: Agent,v: Virologist)");
		a.setSmearedVirologist(v);			//Beállítja a célvirológust, hogy az absorbok ismerjék kire kell kenni
		v.absorb(a);
		Skeleton.log("<- smearAgent(a: Agent, v: Virologist)");
	}

	/**
	 * A virológuson aktiválódik a paraméterként kapott - azaz a virológusra rákent - ágens hatása.
	 * @param a - virológusra kent ágens
	 */
	public void absorb(Agent a) {
		Skeleton.log("-> absorb(a: Agent)");
		chooseFrom(absorbStrats).absorb(a);
		Skeleton.log("<- absorb(a: Agent)");
	}

	/**
	 * A virológus interaktál a mezővel, amin éppen áll.
	 */
	public void touch() {
		Skeleton.log("-> touch()");
		field.interactWithField(this);
		Skeleton.log("<- touch()");
	}

	/**
	 * Egy lebénult virológustól elvesz egy védőfelszerelést.
	 * @param v - a lebénult virológus
	 * @param g - a kiválasztott védőfelszerelés
	 */
	public void loot(Virologist v, Gear g) {
		//TODO
	}

	/**
	 * Felvesz egy védőfelszerelést a mezőről amin áll.
	 * @param g - felvevendő védőfelszerelés
	 */
	public void pickUpGear(Gear g) {
		Skeleton.log("-> pickUpGear(g: Gear)");
		gears.add(g);
		g.giveStrat(this);
		if(gears.size() > 3){
			loseGear(chooseFrom(gears));
			//loseGear(gears.get((int)(Math.random() * gears.size())));
		}
		Skeleton.log("<- pickUpGear(g: Gear)");
	}

	/**
	 * A virológus elveszti egy védőfelszerelését - akkor történik ez, ha bánult állapotban egy másik virológus elveszi tőle,
	 * vagy ha több mint három védőfelszerelés lenne nála, ezért eldob egyet.
	 * @param g - eldobandó védőfelszerelés
	 */
	public void loseGear(Gear g) {
		Skeleton.log("-> loseGear(g: Gear)");
		g.removeStrat(this);
		gears.remove(g);
		Skeleton.log("<- loseGear(g: Gear)");
	}

	/**
	 * A virológus megtanul egy genetikai kódot.
	 * @param c - megtanulandó genetikai kód
	 */
	public void learnCode(Code c) {
		Skeleton.log("-> learnCode(c: Code)");
		learntCodes.add(c);
		Skeleton.log("<- learnCode(c: Code)");
	}

	/**
	 * A virológus létrehoz egy ágenst, ehhez megfelelő mennyiségű nukleotiddal és aminosavval kell rendelkeznie.
	 * @param c - a létrehozandó ágens kódja
	 */
	public void craftAgent(Code c) {
		Skeleton.log("-> craftAgent(c: Code)");
		c.craftAgent(this, nucleotide, aminoAcid);
		Skeleton.log("<- craftAgent(c: Code)");
	}

	/**
	 * Megnöveli a virológus nukleotid készletét, de a felső limitnél nem enged több nukleotidot tárolni.
	 * @param n - felvett nukleotid
	 */
	public int addNucleotide(int n) {
		Skeleton.log("-> addNucleotide(n: int)");
		int addable = Math.min(n, resourceLimit - nucleotide - aminoAcid);
		nucleotide += addable;
		Skeleton.log("<- addNucleotide(n: int)");
		return addable;
	}

	/**
	 * Megnöveli a virológus aminosav készletét, de a felső limitnél nem enged több aminosavat tárolni.
	 * @param a - felvett aminosav
	 */
	public int addAminoAcid(int a) {
		Skeleton.log("-> addAminoAcid(a: int)");
		int addable = Math.min(a, resourceLimit - nucleotide - aminoAcid);
		aminoAcid += addable;
		Skeleton.log("<- addAminoAcid(a: int)");
		return addable;
	}

	/**
	 * Lecsökkenti a virológus nukleotid készletét, amiből 0-nál nem lehet kevesebb.
	 * @param n - levonandó nukleotid
	 */
	public void removeNucleotide(int n) {
		Skeleton.log("-> removeNucleotide(n: int)");
		nucleotide -= n;
		Skeleton.log("<- removeNucleotide(n: int)");
	}

	/**
	 * Lecsökkenti a virológus aminosav készletét, amiből 0-nál nem lehet kevesebb.
	 * @param a - levonandó aminosav
	 */
	public void removeAminoAcid(int a) {
		Skeleton.log("-> removeAminoAcid(n: int)");
		aminoAcid -= a;
		Skeleton.log("<- removeAminoAcid(n: int)");
	}

	/**
	 * Ez a függvény minden kör végén meghívódik, és törli a virológus felhasználatlan ágenseit (az ágensek csak egy körig használhatóak).
	 */
	public void step() {
		Skeleton.log("-> step()");
		craftedAgents.clear();
		Skeleton.log("<- step()");
	}

	/**
	 * Akkor hívódhat meg, ha a virológusra felejtővírust kennek.
	 * Hatására a virológus elfelejti az eddig megtanult genetikait kódokat.
	 */
	public void forgetCodes() {
		Skeleton.log("->forgetCodes()");
		learntCodes.clear();
		Skeleton.log("<-forgetCodes()");
	}

	/**
	 * Hozzáadja a paraméterként kapott ágenst a virológus ágenseihez.
	 * @param a - az új ágens
	 */
	public void addCraftedAgent(Agent a) {
		Skeleton.log("-> addCraftedAgent(a: Agent)");
		craftedAgents.add(a);
		Skeleton.log("<- addCraftedAgent(a: Agent)");
	}

	/**
	 * Ha a virológus felvesz egy védőfelszerelést vagy rákennek egy ProtVaccine-t, akkor meghívódik ez a függvény
	 * Hozzáad a virológus ágenssel való találkozási viselkedés befolyásoló listájához egy új, a védőfelszerelésre vagy ProtVaccine-re jellemző viselkedést.
	 * @param a - az új védekező viselkedés
	 */
	public void addAbsorbStrat(Absorb a) {
		Skeleton.log("-> addAbsorbStrat(a: Absorb)");
		absorbStrats.add(a);
		Skeleton.log("<- addAbsorbStrat(a: Absorb)");
	}

	/**
	 * Ha a virológus levesz egy védőfelszerelést vagy lejár a rákent ProtVaccine ideje, akkor meghívódik ez a függvény.
	 * Kivesz a virológus ágenssel való találkozási viselkedés befolyásoló listájából egy, a védőfelszerelésre vagy ProtVaccine-re jellemző viselkedést.
	 * @param a - a már nemkívánatos védekező viselkedés
	 */
	public void removeAbsorbStrat(Absorb a) {
		Skeleton.log("-> removeAbsorbStrat(a: Absorb)");
		absorbStrats.remove(a);
		Skeleton.log("<- removeAbsorbStrat(a: Absorb)");
	}

	/**
	 * Megüti a paraméterként megadott virológust a saját támadási viselkedési szerint.
	 * @param v - a megütött virológus
	 */
	public void hit(Virologist v){
		v.receiveHit();
		//TODO: then what
	}

	/**
	 * A virológusra ütést mérnek
	 */
	public void receiveHit(){
		//TODO: die
	}

	/**
	 * ha a virológus felvesz egy fegyvert, akkor meghívódik ez a függvény,
	 * és hozzáad a virológus ütési viselkedés befolyásoló listájához egy új,
	 * a fegyverre jellemző - itt paraméterként kapott - viselkedést.
	 * @param h - a felvett ütési viselkedés
	 */
	public void addHitStrat(Hit h){
		hitStrat.add(h);
	}

	/**
	 * ha a virológus eldob vagy elhasznál egy fegyvert, akkor meghívódik ez a függvény,
	 * és kivesz a virológus ütési viselkedés befolyásoló listájából egy,
	 * a fegyverre jellemző - itt paraméterként kapott - viselkedést.
	 * @param h - az eltávolított ütési viselkedés
	 */
	public void removeHitStrat(Hit h){
		hitStrat.remove(h);
	}


	/**
	 *  segédfüggvény, a játékos kiválaszthat egy elemet egy listából
	 * @param c - a lista amiből választunk
	 */
	private <T> T chooseFrom(ArrayList<T> c){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		int depth = stacktrace.length - 4;
		for(int i = 0; i < c.size(); i++){
			System.out.print("\t".repeat(depth));
			System.out.println(i + " " +c.get(i).getClass().toString().split(" ")[1]);
		}
		//majd le lesz cserélve grafikus megoldásra
		System.out.print("\t".repeat(depth) + "~~ Which Absorb mechanic to use? ");
		int index = Skeleton.input.nextInt();
		Skeleton.input.nextLine();
		return c.get(index);
	}
}
