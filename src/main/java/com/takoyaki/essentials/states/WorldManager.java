package com.takoyaki.essentials.states;

import com.takoyaki.essentials.FabricMod;
import com.takoyaki.essentials.types.MinecraftPosition;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

public class WorldManager extends PersistentState {
    private MinecraftPosition spawn = new MinecraftPosition();
    private Boolean isSpawnSet = false;

    // Uses nbt to create a new WorldManager
    private static WorldManager createFromNbt(NbtCompound nbt) {
        WorldManager worldManager = new WorldManager();

        // Get spawn using nbt
        worldManager.spawn = new MinecraftPosition(nbt.getCompound("spawn"));
        worldManager.isSpawnSet = nbt.getBoolean("isSpawnSet");

        return worldManager;
    }

    // Puts spawn into nbt form
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        // Write spawn to nbt
        nbt.put("spawn", spawn.toNbt());
        nbt.putBoolean("isSpawnSet", isSpawnSet);

        return nbt;
    }

    // Gets the world manager from the server
    public static WorldManager getWorldManager(MinecraftServer server) {
        PersistentStateManager manager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        WorldManager worldManager = manager.getOrCreate(
            WorldManager::createFromNbt,
            WorldManager::new,
            FabricMod.MOD_ID
        );

        return worldManager;
    }

    public MinecraftPosition getSpawn() {
        return spawn;
    }

    public void setSpawn(MinecraftPosition spawn) {
        this.spawn = spawn;
        this.isSpawnSet = true;
        this.markDirty();
    }

    public Boolean isSpawnSet() {
        return isSpawnSet;
    }
}
