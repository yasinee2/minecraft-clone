package com.minecraftclone.player;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.minecraftclone.player.input.Action;
import com.minecraftclone.player.input.ActionInput;
import com.minecraftclone.render.RenderEngine;

public class PlayerCharacter {

    public static final float STEP_HEIGHT = 0.1f;
    public static final float WIDTH = 0.7f;
    public static final float HEIGHT = 1.8f;
    public static float EYE_OFFSET = HEIGHT * 0.35f;
    private float SpeedModifyer = 1f;
    private final CharacterControl playerControl;
    private final Node playerNode;

    public float speed = 0.15f;
    private final boolean debugEnabled = false;
    private final Vector3f walkDir = new Vector3f();
    private final ActionInput input;
    private final Camera cam;
    private int life = 20;
    private int hunger = 20;
    private int hotbarSlot = 1;
    private CharacterControl Player;
    private boolean inventoryVisible = false;
    private boolean IsFlying = false;
    private BitmapText FlyStateText;
    private BitmapFont guiFont;

    public PlayerCharacter(BulletAppState bulletAppState, ActionInput input, SimpleApplication app) {
        this.input = input;
        cam = app.getCamera();
        guiFont = app.getAssetManager().loadFont("font/32px-s.fnt");
        FlyStateText = new BitmapText(guiFont);
        FlyStateText.setLocalTranslation(10, cam.getHeight() - 40, 0);
        app.getGuiNode().attachChild(FlyStateText);

        bulletAppState.setDebugEnabled(debugEnabled);

        var shape = new BoxCollisionShape(new Vector3f(WIDTH / 2f, HEIGHT / 2f, WIDTH / 2f));
        var player = new CharacterControl(shape, STEP_HEIGHT);
        player.setJumpSpeed(10f);
        player.setFallSpeed(40f);
        player.setGravity(30f);
        this.Player = player;

        Node playerNode = new Node("Player");
        playerNode.addControl(player);
        bulletAppState.getPhysicsSpace().add(player);

        player.setPhysicsLocation(new Vector3f(5, 20, 2));
        this.playerControl = player;
        this.playerNode = playerNode;

        RenderEngine.giveItem("red_concrete");
        RenderEngine.giveItem("brown_concrete");
        RenderEngine.giveItem("black_concrete");
        RenderEngine.giveItem("yellow_concrete");
        RenderEngine.giveItem("cyan_concrete");
        RenderEngine.giveItem("white_concrete");
        RenderEngine.giveItem("sand");
    }

    public void tick() {
        Vector3f forward = cam.getDirection().clone();
        forward.setY(0).normalizeLocal().multLocal(speed * SpeedModifyer);
        Vector3f left = cam.getLeft().clone();
        left.setY(0).normalizeLocal().multLocal(speed * SpeedModifyer);

        walkDir.set(0, 0, 0);

        if (input.isHeld(Action.FORWARD)) walkDir.addLocal(forward);
        if (input.isHeld(Action.LEFT)) walkDir.addLocal(left);
        if (input.isHeld(Action.BACKWARD)) walkDir.addLocal(forward.negate());
        if (input.isHeld(Action.RIGHT)) walkDir.addLocal(left.negate());

        if (input.isHeld(Action.SPRINT)) {
            SpeedModifyer = 1.5f;
            if (IsFlying == true) SpeedModifyer = 3f;
        } else if (input.isHeld(Action.SNEAK)) {
            SpeedModifyer = 0.5f;
            if (IsFlying == false) EYE_OFFSET = HEIGHT * 0.35f - 0.2f;
        } else {
            SpeedModifyer = 1;
            if (IsFlying == false) EYE_OFFSET = HEIGHT * 0.35f;
        }

        if (IsFlying == true) {
            if (input.isHeld(Action.SNEAK)) {
                Player.setFallSpeed(15f);
            } else {
                Player.setFallSpeed(0f);
            }
        } else {
            Player.setFallSpeed(40f);
        }

        playerControl.setWalkDirection(walkDir);
        if (input.isHeld(Action.JUMP) && playerControl.onGround() && IsFlying == false) playerControl.jump();
        if (input.isHeld(Action.JUMP) && IsFlying == true) {
            playerControl.jump();
        }

        if (input.isTapped(Action.HOTBAR_1)) hotbarSlot = 1;
        if (input.isTapped(Action.HOTBAR_2)) hotbarSlot = 2;
        if (input.isTapped(Action.HOTBAR_3)) hotbarSlot = 3;
        if (input.isTapped(Action.HOTBAR_4)) hotbarSlot = 4;
        if (input.isTapped(Action.HOTBAR_5)) hotbarSlot = 5;
        if (input.isTapped(Action.HOTBAR_6)) hotbarSlot = 6;
        if (input.isTapped(Action.HOTBAR_7)) hotbarSlot = 7;
        if (input.isTapped(Action.HOTBAR_8)) hotbarSlot = 8;
        if (input.isTapped(Action.HOTBAR_9)) hotbarSlot = 9;

        if (input.isTapped(Action.TOGGLE_FLY)) {
            IsFlying = !IsFlying;
            FlyStateText.setText("Flying: " + IsFlying);
            FlyStateText.setLocalTranslation(10, cam.getHeight() - 40, 0);
        }

        if (input.isTapped(Action.TOGGLE_INVENTORY)) inventoryVisible = !inventoryVisible;
    }

    public Node getNode() {
        return playerNode;
    }

    public CharacterControl getPlayerControl() {
        return playerControl;
    }

    public Vector3f getPosition() {
        return playerControl.getPhysicsLocation();
    }

    public int getLife() {
        return life;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHotbarSlot() {
        return hotbarSlot;
    }

    public boolean getinventoryVisible() {
        return inventoryVisible;
    }
}
