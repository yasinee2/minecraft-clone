package com.minecraftclone.world;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.minecraftclone.block.Block;
import java.util.HashMap;
import java.util.Map;

public class World {

    private final Node rootNode;
    private final AssetManager assetManager;

    private final Map<String, Chunk> chunks = new HashMap<>();

    public World(Node rootNode, AssetManager assetManager) {
        this.rootNode = rootNode;
        this.assetManager = assetManager;
    }

    public void placeBlock(int x, int y, int z, Block block) {
        int chunkX = Math.floorDiv(x, Chunk.SIZE);
        int chunkY = Math.floorDiv(y, Chunk.SIZE);
        int chunkZ = Math.floorDiv(z, Chunk.SIZE);

        int localX = x % Chunk.SIZE;
        int localY = y % Chunk.SIZE;
        int localZ = z % Chunk.SIZE;

        String key = chunkX + "," + chunkY + "," + chunkZ;

        Chunk chunk = chunks.get(key);
        if (chunk == null) {
            chunk = new Chunk(chunkX, chunkY, chunkZ);
            chunks.put(key, chunk);
            rootNode.attachChild(chunk.getChunkNode());
        }

        Geometry geom = block.createGeometry(assetManager);

        geom.setLocalTranslation(x, y, z);

        chunk.setBlock(localX, localY, localZ, block, geom);
    }
}
