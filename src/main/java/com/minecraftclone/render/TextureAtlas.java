package com.minecraftclone.render;

public final class TextureAtlas {

    // blocks.png is a 16x16 tile atlas
    public static final int ATLAS_SIZE = 16;

    public static float tileSize() {
        return 1f / ATLAS_SIZE;
    }

    public static float u(int index) {
        return (index % ATLAS_SIZE) * tileSize();
    }

    public static float v(int index) {
        return 1f - ((index / ATLAS_SIZE) + 1) * tileSize();
    }

    private TextureAtlas() {}
}
