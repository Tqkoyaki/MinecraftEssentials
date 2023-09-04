package com.takoyaki.essentials.commands.spawn;

import java.util.Iterator;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.minecraft.server.command.CommandManager.literal;

public final class DimensionTest {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("cdim")
            .executes(ctx -> executes(ctx)));
    }

    // Teleports the player to the world spawn.
    public static int executes(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        // Get the source
        final ServerCommandSource src = ctx.getSource();
        final Iterator<ServerWorld> worlds = src.getServer().getWorlds().iterator();

        // src.getPlayer().sendMessage(Text.of(Registries.REGISTRIES.get(new Identifier("minecraft:overworld")).toString()), false);

        // Send each world id to the player.
        while(worlds.hasNext()) {
            ServerWorld world = worlds.next();
            src.getPlayer().sendMessage(Text.of(world.getRegistryKey().getValue().toString()), false);
            src.getPlayer().sendMessage(Text.of(world.getDimensionKey().getValue().toString()), false);
        }

        src.getPlayer().sendMessage(Text.of(RegistryKey.of(RegistryKeys.WORLD, Identifier.tryParse("minecraft:the_nether")).toString()), false);

        return Command.SINGLE_SUCCESS;
    }
}
