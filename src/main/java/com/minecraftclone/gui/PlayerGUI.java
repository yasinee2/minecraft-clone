package com.minecraftclone.gui;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;
import com.minecraftclone.item.ItemInstance;
import com.minecraftclone.util.ImageLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerGUI {

    private int selectedSlot = 1;
    private int scale = 4; // nur glatte zahlen
    private Picture hotbar, hotbarSelector, inventory, crosshair;
    private int windowWidth, windowHeight;
    private ImageLoader imageLoader;
    private Node guiNode;
    private boolean inventoryShown;

    List<ItemInstance> slots = new ArrayList<>(9);

    public PlayerGUI(AppSettings settings, Node guiNode, AssetManager assetManager) throws IOException {
        this.guiNode = guiNode;
        windowWidth = settings.getWidth();
        windowHeight = settings.getHeight();
        imageLoader = new ImageLoader();

        Texture2D hotbarTexture = loadTexture("src/main/resources/textures/gui/sprites/hud/hotbar.png");
        Texture2D hotbarSelectorTexture = loadTexture("src/main/resources/textures/gui/sprites/hud/hotbar_selection.png");
        Texture2D inventoryTexture = loadTexture("src/main/resources/textures/gui/container/inventory.png");
        Texture2D crosshairTexture = loadTexture("src/main/resources/textures/gui/sprites/hud/crosshair.png");

        hotbar = new Picture("hotbar");
        hotbar.setTexture(assetManager, hotbarTexture, true);
        hotbar.setWidth(182 * scale);
        hotbar.setHeight(22 * scale);
        hotbar.setPosition(windowWidth / 2 - (hotbar.getWidth() / 2), 0);
        guiNode.attachChild(hotbar);

        hotbarSelector = new Picture("hotbarSelector");
        hotbarSelector.setTexture(assetManager, hotbarSelectorTexture, true);
        hotbarSelector.setWidth(24 * scale);
        hotbarSelector.setHeight(23 * scale);
        hotbarSelector.setPosition(windowWidth / 2 - ((hotbarSelector.getWidth() / 2)), 0);
        guiNode.attachChild(hotbarSelector);

        crosshair = new Picture("crosshair");
        crosshair.setTexture(assetManager, crosshairTexture, true);
        crosshair.setWidth(15 * scale);
        crosshair.setHeight(15 * scale);
        crosshair.setPosition(windowWidth / 2 - ((crosshair.getWidth() / 2)), windowHeight / 2 - ((crosshair.getHeight() / 2)));
        guiNode.attachChild(crosshair);

        inventory = new Picture("inventory");
        inventory.setTexture(assetManager, inventoryTexture, true);
        inventory.setWidth(256 * scale);
        inventory.setHeight(256 * scale);
        inventory.setPosition(
            windowWidth / 2 - (((inventory.getWidth() - (80 * scale)) / 2)),
            windowHeight / 2 - (inventory.getHeight() - (90 * scale))
        );

        changeHotbarSlot(selectedSlot);
        inventoryShown = false;
    }

    public void changeHotbarSlot(int slot) {
        //nicht hinterfragen
        if (slot <= 9 && slot >= 1) {
            selectedSlot = slot;
            hotbarSelector.setPosition(
                windowWidth / 2 -
                    ((hotbar.getWidth() / 2) + 1 * scale) -
                    (hotbar.getWidth() - 2 * scale) / 9 +
                    (((hotbar.getWidth() - 2 * scale) / 9) * slot),
                0
            );
        }
    }

    public void hotbarSlotUp() {
        changeHotbarSlot(selectedSlot + 1);
    }

    public void hotbarSlotDown() {
        changeHotbarSlot(selectedSlot - 1);
    }

    public void toggleInventory() {
        if (!inventoryShown) {
            guiNode.attachChild(inventory);
            inventoryShown = true;
        } else {
            inventory.removeFromParent();
            inventoryShown = false;
        }
    }

    private Texture2D loadTexture(String path) throws IOException {
        Texture2D texture = new Texture2D(imageLoader.loadImage(path));
        texture.setMinFilter(Texture.MinFilter.NearestNoMipMaps);
        texture.setMagFilter(Texture.MagFilter.Nearest);
        return texture;
    }
}
