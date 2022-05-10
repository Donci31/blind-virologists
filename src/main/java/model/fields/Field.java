package model.fields;

import model.Prototype;
import model.Virologist;
import model.agents.BearVirus;
import view.FieldView;

import java.util.ArrayList;
import java.util.List;

/**
 * Az üres mezőket megvalósító osztály, valamint ősosztálya az összes többi mezőnek.
 * Nyilvántartja a rajta álló virológusokat, valamint felelős a virológusok mozgásáért.
 * A mezőre lépő virológusok interaktálhatnak a mezővel.
 * Ez üres mező esetén nem jár semmilyen hatással, az osztály leszármazottai viszont felülírhatják ezt.
 */
public class Field {
	protected List<Virologist> virologists;
	protected List<Field> neighbors;
	private String name;
	private FieldView fieldView;
	private static int id_counter = 1;

	/**
	 * Konstruktor, amely a megadott névvel hoz létre egy mezőt
	 * @param name mező neve
	 */
	public Field(String name) {
		this.name = name;
		virologists = new ArrayList<>();
		neighbors = new ArrayList<>();
		Prototype.fields.put(name, this);
	}

	/**
	 * Konstruktor, ami a inkrementálisan oszt ki nevet a mezőnek
	 */
	public Field() {
		this.name = "f" + id_counter++;
		virologists = new ArrayList<>();
		neighbors = new ArrayList<>();
		Prototype.fields.put(name, this);
	}

	/**
	 * A fieldView settere.
	 * @param fieldView a mező nézete
	 */
	public void setFieldView(FieldView fieldView) {
		this.fieldView = fieldView;
	}

	/**
	 * A fieldView gettere.
	 * @return fieldView
	 */
	public FieldView getFieldView() {
		return fieldView;
	}

	/**
	 * Az id alapraméretezett állapotba állító függvénye.
	 */
	public static void resetID() {id_counter = 1; }

	/**
	 * A virologists tömb gettere.
	 * @return virologists tömb
	 */
	public List<Virologist> getVirologists() {
		return virologists;
	}

	/**
	 * A neighbors tömb gettere.
	 * @return neighbors tömb
	 */
	public List<Field> getNeighbors() {
		return neighbors;
	}

	/**
	 * Felvesz egy virológust a mezőre.
	 * @param v - lépő virológus
	 */
	public void accept(Virologist v) {
		virologists.add(v);
		v.setField(this);
	}

	/**
	 * Levesz egy virológust a mezőről.
	 * @param v - ellépő virológus
	 */
	public void remove(Virologist v) {
		virologists.remove(v);
	}

	/**
	 * A paraméterül kapott virológus interaktál a mezővel - üres mező esetén nincs hatása.
	 * @param v - mezővel interaktáló virológus
	 */
	public void interactWithField(Virologist v) {
		//do nothing
	}

	/**
	 * Beállítja a kapott mezőt a jelenlegi mező  megfelelő indexű szomszédjának.
	 * @param idx - szomszéd indexe
	 * @param f - a szomszéd
	 */
	public void setNeighbor(int idx, Field f) {
		neighbors.add(idx, f);
	}

	/**
	 * A paraméterként kapott virológus megfertőzi mezőn lévő összes virológust medvevírussal.
	 * @param v - a fertőző virológus
	 */
	public void smearAllVirologists(Virologist v){
		for(Virologist target : virologists){
			if (v != target) {
				v.smearAgent(new BearVirus(), target);
			}
		}
	}

	/**
	 * Elpusztítja az összes a mezőn található anyagot. Csak raktárak esetében van hatása.
	 */
	public void destroyResources(){
		//do nothing
	}

	/**
	 * Ez a metódus megadja, hogy a paraméterként megadott mező szomszédja-e ennek a mezőnek.
	 */
	public boolean isNeighbor(Field f) { return neighbors.contains(f); }

	/**
	 * Visszaadja a mező azonosítóját
	 */
	public String getName(){return name;}
}
