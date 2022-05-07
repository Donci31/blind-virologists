package model.hitStrats;

import model.Virologist;

/**
 * Ez az osztály valósítja meg a virológusok normál támadási viselkedését,
 * azaz nem teszi lehetővé a virológusoknak a támadást.
 * Ilyenkor az osztály hit függvénye kezeli ezt az eseményt.
 */
public class DefaultHit implements Hit {
    /**
     *  Normál támadási viselkedés, amely a virológus támadásakor hívódik meg, hatására nem történik semmi sem.
     * @param v - a támadás célpontja, virológus
     */
    public void hit(Virologist v){
        //do nothing
    }
}
