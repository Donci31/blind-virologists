package view;

import model.Virologist;

import java.awt.*;

/**
 * A modell Virologist (virológus) osztályának grafikus megjelenítéséért felelős osztály.
 */
public class VirologistView implements Drawable {
    Virologist virologist;
    private Point pos;
    private Image virImg, bearImg;

    /**
     * Konstruktor, amely beállítja a nézethez tartozó attribútumokat.
     * @param virologist a nézethez tartozó modellbeli virológus
     * @param pos a virológus képernyőpozíciója
     * @param virImg a virológus alapállapotú képe
     * @param bearImg a virológus medveállapotú képe
     */
    public VirologistView(Virologist virologist, Point pos, Image virImg, Image bearImg) {
        this.virologist = virologist;
        this.pos = pos;
        this.virImg = virImg;
        this.bearImg = bearImg;
    }

    /**
     * A virológus kirajzolása a megadott grafikus kontextusra.
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        boolean bear = virologist.getBear();
        boolean stunned = virologist.isStunned();

        // TODO lehet, hogy itt a pos-t a Field-től kéne megkérdezni, hogy a mező melyik részére rakja magát
        if (bear) {
            g.drawImage(bearImg, pos.x, pos.y, null);
        } else {
            g.drawImage(virImg, pos.x, pos.y, null);
        }

        if (stunned) {
            g.setColor(Color.RED);
            g.fillOval(pos.x + 5, pos.y + 5, 10, 10);
        }
    }
}
