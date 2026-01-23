package com.minecraftclone.block;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

class DefaultBlock extends Block {

    private String blockTexture;

    public DefaultBlock(String blockTexture) {
        this.blockTexture = blockTexture;
    }

    @Override
    public Geometry createGeometry(AssetManager assetManager) {
        Box box = new Box(0.5f, 0.5f, 0.5f);
        Geometry geom = new Geometry("Stone", box);

        geom.setMaterial(getMaterial(geom, assetManager, "textures/blocks/" + blockTexture));
        return geom;
    }
}
