package view;

import model.fields.Shelter;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * A modell Shelter (óvóhely) típusú mezőjének grafikus megjelenítéséért felelős osztály.
 */
public class ShelterView extends FieldView implements Drawable  {
    Shelter shelter;

    /**
     * Konstruktor, ami beállítja a mező koordinátáit, és azt, hogy melyik óvóhelyhez tartozik a modellből.
     * @param pos mező koordinátái
     * @param shelter a nézethez tartozó modellbeli óvóhely mező
     */
    public ShelterView(Point pos, Shelter shelter) {
        super(pos, null);
        this.shelter = shelter;
        this.shelter.setFieldView(this);
    }

    /**
     * Kirajzolja az óvóhely mezőt a megadott grafikus kontextusra.
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform old = AffineTransform.getTranslateInstance(0, 0);
        AffineTransform tx = AffineTransform.getTranslateInstance(pos.x, pos.y);
        g2.setTransform(tx);
        this.drawBorder(g);
        this.drawHexagon(g, new Color(255,40,40), radius);
        if (shelter.getGear() != null) {
            g.setColor(Color.WHITE);
            int cicrcleRadius = radius / 2;
            g.fillOval((- cicrcleRadius/2), (int) (-(radius * 0.8)), cicrcleRadius, cicrcleRadius);
        }
        g2.setTransform(old);
    }
}
