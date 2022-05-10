package model.fields;

import model.Prototype;
import model.Virologist;
import model.agents.BearVirus;
import model.codes.Code;

/**
 * A laboratórium típusú mezőkért felelős osztály.
 * Ezekről a mezőkről az ideérkező virológusok genetikai kódokat olvashatnak le és tanulhatnak meg, ha interaktálnak a mezővel.
 */
public class Laboratory extends Field {
    private Code code;
    private boolean infected;

    /**
     * Konstruktor, amely beállítja a labor fertőzöttségét
     */
    public Laboratory() {
        super();
        infected = Math.random() > 0.8 && !Prototype.getDeterministic();
    }

    /**
     * Konstruktor, amely beállítja a labor fertőzöttségét és a nevét
     */
    public Laboratory(String name) {
        super(name);
        infected = Math.random() > 0.8 && !Prototype.getDeterministic();
    }

    /**
     * A labor kódjának gettere
     *
     * @return labor kódja
     */
    public Code getCode() {
        return code;
    }

    /**
     * A labor fertőzöttségének beállítása
     *
     * @param infected legyen-e fertőzött
     */
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
     *
     * @param v - mezővel interaktáló virológus
     */
    public void interactWithField(Virologist v) {
        if (code != null) {
            v.learnCode(code);
        }
    }

    /**
     * Beállítja a paraméterül kapott genetikai kódot a laboratóriumban megtanulható genetikai kódnak.
     *
     * @param c - a mezőre elhelyezendő kód
     */
    public void placeCode(Code c) {
        code = c;
    }

    /**
     * Felvesz egy virológust a mezőre. Ha a labor medvevírussal fertőzött, a virológus megfertőződik
     *
     * @param v - lépő virológus
     */
    public void accept(Virologist v) {
        super.accept(v);
        if (infected && !v.getBear()) {
            BearVirus bearVirus = new BearVirus();
            bearVirus.setSmearedVirologist(v);
            v.absorb(bearVirus);
        }
    }
}
