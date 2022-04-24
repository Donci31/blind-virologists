import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InputReader {
    public ArrayList<Field> readFields(String path) {
        Yaml yaml = new Yaml();
        var fieldList = new ArrayList<Field>();
        try (FileReader file = new FileReader(path)) {
            Map<String, Object> obj = yaml.load(file);
            var mapList = (ArrayList<LinkedHashMap<String, Object>>) obj.get("Fields");
            fieldList = buildFields(mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldList;
    }

    private ArrayList<Field> buildFields(ArrayList<LinkedHashMap<String, Object>> list){
        HashMap<String, Field> fieldHashMap = new HashMap<>();
        for(LinkedHashMap<String, Object> map : list){
            String name = map.get("Name").toString();
            fieldHashMap.put(name, new Field(name));
        }

        for(LinkedHashMap<String, Object> map : list){
            ArrayList<String> neighbors = (ArrayList<String>) map.get("Neighbors");
            String name = map.get("Name").toString();
            var active = fieldHashMap.get(name);
            for(int i = 0; i < neighbors.size(); i++){
                active.setNeighbor(i, fieldHashMap.get(neighbors.get(i)));
            }
        }
        for(LinkedHashMap<String, Object> map : list){
            ArrayList<LinkedHashMap<String, Object>> virologistList= (ArrayList<LinkedHashMap<String, Object>>) map.get("Virologists");
            String name = map.get("Name").toString();
            var active = fieldHashMap.get(name);
            for (LinkedHashMap<String, Object> stringObjectLinkedHashMap : virologistList) {
                active.accept(virologistFromMap(stringObjectLinkedHashMap));
            }
        }
        ArrayList<Field> fieldArray = new ArrayList<>(fieldHashMap.values());


        return fieldArray;
    }

    private Virologist virologistFromMap(HashMap<String, Object> map){
        String name = map.get("Name").toString();
        var v = new Virologist(name);
        v.setAminoAcid((int)map.get("aCount"));
        v.setNucleotide((int)map.get("nCount"));
        ArrayList<String> gearStrings = (ArrayList<String>)map.get("Gears");
        if(gearStrings != null) {
            for (String s : gearStrings) {
                String gearName = s.split(" ")[0];
                switch (gearName) {
                    case "GloveGear":
                        v.pickUpGear(new GloveGear());
                        break;
                    case "AxeGear":
                        v.pickUpGear(new AxeGear());
                        break;
                    case "RobeGear":
                        v.pickUpGear(new RobeGear());
                        break;
                    case "SackGear":
                        v.pickUpGear(new SackGear());
                        break;
                }
            }
        }
        ArrayList<String> codeStrings = (ArrayList<String>)map.get("Codes");
        if(codeStrings != null) {
            for (String s : codeStrings) {
                String codeName = s.split(" ")[0];
                switch (codeName) {
                    case "AmniCode":
                        v.learnCode(new AmniCode());
                        break;
                    case "DanceCode":
                        v.learnCode(new DanceCode());
                        break;
                    case "ProtCode":
                        v.learnCode(new ProtCode());
                        break;
                    case "StunCode":
                        v.learnCode(new StunCode());
                        break;
                }
            }
        }

        ArrayList<String> agentStrings = (ArrayList<String>)map.get("Codes");
        if(agentStrings != null) {
            for (String s : agentStrings) {
                String agentName = s.split(" ")[0];
                switch (agentName) {
                    case "AmniVirus":
                        v.addAgent(new AmniVirus());
                        break;
                    case "DanceVirus":
                        v.addAgent(new DanceVirus());
                        break;
                    case "ProtVaccine":
                        v.addAgent(new ProtVaccine());
                        break;
                    case "StunVirus":
                        v.addAgent(new StunVirus());
                        break;
                }

            }
        }
        return v;
    }
}
