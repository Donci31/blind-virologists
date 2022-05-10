package model;

import model.codes.AmniCode;
import model.codes.DanceCode;
import model.codes.ProtCode;
import model.codes.StunCode;
import model.fields.Field;
import model.fields.Laboratory;
import model.fields.Shelter;
import model.fields.Warehouse;
import model.gears.AxeGear;
import model.gears.GloveGear;
import model.gears.RobeGear;
import model.gears.SackGear;
import view.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * A pályát, azaz a mezők hálózatát megvalósító osztály.
 * Felelős a mezők létrehozásáért és nyilvántartásáért, valamint a virológusok által gyűjthető különböző genetikai kódok számának meghatározásáért.
 */
public class Map {
    private ArrayList<Field> fields = new ArrayList<>();

    /**
     * Létrehozza a pálya mezőit, beállítja a szomszédosságot. Leteszi a virológusokat random mezőkre.
     *
     * @param vCount ennyi virológust helyez le
     */
    public void generateMap(int vCount) {
        int r = 90;
        int k = 19;
        int labor = 0;
        AmniCode amnic = new AmniCode();
        StunCode stunc = new StunCode();
        DanceCode dancec = new DanceCode();
        ProtCode protc = new ProtCode();
        int vanamni = -1;
        int vanstun = -1;
        int vandance = -1;
        int vanprot = -1;
        ArrayList<FieldView> fviewlist = new ArrayList<FieldView>();
        for (int h = 0; h < k; h++) {
            double n = Math.random();
            if (n < 0.2) {
                Field f = new Field();
                fields.add(f);
                fviewlist.add(new FieldView(new Point(0, 0), f));
            } else if (n < 0.63) {
                Laboratory m = new Laboratory();
                fields.add(m);
                double kod = Math.random();
                if (kod < 0.15) {
                    m.placeCode(amnic);
                    vanamni = h;
                } else if (kod < 0.30) {
                    m.placeCode(stunc);
                    vanstun = h;
                } else if (kod < 0.45) {
                    m.placeCode(dancec);
                    vandance = h;
                } else if (kod < 0.6) {
                    m.placeCode(protc);
                    vanprot = h;
                }
                fviewlist.add(new LaboratoryView(new Point(0, 0), m));
                labor += 1;
            } // itt majd lehet kell hogy az összes kódot lerakja ha az kritérium
            else if (n < 0.8) {
                Shelter s = new Shelter();
                fields.add(s);
                double fel = Math.random();
                if (fel < 0.15) {
                    s.addGear(new RobeGear());
                } else if (fel < 0.3) {
                    s.addGear(new AxeGear());
                } else if (fel < 0.45) {
                    s.addGear(new GloveGear());
                } else if (fel < 0.6) {
                    s.addGear(new SackGear());
                }
                fviewlist.add(new ShelterView(new Point(0, 0), s));
            } else {
                Warehouse l = new Warehouse();
                fields.add(l);
                fviewlist.add(new WarehouseView(new Point(0, 0), l));
            }
        }
        if (labor == 0) { // Ha nincs benne labor akkor tesz bele egyet
            int hany = (int) (Math.random() * k);
            Laboratory j = new Laboratory();
            fields.set(hany, j);
            fviewlist.set(hany, new LaboratoryView(new Point(0, 0), j));
        }
        while (vanamni == -1 || vandance == -1 || vanprot == -1 || vanstun == -1) {
            if (vanamni == -1) {
                int hany = (int) (Math.random() * k);
                while (hany == vanamni || hany == vanprot || hany == vandance || hany == vanstun) {
                    hany = (int) (Math.random() * k);
                }
                Laboratory j = new Laboratory();
                j.placeCode(amnic);
                fields.set(hany, j);
                vanamni = hany;
                fviewlist.set(hany, new LaboratoryView(new Point(0, 0), j));
            }
            if (vanstun == -1) {
                int hany = (int) (Math.random() * k);
                while (hany == vanamni || hany == vanprot || hany == vandance || hany == vanstun) {
                    hany = (int) (Math.random() * k);
                }
                Laboratory j = new Laboratory();
                j.placeCode(stunc);
                fields.set(hany, j);
                vanstun = hany;
                fviewlist.set(hany, new LaboratoryView(new Point(0, 0), j));
            }
            if (vandance == -1) {
                int hany = (int) (Math.random() * k);
                while (hany == vanamni || hany == vanprot || hany == vandance || hany == vanstun) {
                    hany = (int) (Math.random() * k);
                }
                Laboratory j = new Laboratory();
                j.placeCode(dancec);
                fields.set(hany, j);
                vandance = hany;
                fviewlist.set(hany, new LaboratoryView(new Point(0, 0), j));
            }
            if (vanprot == -1) {
                int hany = (int) (Math.random() * k);
                while (hany == vanamni || hany == vanprot || hany == vandance || hany == vanstun) {
                    hany = (int) (Math.random() * k);
                }
                Laboratory j = new Laboratory();
                j.placeCode(protc);
                fields.set(hany, j);
                vanprot = hany;
                fviewlist.set(hany, new LaboratoryView(new Point(0, 0), j));
            }
        }
        for (Field i : fields) {
            i.getNeighbors().clear();
            for (int cs = 0; cs < 6; cs++) {
                i.getNeighbors().add(null);
            }
        }

        int i = 1;
        int currfield = 0;
        fviewlist.get(0).Getpos().setLocation(350, 280);
        while (i < k) { // i=1-től mert a base is field

            Field prevfield = fields.get(currfield);
            int ieleje = i;
            for (int j = 0; j < 6; j++) {
                if (i < k) {
                    if (prevfield.getNeighbors().get(j) == null) {
                        prevfield.getNeighbors().set(j, fields.get(i));// hozzáadni a j-edik szomszédhoz órajárásával
                        // megegyező irány
                        prevfield.getNeighbors().get(j).getNeighbors().set((j + 3) % 6, prevfield);// kölcsönösen
                        // hozzáadja
                        Point prev = fviewlist.get(fields.indexOf(prevfield)).Getpos();
                        fviewlist.get(i).Getpos().setLocation(prev.x + r * Math.sin(-j * Math.PI / 3.0),
                                prev.y + r * Math.cos(-j * Math.PI / 3.0));
                        i++;

                        if (prevfield.getNeighbors().get((j - 1 + 6) % 6) != null) { // balra levő mezővel összeköti, ha
                            // van
                            int g = (j - 1 + 6) % 6;
                            prevfield.getNeighbors().get(j).getNeighbors().set((5 + g) % 6,
                                    prevfield.getNeighbors().get(g));
                            prevfield.getNeighbors().get(g).getNeighbors().set((1 + j) % 6,
                                    prevfield.getNeighbors().get(j));
                        }
                        if ((prevfield.getNeighbors().get((j + 1) % 6) != null)) { // A jobbra levő mezővel összeköti ha
                            // van
                            int u = (j + 1) % 6;
                            prevfield.getNeighbors().get(u).getNeighbors().set((5 + j) % 6,
                                    prevfield.getNeighbors().get(j));
                            prevfield.getNeighbors().get(j).getNeighbors().set((1 + u) % 6,
                                    prevfield.getNeighbors().get(u));
                        }
                    }
                }
            }
            currfield += 1;
        }
        for (Drawable d : fviewlist) {
            Game.addDrawable(d);
        }

        for (int m = 0; m < vCount; m++) {
            Virologist virologist = new Virologist();
            Game.addVirologist(virologist);

            Field field = fields.get((int) (Math.random() * k));
            field.accept(virologist);

            VirologistView virologistView = new VirologistView(
                    new Point((int) (Math.cos(2 * Math.PI * m / vCount) * 25) - VirologistView.size / 2,
                            (int) (Math.sin(2 * Math.PI * m / vCount) * 25) - VirologistView.size / 2), virologist, m + 1);
            Game.addDrawable(virologistView);
        }
    }
}
