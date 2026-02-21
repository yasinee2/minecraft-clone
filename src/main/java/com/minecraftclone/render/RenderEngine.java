package com.minecraftclone.render;

import com.minecraftclone.Main;
import com.minecraftclone.block.Block;
import com.minecraftclone.block.Blocks;
import com.minecraftclone.gui.PlayerGUI;
import com.minecraftclone.item.ItemInstance;
import com.minecraftclone.item.ItemRegistry;
import com.minecraftclone.player.PlayerCharacter;
import java.io.IOException;
import java.lang.reflect.Field;

public class RenderEngine {

    private static String[] HotbarItems = new String[9];
    private static RenderEngine instance;
    PlayerGUI gui;
    Main app;
    PlayerCharacter player;

    public RenderEngine(Main app, PlayerCharacter playerCharacter) {
        this.app = app;
        this.player = playerCharacter;
        instance = this;
        try {
            this.gui = new PlayerGUI(app);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guiUpdate() {
        gui.setLife(player.getLife());
        gui.setHunger(player.getHunger());
        gui.changeHotbarSlot(player.getHotbarSlot());
        gui.setInventoryVisibility(player.getinventoryVisible());
        UpdateHotbar();
    }

    //does: Displays all items from the HotbarItems Array in the Hotbar ingame
    private static void UpdateHotbar() {
        if (instance == null || instance.gui == null) return;
        int column = 1;
        for (int slot = 0; slot < HotbarItems.length; slot++) {
            String itemName = HotbarItems[slot];
            if (itemName != null) instance.gui.inventoryDisplayItem(1, column, new ItemInstance(ItemRegistry.get(itemName)));
            column += 1;
        }
    }

    //Does: Takes Strings from HotbarItems and returns them as a Block class string
    public static Block[] MakeBlocksInHotbarPlacable() {
        Block[] result = new Block[HotbarItems.length];
        for (int i = 0; i < HotbarItems.length; i++) {
            if (HotbarItems[i] == null) continue;
            if (!HotbarItems[i].endsWith("_item")) {
                try {
                    Field field = Blocks.class.getField(HotbarItems[i].toUpperCase());
                    result[i] = (Block) field.get(null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    System.err.println("Block not found: " + HotbarItems[i]);
                }
            }
        }
        return result;
    }

    public static String getHotbarItem(int Slot) {
        String Item = HotbarItems[Slot];
        return Item;
    }

    public static String[] getWholeHotbarItems() {
        return HotbarItems;
    }

    public static void setHotbarItem(int Slot, String ItemName) {
        HotbarItems[Slot] = ItemName;
        UpdateHotbar();
    }

    public static void giveItem(String ItemName) {
        int NexavailableSlot = 0;
        if (getHotbarItem(0) != null) {
            for (String item : HotbarItems) if (item != null) NexavailableSlot++;
        }
        if (NexavailableSlot > 9) {
            System.err.println("Hotbarslots are full");
        } else {
            try {
                setHotbarItem(NexavailableSlot, ItemName);
                UpdateHotbar();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
