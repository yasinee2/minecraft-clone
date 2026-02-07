package com.minecraftclone.world;

import com.jme3.bullet.control.CharacterControl;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.minecraftclone.block.Block;
import com.minecraftclone.entitiy.PlayerCharacter;
import com.minecraftclone.input.ActionInput;

public final class BlockInteractionSystem {

    private static final float DEFAULT_REACH = 5.0f;
    private static final float EPSILON = 0.001f;

    private final World world;
    private final Camera camera;
    private final ActionInput input;

    private Block selectedBlock;
    private float reachDistance = DEFAULT_REACH;
    private boolean allowBreaking = true;
    private boolean allowPlacing = true;

    public BlockInteractionSystem(World world, Camera camera, ActionInput input) {
        this.world = world;
        this.camera = camera;
        this.input = input;
    }

    public void update() {
        if (input.breakBlock() && allowBreaking) tryBreak();
        if (input.placeBlock() && allowPlacing) tryPlace();
    }

    public void setSelectedBlock(Block block) {
        this.selectedBlock = block;
    }

    public void setReachDistance(float reach) {
        this.reachDistance = reach;
    }

    public void setAllowBreaking(boolean value) {
        this.allowBreaking = value;
    }

    public void setAllowPlacing(boolean value) {
        this.allowPlacing = value;
    }

    // =========================
    // BLOCK INTERACTION
    // =========================

    private void tryBreak() {
        System.out.println("Attempting to break block...");
        long startTime = System.nanoTime();

        RaycastResult hit = raycastBlocks();
        if (hit == null) {
            System.out.println("No block in reach to break.");
            return;
        }

        if (!world.isBlockLoaded(hit.x, hit.y, hit.z)) {
            System.out.println("Block is not loaded.");
            return;
        }

        Block block = world.getBlock(hit.x, hit.y, hit.z);
        if (block == null) return;
        if (!block.isBreakable()) return;

        world.setBlock(hit.x, hit.y, hit.z, null);

        long endTime = System.nanoTime();
        System.out.println("Block broken successfully in " + ((endTime - startTime) / 1_000_000.0) + " ms");
    }

    private void tryPlace() {
        if (selectedBlock == null) return;

        RaycastResult hit = raycastBlocks();
        if (hit == null) return;

        int px = hit.x + hit.nx;
        int py = hit.y + hit.ny;
        int pz = hit.z + hit.nz;

        if (!world.isBlockLoaded(px, py, pz)) return;
        if (world.getBlock(px, py, pz) != null) return;
        if (!selectedBlock.canBePlacedAt(world, px, py, pz)) return;
        if (collidesWithPlayer(px, py, pz)) return;

        world.setBlock(px, py, pz, selectedBlock);
    }

    // =========================
    // FAST AABB BLOCK RAYCAST
    // =========================

    private RaycastResult raycastBlocks() {
        Vector3f origin = camera.getLocation();
        Vector3f dir = camera.getDirection().normalize();

        float step = 0.1f; // step size along ray
        float maxDistance = reachDistance;

        for (float t = 0; t <= maxDistance; t += step) {
            Vector3f pos = origin.add(dir.mult(t));

            int bx = (int) Math.floor(pos.x);
            int by = (int) Math.floor(pos.y);
            int bz = (int) Math.floor(pos.z);

            if (!world.isBlockLoaded(bx, by, bz)) continue;

            Block b = world.getBlock(bx, by, bz);
            if (b != null) {
                // Determine which face was hit for placement
                int nx = 0,
                    ny = 0,
                    nz = 0;

                float fx = pos.x - bx;
                float fy = pos.y - by;
                float fz = pos.z - bz;

                if (fx < EPSILON) nx = -1;
                else if (fx > 1 - EPSILON) nx = 1;
                if (fy < EPSILON) ny = -1;
                else if (fy > 1 - EPSILON) ny = 1;
                if (fz < EPSILON) nz = -1;
                else if (fz > 1 - EPSILON) nz = 1;

                return new RaycastResult(bx, by, bz, nx, ny, nz);
            }
        }
        return null;
    }

    private boolean collidesWithPlayer(int x, int y, int z) {
        CharacterControl player = world.getPlayerCharacter().getPlayerControl();

        Vector3f blockMin = new Vector3f(x, y, z);
        Vector3f blockMax = blockMin.add(1, 1, 1);

        Vector3f playerPos = player.getPhysicsLocation();
        float radius = PlayerCharacter.RADIUS;
        float height = PlayerCharacter.HEIGHT;
        Vector3f playerMin = playerPos.add(-radius, -0.5f * height, -radius);
        Vector3f playerMax = playerPos.add(radius, 0.5f * height, radius);

        boolean overlapX = blockMin.x < playerMax.x && blockMax.x > playerMin.x;
        boolean overlapY = blockMin.y < playerMax.y && blockMax.y > playerMin.y;
        boolean overlapZ = blockMin.z < playerMax.z && blockMax.z > playerMin.z;

        return overlapX && overlapY && overlapZ;
    }

    // =========================
    // INTERNAL STRUCT
    // =========================

    private static final class RaycastResult {

        final int x, y, z;
        final int nx, ny, nz;

        RaycastResult(int x, int y, int z, int nx, int ny, int nz) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.nx = nx;
            this.ny = ny;
            this.nz = nz;
        }
    }
}
