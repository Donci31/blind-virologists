package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Ez a grafikus osztály felelős a pálya, és minden rajta lévő objektum kirajzolásáért.
 */
public class Canvas extends JPanel {
    private int width, height;
    boolean listeningForClick;
    ArrayList<Drawable> drawables;

    /**
     * Konstruktor, amely beállítja a vászon méreteit.
     * @param width a vászon szélessége
     * @param height a vászon magassága
     */
    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.listeningForClick = false;
        drawables = new ArrayList<>();


        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 0, Color.black));
    }

    /**
     * Kirajzolja az egész vásznat és a rajta levő objektumokat, azaz meghívja az összes Drawable objektumot draw() metódusát.
     * @param g grafikus kontextus
     */
    public void drawAll(Graphics g) {
        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }
    }

    /**
     * Hozzáad egy Drawable objektumot a Drawable objektumokat tartalmazó tömbhöz.
     * @param d a hozzáadandó Drawable objektum
     */
    void addDrawable(Drawable d) {
        drawables.add(d);
    }

    class ClickListener extends MouseAdapter {
        /**
         * Ez a függvény kezeli le a vásznon történő egérkattintásokat.
         * @param e az egér hatására keltett esemény
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO
        }
    }
}
