package com.minecraftclone.entity;

import GLOOP.*;
import com.minecraftclone.render.Camera;

public class Player {

    GLQuader body;
    Camera cam;

    private double posX = 0;
    private double posY = 0;
    private double posZ = 0;

    public Player(Camera camera) {
        body = new GLQuader(64, 128, 64, posX, posY, posZ);
    }

    private void posUpdate(double x, double y, double z) {
        body.setzePosition(x, y, z);
        cam.setPos(x, y, z);
    }
}

//tasten instanz variablen
//fly mode
//cam in y achse verschoben 96
