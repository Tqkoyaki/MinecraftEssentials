package com.takoyaki.essentials.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.takoyaki.essentials.commands.spawn.DimensionTest;
import com.takoyaki.essentials.commands.spawn.SetWorldSpawn;
import com.takoyaki.essentials.commands.spawn.TeleportToSpawn;

import net.minecraft.server.command.ServerCommandSource;

public final class CommandProvider {
    // Registers all commands
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        DimensionTest.register(dispatcher);
        SetWorldSpawn.register(dispatcher);
        TeleportToSpawn.register(dispatcher);
    }
}
