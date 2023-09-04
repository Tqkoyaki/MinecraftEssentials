package com.takoyaki.essentials.commands.spawn;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.takoyaki.essentials.states.WorldManager;
import com.takoyaki.essentials.types.MinecraftPosition;
import com.takoyaki.essentials.utils.CommandPermission;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.Vec3d;

import static net.minecraft.server.command.CommandManager.literal;

public class SetWorldSpawn {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("setspawn")
            .requires(src -> src.hasPermissionLevel(CommandPermission.ADMIN.getValue()))
            .executes(ctx -> executes(ctx)));
    }

    // Sets the world spawn to the player's current position.
    public static int executes(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        // Get the source
        final ServerCommandSource src = ctx.getSource();
        final Vec3d playerPos = src.getPlayer().getPos();
        final float playerYaw = src.getPlayer().getYaw();
        final float playerPitch = src.getPlayer().getPitch();
        final WorldManager worldManager = WorldManager.getWorldManager(src.getServer());

        // Set the spawn
        worldManager.setSpawn(
            new MinecraftPosition(
                playerPos, 
                src.getWorld().getRegistryKey(),
                playerYaw,
                playerPitch));

        return Command.SINGLE_SUCCESS;
    }
}
