package view;

import javax.swing.*;

import model.Virologist;
import model.fields.Field;
import model.fields.Laboratory;
import model.fields.Shelter;
import model.fields.Warehouse;
import model.gears.SackGear;

import java.awt.*;
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

        // TODO: remove
        // Rajzolás teszteléshez:
        /*
        int d = 100;

		addDrawable(new FieldView(new Point(100,100), new Field()));
		addDrawable(new LaboratoryView(new Point(100+d,100), new Laboratory()));
		addDrawable(new WarehouseView(new Point(100+2*d,100), new Warehouse()));
        addDrawable(new ShelterView(new Point(100+3*d,100), new Shelter()));
        Shelter s2 = new Shelter();
        s2.addGear(new SackGear());
        addDrawable(new ShelterView(new Point(100+4*d,100), s2));

        addDrawable(new VirologistView(new Point(100,200), new Virologist()));
        Virologist v1 = new Virologist();
        v1.setStunned(true);
        addDrawable(new VirologistView(new Point(100+d,200), v1));
        Virologist v2 = new Virologist();
        v2.setBear(true);
        addDrawable(new VirologistView(new Point(100+2*d,200), v2));

        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 0, Color.black));
        */
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
    public void addDrawable(Drawable d) {
        drawables.add(d);
    }

    public void paintComponent(Graphics _g) {   
        Graphics2D g = (Graphics2D) _g;
        g.setBackground(new Color(153, 217, 234));
        g.clearRect(0, 0, getWidth(), getHeight());
        drawAll(g);
    }
}
