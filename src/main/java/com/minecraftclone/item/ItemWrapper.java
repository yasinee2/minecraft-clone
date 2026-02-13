package com.minecraftclone.item;

import java.util.Map;

public class ItemWrapper {

    private Map<String, Item> items;

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }
}
