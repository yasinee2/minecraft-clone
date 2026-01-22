package com.minecraftclone.entity;

import GLOOP.*;
import com.minecraftclone.render.Camera;

public class Player {

    GLQuader body;
    Camera cam;

    private double posX = 100;
    private double posY = floor; //PlayerScale/2 is floor
    private double posZ = 100;

    private static int playerScale = 128;
    private static int floor = playerScale / 2;

    public Player(Camera camera) {
        body = new GLQuader(posX, posY, posZ, playerScale / 2, playerScale, playerScale / 2);
    }

    private void posUpdate(double x, double y, double z) {
        body.setzePosition(x, y, z);
        cam.setPos(x, y + playerScale / 4 + playerScale / 8, z); //Setzt Kamera immer auf das obere viertel des Spielers
    }
}

//tasten instanz variablen
//fly mode
//cam in y achse verschoben 96
