package com.coolerpromc.custombiomes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * This class is used to generate world generation data for the mod.
 * It extends FabricDynamicRegistryProvider to provide dynamic registry entries.
 * It could be any name, this class support DataPack entries, not limited to world generation.
 * For Forge/NeoForge, use `DatapackBuiltinEntriesProvider` instead.
 */
public class ModWorldGenerator extends FabricDynamicRegistryProvider {
	public ModWorldGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup provider, Entries entries) {
		entries.addAll(provider.getWrapperOrThrow(RegistryKeys.BIOME));
	}

	@Override
	public String getName() {
			return "World Gen";
		}
}
