package view;

import model.Virologist;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A modell Virologist (virológus) osztályának grafikus megjelenítéséért felelős osztály.
 */
public class VirologistView implements Drawable {
    Virologist virologist;
    private Point pos;
    private Image virImg, bearImg;
    private String colorString;

    public static final int size = 30;

    /**
     * Konstruktor, amely beállítja a nézethez tartozó attribútumokat.
     *
     * @param virologist a nézethez tartozó modellbeli virológus
     * @param pos        a virológus képernyőpozíciója
     * @param playerID   hanyadik játékos
     */
    public VirologistView(Point pos, Virologist virologist, int playerID) {
        this.virologist = virologist;
        this.pos = pos;

        try {
            bearImg = ImageIO.read(this.getClass().getClassLoader().getResource("bear" + playerID + ".png"));
            virImg = ImageIO.read(this.getClass().getClassLoader().getResource("mask" + playerID + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (playerID == 1) {
            colorString = "Brown";
        } else if (playerID == 2) {
            colorString = "Yellow";
        } else {
            colorString = "Red";
        }
    }

    /**
     * A virológus színének nevének gettere.
     * @return a szín neve
     */
    public String getColorString() {
        return colorString;
    }

    /**
     * A virológus kirajzolása a megadott grafikus kontextusra.
     *
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        boolean bear = virologist.getBear();
        boolean stunned = virologist.isStunned();
        FieldView fieldView = virologist.getField().getFieldView();
        Point fieldPos = fieldView.Getpos();
        if (bear) {
            g.drawImage(bearImg, fieldPos.x + pos.x, fieldPos.y + pos.y, size, size, null);
        } else {
            g.drawImage(virImg, fieldPos.x + pos.x, fieldPos.y + pos.y, size, size, null);
        }

        if (stunned) {
            g.setColor(new Color(136, 0, 21));
            g.fillOval(fieldPos.x + pos.x + (int)(size * 0.8), fieldPos.y + pos.y, size / 3, size / 3);
        }
    }

    /**
     * A virológust megjelenítő kép gettere.
     * @return a virológus képe
     */
    public Image getImage() {
        if (virologist.getBear()) {
            return bearImg;
        } else {
            return virImg;
        }
    }
}
