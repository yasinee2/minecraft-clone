package com.minecraftclone;

import GLOOP.*;

class Camera {

    GLKamera cam;
    double yaw = 0;
    double pitch = 0;

    double sensitivity;

    Camera() {
        this.cam = new GLKamera(1280, 720);
    }

    private void update() {
        GLVektor pos = cam.gibPosition();

        double dirX = Math.cos(pitch) * Math.sin(yaw);
        double dirY = Math.sin(pitch);
        double dirZ = Math.cos(pitch) * Math.cos(yaw);

        cam.setzeBlickpunkt(pos.x + dirX, pos.y + dirY, pos.z + dirZ);
    }
}
