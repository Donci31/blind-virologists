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

    public static void printField(Field f) {
        DumperOptions options = new DumperOptions();
        options.setIndent(4);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);

        LinkedHashMap<String, Object> mainMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> fieldMap = new LinkedHashMap<>();
        fieldMap.put("Name", "f1");
        fieldMap.put("Neighbors", getNeighborBlock(f.getNeighbors()));
        mainMap.put(getClassName(f), fieldMap);
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
}
