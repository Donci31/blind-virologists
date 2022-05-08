package model;

import view.Canvas;
import view.FieldView;

import javax.swing.*;

/**
 * A virológus egy körben lehetséges tevékenységeinek megjelenítéséért és a kiválasztott tevékenység végrehajtásáért felelős osztály.
 */
public class ActionMenu {
    private JButton move, smear, interactWithField, loot, craft, hit, endTurn;
    private Canvas canvas;
    private static FieldView selectedField;

    // TODO ha tényleg a Singleton mintát szeretnénk, akkor kellene egy getInstance() és egyebek is

    /**
     * A v virológus hívja meg a saját step() metódusából, ezzel jelezvén, hogy várja a rá vonatkozó akciót, amit az ActionMenu gombjaival tud a felhasználó jelezni neki.
     * Ez a metódus azzal jelzi, hogy a felhasználó az “End Turn” opciót választotta, hogy hamissal tér vissza az igaz helyett, így a virológus nem hívja meg újra, hanem befejezi a körét.
     * @param v az aktív virológus
     */
    public static boolean waitForAction(Virologist v) {
        // TODO
        return false;
    }

    /**
     * A selectedField settere.
     * @param _selectedField az attribútum új értéke
     */
    public static void setSelectedField(FieldView _selectedField) {
        selectedField = _selectedField;
    }
}
