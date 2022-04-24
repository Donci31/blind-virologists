import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class InputWriter {

    public static void printFields(ArrayList<Field> fields) {
        // Szép kiíráshoz szükséges konfiguráció
        DumperOptions options = new DumperOptions();
        options.setIndent(4);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        LinkedHashMap<String, Object> mainMap = new LinkedHashMap<>();
        mainMap.put("Fields", getFieldsList(fields));
        mainMap.put("Applied Agents", "Yo");
        try (FileWriter file = new FileWriter("./src/main/resources/file.yml")) {
            yaml.dump(mainMap, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        fieldMap.put("Name", f.getId());
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
            names.add(neighbor.getId());
        }
        return names;
    }

    private static LinkedHashMap<String, Object> getVirologistBlock(Virologist v) {
        LinkedHashMap<String, Object> virologistMap = new LinkedHashMap<>();
        virologistMap.put("Name", v.getId());
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
            names.add(getClassName(g) + " " + g.getId());
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
}
