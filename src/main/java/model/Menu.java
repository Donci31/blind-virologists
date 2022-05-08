package model;

import javax.swing.*;
import java.awt.*;

/**
 * A menüt megvalósító osztály. Játék állapotáról ad információkat, és irányítani lehet vele a játékot (újraindít, kilép, stb.).
 */
public class Menu extends JMenuBar {
    JMenu game, inventory, field;

    public Menu() {
        game = new JMenu("Game");
        inventory = new JMenu("Inventory");
        field = new JMenu("Field");
        this.add(game);
        this.add(inventory);
        this.add(field);


    }
}
