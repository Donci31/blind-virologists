package model;

import model.fields.Field;

import java.util.ArrayList;

/**
 * A pályát, azaz a mezők hálózatát megvalósító osztály.
 * Felelős a mezők létrehozásáért és nyilvántartásáért, valamint a virológusok által gyűjthető különböző genetikai kódok számának meghatározásáért.
 */
public class Map {
	private int uniqueCodeCount;
	private ArrayList<Field> fields= new ArrayList<>();

	/**
	 * Létrehozza a pálya mezőit, beállítja a szomszédosságot. Leteszi a virológusokat
	 */
	public void generateMap() {
		//TODO
		int k=20;
		for(int h=0;h<k;h++) {
			fields.add(new Field());
			for (int cs = 0; cs < 6; cs++) {
				fields.get(h).getNeighbors().add(null);
			}
		}
		int i=1;
		int currfield=0;
		while(i<k){   //i=1-től mert a base is field
			currfield+=1;
			Field prevfield=fields.get(currfield);
			int ieleje=i;
			for(int j=0;j<6;j++) {
				if (i < k) {
					if (prevfield.getNeighbors().get(j) == null) {
						prevfield.getNeighbors().set(j, fields.get(i));//hozzáadni a j-edik szomszédhoz órajárásával megegyező irány
						prevfield.getNeighbors().get(j).getNeighbors().set((j +3) % 6, prevfield);//kölcsönösen hozzáadja
						i++;
						if (prevfield.getNeighbors().get((j - 1+6) % 6) != null) { //balra levő mezővel összeköti, ha van
							int g = (j - 1+6) % 6;
							prevfield.getNeighbors().get(j).getNeighbors().set((5 + g) % 6, prevfield.getNeighbors().get(g));
							prevfield.getNeighbors().get(g).getNeighbors().set((1 + j) % 6, prevfield.getNeighbors().get(j));
						}
						if ((prevfield.getNeighbors().get((j + 1) % 6) != null)) {   //A jobbra levő mezővel összeköti ha van
							int u = (j + 1) % 6;
							prevfield.getNeighbors().get(u).getNeighbors().set((5 + j) % 6, prevfield.getNeighbors().get(j));
							prevfield.getNeighbors().get(j).getNeighbors().set((1 + u) % 6, prevfield.getNeighbors().get(u));
						}
					}

				}
			}
		}
	}

}
