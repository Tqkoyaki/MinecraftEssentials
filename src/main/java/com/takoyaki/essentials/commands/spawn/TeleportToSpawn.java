package com.takoyaki.essentials.commands.spawn;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.takoyaki.essentials.states.WorldManager;
import com.takoyaki.essentials.types.MinecraftPosition;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.server.command.CommandManager.literal;

public class TeleportToSpawn {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("spawn")
            .executes(ctx -> executes(ctx)));
    }

    // Teleports the player to the world spawn.
    public static int executes(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        // Get the source
        final ServerCommandSource src = ctx.getSource();
        final WorldManager worldManager = WorldManager.getWorldManager(src.getServer());
        
        if(worldManager.isSpawnSet()) {
            MinecraftPosition spawn = worldManager.getSpawn();

            // Teleport the player to the spawn
            src.getPlayer().teleport(
                src.getServer().getWorld(spawn.getWorldKey()),
                spawn.getX(),
                spawn.getY(),
                spawn.getZ(),
                spawn.getYaw(),
                spawn.getPitch()
            );

            return Command.SINGLE_SUCCESS;
        }

        // Teleports the user to minecraft default spawn
        final BlockPos spawnPos = src.getWorld().getSpawnPos();
        src.getPlayer().teleport(
            src.getServer().getOverworld(),
            spawnPos.getX(),
            spawnPos.getY(),
            spawnPos.getZ(),
            0,
            0
        );

        return Command.SINGLE_SUCCESS;
    }
}
