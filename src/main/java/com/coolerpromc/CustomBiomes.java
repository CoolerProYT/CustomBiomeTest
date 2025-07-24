package com.coolerpromc;

import com.coolerpromc.core.BiomeRegister;
import net.fabricmc.api.ModInitializer;

public class CustomBiomes implements ModInitializer {
	public static final String MOD_ID = "custom-biomes";

	@Override
	public void onInitialize() {
		BiomeRegister.init();
	}
}