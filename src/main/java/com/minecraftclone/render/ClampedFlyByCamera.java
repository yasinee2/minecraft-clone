package com.minecraftclone.render;

import com.jme3.input.FlyByCamera;
import com.jme3.renderer.Camera;

public class ClampedFlyByCamera extends FlyByCamera {

    private float pitch = 0f; // current vertical angle in radians
    private float minPitch = -(float) Math.PI / 2f + 0.01f; // look down limit
    private float maxPitch = (float) Math.PI / 2f - 0.01f; // look up limit

    public ClampedFlyByCamera(Camera cam) {
        super(cam);
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if (!isEnabled()) return;

        if (name.equals("FLYCAM_Up") || name.equals("FLYCAM_Down")) {
            float dir = name.equals("FLYCAM_Up") ? -1f : 1f;
            if (invertY) dir *= -1f;

            float delta = dir * value * getRotationSpeed();
            float newPitch = pitch + delta;

            // clamp pitch
            if (newPitch > maxPitch) {
                delta = maxPitch - pitch;
                pitch = maxPitch;
            } else if (newPitch < minPitch) {
                delta = minPitch - pitch;
                pitch = minPitch;
            } else {
                pitch = newPitch;
            }

            // apply only the allowed rotation
            rotateCamera(delta, cam.getLeft());
            return;
        }

        super.onAnalog(name, value, tpf);
    }
}
