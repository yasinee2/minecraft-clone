package com.minecraftclone.entitiy;

import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.minecraftclone.world.ActionInput;

public class EntityManager {

    private PlayerCharacter playerCharacter;

    public EntityManager(BulletAppState bulletAppState, Node rootNode, ActionInput actionInput, Camera cam) {
        playerCharacter = new PlayerCharacter(bulletAppState, actionInput, cam);
        rootNode.attachChild(playerCharacter.getNode());
    }

    public void tick() {
        playerCharacter.tick();
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }
}
