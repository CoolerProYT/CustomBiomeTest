package com.coolerpromc.custombiomes;

import com.coolerpromc.custombiomes.core.BiomeRegister;
import net.fabricmc.api.ModInitializer;

/**
 * Custom Biomes Mod
 * This mod initializes custom biomes for Minecraft.
 * For Forge or NeoForge, call `BiomeRegister.init()` in common setup.
 */
public class CustomBiomes implements ModInitializer {
	public static final String MOD_ID = "custom-biomes";

	@Override
	public void onInitialize() {
		BiomeRegister.init();
	}
}