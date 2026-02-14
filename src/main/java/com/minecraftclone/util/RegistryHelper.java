package com.minecraftclone.util;

//import com.minecraftclone.item.Item;
//import com.minecraftclone.item.ItemType;
import java.util.HashMap;
import java.util.Map;

public class RegistryHelper {

    //Info: Ich habe keine möglichkeit gefunden diese funktion so anzupassen, dass sie auch für eine Blockregistry funktionieren würde
    /*
    public static void loadFromSection(Map<String, Object> section, Map<String, Item> target) {
        for (String key : section.keySet()) {

            Map<String, Object> itemData = asStringMap(section.get(key));

            Item item = new Item(
                key,
                ItemType.valueOf((String) itemData.get("type")),
                (Integer) itemData.get("maxStack"),
                (String) itemData.get("name"),
                (Integer) itemData.get("baseDurability"),
                (Integer) itemData.get("baseDamage"),
                (Integer) itemData.get("miningEfficiency")
            );
            target.put(key, item);
        }
    }
    */

    public static Map<String, Object> asStringMap(Object object) {
        //DOES: Fix trust me basis
        if (!(object instanceof Map<?, ?> raw)) {
            throw new IllegalStateException("Invalid YAML structure: expected a map");
        }

        Map<String, Object> stringMap = new HashMap<>();
        for (Map.Entry<?, ?> e : raw.entrySet()) {
            if (!(e.getKey() instanceof String key)) {
                throw new IllegalStateException("Invalid YAML: non-string key");
            }
            stringMap.put(key, e.getValue());
        }
        return stringMap;
    }
}
