package com.minecraftclone.world;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.minecraftclone.block.Block;

public class Chunk {

    public static final int SIZE = 8;

    @SuppressWarnings("unused")
    private final int chunkX, chunkY, chunkZ;

    private final Node chunkNode = new Node("Chunk");

    private final Block[][][] blocks = new Block[SIZE][SIZE][SIZE];

    public Chunk(int chunkX, int chunkY, int chunkZ) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.chunkZ = chunkZ;
    }

    public void setBlock(int x, int y, int z, Block block, Geometry geom) {
        blocks[x][y][z] = block;
        chunkNode.attachChild(geom);
    }

    public Node getChunkNode() {
        return chunkNode;
    }
}
