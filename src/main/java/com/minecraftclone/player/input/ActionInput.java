package com.minecraftclone.player.input;

import com.jme3.input.controls.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ActionInput implements ActionListener {

    private final Map<Action, InputState> actions = new HashMap<>();

    //DOES: Puts every Playerinput into the Hashmap actions
    public ActionInput() {
        for (Action action : Action.values()) {
            actions.put(action, new InputState());
        }
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        Action action = Action.valueOf(name);
        actions.get(action).update(isPressed);
    }

    /**
     * Returns if the Specified Keybinding is held
     * @param action
     * @return
     */
    public boolean isHeld(Action action) {
        return actions.get(action).isHeld();
    }

    /**
     * Returns if the Specified Keybinding is tapped
     * @param action
     * @return
     */
    public boolean isTapped(Action action) {
        return actions.get(action).consumeTap();
    }
}
