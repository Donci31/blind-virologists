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
        // TODO
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
