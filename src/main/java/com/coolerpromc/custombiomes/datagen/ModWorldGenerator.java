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
 * IMPORTANT: Due to unresolved issues with NeoForge's data generation system, data generation for biomes is unavailable.
 * Please run data generation using the Fabric version of the mod and copy the generated files to the NeoForge version.
 */
public class ModWorldGenerator extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.BIOME, ModBiomes::boostrap);

	public ModWorldGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of(CustomBiomes.MOD_ID));
	}
}
