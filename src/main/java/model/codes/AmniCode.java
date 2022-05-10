package model.codes;

import model.Virologist;
import model.agents.AmniVirus;

/**
 * Az model.agents.AmniVirus vírust kódoló genetikai kódot megvalósító osztály.
 * Felelős az ilyen vírusok létrehozásáért.
 */
public class AmniCode extends Code {

    /**
     * Konstruktor, ami beállítja, hogy mennyi nyersanyagba kerül egy model.agents.AmniVirus létrehozása.
     */
    public AmniCode() {
        nCost = 50;
        aCost = 100;
    }

    /**
     * Létrehozza az ágenst, és elveszi a virológustól a kódoláshoz szükséges anyagokat.
     *
     * @param v     - craftoló virológus
     * @param nCost - szükséges nukleotid
     * @param aCost - szükséges aminosav
     * @throws IllegalArgumentException - ha túlköltekezne a virológus
     */
    public void craftAgent(Virologist v, int nCost, int aCost) throws IllegalArgumentException {

        if (v.getAminoAcid() >= aCost && v.getNucleotide() >= nCost) {
            AmniVirus a = new AmniVirus();
            v.removeNucleotide(nCost);
            v.removeAminoAcid(aCost);
            v.addCraftedAgent(a);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
