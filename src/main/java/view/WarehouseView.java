package view;

import model.fields.Warehouse;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A modell Warehouse (raktár) típusú mezőjének grafikus megjelenítéséért felelős osztály.
 */
public class WarehouseView extends FieldView implements Drawable {
    Warehouse warehouse;

    /**
     * Konstruktor, ami beállítja a mező koordinátáit, és azt, hogy melyik raktárhoz tartozik a modellből.
     * @param pos mező koordinátái
     * @param warehouse a nézethez tartozó modellbeli raktár mező
     */
    public WarehouseView(Point pos, Warehouse warehouse) {
        super(pos, null);
        this.warehouse = warehouse;
        this.warehouse.setFieldView(this);
    }

    /**
     * Kirajzolja a raktár mezőt a megadott grafikus kontextusra.
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform old = AffineTransform.getTranslateInstance(0, 0);
        AffineTransform tx = AffineTransform.getTranslateInstance(pos.x, pos.y);
        g2.setTransform(tx);
        this.drawBorder(g);
        this.drawHexagon(g, new Color(240,228,13), radius);
        g2.setTransform(old);
    }
}
