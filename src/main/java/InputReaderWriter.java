import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class InputReaderWriter {

    public static void printFields(ArrayList<Field> fields) {
        DumperOptions options = new DumperOptions();
        options.setIndent(4);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        LinkedHashMap<String, Object> mainMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> fieldMap = new LinkedHashMap<>();
        fieldMap.put("Type", getClassName(fields.get(0)));
        fieldMap.put("Name", fields.get(0).toString());
        fieldMap.put("Neighbors", getNeighborBlock(fields.get(0).getNeighbors()));

        ArrayList<LinkedHashMap<String, Object>> virologists = new ArrayList<>();
        for (Virologist v : fields.get(0).getVirologists()) {
            virologists.add(getVirologistBlock(v));
        }
        fieldMap.put("Virologists", virologists);
        mainMap.put("Fields", fieldMap);
        try (FileWriter file = new FileWriter("./src/main/resources/file.yml")) {
            yaml.dump(mainMap, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getClassName(Object o) {
        return o.getClass().toString().split(" ")[1];
    }

    private static ArrayList<String> getNeighborBlock(List<Field> neighbors) {
        ArrayList<String> names = new ArrayList<>();
        for (Field neighbor : neighbors) {
            names.add(neighbor.toString());
        }
        return names;
    }

    private static LinkedHashMap<String, Object> getVirologistBlock(Virologist v) {
        LinkedHashMap<String, Object> virologistMap = new LinkedHashMap<>();
        virologistMap.put("Name", v.toString());
        virologistMap.put("nCount", v.getNucleotide());
        virologistMap.put("aCount", v.getAminoAcid());
        virologistMap.put("Gears", v.getAminoAcid());
        virologistMap.put("Crafted Agents", v.getAminoAcid());
        virologistMap.put("Learnt Codes", v.getAminoAcid());
        return virologistMap;
    }
}
