package com.coolerpromc.custombiomes.datagen;

import com.coolerpromc.custombiomes.CustomBiomes;
import com.coolerpromc.custombiomes.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * IMPORTANT: This class is used to generate the world generation data for the custom biomes.
 */
public class ModWorldGenerator extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.BIOME, ModBiomes::boostrap);

	public ModWorldGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of(CustomBiomes.MOD_ID));
	}
}
