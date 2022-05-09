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
     * @param pos mező koordinátái
     * @param field a nézethez tartozó modellbeli szabad terület mező
     */
    public FieldView(Point pos, Field field) {
        this.pos = pos;
        this.field = field;
    }

    public void Changepos(Point newpos){pos=newpos;}

    public Point Getpos(){return pos;}
    /**
     * Kirajzolja a szabad terület mezőt a megadott grafikus kontextusra.
     * A leszármazottai felüldefiniálják.
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        this.drawBorder(g);
        this.drawHexagon(g, new Color(105,159,4), radius);
    }

    protected void drawBorder(Graphics g) {
        this.drawHexagon(g, Color.BLACK, radius + 5);
    }

    protected void drawHexagon(Graphics g, Color color, int radius) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            double phase = ((2 * i + 1) * Math.PI / 6);
            p.addPoint((int) (pos.x + radius * Math.cos(phase)), (int) (pos.y + radius * Math.sin(phase)));
        }
        g.setColor(color);
        g.fillPolygon(p);
    }

    /**
     * Leellenőrzi, hogy tényleg a mező területére kattintott-e a felhasználó.
     * Ha igen, akkor beállítja az ActionMenu selectedField attribútumát a nézethez tartozó mezőre a modellből.
     * @param clickPos a kattintás pozíciója képernyőkoordinátákban
     */
    public void onClick(Point clickPos) {
        // TODO check if this field was clicked
//        if (/*this was clicked*/) {
//            ActionMenu.setSelectedField(field);
//        }
    }
}
