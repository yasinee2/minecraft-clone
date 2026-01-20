package com.minecraftclone;

import GLOOP.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

public class FPSCamera {

    private final GLKamera cam;

    private double yaw = 0.0; // left/right
    private double pitch = 0.0; // up/down

    private int lastX = -1;
    private int lastY = -1;

    private final double sensitivity = 0.002;
    private final double maxPitch = Math.toRadians(89);

    public FPSCamera(GLKamera cam) {
        this.cam = cam;

        JFrame frame = cam.gibFrame();
        frame.addMouseMotionListener(
            new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    onMouseMove(e);
                }
            }
        );
    }

    private void onMouseMove(MouseEvent e) {
        if (lastX == -1) {
            lastX = e.getX();
            lastY = e.getY();
            return;
        }

        int dx = e.getX() - lastX;
        int dy = e.getY() - lastY;

        yaw -= dx * sensitivity;
        pitch -= dy * sensitivity;

        pitch = Math.max(-maxPitch, Math.min(maxPitch, pitch));

        updateCamera();

        lastX = e.getX();
        lastY = e.getY();
    }

    private void updateCamera() {
        GLVektor pos = cam.gibPosition();

        double dirX = Math.cos(pitch) * Math.sin(yaw);
        double dirY = Math.sin(pitch);
        double dirZ = Math.cos(pitch) * Math.cos(yaw);

        cam.setzeBlickpunkt(pos.x + dirX, pos.y + dirY, pos.z + dirZ);

        cam.setzeScheitelrichtung(0, 1, 0);
    }
}
