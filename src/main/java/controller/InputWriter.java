package controller;

import model.Virologist;
import model.agents.Agent;
import model.codes.Code;
import model.fields.Field;
import model.fields.Laboratory;
import model.fields.Shelter;
import model.fields.Warehouse;
import model.gears.AxeGear;
import model.gears.Gear;
import model.gears.GloveGear;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A mentések fájlba írását megvalósító osztály.
 */
public abstract class InputWriter {

    /**
     * A mentések fájlba írását elvégző metódus.
     *
     * @param filename melyik fájlba mentse
     * @param fields   mely mezőket, és az azokhoz tartozó többi objektumot
     */
    public static void printFields(String filename, ArrayList<Field> fields) {
        // Szép kiíráshoz szükséges konfiguráció
        DumperOptions options = new DumperOptions();
        options.setIndent(4);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        LinkedHashMap<String, Object> mainMap = new LinkedHashMap<>();
        mainMap.put("Fields", getFieldsList(fields));
        mainMap.put("Applied Agents", getAppliedAgentsList());

        try (FileWriter file = new FileWriter(Prototype.class.getClassLoader().getResource(filename).getPath())) {
            Virologist v = getWinner(fields);
            if (v != null) {
                yaml.dump("The game has ended, " + v.getName() + " won!", file);
            } else {
                yaml.dump(mainMap, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Az osztály nevét visszaadó függvény.
     *
     * @param o az objektum
     * @return az objektum neve
     */
    private static String getClassName(Object o) {
        return o.getClass().getSimpleName();
    }

    private static ArrayList<Object> getFieldsList(ArrayList<Field> fields) {
        ArrayList<Object> fieldList = new ArrayList<>();
        for (Field f : fields) {
            fieldList.add(getFieldBlock(f));
        }
        return fieldList;
    }

    /**
     * @param f
     * @return
     */
    private static LinkedHashMap<String, Object> getFieldBlock(Field f) {
        LinkedHashMap<String, Object> fieldMap = new LinkedHashMap<>();
        fieldMap.put("Type", getClassName(f));

        if (getClassName(f).equals("Laboratory")) {
            Laboratory out = (Laboratory) f;
            fieldMap.put("Infected", out.isInfected());
            if (out.getCode() == null)
                fieldMap.put("Code", 0);
            else
                fieldMap.put("Code", getClassName(out.getCode()) + " " + out.getCode().getId());
        } else if (getClassName(f).equals("Warehouse")) {
            Warehouse out = (Warehouse) f;
            fieldMap.put("nCount", out.getnProduced());
            fieldMap.put("aCount", out.getaProduced());
        } else if (getClassName(f).equals("Shelter")) {
            Shelter out = (Shelter) f;
            if (out.getGear() == null)
                fieldMap.put("Gear", 0);
            else
                fieldMap.put("Gear", getClassName(out.getGear()) + " " + out.getGear().getId());
        }
        fieldMap.put("Name", f.getName());
        fieldMap.put("Neighbors", getNeighborList(f.getNeighbors()));

        ArrayList<LinkedHashMap<String, Object>> virologists = new ArrayList<>();
        for (Virologist v : f.getVirologists()) {
            virologists.add(getVirologistBlock(v));
        }
        fieldMap.put("Virologists", virologists);
        return fieldMap;
    }

    private static ArrayList<Object> getNeighborList(List<Field> neighbors) {
        ArrayList<Object> names = new ArrayList<>();
        for (Field neighbor : neighbors) {
            names.add(neighbor.getName());
        }
        return names;
    }

    private static LinkedHashMap<String, Object> getVirologistBlock(Virologist v) {
        LinkedHashMap<String, Object> virologistMap = new LinkedHashMap<>();
        virologistMap.put("Name", v.getName());
        virologistMap.put("nCount", v.getNucleotide());
        virologistMap.put("aCount", v.getAminoAcid());
        virologistMap.put("Gears", getGearsList(v));
        virologistMap.put("Crafted Agents", getCraftedList(v));
        virologistMap.put("Learnt Codes", getCodesList(v));
        return virologistMap;
    }

    private static ArrayList<Object> getGearsList(Virologist v) {
        ArrayList<Object> names = new ArrayList<>();
        for (Gear g : v.getGears()) {
            if (getClassName(g).equals("GloveGear")) {
                GloveGear out = (GloveGear) g;
                names.add(getClassName(g) + " " + g.getId() + " timesUsed=" + out.getTimesUsed());
            } else if (getClassName(g).equals("AxeGear")) {
                AxeGear out = (AxeGear) g;
                names.add(getClassName(g) + " " + g.getId() + " used=" + out.isUsed());
            } else {
                names.add(getClassName(g) + " " + g.getId());
            }
        }
        return names;
    }

    private static ArrayList<Object> getCraftedList(Virologist v) {
        ArrayList<Object> names = new ArrayList<>();
        for (Agent a : v.getCraftedAgents()) {
            names.add(getClassName(a) + " " + a.getId());
        }
        return names;
    }

    private static ArrayList<Object> getCodesList(Virologist v) {
        ArrayList<Object> names = new ArrayList<>();
        for (Code c : v.getLearntCodes()) {
            names.add(getClassName(c) + " " + c.getId());
        }
        return names;
    }

    private static ArrayList<Object> getAppliedAgentsList() {
        ArrayList<Object> names = new ArrayList<>();
        for (Agent a : SteppableController.getAppliedAgents()) {
            names.add(getClassName(a) + " " + a.getId() + " on=" + a.getSmearedVirologist().getName() + " remaining=" + a.getVirusTimer());
        }
        return names;
    }

    //TODO: should be handled in the model.Game class
    private static Virologist getWinner(ArrayList<Field> fields) {
        for (Field f : fields) {
            for (Virologist v : f.getVirologists()) {
                //4 is a temp value, dependent on number of learnable codes
                if (v.getLearntCodes().size() >= 4) {
                    return v;
                }
            }
        }
        return null;
    }
}
