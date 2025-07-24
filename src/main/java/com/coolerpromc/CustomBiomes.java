package com.coolerpromc;

import com.coolerpromc.core.BiomeRegister;
import net.fabricmc.api.ModInitializer;

/**
 * Custom Biomes Mod
 * This mod initializes custom biomes for Minecraft.
 * For Forge or NeoForge, call `BiomeRegister.init()` in the main mod class constructor.
 */
public class CustomBiomes implements ModInitializer {
	public static final String MOD_ID = "custom-biomes";

	@Override
	public void onInitialize() {
		BiomeRegister.init();
	}
}