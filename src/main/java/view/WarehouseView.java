package view;

import model.fields.Warehouse;

import java.awt.*;

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
    }

    /**
     * Kirajzolja a raktár mezőt a megadott grafikus kontextusra.
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        this.drawBorder(g);
        this.drawHexagon(g, new Color(240,228,13), radius);
    }
}
