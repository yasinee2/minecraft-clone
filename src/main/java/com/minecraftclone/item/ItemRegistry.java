package com.minecraftclone.item;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {

    Gson gson = new Gson();
    private static final Map<String, Item> ITEMS = new HashMap<>();

    public static void register(String id, Item item) {
        //ITEMS.put(id, item);
    }

    public static Item get(String id) {
        return ITEMS.get(id);
    }
}
