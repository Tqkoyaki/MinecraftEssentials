package com.takoyaki.essentials.types;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MinecraftPosition {
    private Vec3d position;
    private RegistryKey<World> worldKey;
    private float yaw, pitch;

    public MinecraftPosition(Vec3d position, RegistryKey<World> world, float yaw, float pitch) {
        this.position = position;
        this.worldKey = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public MinecraftPosition(Vec3d position, RegistryKey<World> world) {
        this(position, world, 0, 0);
    }

    public MinecraftPosition(Vec3d position) {
        this(position, World.OVERWORLD, 0, 0);
    }

    public MinecraftPosition() {
        this(new Vec3d(0, 0, 0), World.OVERWORLD, 0, 0);
    }

    public MinecraftPosition(NbtCompound nbt) {
        this(
            new Vec3d(
                nbt.getDouble("x"),
                nbt.getDouble("y"),
                nbt.getDouble("z")
            ),
            RegistryKey.of(RegistryKeys.WORLD, Identifier.tryParse(nbt.getString("dim"))),
            nbt.getFloat("yaw"),
            nbt.getFloat("pitch")
        );
    }

    public NbtCompound toNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putDouble("x", position.getX());
        nbt.putDouble("y", position.getY());
        nbt.putDouble("z", position.getZ());
        nbt.putString("dim", worldKey.getValue().toString());
        nbt.putFloat("yaw", yaw);
        nbt.putFloat("pitch", pitch);
        return nbt;
    }

    public Vec3d getPosition() {
        return position;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public double getZ() {
        return position.getZ();
    }

    public RegistryKey<World> getWorldKey() {
        return worldKey;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
