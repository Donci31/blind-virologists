import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A mentések beolvasására és betöltésére szolgáló osztály
 */
public class InputReader {
    /**
     * Visszatér a megadott fáljban elmentett mezők listájával
     * @param path - a betölteni kívánt fájl elérési útja
     * @return - a létrehozott mezők listája
     */
    public ArrayList<Field> readFields(String path) {
        Yaml yaml = new Yaml();
        //a mezők listája amivel vissza fog térni
        var fieldList = new ArrayList<Field>();
        try (FileReader file = new FileReader(path)) {
            //a map amibe betöltjük a beolvasott adatokat
            Map<String, Object> obj = yaml.load(file);
            //kiolvassa a fájlba írt hashmapek listáját
            var mapList = (ArrayList<LinkedHashMap<String, Object>>) obj.get("Fields");
            //a lista hashmapjeiből mezőket épít
            fieldList = buildFields(mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldList;
    }

    /**
     * Létrehozza a mezők listáját a mentési fájlból kiolvasott listából
     * @param list - a kiolvasott lista
     * @return - a visszaadott lista, felépített mezőkkel
     */
    private ArrayList<Field> buildFields(ArrayList<LinkedHashMap<String, Object>> list){
        //létrehoz egy mapet a mezők tárolására
        HashMap<String, Field> fieldHashMap = new HashMap<>();
        //létrehozzuk a mezőket a kiolvasott nevekkel
        for(LinkedHashMap<String, Object> map : list){
            //kiolvassa a mező nevét
            String name = map.get("Name").toString();
            //létrehoz egy mezőt az adott névvel és beteszi a mapbe
            String type = map.get("Type").toString();
            Field created = new Field(name);
            switch (type){
                case "Laboratory":
                    created = new Laboratory(name);
                    ((Laboratory)created).setInfected((boolean)map.get("Infected"));
                    break;
                case "Warehouse":
                    created = new Warehouse(name);
                    ((Warehouse)created).setnProduced((int)map.get("nCount"));
                    ((Warehouse)created).setaProduced((int)map.get("aCount"));
                    break;
                case "Shelter":
                    created = new Shelter(name);
                    //TODO load gears
                    break;
                default:
                    break;

            }
            fieldHashMap.put(name, created);

        }
        //beállítjuk a létrehozott mezők szomszédjait
        for(LinkedHashMap<String, Object> map : list){
            //kiolvassa a szomszédossági listát a mapből
            ArrayList<String> neighbors = (ArrayList<String>) map.get("Neighbors");
            //kiolvassa a mező nevét
            String name = map.get("Name").toString();
            //kiválasztja a névnek megfelelő mezőt
            var active = fieldHashMap.get(name);
            for(int i = 0; i < neighbors.size(); i++){
                //beállítja a kiválasztott mező szomszédait
                active.setNeighbor(i, fieldHashMap.get(neighbors.get(i)));
            }
        }
        //minden mezőre felhelyezzi a virológusokat
        for(LinkedHashMap<String, Object> map : list){
            //kiolvassa a virológusok hashmapjeinek listáját
            ArrayList<LinkedHashMap<String, Object>> virologistList= (ArrayList<LinkedHashMap<String, Object>>) map.get("Virologists");
            //kiolvassa a mező nevét
            String name = map.get("Name").toString();
            //kiválasztja a névnek megfelelő mezőt a mapből
            var active = fieldHashMap.get(name);
            //a hashmapekből létrehozza a virológusokat
            for (LinkedHashMap<String, Object> stringObjectLinkedHashMap : virologistList) {
                //felhelyezi a virológusokat a mezőre
                active.accept(virologistFromMap(stringObjectLinkedHashMap));
            }
        }
        //a mezők mapjéből listát csinál
        ArrayList<Field> fieldArray = new ArrayList<>(fieldHashMap.values());
        return fieldArray;
    }

    /**
     * Létrehoz egy virológust egy hashmapből
     * @param map - a virológus adatait tároló hashmap
     * @return - a létrehozott virológus
     */
    private Virologist virologistFromMap(HashMap<String, Object> map){
        //kiolvassa a virológus nevét a mabjéből
        String name = map.get("Name").toString();
        //létrehozza a virológust a névvel
        var v = new Virologist(name);
        //beállítja a virológus aminosav és nukleotidszámát
        v.setAminoAcid((int)map.get("aCount"));
        v.setNucleotide((int)map.get("nCount"));
        //betölti a gearek listáját
        ArrayList<String> gearStrings = (ArrayList<String>)map.get("Gears");
        if(gearStrings != null) {
            for (String s : gearStrings) {
                String[] split = s.split(" ");
                String gearName = split[0];
                Gear g;
                switch (gearName) {
                    case "GloveGear":
                        g = new GloveGear();
                        ((GloveGear)g).setTimesUsed(Integer.parseInt(split[3]));
                        break;
                    case "AxeGear":
                        g = new AxeGear();
                        ((AxeGear)g).setUsed(Boolean.parseBoolean(split[3]));
                        break;
                    case "RobeGear":
                        g = new RobeGear();
                        break;
                    case "SackGear":
                        g = new SackGear();
                        break;
                    default:
                        g = null;
                        break;
                }
                if(g != null){
                    g.setID(split[1]);
                    v.pickUpGear(g);
                }
            }
        }
        //betölti a kódok listáját
        ArrayList<String> codeStrings = (ArrayList<String>)map.get("Learnt Codes");
        if(codeStrings != null) {
            for (String s : codeStrings) {
                String[] split = s.split(" ");
                String codeName = split[0];
                Code c;
                switch (codeName) {
                    case "AmniCode":
                        c = new AmniCode();
                        break;
                    case "DanceCode":
                        c = new DanceCode();
                        break;
                    case "ProtCode":
                        c = new ProtCode();
                        break;
                    case "StunCode":
                        c = new StunCode();
                        break;
                    default:
                        c = null;
                        break;
                }
                if(c != null){
                    c.setID(split[1]);
                    v.learnCode(c);
                }
            }
        }
        //betölti a gearek listáját
        ArrayList<String> agentStrings = (ArrayList<String>)map.get("Crafted Agents");
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
