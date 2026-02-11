package com.minecraftclone.cheapNBT;

public abstract class Tag {

    private String name;

    Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
