package com.minecraftclone.block;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.texture.Texture;

public abstract class Block {

    protected int x, y, z;

    public abstract Geometry createGeometry(AssetManager assetManager);

    protected Material getMaterial(Geometry geom, AssetManager assetManager, String path) {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture tex = assetManager.loadTexture(path);
        tex.setMagFilter(Texture.MagFilter.Nearest);
        tex.setMinFilter(Texture.MinFilter.NearestNoMipMaps);
        mat.setTexture("ColorMap", tex);
        return mat;
    }

    protected Material getHDMaterial(Geometry geom, AssetManager assetManager, String path) {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap", assetManager.loadTexture(path));
        return mat;
    }
}
