package com.coolerpromc.custombiomes.datagen;

import com.coolerpromc.custombiomes.worldgen.biome.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

/**
 * Data generator entry point for your mod
 * For Forge/NeoForge, you would typically use a class annotated with `@Mod.EventBusSubscriber(modid = YourMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)` and subscribe to the `GatherDataEvent` or similar events.
 */
public class CustomBiomesDataGenerator implements DataGeneratorEntrypoint {
	/**
	 * This method is called when the data generator is initialized.
	 * You can add your custom data generators here.
	 * For Forge/NeoForge, you would typically use the `GatherDataEvent` to add your data generators.
	 */
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModWorldGenerator::new);
	}

	/**
	 * This method is called to build the registry set for your mod.
	 * You can add your custom registries here.
	 * For Forge/NeoForge, you would typically use the `RegistrySetBuilder` and pass it to `DatapackBuiltinEntriesProvider` in its subclass constructor.
	 */
	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::boostrap);
	}
}