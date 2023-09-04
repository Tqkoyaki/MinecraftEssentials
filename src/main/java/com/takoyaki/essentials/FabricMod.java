package com.takoyaki.essentials;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.takoyaki.essentials.commands.CommandProvider;

public class FabricMod implements ModInitializer {
	public static final String MOD_ID = "essentials";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			CommandProvider.register(dispatcher);
		});
	}
}