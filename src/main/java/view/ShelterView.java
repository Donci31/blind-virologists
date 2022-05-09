package view;

import model.fields.Shelter;

import java.awt.*;

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
    }

    /**
     * Kirajzolja az óvóhely mezőt a megadott grafikus kontextusra.
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        this.drawBorder(g);
        this.drawHexagon(g, new Color(255,40,40), radius);
        if (shelter.getGear() != null) {
            g.setColor(Color.WHITE);
            int cicrcleRadius = radius / 2;
            g.fillOval((int) (pos.x - cicrcleRadius/2), (int) (pos.y - (radius * 0.8)), cicrcleRadius, cicrcleRadius);
        }
    }
}
