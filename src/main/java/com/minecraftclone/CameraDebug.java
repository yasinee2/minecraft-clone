package com.minecraftclone;

import GLOOP.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CameraDebug {

    public CameraDebug() throws Exception {
        GLKamera cam = new GLKamera();

        // Track camera values every frame
        new Thread(() -> {
            while (true) {
                GLVektor pos = cam.gibPosition();
                GLVektor look = cam.gibBlickpunkt();
                System.out.printf(
                    "POS: (%.2f, %.2f, %.2f) | LOOK: (%.2f, %.2f, %.2f)%n",
                    pos.x,
                    pos.y,
                    pos.z,
                    look.x,
                    look.y,
                    look.z
                );
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
            }
        })
            .start();

        // Test mouse listener
        cam
            .gibFrame()
            .addMouseMotionListener(
                new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        System.out.printf("Mouse moved: (%d, %d)%n", e.getX(), e.getY());
                    }
                }
            );

        // Keep the program alive
        while (true) Thread.sleep(1000);
    }
}
