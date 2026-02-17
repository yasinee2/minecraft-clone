package com.minecraftclone.gui;

import com.minecraftclone.Main;
import com.minecraftclone.item.ItemInstance;
import java.io.IOException;

//Todo: Make text scalable and improve scaling
public class PlayerGUI {

    private HUD hud;
    private InventoryGUI inventoryGUI;

    public PlayerGUI(Main main) throws IOException {
        //Does: Gets the window resolution
        int windowWidth = main.getCamera().getWidth();
        int windowHeight = main.getCamera().getHeight();

        //DOES: Autoscale for HUD elements based on screen resolution
        int scale = Math.min(windowWidth / 480, windowHeight / 270);
        scale = Math.max(1, scale);

        inventoryGUI = new InventoryGUI(main, scale);
        hud = new HUD(main, scale);
    }

    public void inventoryDisplayItem(int row, int column, ItemInstance item) {
        inventoryGUI.displayItem(row, column, item);
        hud.updateHotbarDisplayItem(inventoryGUI.getInventoryList(), inventoryGUI.getInventoryTextList());
    }

    public void setLife(int life) {
        hud.setLife(life);
    }

    public void setHunger(int hunger) {
        hud.setHunger(hunger);
    }

    public void changeHotbarSelectedSlot(int slot) {
        hud.changeHotbarSelectedSlot(slot);
    }

    public void setInventoryVisibility(boolean visible) {
        inventoryGUI.setInventoryVisibility(visible);
    }
}
