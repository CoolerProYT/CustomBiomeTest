package com.coolerpromc;

import com.coolerpromc.api.Region;
import com.coolerpromc.api.RegionType;
import com.coolerpromc.api.Regions;
import com.coolerpromc.api.SurfaceRuleManager;
import com.coolerpromc.config.TerraBlenderConfig;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.function.Consumer;

public class CustomBiomes implements ModInitializer {
	public static final String MOD_ID = "custom-biomes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final TerraBlenderConfig CONFIG = new TerraBlenderConfig(FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".toml"));

	@Override
	public void onInitialize() {
		Regions.register(new Region(ResourceLocation.fromNamespaceAndPath("minecraft", "overworld"), RegionType.OVERWORLD, 5) {
			@Override
			public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
				this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
					modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DESERT, ModBiomes.TEST_BIOME);
				});
			}
		});
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
	}
}