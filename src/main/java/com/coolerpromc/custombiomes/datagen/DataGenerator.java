package com.coolerpromc.custombiomes.datagen;

import com.coolerpromc.custombiomes.CustomBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

/**
 * Data generator entry point for your mod
 */
@EventBusSubscriber(modid = CustomBiomes.MOD_ID)
public class DataGenerator {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		net.minecraft.data.DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		event.addProvider(new ModWorldGenerator(packOutput, lookupProvider));
	}
}