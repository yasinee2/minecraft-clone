package com.minecraftclone.player.input;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

public class KeyMapping {

    private InputManager keys;
    private ActionListener actionListener;
    private AnalogListener analogListener;

    public KeyMapping(InputManager keys, ActionListener actionListener, AnalogListener analogListener) {
        this.keys = keys;
        this.actionListener = actionListener;
        this.analogListener = analogListener;

        //TODO: add all bindings in Analog - and ActionInput

        bindKeyAction("jump", KeyInput.KEY_SPACE);
        bindKeyAction("forward", KeyInput.KEY_W);
        bindKeyAction("backward", KeyInput.KEY_S);
        bindKeyAction("left", KeyInput.KEY_A);
        bindKeyAction("right", KeyInput.KEY_D);
        bindKeyAction("toggle_Inventory", KeyInput.KEY_E);
        bindKeyAction("drop", KeyInput.KEY_Q);
        bindKeyAction("sneak", KeyInput.KEY_LSHIFT);
        bindKeyAction("pause", KeyInput.KEY_ESCAPE);

        bindKeyAction("hotbar_1", KeyInput.KEY_1);
        bindKeyAction("hotbar_2", KeyInput.KEY_2);
        bindKeyAction("hotbar_3", KeyInput.KEY_3);
        bindKeyAction("hotbar_4", KeyInput.KEY_4);
        bindKeyAction("hotbar_5", KeyInput.KEY_5);
        bindKeyAction("hotbar_6", KeyInput.KEY_6);
        bindKeyAction("hotbar_7", KeyInput.KEY_7);
        bindKeyAction("hotbar_8", KeyInput.KEY_8);
        bindKeyAction("hotbar_9", KeyInput.KEY_9);

        bindMouseAction("place_block", MouseInput.BUTTON_RIGHT);
        bindMouseAction("break_block", MouseInput.BUTTON_LEFT);

        bindMouseAxis("MouseX+", MouseInput.AXIS_X, false);
        bindMouseAxis("MouseX-", MouseInput.AXIS_X, true);
        bindMouseAxis("MouseY+", MouseInput.AXIS_Y, false);
        bindMouseAxis("MouseY-", MouseInput.AXIS_Y, true);
    }

    private void bindKeyAction(String name, int keyCode) {
        keys.addMapping(name, new KeyTrigger(keyCode));
        keys.addListener(actionListener, name);
    }

    private void bindMouseAction(String name, int buttonCode) {
        keys.addMapping(name, new MouseButtonTrigger(buttonCode)); // Use MouseButtonTrigger for mouse events
        keys.addListener(actionListener, name);
    }

    private void bindMouseAxis(String name, int axisCode, boolean negative) {
        keys.addMapping(name, new MouseAxisTrigger(axisCode, negative));
        keys.addListener(analogListener, name);
    }
}
