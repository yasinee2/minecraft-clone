package com.minecraftclone.item;

public class ItemInstance {

    private String id;
    private ItemType type;
    private String name;

    private int maxStack;
    private int baseDamage;
    private int baseDurability;
    private int baseMiningEfficiency;

    private int stackSize;

    //private int durability;

    public ItemInstance(Item base) {
        this.id = base.getId();
        this.type = base.getType();
        this.name = base.getName();

        this.maxStack = base.getMaxStack();
        this.baseDamage = base.getBaseDamage();
        this.baseDurability = base.getBaseDurability();
        this.baseMiningEfficiency = base.getBaseMiningEfficiency();

        this.stackSize = maxStack; //Todo: for testing purposes only
    }

    public String getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getStackSize() {
        return stackSize;
    }
}
