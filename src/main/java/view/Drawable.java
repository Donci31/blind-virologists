package view;

import java.awt.*;

/**
 * Interfész a kirajzolható objektumok kezelésére.
 */
public interface Drawable {
    /**
     * Kirajzolja az objektumot a megadott grafikus kontextusra.
     * @param g megadott grafikus kontextus
     */
    void draw(Graphics g);
}
