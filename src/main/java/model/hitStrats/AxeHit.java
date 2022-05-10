package model.hitStrats;

import model.Virologist;
import model.gears.AxeGear;

/**
 * Ez az osztály valósítja meg a virológusok viselkedését, ha támadni szeretnének, és eközben birtokolnak baltát.
 * Ilyenkor az osztály hit függvénye kezeli ezt az eseményt.
 */
public class AxeHit implements Hit {
    private AxeGear axe;

    /**
     * Konstruktor, beállítja a balta példányt, amihez ez a viselkedés tartozik.
     *
     * @param axe balta, amihez a viselkedés tartozik
     */
    public AxeHit(AxeGear axe) {
        this.axe = axe;
    }

    /**
     * Megüti a paraméterként kapott virológust, elhasználja a baltát.
     *
     * @param v - a támadás célpontja, virológus
     */
    public void hit(Virologist v) {
        if (!axe.isUsed()) {
            axe.useAxe();
            v.receiveHit();
        }
    }
}
