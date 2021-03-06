package view;

import model.fields.Laboratory;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A modell Laboratory (laboratórium) típusú mezőjének grafikus megjelenítéséért felelős osztály.
 */
public class LaboratoryView extends FieldView implements Drawable {
    Laboratory laboratory;

    /**
     * Konstruktor, ami beállítja a mező koordinátáit, és azt, hogy melyik laboratóriumhoz tartozik a modellből.
     *
     * @param pos        mező koordinátái
     * @param laboratory a nézethez tartozó modellbeli laboratórium mező
     */
    public LaboratoryView(Point pos, Laboratory laboratory) {
        super(pos, null);
        this.laboratory = laboratory;
        this.laboratory.setFieldView(this);
    }

    /**
     * Kirajzolja az óvóhely mezőt a megadott grafikus kontextusra.
     *
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        this.drawBorder(g);
        this.drawHexagon(g, new Color(12, 88, 124), radius);
    }
}
