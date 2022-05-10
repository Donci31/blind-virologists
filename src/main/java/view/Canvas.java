package view;

import model.Game;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Ez a grafikus osztály felelős a pálya, és minden rajta lévő objektum kirajzolásáért.
 */
public class Canvas extends JPanel {
    ArrayList<Drawable> drawables;

    /**
     * Konstruktor, amely beállítja a vászon méreteit.
     */
    public Canvas() {
        drawables = new ArrayList<>();
    }

    /**
     * Kirajzolja az egész vásznat és a rajta levő objektumokat, azaz meghívja az összes Drawable objektumot draw() metódusát.
     *
     * @param g grafikus kontextus
     */
    public void drawAll(Graphics g) {
        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }
    }

    /**
     * Hozzáad egy Drawable objektumot a Drawable objektumokat tartalmazó tömbhöz.
     *
     * @param d a hozzáadandó Drawable objektum
     */
    public void addDrawable(Drawable d) {
        drawables.add(d);
    }

    public void paintComponent(Graphics _g) {
        Graphics2D g = (Graphics2D) _g;
        g.setBackground(new Color(153, 217, 234));
        g.clearRect(0, 0, getWidth(), getHeight());
        drawAll(g);

        String virologistID = Game.getActiveVirologist().getName();
        String virologistString = "Player " + virologistID.substring(virologistID.length() - 1);
        g.drawString(virologistString, 670, 530);
    }
}
