package model.fields;

import model.Steppable;
import model.SteppableController;
import model.Virologist;

/**
 * A raktár típusú mezők megvalósításáért felelős osztály.
 * Ezeken a mezőkön nyersanyag található, amelyet az ide érkező virológusok összeszedhetnek.
 */
public class Warehouse extends Field implements Steppable {
    private int nProduced = 0;
    private int aProduced = 0;

    /**
     * Konstruktor, ami beteszi a raktárat a léptethető mezők listájába.
     */
    public Warehouse() {
        super();
        SteppableController.addSteppable(this);
    }

    /**
     * Konstruktor, ami beállítja a nevet a megadottra, és beteszi a raktárat a léptethető mezők listájába.
     *
     * @param name megadott név
     */
    public Warehouse(String name) {
        super(name);
        SteppableController.addSteppable(this);
    }

    /**
     * Visszaadja a nukleotidok számát.
     */
    public int getnProduced() {
        return nProduced;
    }

    /**
     * Visszaadja az aminosavak számát.
     */
    public int getaProduced() {
        return aProduced;
    }

    /**
     * beállítja az nukleotidok számát
     *
     * @param n - az új szám
     */
    public void setnProduced(int n) {
        nProduced = n;
    }

    /**
     * beállítja az aminosavak számát
     *
     * @param a - az új szám
     */
    public void setaProduced(int a) {
        aProduced = a;
    }

    /**
     * Körönként egyszer hívódik meg, a raktárban található anyagok száma random értékkel növekszik.
     */
    public void step() {
        nProduced += (int) (Math.random() * 10);
        aProduced += (int) (Math.random() * 10);
    }

    /**
     * A paraméterként kapott virológus felveszi a raktárból az összes anyagot, amit még magával tud vinni.
     *
     * @param v - mezővel interaktáló virológus
     */
    public void interactWithField(Virologist v) {
        nProduced -= v.addNucleotide(nProduced);
        aProduced -= v.addAminoAcid(aProduced);
    }

    /**
     * Elpusztítja az összes a mezőn található anyagot.
     */
    public void destroyResources() {
        aProduced = 0;
        nProduced = 0;
    }
}
