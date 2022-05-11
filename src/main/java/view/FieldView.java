package view;

import model.fields.Field;

import java.awt.*;

/**
 * A modell Field (szabad terület) típusú mezőjének grafikus megjelenítéséért felelős osztály.
 * Ebből az osztályból származik le a többi mezőtípus nézete is.
 */
public class FieldView implements Drawable {
    protected Point pos;
    private Field field;

    protected final int radius = 50;

    /**
     * Konstruktor, ami beállítja a mező koordinátáit, és azt, hogy melyik szabad területhez tartozik a modellből.
     *
     * @param pos   mező koordinátái
     * @param field a nézethez tartozó modellbeli szabad terület mező
     */
    public FieldView(Point pos, Field field) {
        this.pos = pos;
        this.field = field;
        if (this.field != null) {
            this.field.setFieldView(this);
        }
    }

    /**
     * A pozíció gettere.
     * @return pos
     */
    public Point Getpos() {
        return pos;
    }

    /**
     * Kirajzolja a szabad terület mezőt a megadott grafikus kontextusra.
     * A leszármazottai felüldefiniálják.
     *
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        this.drawBorder(g);
        this.drawHexagon(g, new Color(105, 159, 4), radius);
    }

    /**
     * A hatszög tusvonalának megrajzolása feketével
     * @param g grafikus kontextus
     */
    protected void drawBorder(Graphics g) {
        this.drawHexagon(g, Color.BLACK, radius + 5);
    }

    /**
     * A hatszög belsejének kirajzolása
     * @param g grafikus kontextus
     * @param color mező színe
     * @param radius hatszög köré írható kör sugara
     */
    protected void drawHexagon(Graphics g, Color color, int radius) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            double phase = ((2 * i) * Math.PI / 6);
            p.addPoint((int) (radius * Math.cos(phase)) + pos.x, (int) (radius * Math.sin(phase)) + pos.y);
        }
        g.setColor(color);
        g.fillPolygon(p);
    }
}
