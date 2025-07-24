package com.coolerpromc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

/**
 * This class is used to generate world generation data for the mod.
 * It extends FabricDynamicRegistryProvider to provide dynamic registry entries.
 * It could be any name, this class support DataPack entries, not limited to world generation.
 * For Forge/NeoForge, use `DatapackBuiltinEntriesProvider` instead.
 */
public class ModWorldGenerator extends FabricDynamicRegistryProvider {
	public ModWorldGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider provider, Entries entries) {
		entries.addAll(provider.lookupOrThrow(Registries.BIOME));
	}

	@Override
	public String getName() {
			return "World Gen";
		}
}
