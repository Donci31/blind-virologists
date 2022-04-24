import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class InputWriter {

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
        try (FileWriter file = new FileWriter("./src/main/resources/" + filename)) {
            yaml.dump(mainMap, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getClassName(Object o) {
        return o.getClass().toString().split(" ")[1];
    }

    private static ArrayList<Object> getFieldsList(ArrayList<Field> fields) {
        ArrayList<Object> fieldList = new ArrayList<>();
        for (Field f : fields) {
            fieldList.add(getFieldBlock(f));
        }
        return  fieldList;
    }

    private static LinkedHashMap<String, Object> getFieldBlock(Field f) {
        LinkedHashMap<String, Object> fieldMap = new LinkedHashMap<>();
        fieldMap.put("Type", getClassName(f));

        if (getClassName(f).equals("Laboratory")) {
            Laboratory out = (Laboratory) f;
            fieldMap.put("Infected", out.isInfected());
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
                names.add(getClassName(g) + " " + g.getId() + " timesUsed=" + out.isUsed());
            }
            else {
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
        for (Agent a :SteppableController.getAppliedAgents()) {
            names.add(getClassName(a) + " " + a.getId() + " on=" + a.getSmearedVirologist().getName() + " remaining=" + a.getVirusTimer());
        }
        return names;
    }
}
