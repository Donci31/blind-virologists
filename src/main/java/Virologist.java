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
	private int resourceLimit = 500;
	private boolean stunned = false;
	private String name;
	private ArrayList<Absorb> absorbStrats = new ArrayList<>();
	private ArrayList<Agent> craftedAgents = new ArrayList<>();
	private ArrayList<Gear> gears = new ArrayList<>();
	private Field field;
	private ArrayList<Code> learntCodes = new ArrayList<>();
	private Move moveStrat = new DefaultMove();
	private Hit hitStrat = new DefaultHit();
	private static int id_counter = 1;
	private int chosenAbsorbStratIdx = 0;
	private boolean isBear=false;

	/**
	 * Az isBear gettere.
	 * @return isBear
	 */
	public boolean getBear(){return isBear;}

	/**
	 * Az isBear settere.
	 * @param f új isBear érték
	 */
	public void setBear(boolean f){isBear=f;}

	/**
	 * Konstruktor, ami a virologist_id-t fogadja paraméterként.
	 * @param name virologist_id
	 */
	public Virologist(String name) {
		SteppableController.addSteppable(this);
		id_counter++;
		this.name = name;
		Prototype.virologists.put(name, this);
		var defAbs=new DefaultAbsorb();
		addAbsorbStrat(defAbs);
	}

	/**
	 * Default konstruktor.
	 */
	public Virologist() {
		SteppableController.addSteppable(this);
		this.name = "v" + id_counter++;
		Prototype.virologists.put(name, this);
		var defAbs=new DefaultAbsorb();
		addAbsorbStrat(defAbs);
	}

	/**
	 * Az id alapraméretezett állapotba állító függvénye.
	 */
	public static void resetID() {id_counter = 1; }

	/**
	 * Az aminosav gettere.
	 * @return aminosav mennyisége
	 */
	public int getAminoAcid() {
		return aminoAcid;
	}

	/**
	 * A nukleotid gettere.
	 * @return nukleotid mennyisége
	 */
	public int getNucleotide() {
		return nucleotide;
	}

	/**
	 * Az aminosav settere
	 * @param a - az új érték
	 */
	public void setAminoAcid(int a) {
		aminoAcid = a;
	}

	/**
	 * A nukleotidok settere
	 * @param n - az új érték
	 */
	public void setNucleotide(int n) {
		nucleotide = n;
	}
	/**
	 * A virológus felszereléseinek gettere.
	 * @return a virológus felszereléseinek listája
	 */
	public ArrayList<Gear> getGears() {
		return gears;
	}

	/**
	 * A virológus craftolt ágenseinek gettere.
	 * @return a virológus craftolt ágenseinek listája
	 */
	public ArrayList<Agent> getCraftedAgents() {
		return craftedAgents;
	}

	/**
	 * A virológus megtanult genetikai kódjainak gettere.
	 * @return a virológus megtanult genetikai kódjainak listája
	 */
	public ArrayList<Code> getLearntCodes() {
		return learntCodes;
	}

	/**
	 * hozzáad egy ágenst a virológus ágenslistájához
	 * @param a - a hozzáadott ágens
	 */
	public void addAgent(Agent a){
		craftedAgents.add(a);
	}

	/**
	 *
	 * @return visszatér a virológus azonosítójával
	 */
	public String getName() { return name;}

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
		this.moveStrat = moveStrat;
	}


	/**
	 * A stun attribútum settere
	 */
	public void setStunned(Boolean b){
		stunned = b;
	}

	/**
	 * A stun attribútum gettere.
	 * @return stun attribútum
	 */
	public boolean isStunned() {
		return stunned;
	}

	/**
	 * A virológus átlép a paraméterként adott mezőre.
	 * @param f - a mező
	 */
	public void move(Field f) {
		moveStrat.move(f, this);
	}

	/**
	 * A paraméterként kapott virológusra keni a paraméterként kapott ágenst.
	 * @param a - felkenendő ágens
	 * @param v - megcélzott virológus
	 */
	public void smearAgent(Agent a, Virologist v) {
		craftedAgents.remove(a);
		a.setSmearedVirologist(v);//Beállítja a célvirológust, hogy az absorbok ismerjék kire kell kenni
		a.setCrafterVirologist(this); //Beállítja a Fromvirologúst ha vissza kell dobni az ágenst
		v.absorb(a);
	}

	/**
	 * A virológuson aktiválódik a paraméterként kapott - azaz a virológusra rákent - ágens hatása.
	 * @param a - virológusra kent ágens
	 */
	public void absorb(Agent a) {
		//chooseFrom(absorbStrats).absorb(a);
		chosenAbsorbStratIdx = Math.min(absorbStrats.size()-1, chosenAbsorbStratIdx);
		try {
			absorbStrats.get(chosenAbsorbStratIdx).absorb(a);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds!");
		}
	}

	/**
	 * A virológus interaktál a mezővel, amin éppen áll.
	 */
	public void touch() {
		field.interactWithField(this);
	}

	/**
	 * Egy lebénult virológustól elvesz egy védőfelszerelést.
	 * @param v - a lebénult virológus
	 * @param g - a kiválasztott védőfelszerelés
	 */
	public void loot(Virologist v, Gear g) {
		// Itt már addolja a saját felszereléseihez g Gear-t
		//TODO: check
		if (v.stunned) {
			v.loseGear(g);
			pickUpGear(g);
		}
	}

	/**
	 * Felvesz egy védőfelszerelést a mezőről amin áll.
	 * @param g - felvevendő védőfelszerelés
	 */
	public void pickUpGear(Gear g) {
		gears.add(g);
		g.giveStrat(this);
		if(gears.size() > 3){
			loseGear(gears.get(0));
			//loseGear(gears.get((int)(Math.random() * gears.size())));
		}
	}

	/**
	 * A virológus elveszti egy védőfelszerelését - akkor történik ez, ha bánult állapotban egy másik virológus elveszi tőle,
	 * vagy ha több mint három védőfelszerelés lenne nála, ezért eldob egyet.
	 * @param g - eldobandó védőfelszerelés
	 */
	public void loseGear(Gear g) {
		g.removeStrat(this);
		gears.remove(g);
	}

	/**
	 * A virológus megtanul egy genetikai kódot.
	 * @param c - megtanulandó genetikai kód
	 */
	public void learnCode(Code c) {
		if (!learntCodes.contains(c)) {
			learntCodes.add(c);
			if (learntCodes.size() >= 4) {
				Game.endGame(this);
			}
		}
	}

	/**
	 * A virológus létrehoz egy ágenst, ehhez megfelelő mennyiségű nukleotiddal és aminosavval kell rendelkeznie.
	 * @param c - a létrehozandó ágens kódja
	 * @throws IllegalArgumentException - ha túlköltekezne a virológus
	 */
	public void craftAgent(Code c) throws IllegalArgumentException {
		for(Code learnt: learntCodes){
			if(learnt.getId().equals(c.getId())){
				c.craftAgent(this, c.nCost, c.aCost);
			}
		}
	}

	/**
	 * Megnöveli a virológus nukleotid készletét, de a felső limitnél nem enged több nukleotidot tárolni.
	 * @param n - felvett nukleotid
	 */
	public int addNucleotide(int n) {
		int addable = Math.min(n, resourceLimit);
		nucleotide += addable;
		return addable;
	}

	/**
	 * Megnöveli a virológus aminosav készletét, de a felső limitnél nem enged több aminosavat tárolni.
	 * @param a - felvett aminosav
	 */
	public int addAminoAcid(int a) {
		int addable = Math.min(a, resourceLimit);
		aminoAcid += addable;
		return addable;
	}

	/**
	 * Lecsökkenti a virológus nukleotid készletét, amiből 0-nál nem lehet kevesebb.
	 * @param n - levonandó nukleotid
	 */
	public void removeNucleotide(int n) {
		nucleotide -= n;
		if (nucleotide < 0) nucleotide = 0;
	}

	/**
	 * Lecsökkenti a virológus aminosav készletét, amiből 0-nál nem lehet kevesebb.
	 * @param a - levonandó aminosav
	 */
	public void removeAminoAcid(int a) {
		aminoAcid -= a;
		if (aminoAcid < 0) aminoAcid = 0;
	}

	/**
	 * Ez a függvény minden kör végén meghívódik, és törli a virológus felhasználatlan ágenseit (az ágensek csak egy körig használhatóak).
	 */
	public void step() {
		craftedAgents.clear();
	}

	/**
	 * Akkor hívódhat meg, ha a virológusra felejtővírust kennek.
	 * Hatására a virológus elfelejti az eddig megtanult genetikait kódokat.
	 */
	public void forgetCodes() {
		learntCodes.clear();
	}

	/**
	 * Hozzáadja a paraméterként kapott ágenst a virológus ágenseihez.
	 * @param a - az új ágens
	 */
	public void addCraftedAgent(Agent a) {
		craftedAgents.add(a);
	}

	/**
	 * Ha a virológus felvesz egy védőfelszerelést vagy rákennek egy ProtVaccine-t, akkor meghívódik ez a függvény
	 * Hozzáad a virológus ágenssel való találkozási viselkedés befolyásoló listájához egy új, a védőfelszerelésre vagy ProtVaccine-re jellemző viselkedést.
	 * @param a - az új védekező viselkedés
	 */
	public void addAbsorbStrat(Absorb a) {
		absorbStrats.add(a);
		chosenAbsorbStratIdx = absorbStrats.size()-1;
	}

	/**
	 * Ha a virológus levesz egy védőfelszerelést vagy lejár a rákent ProtVaccine ideje, akkor meghívódik ez a függvény.
	 * Kivesz a virológus ágenssel való találkozási viselkedés befolyásoló listájából egy, a védőfelszerelésre vagy ProtVaccine-re jellemző viselkedést.
	 * @param a - a már nemkívánatos védekező viselkedés
	 */
	public void removeAbsorbStrat(Absorb a) {
		absorbStrats.remove(a);
	}

	/**
	 * Megüti a paraméterként megadott virológust a saját támadási viselkedési szerint.
	 * @param v - a megütött virológus
	 */
	public void hit(Virologist v){
		hitStrat.hit(v);
	}

	public ArrayList<String> getIdList() {
		ArrayList<String> names = new ArrayList<>();
		for (Code c : learntCodes) {
			names.add(c.getId());
		}
		return names;
	}

	/**
	 * A virológusra ütést mérnek, ennek hatására meghal és eltűnik a játékból
	 */
	public void receiveHit(){
		SteppableController.removeSteppable(this);
		field.remove(this);
		SteppableController.removeVirologistsAgents(this);
		Prototype.virologists.remove(this.name);
	}

	/**
	 * Ha a virológus felvesz egy fegyvert, akkor meghívódik ez a függvény,
	 * és beállítja az ütési viselkedést a paraméterként megadottra
	 * @param h - a felvett ütési viselkedés
	 */
	public void addHitStrat(Hit h){
		hitStrat = h;
	}

	/**
	 * Ha a virológus eldob vagy elhasznál egy fegyvert, akkor meghívódik ez a függvény,
	 * és visszaállítja az alapraméretezettre a ütési viselkedése
	 * @param h - az eltávolított ütési viselkedés
	 */
	public void removeHitStrat(Hit h){
		hitStrat = new DefaultHit();
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

		Scanner input = new Scanner(System.in);
		int index = input.nextInt();
		input.nextLine();
		return c.get(index);
	}

	/**
	 * Beállítja, hogy melyik védekezési viselkedés legyen az aktív, hogyha érkezik egy ágens.
	 * @param idx hanyadik absorb stratégiát használja
	 * @throws IllegalArgumentException - ha túlcímzi a védekezési lehetőségeket
	 */
	public void chooseAbsorbStrat(int idx) throws IllegalArgumentException {
		if (idx >= absorbStrats.size()) {
			throw new IllegalArgumentException();
		}
		chosenAbsorbStratIdx = Math.min(absorbStrats.size() - 1, idx);
	}
}
