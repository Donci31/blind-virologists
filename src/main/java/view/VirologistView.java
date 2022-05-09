package view;

import model.Virologist;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A modell Virologist (virológus) osztályának grafikus megjelenítéséért felelős osztály.
 */
public class VirologistView implements Drawable {
    Virologist virologist;
    private Point pos;
    private static Image virImg, bearImg;

    private final int size = 50;

    static {
        try {
            bearImg =  ImageIO.read(new File("./src/main/resources/brown_bear.png"));
            virImg =  ImageIO.read(new File("./src/main/resources/mask.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Konstruktor, amely beállítja a nézethez tartozó attribútumokat.
     * @param virologist a nézethez tartozó modellbeli virológus
     * @param pos a virológus képernyőpozíciója
     * @param virImg a virológus alapállapotú képe
     * @param bearImg a virológus medveállapotú képe
     */
    public VirologistView(Point pos, Virologist virologist) {
        this.virologist = virologist;
        this.pos = pos;       
    }

    /**
     * A virológus kirajzolása a megadott grafikus kontextusra.
     * @param g megadott grafikus kontextus
     */
    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.RED);
        //g.fillOval(pos.x, pos.y, 100, 100);
        //g.drawImage(bearImg, pos.x, pos.y, 50, 50, null);
        
        boolean bear = virologist.getBear();
        boolean stunned = virologist.isStunned();

        // TODO lehet, hogy itt a pos-t a Field-től kéne megkérdezni, hogy a mező melyik részére rakja magát
        if (bear) {
            g.drawImage(bearImg, pos.x - (size/2), pos.y - (size/2), size, size, null);
        } else {
            g.drawImage(virImg, pos.x - (size/2), pos.y - (size/2), size, size, null);
        }

        if (stunned) {
            g.setColor(new Color(136, 0, 21));
            g.fillOval(pos.x + size/3, pos.y - size/2, size/2, size/2);
        }
    }
}
