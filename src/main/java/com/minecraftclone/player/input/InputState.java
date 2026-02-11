package com.minecraftclone.player.input;

class InputState {

    private boolean held;
    private boolean tapped;

    void update(boolean isPressed) {
        if (isPressed && !held) {
            tapped = true;
        }
        held = isPressed;
    }

    boolean isHeld() {
        return held;
    }

    boolean consumeTap() {
        boolean result = tapped;
        tapped = false;
        return result;
    }
}
