package com.minecraftclone.block;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class StoneBlock extends Block {

    @Override
    public Geometry createGeometry(AssetManager assetManager) {
        Box box = new Box(0.5f, 0.5f, 0.5f);
        Geometry geom = new Geometry("Stone", box);

        geom.setMaterial(getMaterial(geom, assetManager, "textures/blocks/stone.png"));
        return geom;
    }
}
