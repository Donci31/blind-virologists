package model;

import model.fields.Field;
import model.fields.Laboratory;
import model.fields.Shelter;
import model.fields.Warehouse;
import view.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A pályát, azaz a mezők hálózatát megvalósító osztály.
 * Felelős a mezők létrehozásáért és nyilvántartásáért, valamint a virológusok által gyűjthető különböző genetikai kódok számának meghatározásáért.
 */
public class Map {
	private int uniqueCodeCount;
	private ArrayList<Field> fields = new ArrayList<>();

	/**
	 * Létrehozza a pálya mezőit, beállítja a szomszédosságot. Leteszi a
	 * virológusokat
	 */
	public void generateMap() {
		// TODO
		int k = 20;
		int labor = 0;
		ArrayList<FieldView> fviewlist = new ArrayList<FieldView>();
		for (int h = 0; h < k; h++) {
			double n = Math.random();
			if (n < 0.2) {
				Field f = new Field();
				fields.add(f);
				fviewlist.add(new FieldView(new Point(0, 0), f));
			} else if (n < 0.53) {
				Laboratory m = new Laboratory();
				fields.add(m);
				fviewlist.add(new LaboratoryView(new Point(0, 0), m));
				labor += 1;
			} // itt majd lehet kell hogy az összes kódot lerakja ha az kritérium
			else if (n < 0.6) {
				Shelter s = new Shelter();
				fields.add(s);
				fviewlist.add(new ShelterView(new Point(0, 0), s));
			} else {
				Warehouse l = new Warehouse();
				fields.add(l);
				fviewlist.add(new WarehouseView(new Point(0, 0), l));
			}
			fields.add(new Field());

		}
		if (labor == 0) { // Ha nincs benne labor akkor tesz bele egyet
			int hany = (int) Math.random() * k;
			Laboratory j = new Laboratory();
			fields.set(hany, j);
			fviewlist.set(hany, new LaboratoryView(new Point(0, 0), j));
		}
		for (Field i : fields) {
			for (int cs = 0; cs < 6; cs++) {
				i.getNeighbors().add(null);
			}
		}

		int i = 1;
		int currfield = 0;
		while (i < k) { // i=1-től mert a base is field
			currfield += 1;
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
						fviewlist.get(i).Getpos().setLocation(prev.x + r * Math.sin(-i * Math.PI / 6.0),
								prev.y + r * Math.cos(-i * Math.PI / 6.0));// TODO kipróbálni hogy jó-e a helye
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
		}
		for (Drawable d : fviewlist) {
			Game.addDrawable(d);
		}
	}

}
