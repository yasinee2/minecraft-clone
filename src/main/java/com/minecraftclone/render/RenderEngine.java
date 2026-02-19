package com.minecraftclone.render;

import com.minecraftclone.Main;
import com.minecraftclone.gui.PlayerGUI;
import com.minecraftclone.item.ItemInstance;
import com.minecraftclone.item.ItemRegistry;
import com.minecraftclone.player.PlayerCharacter;
import java.io.IOException;

public class RenderEngine {

    PlayerGUI gui;
    Main app10;
    PlayerCharacter player;
    int x = 10;

    public RenderEngine(Main app, PlayerCharacter playerCharacter) {
        app10 = app;
        this.player = playerCharacter;
        try {
            this.gui = new PlayerGUI(app);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gui.inventoryDisplayItem(1, 1, new ItemInstance(ItemRegistry.get("iron_sword")));
        gui.inventoryDisplayItem(1, 2, new ItemInstance(ItemRegistry.get("lapis_block")));
        gui.inventoryDisplayItem(1, 3, new ItemInstance(ItemRegistry.get("oak_planks")));
        gui.inventoryDisplayItem(1, 4, new ItemInstance(ItemRegistry.get("obsidian")));

        System.out.println(x);
    }

    public void guiUpdate() {
        gui.setLife(player.getLife());
        gui.setHunger(player.getHunger());
        gui.changeHotbarSlot(player.getHotbarSlot());
        gui.setInventoryVisibility(player.getinventoryVisible());
    }
}
